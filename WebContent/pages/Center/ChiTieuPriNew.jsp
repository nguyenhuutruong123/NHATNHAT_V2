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
<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	//Lay doi tuong objbean
 	ChiTieu objbean=(ChiTieu)session.getAttribute("obj");
	//truyen qua doi tuong list vung
	List<ChiTieuNPP> listnhapp= (List<ChiTieuNPP> )objbean.getListChiTieuNPP();
	String check1=(String)session.getAttribute("check");
	//lay resultset vung de do vao combobox
	ResultSet listkhuvuc= (ResultSet)session.getAttribute("listkhuvuc");
	String loaichitieu=(String)session.getAttribute("loaichitieu");
	ResultSet rs_dvkd=objbean.getRsDVKD();
	ResultSet	rs_kenh= objbean.getRsKenh();
	
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">


<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">


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
<SCRIPT language="JavaScript" type="text/javascript">

function submitform()
{
    document.forms["ChiTieuTTForm"].submit();
}

function lamtrontien_phandu(){
	
	var tongtien = document.forms["ChiTieuTTForm"].tongchitieu.value;

	var chitieu = document.getElementsByName("chitieu");
	
	var index=-1;
	
	for(var i=chitieu.length-1;i >=0; i--)
	{
		if(chitieu.item(i).value >0)
		{
			index=i;
			break;
		}
	}
	
	var tongtientruoc=0;
	
	for(var i=0; i < index; i++)
	{
		
		if(chitieu.item(i).value != "")
		{
			var thanh_tien=0;
			try{
			 thanh_tien=parseFloat(chitieu.item(i).value);
			}catch(err){}
			 tongtientruoc=parseFloat(tongtientruoc) +parseFloat(thanh_tien)
		}
	}
	
	if(index!=-1 && tongtien!=0){
		chitieu.item(index).value=parseFloat(tongtien)-parseFloat(tongtientruoc);
	}
}
 function moform(value){
	 try{
	 document.forms["ChiTieuTTForm"].ngayketthuc.value="0";
	 }catch(err){
		 
	 }
	 document.forms["ChiTieuTTForm"].songaylamviec.value="";
	 document.forms["ChiTieuTTForm"].tongchitieu.value="0";
	document.forms['ChiTieuTTForm'].loaichitieu.value=value;	
	document.forms['ChiTieuTTForm'].submit();
 } 
function checkall(value){
	var checkone=document.getElementsByName("checkkhuvuc1");
	var giatricheck=document.getElementsByName("checkkhuvuc");
	var chuoi;
	if(value==true){
		chuoi="1";
	}else{
		chuoi="0";
	}
	for(var i=0;i<checkone.length;i++ ){
		checkone.item(i).checked=value;
		giatricheck.item(i).value=chuoi;
	}
}
function recheck(){
	var checkone=document.getElementsByName("checkkhuvuc1");
	var giatricheck=document.getElementsByName("checkkhuvuc");
	for(var i=0;i<checkone.length;i++ ){
		if(checkone.item(i).checked==true){
			giatricheck.item(i).value="1";
		}else {
			giatricheck.item(i).value="0";
		}
		
			
	}
}
function keypress(e) 
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
function loctien(){
	var checkimport=document.forms["ChiTieuTTForm"].luutam.value;
	if(checkimport=="0"){
		//ch? th?c hi?n submit khi trong tinh trang la tu dong load chi tieu
	document.forms["ChiTieuTTForm"].tongchitieu.value="0";
	document.forms["ChiTieuTTForm"].action.value="loctien";
	document.forms["ChiTieuTTForm"].submit();
	}else{
		document.forms["ChiTieuTTForm"].submit();
	}
	
}
function lockhuvuc(){
	var checkimport=document.forms["ChiTieuTTForm"].luutam.value;
	if(checkimport=="0"){
	document.forms["ChiTieuTTForm"].tongchitieu.value="0";
	document.forms["ChiTieuTTForm"].action.value="lockhuvuc";
	document.forms["ChiTieuTTForm"].submit();
	}
}
function upload(){// nhan nut cap nhat

	   if(document.forms["ChiTieuTTForm"].filename.value==""){
		   
		   document.forms["ChiTieuTTForm"].dataerror.value="Ch??a t??m file upload l??n. Vui l??ng ch???n file c???n upload.";
	   }else{
		 document.forms["ChiTieuTTForm"].setAttribute('enctype', "multipart/form-data", 0);
		 document.forms["ChiTieuTTForm"].submit();	
	   }

}
function save(){
	  document.forms["ChiTieuTTForm"].dataerror.value=" ";
 	 var thang=document.forms["ChiTieuTTForm"].thang.value;
  	var nam=document.forms["ChiTieuTTForm"].nam.value;
  	var tongchitieu=document.forms["ChiTieuTTForm"].tongchitieu.value;
 	 var loaichitieu=document.forms["ChiTieuTTForm"].loaichitieu.value;
 	
 	
 	 
  if(nam==0){
	  document.forms["ChiTieuTTForm"].dataerror.value="Ch???n n??m c???n ?????t ch??? ti??u ";
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
	
		 if(parseInt(nam)<parseInt(year_)){
			 
			  document.forms["ChiTieuTTForm"].dataerror.value="Th???i gian ?????t ch??? ti??u kh??ng h???p l??. Ph???i ?????t th???i gian ch??? ti??u l???n h??n th???i gian hi???n th???i ";
				return; 
		 }else if(parseInt(nam)==parseInt(year_) && parseInt(thang)<parseInt(month_)){
			  document.forms["ChiTieuTTForm"].dataerror.value="Th???i gian ?????t ch??? ti??u kh??ng h???p l??. Ph???i ?????t th???i gian ch??? ti??u l???n h??n th???i gian hi???n th???i";
				return; 
		 }
	 
	if(tongchitieu=="" || tongchitieu=="0" ){
	  document.forms["ChiTieuTTForm"].dataerror.value="Nh???p ch??? ti??u l???n h??n 0 ";
	  return;
	}

	 var ngayketthuc=document.forms["ChiTieuTTForm"].ngayketthuc.value;
		if(ngayketthuc==""){
	  document.forms["ChiTieuTTForm"].dataerror.value="Ch?n ng??y k?t th??c  ";
		return;
		}else{
		 //kiem tra ngay thang co hop le khong
		//Run some code here
		 var today = new Date(ngayketthuc);//d?i ra ki?u ng??y th??ng v?? b? l?i, khi d?? n?? c?? gi?? tr? Invalid Date
		if(today=="Invalid Date"){
			 document.forms["ChiTieuTTForm"].dataerror.value="Nh???p th???i gian kh??ng ????ng. ?????nh d???ng ng??y th??ng l??: yyyy-MM-dd ";
	     	return; 
		}
		
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
	var tongchitieu= document.forms["ChiTieuTTForm"].tongchitieu.value;
if(parseFloat(tongchitieu)!= tongtien)
	{
	  document.forms["ChiTieuTTForm"].dataerror.value="Nh???p t???ng ch??? ti??u c???a Khu V???c, ph???i b???ng t???ng ch??? ti??u. ";
	  return;
	  }
	document.forms["ChiTieuTTForm"].action.value="new";
	document.forms["ChiTieuTTForm"].submit();


}
function kiemtra(){
	if(document.forms['ChiTieuTTForm'].luutam.value=='0')
	{return false;}
	else{
		return true;
	}
}
function checkradio(value){
	var checkone=document.getElementsByName("checkkhuvuc1");
	document.forms["ChiTieuTTForm"].luutam.value=value;
	var read;
}
</SCRIPT>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/ajax.js"></script>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="ChiTieuTTForm" method="post" action="../../ChiTieuPriNewSvl" >
<input type="hidden"  name="userId" value='<%=userId%>'>
<input type="hidden" name="userTen" value='<%=userTen%>'>
<input type="hidden" name="nkmId" value="">
<input type="hidden" name="action" value="0">
<input type="hidden" name="id" value="0">
<input type="hidden" name="tenform" value="0">
<input type="hidden" name="luutam" value="<%=check1%>"><!--  de luu gia tri cua radio khi cho -->
<input type="hidden" name="loaichitieu" value="<%=loaichitieu%>">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? ch??? ti??u > Khai b??o > Ch??? ti??u Mua V??o&gt;T???o m???i</TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen%>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../ChiTieuPriSvl?userId=<%=userId%>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
						    <TD width="30" align="left" ><A href="javascript: save()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
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
	    				<textarea name="dataerror"  style="width: 100%" readonly="readonly" rows="1"><%=objbean.getMessage()%></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Th??ng tin ch??? ti??u</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
						
							<TR>
								<TD width="20%" class="plainlabel" >Th??ng <FONT class="erroralert"> </FONT></TD>
								<TD class="plainlabel">
									<select name="thang" style="width :50px" ">
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
										<select name="nam"  style="width :50px" ">
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
						  
				  		   <TR>
						  	  	<TD class="plainlabel">S??? ch??? ti??u</TD>
						  	  <TD class="plainlabel">
						  
						  	  <input onkeypress="return keypress(event);" type="text"  name="tongchitieu" value="<%=Math.round(objbean.getChitieu())%>" > 
						  	  	
						  	  		
						  	  	</TD>
						  	</TR>
						  	<tr class="plainlabel">
                             <td>????n v??? kinh doanh </td>
                             <td>
                             <select name=dvkdid ">
                                 <option value ="" > </option>  
                             <%
                             if (rs_dvkd!=null){
                            	 while (rs_dvkd.next()){                      				                       				
                       				 if(rs_dvkd.getString("pk_seq").equals(objbean.getDVKDId())){ %>
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
                             <tr class="plainlabel">
                             <td>K??nh b??n h??ng </td>
                             <td>
								 <select name=kbhid ">
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
						  	  	<TD class="plainlabel">S??? ng??y l??m vi???c</TD>
						  	  <TD class="plainlabel" >
						  
						  	  <input onkeypress="return keypress(event);" type="text"  name="songaylamviec" value="<%=objbean.getSoNgayLamViec()%>" > 
						  	  		
						  	  	</TD>
						  	</TR>
						  	 <TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TD>
						  	  <TD class="plainlabel" >
						  
						  	  <input type="text"  name="diengiai" value="<%=objbean.getDienGiai()%>" > 
						  	  		
						  	  	</TD>
						  	</TR>
						  	
						  <TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Ng??y b???t ?????u",session,jedis) %></TD>
						  	  <TD class="plainlabel" >
						  
						  	  <input type="text" class="days" name="ngaybatdau" value="<%=objbean.getNgayBatDau()%>" > 
						  	  		
						  	  	</TD>
						  	</TR>
						  	
						  	<TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Ng??y k???t th??c",session,jedis) %></TD>
						  	  <TD class="plainlabel" >
						  
						  	  <input type="text" class="days" name="ngayketthuc" value="<%=objbean.getNgayKetThuc()%>" > 
						  	  		
						  	  	</TD>
						  	</TR>
						  	<tr class="plainlabel">
						  
						  	  <td colspan="2">
						  	  <INPUT type="file" name="filename" size="40" value=''> 
						  	  </td> 
						  	</tr>
						  	<tr class="plainlabel">
						  	<td colspan="2">
						  		&nbsp;&nbsp;&nbsp;&nbsp; <a class="button2" href="javascript:upload()">
								<img style="top: -4px;" src="../images/button.png" alt=""> C???p nh???t</a>							
						  	</td>
						  	</tr>
						  	
						  	
						</TABLE>
													
						</FIELDSET>						
					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
	</TABLE>
</form>
<script type="text/javascript">

//lamtrontien_phandu();
</script>
</BODY>
</HTML>