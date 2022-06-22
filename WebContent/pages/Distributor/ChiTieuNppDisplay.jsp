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
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<%
	
	//Lay doi tuong objbean
 	ChiTieuNhaPP objbean=(ChiTieuNhaPP)session.getAttribute("obj");
	//truyen qua doi tuong list vung
	List<ChiTieuDDKD> listddkd= (List<ChiTieuDDKD>)objbean.getListChiTieuDDKD();
	String check1=(String)session.getAttribute("check");
	String tenhapp=(String)session.getAttribute("tennhapp");
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
function keypress(e) //Hàm dùng d? ngan ngu?i dùng nh?p các ký t? khác ký t? s? vào TextBox
{    
	var keypressed = null;
	if (window.event)
		keypressed = window.event.keyCode; //IE
	else
		keypressed = e.which; //NON-IE, Standard
	
	if (keypressed < 48 || keypressed > 57)
	{ 
		if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39)
		{//Phím Delete và Phím Back
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
	  document.forms["ChiTieuTTForm"].dataerror.value="Chọn năm cần đặt chỉ tiêu ";
	  return;
  }
  if(thang==0){
	  document.forms["ChiTieuTTForm"].dataerror.value="Chọn tháng cần đặt chỉ tiêu ";
	  return;
	  }
  //kiem tra xem thang nam dat chi tieu co hop le hay khong
  var d=new Date();
	 var year_= d.getFullYear();
	 var month_=d.getMonth()+1;
	 if(parseInt(nam)<parseInt(year_)){
		  document.forms["ChiTieuTTForm"].dataerror.value="Thời gian đặt chỉ tiêu không hợp lệ,phải đặt thời gian chỉ tiêu lớn hơn thời gian hiện tại ";
			return; 
	 }else if(parseInt(nam)==parseInt(year_) && parseInt(thang)<=parseInt(month_)){
		  document.forms["ChiTieuTTForm"].dataerror.value="Thời gian đặt chỉ tiêu không hợp lệ,phải đặt thời gian chỉ tiêu lớn hơn thời gian hiện tại ";
			return; 
	 }

if(tongchitieu=="" || tongchitieu=="0" ){
	  document.forms["ChiTieuTTForm"].dataerror.value="Chọn tháng năm để lấy chỉ tiêu được phân bổ từ trung tâm";
	  return;
}
if(ngayketthuc==""){
	  document.forms["ChiTieuTTForm"].dataerror.value="Chọn ngày kết thúc  ";
return;
}else{
		 //kiem tra ngay thang co hop le khong
		//Run some code here
		 var today = new Date(ngayketthuc);//đổi ra kiểu ngày tháng và bị lỗi, khi đó nó có giá trị Invalid Date
		if(today=="Invalid Date"){
			 document.forms["ChiTieuTTForm"].dataerror.value="Nhập ngày kết thúc không đúng định dạng ngày tháng,kiểu định dạng yyyy-MM-dd ";
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
	  document.forms["ChiTieuTTForm"].dataerror.value="Nhập tổng chỉ tiêu của Khu Vực phải bằng tổng chỉ tiêu. ";
	  return;
	  }
document.forms["ChiTieuTTForm"].action.value="new";
document.forms["ChiTieuTTForm"].submit();


}
function checkradio(value){
	document.forms["ChiTieuTTForm"].luutam.value=value;
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
<input type="hidden" name="id" value="0">
<input type=hidden name="luutam" value="<%=check1%>"><!--  de luu gia tri cua radio khi cho -->
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý chỉ tiêu > Chỉ tiêu Sells Out &gt; Xem</TD> 
							 <TD colspan="2" align="right" class="tbnavigation">Chào mừng  <%=tenhapp %>&nbsp;  </TD></tr>
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
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%=objbean.getMessage()%></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Thông tin chỉ tiêu </LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
							<TR>
								<TD width="20%" class="plainlabel" >Tháng <FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel">
									<input type="text" readonly="readonly" value="<%=objbean.getThang() %>">
								</TD>
							</TR>
							<TR>
							  	<TD class="plainlabel">Năm</TD>
						  	  	<TD class="plainlabel">
									<input type="text" readonly="readonly" value="<%=objbean.getNam() %>">
						  	  	</TD>
						  </TR>
						  <TR>
							  	<TD class="plainlabel">Đơn vị kinh doanh</TD>
						  	  	<TD class="plainlabel">
									<input type="text" readonly="readonly" value="<%=objbean.getTenDVKD() %>">
						  	  	</TD>
						  </TR>
						  <TR>
							  	<TD class="plainlabel">Kênh bán hàng:</TD>
						  	  	<TD class="plainlabel">
									<input type="text" readonly="readonly" value="<%=objbean.getTenKenhDefauld() %>">
						  	  	</TD>
						  </TR>
				  		   <TR>
						  	  	<TD class="plainlabel">Số chỉ tiêu</TD>
						  	  <TD class="plainlabel">
						  	  <input onkeypress="return keypress(event);" type="text" readonly="readonly" 
						  	   name="tongchitieu" value="<%=formatter.format(objbean.getChitieu())%>" > 
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
						  	  	<textarea name="diengiai" readonly="readonly" style="width: 50%"  rows="2"><%=objbean.getDienGiai()%></textarea>	
						  	  	</TD>
						  	</TR>
						</TABLE>
				<TABLE width="100%" border="0" cellspacing="1" cellpadding="0" >							
								<TR class="tbheader">
									<TH width="5%">STT </TH>
									<TH width="20%">Tên nhân viên </TH>
									<TH width="10%">Chỉ tiêu </TH>
									<!-- <TH width="15%">Số đơn hàng </TH>
									<TH width="15%">Số SKU </TH> -->
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
									<TD> <input type =text name="chitieu" style="width :100%" value="<%=formatter.format(rs_ctddkd.getDouble("chitieu"))%>" > </TD>
								  								   
									 <%
									if(nhomspid!=null){
										for(int i=0;i<nhomspid.length;i++){
											if(nhomspid[i]!=null){
											%>
											<TH width="10%"> <input name="<%=rs_ctddkd.getString("ddkd_fk").trim()+nhomspid[i]%>" style="width :100%"  value= <%=formatter.format(rs_ctddkd.getDouble("CT"+nhomspid[i]))%> type="text" ></TH>
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
if(objbean != null){
	objbean.DBclose();
	objbean = null;
}
	


%>
<%}%>