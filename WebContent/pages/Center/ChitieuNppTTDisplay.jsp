<%@page import="geso.dms.center.beans.chitieunpp.IChiTieuNhaPP"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.beans.chitieunpp.imp.ChiTieuDDKD"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
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
	
 	IChiTieuNhaPP objbean=(IChiTieuNhaPP)session.getAttribute("obj");
	List<ChiTieuDDKD> listddkd= (List<ChiTieuDDKD>)objbean.getListChiTieuDDKD();
	ResultSet rs_dvkd=objbean.getrsdvkd();
	ResultSet rs_ctddkd=objbean.getChitieuDDKd();
	ResultSet rs_kenh=objbean.getRsKenh();
	String []nhomsp=objbean.getNhomSp();
	String []nhomspid=objbean.getNhomSpId();
	NumberFormat formatter = new DecimalFormat("#,###,###"); 
	
	
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	document.forms["ChiTieuTTForm"].tongchitieu.value="0";
	document.forms["ChiTieuTTForm"].action.value="loadchitieu";
    document.forms["ChiTieuTTForm"].submit();
}

 
</SCRIPT>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/ajax.js"></script>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="ChiTieuTTForm" method="post" action="../../ChiTieuNhaPPTTNewSvl" >
<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="nkmId" value="">
<input type="hidden" name="action" value="0">
<input type="hidden" name="id" value="<%=objbean.getID() %>">
<input type="hidden" name="tenform" value="update">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
		  					 <TD align="left" colspan="2" class="tbnavigation">&nbsp; Quản lý chỉ tiêu > khai báo >Chia chỉ tiêu DDKD> Hiển thị </TD>  
                            <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%>&nbsp;  </TD></tr>
          						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../ChiTieuNppTTSvl?userId=<%=userId%>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
						    <TD width="30" align="left" >
						     </TD>
							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	 		

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Thông tin chỉ tiêu </LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
							<TR>
								<TD width="20%" class="plainlabel" >Tháng <FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel">
								<input name="thang" type="text" value="<%=objbean.getThang()%>">
									
								</TD>
							</TR>
							<TR>
							  	<TD class="plainlabel">Năm</TD>
						  	  	<TD class="plainlabel">
									<input name="nam" type="text" value="<%=objbean.getNam()%>"> 
									
						  	  	</TD>
						  </TR>
						    <tr class="plainlabel">
                             <td> Đơn vị kinh doanh </td>
                             <td>
                             <select name=selectdvkd  >
                               
                             <%
                             if (rs_dvkd!=null){
                            	 while (rs_dvkd.next()){                      				                       				
                       				 if(rs_dvkd.getString("pk_seq").equals(objbean.getDVKDId())){ %>
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
                               <tr class="plainlabel">
                             <td>Kênh bán hàng </td>
                             <td>
								 <select name=kenhid >
                             
            
                             <%
                          
                             if (rs_kenh!=null){
                            	 while (rs_kenh.next()){
                       				%>
                       				<% if(rs_kenh.getString("pk_seq").equals(objbean.getKenhId())){ %>
                       				<option value ="<%=rs_kenh.getString("pk_seq") %>" selected="selected"> <%=rs_kenh.getString("ten") %></option>
                       				<%}else{ %>
                       				<option value ="<%=rs_kenh.getString("pk_seq") %>"> <%=rs_kenh.getString("ten") %></option>
                       				<%} %>
                       				<%     		
                            	 }
                             }
                             %>
                             </select>                            
                             </td>
                             </tr>
				  		   <TR>
						  	  	<TD class="plainlabel">Số chỉ tiêu</TD>
						  	  <TD class="plainlabel">
						  	  <input onkeypress="return keypress(event);" type="text" readonly="readonly" name="tongchitieu" value="<%= formatter.format(objbean.getChitieu())%>" > 
						  	  	</TD>
						  	</TR>
						  	<TR>
						  	  	<TD class="plainlabel">Số ngày làm việc </TD>
						  	  	<TD class="plainlabel">
						  		<input type="text" name="songaylamviec" readonly="readonly" value="<%=objbean.getSoNgayLamViec()%>">  
						  							  	  	</TD>
						  	</TR>
						  	<TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Ngày kết thúc",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
						  		<input type="text" name="ngayketthuc" readonly="readonly" value="<%=objbean.getNgayKetThuc()%>">  
						  							  	  	</TD>
						  	</TR>		 
						      <TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
						  	  	<textarea name="diengiai"  style="width: 50%"  rows="2"><%=objbean.getDienGiai()%></textarea>	
						  	  	</TD>
						  	</TR>
						</TABLE>
						<TABLE width="100%" border="0" cellspacing="1" cellpadding="0" >							
								<TR class="tbheader">
									<TH width="5%">STT </TH>
									<TH width="20%">Tên nhân viên </TH>
									<TH width="10%">Chỉ tiêu </TH>
								
										<%
									if(nhomsp!=null){
										for(int i=0;i<nhomsp.length;i++){
											if(nhomsp[i]!=null){
											%>
											<TH width="10%"><%=nhomsp[i] %></TH>
											<%
										}}
										
									}
									%>
								</TR>
							<%
																int m=0;
																				if(rs_ctddkd!=null)
																				{
																					while (rs_ctddkd.next()){
																						
															%>
									<tr>
									<TD > <input type =text name="sott" readonly="readonly" style="width :100%" value=<%=m+1 %> >  <input type ="hidden" name="maddkd" readonly="readonly" style="width :100%" value=<%=rs_ctddkd.getString("ddkd_fk")%>>  </TD>
									<TD ><input type =text name="tenddkd" style="width :100%" readonly="readonly" value="<%=rs_ctddkd.getString("ten")%>"> </TD>
									<TD> <input type =text name="chitieu" readonly="readonly" style="width :100%" value="<%= rs_ctddkd.getString("chitieu")%>" onkeypress="return keypress(event);"> </TD>
								 							   
									 <%
									if(nhomspid!=null){
										for(int i=0;i<nhomspid.length;i++){
											if(nhomspid[i]!=null){
											%>
											<TH width="10%"> <input readonly="readonly" name="<%=rs_ctddkd.getString("ddkd_fk").trim()+nhomspid[i]%>" style="width :100%"  value= <%=rs_ctddkd.getDouble("CT"+nhomspid[i])%> type="text" ></TH>
											<%
										}}
										
									}
									%>
  </tr>
									<%
									m++;
								}
							}
							%>
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
		if(rs_dvkd != null)
			rs_dvkd.close();
		if(rs_kenh != null)
			rs_kenh.close();
		if(objbean != null){
			objbean.DBclose();
			objbean = null;
		}
	
	}
	catch (Exception e) {}

%>
<%}%>