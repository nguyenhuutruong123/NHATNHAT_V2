<%@page import="geso.dms.distributor.beans.denghidathangnpp.IDathangnpp"%>
<%@page import="geso.dms.distributor.beans.denghidathangnpp.IDanhsachsanpham"%>
<%@page import="geso.dms.distributor.beans.denghidathangnpp.IDenghidathangnpp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.denghidathang.IDenghidathang" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page import="java.util.*;"%>  
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IDathangnpp obj = (IDathangnpp)session.getAttribute("obj"); %>
<% ResultSet ncc = (ResultSet) obj.getNcc(); %>
<% ResultSet danhsach =(ResultSet)obj.getdanhsach(); %>

<% ResultSet dvkd = (ResultSet)obj.getDvkdIds(); %>
<% //String dvkdId = (String)obj.getDvkdId(); %>
<% String soluong[] = obj.getSoluong();%>
<% ResultSet kbh = (ResultSet)obj.getKbhIds(); %>
<% ResultSet gsbh = (ResultSet)obj.getGsbh(); %>
<% //String kbhId = (String)obj.getKbhId();
 //  List<IDanhsachsanpham> danhsachsanpham = obj.getDanhsachsanpham(); 
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<SCRIPT src="../js/md5.js" type="text/javascript" language="javascript">
</SCRIPT>
<SCRIPT src="../js/scripts.js" type="text/javascript"
    language="javascript">
</SCRIPT>
<SCRIPT src="../js/commun.js" type="text/javascript"
    language="javascript">
</SCRIPT>
<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript">
	jQuery(function($){ 
		 $('.addspeech').speechbubble();})
</script>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/ajax-dynamic-list.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<!-- 
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script> 
<META http-equiv="Content-Style-Type" content="text/css"> 
-->
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});            
        }); 		
		
</script>

<SCRIPT type="text/javascript">

function submitform()
{   
   document.forms["dndhFormnpp"].submit();
}
function thuchien()
{
	document.forms["dndhFormnpp"].action.value ="thuchien";
	document.forms["dndhFormnpp"].submit();
}
function saveform1()
{
	document.forms["dndhFormnpp"].action.value ="dathang";
	document.forms["dndhFormnpp"].submit();

}
function chotform()
{
	document.forms["dndhFormnpp"].action.value ="chot";
	document.forms["dndhFormnpp"].submit();

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
function tinh()
{
	var soluong = document.getElementsByName("soluong");
	var dongia = document.getElementsByName("dongia");
	var tongtien = document.getElementsByName("tongtien");
	//var khoicp = document.getElementsByName("khoicp");
	 var tongchuavat = document.getElementById("tongchuavat");
     var vat = document.getElementById("vat");
     var tongcovat = document.getElementById("tongcovat");
  //    tongtien.value 
     // vat.value =
     // tongcovat.value =
    var tong = 0;  
	var i = 0;
	for(i =0;i< soluong.length;i++)
	{
		
     if(parseInt(soluong[i].value) >0)		
     { 
    	    tongtien[i].value = parseInt(soluong[i].value)*parseInt(dongia[i].value);
     }
     else
    	 tongtien[i].value=0;
     
     tong = tong + parseInt(tongtien[i].value);
  	}
	if(tongchuavat != null)
		tongchuavat.value =  tong.toString() ; 
	if(vat != null)
		
	 vat.value = '10';
	
    if(tongcovat != null)
	tongcovat.value =  tong + tong*0.1 ; 
	setTimeout(tinh,20);	
}
tinh();
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0" >
<form name="dndhFormnpp" method="post" action="../../DathangnppSvl">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<INPUT name="nppId" type="hidden" value='<%=obj.getNppId() %>' size="30">
<input type="hidden" name="action" value='next'>
<input type="hidden" name="id" value='<%= obj.getId() %>'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"  bgcolor="#FFFFFF">
    <TR>
        <TD colspan="4" align='left' valign='top'> <!--begin body Dossier-->

            <TABLE width="100%" cellspacing="0" cellpadding="0">
                <TR>
                    <TD align="left" >
	                	<TABLE width="100%" cellspacing="1" cellpadding="0">
    	                	<TR>
        	                	<TD align="left">
            	                	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                	                	<tr height="22">
                    	                	<TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? kho &gt; &nbsp;Mua h??ng &gt; &nbsp; ????? ngh??? &gt; &nbsp; t???o m???i
                                   
                        	                <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%= obj.getNppTen() %> &nbsp;</TD>
                            	        </tr>
                                	  </table>
                              	</TD>
                         	</TR> 
                     	</TABLE>
                     	
            <TABLE width="100%" border="0" cellpadding="3" cellspacing="0">
                <TR ><TD >
                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR class = "tbdarkrow">
                            <TD width="30" align="left"><A href="../../DenghidathangnppSvl?userId=<%=userId%>"  ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
                            <TD width="2" align="left" >&nbsp;</TD>
                            <% if(obj.getTrangthai().trim().equals("0")){ %>
                             <TD width="30" align="left" ><A href="javascript:saveform1()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
                             <TD align="left">&nbsp;</TD>
                               <%} %>
                              <% if(obj.getTrangthai().trim().equals("3")){ %>
                                <TD width="30" align="left" ><A href="javascript:saveform1()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
                             
                              <TD width="30" align="left"><A href="javascript: chotform()" ><img src="../images/Chot.png" alt="Chot don hang" title="Chot don hang" width="30" height="30" longdesc="Chot don hang" border=1 style="border-style:outset"></A></TD>
                             <TD align="left">&nbsp;</TD>
                            <TD align="left">&nbsp;</TD>
                              <%}%>
                            
                        </TR>
                    </TABLE>
                </TD></TR>
            </TABLE>

            <TABLE width = 100% cellpadding="0" cellspacing="0">
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
	    				<textarea name="err" style="width:100%" readonly="readonly" rows="1"  ><%=obj.getMessage()%></textarea>
						<% obj.setMessage(""); %>
						</FIELDSET>
				   </TD>
				</tr>			
		 	
			<TR>
                
                <TR>
                    <TD >
                        <TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
                            <TR>
                                <TD  align="left">
                                    <FIELDSET>

                                    <LEGEND class="legendtitle">&nbsp;T???o ????n ?????t h??ng &nbsp;</LEGEND>
                                                <TABLE cellpadding = 4 cellspacing = 0 width = "100%" border = 0>
                                                    <TR>
                                                        <TD width="20%" class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %>  </TD>
                                                        <TD width="70%" class="plainlabel"><%= obj.getNppTen() %></TD>                                                   
                                                    </TR>

                            
                                                    <TR >
                                                        <TD class="plainlabel">Ng??y ????? ngh??? </TD>
                                                        <TD class="plainlabel">
        
                                                            <table border=0 cellpadding = 0 cellspacing = 0>
                                                                <tr>
                                                                    <td class="plainlabel"><input class="days" type="text" name="ngaydn" size="11" value="<%=obj.getNgaydn() %>" ></td>
                                                                                                                                        
                                                                </tr>
                                                            </table>                                                
                                                        </TD>
                                                    </TR>
                                                    <TR class="tblightrow">
                                                        <TD  class="plainlabel">Nh?? cung c???p </TD>
                                                        <TD  class="plainlabel"> 
                                                          <select name="nccId" onChange="submitform();">
                                                          	
                                                           <% try{ %>
                                                       			    	
                                                         	<%   if(ncc != null){
                                                         			while(ncc.next()){      
                                                            	      if (ncc.getString("nccId").equals(obj.getNccId())){ %>   
                                                                	       <option value='<%=ncc.getString("nccId")%>' selected><%=ncc.getString("nccTen")%></option>
                                                                	       
                                                                   <% }else{ %>
                                                                   
                                                                	<%} 
                                                                	} 
                                                                  }%>
                                                            <% }catch(java.sql.SQLException e){   %>
                                                                    <%  }  %>
                                                          </select>                                             
                                                        </TD>

                                                    </TR>
													<TR>
														<TD class="plainlabel">????n v??? kinh doanh </TD>
								    					<TD class="plainlabel">
								    						<SELECT  name="dvkdId" onChange="submitform();">
								  								<OPTION value""></OPTION>
														  	 <% if(!(dvkd ==null)){
														  	 		try{ while(dvkd.next()){ 
															    		if(dvkd.getString("dvkdId").equals(obj.getDvkdId())){ %>
								      										<option value='<%=dvkd.getString("dvkdId") %>' selected><%=dvkd.getString("dvkd") %></option>
								      							  	  <%}else{ %>
								     							  <%}}}catch(java.sql.SQLException e){} 
								     							 }%>	
                                  							</select>
                                  						</TD>
													</TR>
													<TR>
														<TD class="plainlabel">K??nh b??n h??ng </TD>
								    					<TD class="plainlabel">
								    						<SELECT  name="kbhId"  readonly>
								  								<OPTION value""></OPTION>
														  	 <% if(kbh != null) 
														  	  try{ while(kbh.next()){ 
															    	if(kbh.getString("kbhId").equals(obj.getKbhId())){ %>
								      									<option value='<%=kbh.getString("kbhId") %>' selected><%=kbh.getString("kbh") %></option>
								      							  <%}else{ %>
								     							  <%}}}catch(java.sql.SQLException e){} %>	
                                  							</select>
                                  						</TD>
													</TR>
                                                	<TR>
														<TD class="plainlabel">Gi??m s??t b??n h??ng </TD>
								    					<TD class="plainlabel">
								    						<SELECT  name="gsbhId" >
								  						  	 <% if(gsbh != null) 
														  	  try{ while(gsbh.next()){ 
															    	if(gsbh.getString("pk_seq").equals(obj.getGsbhId())){ %>
								      									<option value='<%=gsbh.getString("pk_seq") %>' selected><%=gsbh.getString("ten") %></option>
								      							  <%}else{ %>
								      							  <option value='<%=gsbh.getString("pk_seq") %>'><%=gsbh.getString("ten") %></option>
								     							  <%}}}catch(java.sql.SQLException e){} %>	
                                  							</select>
                                  						</TD>
													</TR>
                                                  
                                                	<TR>
														<TD class="plainlabel">T???ng s??? ti???n(ch??a Vat)</TD>
								    					<TD class="plainlabel">
								    					 <input type ="text" name ="tongchuavat" id ="tongchuavat" value ="0" readonly>
								    					</TD>
													</TR>
                                                   <TR>
														<TD class="plainlabel">VAT(10%)</TD>
								    					<TD class="plainlabel">
								    					 <input type ="text" name ="vat" id ="vat" value ="0"  readonly>
								    					</TD>
													</TR>
													<TR>
														<TD class="plainlabel">T???ng s??? c??(ch??a Vat)</TD>
								    					<TD class="plainlabel">
								    					 <input type ="text" name ="tongcovat" id ="tongcovat" value ="0" readonly >
								    					</TD>
													</TR>
													<TR>
														
													
														<TD class="plainlabel" colspan=2>
														<!--	<INPUT type="submit" name="next" value="Tiep tuc">  --></TD>
													</TR>
													<tr>
                                                   <TABLE class="tabledetail"  border="1"  width = 100% cellpadding="0"  cellspacing="1" >
	                                                    <TR class="tbheader" >
	                                                        <TH width="7%">M?? s???n ph???m </TH>
	                                                        <TH width="16%">T??n s???n ph???m</TH>
	                                                        <TH width="7%">????? ngh??? ?????t(T) </TH>
	                                                       <% if(obj.getTrangthai().trim().equals("1")){ %>
	                                                        <TH width="7%">T???n kho </TH>
	                                                        <%} %>
	                                                        <TH width="7%">SL ?????t(T) </TH>
	                                                        <TH width="7%">????n v??? </TH>
	                                                        <TH width="7%">????n gi??(T) </TH>
	                                                        <TH width="7%">Th??nh ti???n </TH>
	                                                        
	                                                    </TR>
	                                                    
	                                                   <% 
	                                                   int i =0;
	                                                        if(danhsach != null)
	                                                   while(danhsach.next()) {
	                                                	   float ton = Float.parseFloat(danhsach.getString("soluong"));
	                                                	   float soluong1 = Float.parseFloat(soluong[i]);
	                                                      
	                                                	   if(i % 2 == 0){
	                                                    %>  
	                                                    	<TR class='tblightrow'>
	                                                      	<%}else{ %>
	                                                    	<TR class='tblightrow'> 
	                                                    	<%}%>
	                                                    	  <TD >
	                                                    	  <%=danhsach.getString("ma") %>
	                                                    	  <input type ="hidden"name ="masp" id ="masp"  value ='<%=danhsach.getString("pk_seq") %>'readonly> </TD>
	                                                        <TD ><%=danhsach.getString("ten") %></TD>
	                                                        <TD ><input style="border:0" type ="text"name ="denghi" id ="denghi" size ="10%" value ='<%=danhsach.getString("denghidat") %>'readonly> </TD>
	                                                        <% if(obj.getTrangthai().trim().equals("1")){ %>
	                                                          <TD ><input type ="text"name ="tonkho" id ="tonkho" size ="10%" value ='<%= danhsach.getString("soluong") %>'readonly> </TD>
	                                                          <%} else {%>
	                                                           <input type ="hidden"name ="tonkho" id ="tonkho" size ="10%" value ='<%= danhsach.getString("soluong") %>'readonly> 
	                                                          <%} %>
	                                                         <%if(soluong1 > ton) {%>
	                                                         <TD align = "center">
	                                                         <div class="addspeech" title="S???n ph???m n??y c??n t???i ??a <%=danhsach.getString("soluong")%> s???n ph???m,vui l??ng ch???n l???i s??? l?????ng ">
							                                 <input type ="text"name ="soluong" id ="soluong" size ="10%" value ='<%= soluong[i]%>' style=" background:green" onkeypress="return keypress(event);">
							                                 </div> 
	                                                         </TD>
	                                                        <%}else { if(!soluong[i].equals("0")){ %>
	                                                        
	                                                        <TD ><input type ="text"name ="soluong" id ="soluong" size ="10%" value ='<%=soluong[i]%>' onkeypress="return keypress(event);"> </TD>
	                                                        <%}
	                                                        else { %>
	                                                        	<TD ><input type ="text"name ="soluong" id ="soluong" size ="10%" value ='<%=danhsach.getString("denghidat")%>' onkeypress="return keypress(event);"> </TD>
	                                                       <%}} %>
	                                                       <TD ><input style="border:0" type ="text"name ="donvi" id ="donvi" size ="10%" value ='<%=danhsach.getString("donvi") %>'readonly> </TD>
	                                                      
	                                                        <TD ><input style="border:0" type ="text"name ="dongia" id ="dongia" size ="10%" value ='<%=danhsach.getString("dongia") %>'readonly> </TD>
	                                                         <TD ><input style="border:0" type ="text"name ="tongtien" id ="tongtien" size ="10%" value ='' readonly> </TD>
	                                                     
	                                                    </TR>
	                                                  <% i++;}%>
	                                               </TABLE>
                                                    </tr>
                                                </TABLE>
                                               </FIELDSET>
                                            </TD>
                                    </TR>
											<SCRIPT type="text/javascript">
											tinh();
										      
											</SCRIPT>
                                   
                                </TABLE>
                                
                        </TD>
                    </TR>
                     
            </TABLE>
    </td>

  </tr>

</table>


</TABLE>
</form>

</body>
</HTML>

<% if(!(ncc == null))  ncc.close(); %>
<% if(!(dvkd == null)) dvkd.close(); %>
<% if(!(kbh == null)) kbh.close(); %>
<% if(obj != null){
	obj.DBclose();
	obj = null;
}%>
<%}%>