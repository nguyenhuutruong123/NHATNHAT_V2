<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.distributor.beans.chitieunpp.imp.ChiTieuDDKD"%>
<%@page import="geso.dms.distributor.beans.chitieunpp.imp.ChiTieuNhaPP"%>
<%@page import="geso.dms.center.beans.chitieu.imp.ChiTieuNPP"%>
<%@page import="java.util.Calendar"%>
<%@page import="geso.dms.center.beans.chitieuttchovung.imp.ChiTieuTTKhuVuc"%>
<%@page import="geso.dms.center.beans.chitieuttchovung.imp.ChiTieuTTChoVung"%>
<%@page import="java.util.Date"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page import="geso.dms.center.beans.chitieu.imp.ChiTieu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhomkhuyenmai.INhomkhuyenmai" %>
<%@ page  import = "geso.dms.center.beans.nhomkhuyenmai.imp.Nhomkhuyenmai" %>
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
	
 
 	ChiTieuNhaPP objbean=(ChiTieuNhaPP)session.getAttribute("obj");
 
	List<ChiTieuDDKD> listddkd= (List<ChiTieuDDKD>)objbean.getListChiTieuDDKD();
	 
	String tenhapp= (String)session.getAttribute("tennhapp");
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
function keypress(e) //H??m d??ng d? ngan ngu?i d??ng nh?p c??c k?? t? kh??c k?? t? s? v??o TextBox
{    
	var keypressed = null;
	if (window.event)
		keypressed = window.event.keyCode; //IE
	else
		keypressed = e.which; //NON-IE, Standard
	
	if (keypressed < 48 || keypressed > 57)
	{ 
		if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
		{//Ph??m Delete v?? Ph??m Back
			return;
		}
		return false;
	}
}
function save(){
	  document.forms["ChiTieuTTForm"].dataerror.value=" ";
  var thang=document.forms["ChiTieuTTForm"].thang.value;
  var nam=document.forms["ChiTieuTTForm"].nam.value;
  var tongchitieu=document.forms["ChiTieuTTForm"].tongchitieu.value;
  var ngayketthuc=document.forms["ChiTieuTTForm"].ngayketthuc.value;
  if(nam==0){
	  document.forms["ChiTieuTTForm"].dataerror.value="Ch???n n??m c???n ?????t ch??? ti??u ";
	  return;
  }
  var dvkd_fk=document.forms["ChiTieuTTForm"].selectdvkd.value;	
	 if(dvkd_fk==""){
		document.forms["ChiTieuTTForm"].dataerror.value="Ch???n ????n v??? kinh doanh ";
		  return;
	 }
  if(thang==0){
	  document.forms["ChiTieuTTForm"].dataerror.value="Ch???n th??ng c???n ?????t ch??? ti??u ";
	  return;
	  }
  //kiem tra xem thang nam dat chi tieu co hop le hay khong
     var d=new Date();
	 var year_= d.getFullYear();
	 var month_=d.getMonth()+1;
	 if(thang==6 && dvkd_fk=="100001"){
	 }else{
		 if(parseInt(nam)<parseInt(year_)){
			  document.forms["ChiTieuTTForm"].dataerror.value="Th???i gian ?????t ch??? ti??u kh??ng h???p l???,ph???i ?????t th???i gian ch??? ti??u l???n h??n th???i gian hi???n t???i ";
				return; 
		 }else if(parseInt(nam)==parseInt(year_) && parseInt(thang)<parseInt(month_)){
			  document.forms["ChiTieuTTForm"].dataerror.value="Th???i gian ?????t ch??? ti??u kh??ng h???p l???,ph???i ?????t th???i gian ch??? ti??u l???n h??n th???i gian hi???n t???i ";
				return; 
		 }
	 }

if(tongchitieu=="" || tongchitieu=="0" ){
	  document.forms["ChiTieuTTForm"].dataerror.value="Ch???n th??ng n??m ????? l???y ch??? ti??u ???????c ph??n b??? t??? trung t??m";
	  return;
}
if(ngayketthuc==""){
	  document.forms["ChiTieuTTForm"].dataerror.value="Ch???n ng??y k???t th??c  ";
return;
}
var thanhtien = document.getElementsByName("chitieu");
	var tongtien = 0;
	for(var i=0; i < thanhtien.length; i++)
	{
		if(thanhtien.item(i).value != "")
		{
			var thanh_tien = thanhtien.item(i).value;
			tongtien = parseFloat(tongtien) +  parseFloat(thanh_tien);
		}
	}  
		var tongchitieu= document.forms["ChiTieuTTForm"].tongchitieu.value.replace(",","");
		while(tongchitieu.match(","))
		{
			tongchitieu=tongchitieu.replace(",","");
		}
		if(parseFloat(tongchitieu)!= tongtien)
		{
	  		document.forms["ChiTieuTTForm"].dataerror.value="Nh???p t???ng ch??? ti??u c???a Khu V???c ph???i b???ng t???ng ch??? ti??u. ";
	  		return;
	  	}
		
	  
document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";

document.forms["ChiTieuTTForm"].action.value="update";
document.forms["ChiTieuTTForm"].submit();


}
 
</SCRIPT>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/ajax.js"></script>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="ChiTieuTTForm" method="post" action="../../ChiTieuNhaPPNewSvl" >
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
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? ch??? ti??u &gt; Ph??n b??? ch??? ti??u &gt;C???p nh???t</TD> 
							 <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%=tenhapp %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../ChiTieuNppSvl?userId=<%=userId%>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
						    <TD width="30" align="left" >
						    <div id="btnSave">
						    <A href="javascript: save()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
						    </div>
						    </TD>
							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%=objbean.getMessage()%></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Th??ng tin ch??? ti??u </LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
							<TR>
								<TD width="20%" class="plainlabel" >Th??ng <FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel">
									<select name="thang" style="width :50px" onchange="submitform()">
									<option value=0> </option>  
									<%
  										int k=1;
  									for(k=1;k<=12;k++){
  									  if(k==objbean.getThang()){
  									%>
									<option value=<%=k%> selected="selected" > <%=k%></option> 
									<%
 										}else{
 									%>
									<option value=<%=k%> ><%=k%></option> 
									<%
 										}
 										  }
 									%>
									</select>
								</TD>
							</TR>
							<TR>
							  	<TD class="plainlabel">N??m</TD>
						  	  	<TD class="plainlabel">
										<select name="nam"  style="width :50px" onchange="submitform()">
									<option value=0> </option>  
									<%
									  Calendar c2 = Calendar.getInstance();
  										int t=c2.get(Calendar.YEAR) +6;
  										int n;
  										for(n=2008;n<t;n++){
  										if(n==objbean.getNam()){
  									%>
									<option value=<%=n%> selected="selected" > <%=n%></option> 
									<%
 										}else{
 									%>
									<option value=<%=n%> ><%=n%></option> 
									<%
 										}
 									  }
 									%>
									</select>
						  	  	</TD>
						  </TR>
						    <tr class="plainlabel">
                             <td>????n v??? kinh doanh </td>
                             <td>
                             <select name=selectdvkd  >
                                 <option value =""> </option>  
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
                             <td>K??nh b??n h??ng </td>
                             <td>
								 <select name=kenhid >
                             <option value =""> </option>             
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
						  	  	<TD class="plainlabel">S??? ch??? ti??u</TD>
						  	  <TD class="plainlabel">
						  	  <input onkeypress="return keypress(event);" type="text" readonly="readonly" name="tongchitieu" value="<%= formatter.format(objbean.getChitieu())%>" > 
						  	  	</TD>
						  	</TR>
						  	<TR>
						  	  	<TD class="plainlabel">S??? ng??y l??m vi???c </TD>
						  	  	<TD class="plainlabel">
						  		<input type="text" name="songaylamviec" readonly="readonly" value="<%=objbean.getSoNgayLamViec()%>">  
						  							  	  	</TD>
						  	</TR>
						  	<TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Ng??y k???t th??c",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
						  		<input type="text" name="ngayketthuc" readonly="readonly" value="<%=objbean.getNgayKetThuc()%>">  
						  							  	  	</TD>
						  	</TR>		 
						      <TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
						  	  	<textarea name="diengiai"  style="width: 50%"  rows="2"><%=objbean.getDienGiai()%></textarea>	
						  	  	</TD>
						  	</TR>
						</TABLE>
						<TABLE width="100%" border="0" cellspacing="1" cellpadding="0" >							
								<TR class="tbheader">
									<TH width="5%">STT </TH>
									<TH width="20%">T??n nh??n vi??n </TH>
									<TH width="10%">Ch??? ti??u </TH>
									<!-- <TH width="15%">S??? ????n h??ng </TH>
									<TH width="15%">S??? SKU </TH> -->
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
									<TD> <input type =text name="chitieu" style="width :100%" value="<%= rs_ctddkd.getString("chitieu")%>" onkeypress="return keypress(event);"> </TD>
								   <%-- <TD><input type =text name="sodonhang" style="width :100%"  value=<%=ddkd.getSoDonHang() %> onkeypress="return keypress(event);"> </TD>
								   <TD ><input type =text name="sosku" style="width :100%"  value=<%=ddkd.getSoSku() %> onkeypress="return keypress(event);"> </TD>
 									--%>								   
									 <%
									if(nhomspid!=null){
										for(int i=0;i<nhomspid.length;i++){
											if(nhomspid[i]!=null){
											%>
											<TH width="10%"> <input name="<%=rs_ctddkd.getString("ddkd_fk").trim()+nhomspid[i]%>" style="width :100%"  value= <%=rs_ctddkd.getDouble("CT"+nhomspid[i])%> type="text" ></TH>
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
	catch (SQLException e) {}

%>
<%}%>