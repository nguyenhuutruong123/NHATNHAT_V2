<%@page import="java.sql.SQLException"%>
<%@page import="geso.dms.distributor.beans.donhangnhapp.imp.DonHangNPP"%>
<%@page import="geso.dms.distributor.beans.donhangnhapp.imp.SanPhamDhNpp"%>
<%@page import="geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham"%>
<%@page import="geso.dms.distributor.beans.donhangnhapp.ISanPhamDhNpp"%>
<%@page import="geso.dms.distributor.beans.donhangnhapp.IDonhangnpp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.donhang.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DateFormat" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.SimpleDateFormat" %>

<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<% IDonhangnpp dhBean = (IDonhangnpp)session.getAttribute("obj"); %>
<% List<ISanPhamDhNpp> splist = (List<ISanPhamDhNpp>)dhBean.getSanPhamList(); %>
<% 
		ResultSet rskho=dhBean.getrskho();
		ResultSet rskenh=dhBean.getrskenh();
		String tennhapp=dhBean.getTenNPPMua();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<style type="text/css">
	#mainContainer{
		width:660px;
		margin:0 auto;
		text-align:left;
		height:100%;
		border-left:3px double #000;
		border-right:3px double #000;
	}
	#formContent{
		padding:5px;
	}
	/* END CSS ONLY NEEDED IN DEMO */
		
	/* Big box with list of options */
	#ajax_listOfOptions{
		position:absolute;	/* Never change this one */
		width:auto;	/* Width of box */
		height:200px;	/* Height of box */
		overflow:auto;	/* Scrolling features */
		border:1px solid #317082;	/* Dark green border */
		background-color:#C5E8CD;	/* White background color */
    	color: black;
		text-align:left;
		font-size:1.0em;
		z-index:100;
	}
	#ajax_listOfOptions div{	/* General rule for both .optionDiv and .optionDivSelected */
		margin:1px;		
		padding:1px;
		cursor:pointer;
		font-size:1.0em;
	}
	#ajax_listOfOptions .optionDiv{	/* Div for each item in list */
		
	}
	#ajax_listOfOptions .optionDivSelected{ /* Selected item in the list */
		background-color:#317082; /*mau khi di chuyen */
		color:#FFF;
	}
	#ajax_listOfOptions_iframe{
		background-color:#F00;
		position:absolute;
		z-index:5;
	}
	form{
		display:inline;
	}
	#dhtmltooltip
	{
		position: absolute;
		left: -300px;
		width: 150px;
		border: 1px solid black;
		padding: 2px;
		background-color: lightyellow;
		visibility: hidden;
		z-index: 100;
		/*Remove below line to remove shadow. Below line should always appear last within this CSS*/
		filter: progid:DXImageTransform.Microsoft.Shadow(color=gray,direction=135);
	}	
	#dhtmlpointer
	{
		position:absolute;
		left: -300px;
		z-index: 101;
		visibility: hidden;
	}	
</style>
<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript">
	jQuery(function($){ 
		 $('.addspeech').speechbubble();
	})
</script>

<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/ajax-dynamic-list.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>

<script language="javascript" type="text/javascript">
function chotdonhang()
{
	document.forms['dhForm'].action1.value="chot";
	document.forms['dhForm'].submit();
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

function TinhTongtien()
{
	var thanhtien = document.getElementsByName("thanhtien");
	var tongtien = 0;
	for(var i=0; i < thanhtien.length; i++)
	{
		if(thanhtien.item(i).value != "")
		{
			//var thanh_tien = thanhtien.item(i).value.replace(".", "");
			var thanh_tien = thanhtien.item(i).value;
			tongtien = parseFloat(tongtien) +  parseFloat(thanh_tien);
		}
	}
	document.getElementById("SoTienChuaVAT1").value = tongtien;
/* 	var vat = document.getElementById("VAT").value;
	if(vat == "")
		vat = "10";
	document.getElementById("TIENCOTHUE").value = (parseFloat(vat) * tongtien) / 100 + tongtien; */

}

function tinhtongtien()
{
	var masp=document.getElementsByName("masp");
	var soluong=document.getElementsByName("soluong");
	var soluongnhan=document.getElementsByName("soluongnhan");
	var thanhtien = document.getElementsByName("thanhtien");
	var dongia=document.getElementsByName("dongia");
	//var chietkhau = document.getElementsByName("ChietKhau");
	var tongtien = 0;
	for(var i=0; i < masp.length; i++)
	{
		if(masp.item(i).value != "")
		{
			var sl_cu=soluong.item(i).value;
			var sl=soluongnhan.item(i).value;
			 var dg=dongia.item(i).value;
			 var tt=0;
			
			 if(parseInt(soluongnhan.item(i).value) > 0)
			{
				 if(parseInt(sl_cu)<parseInt(sl)){
					 //alear("Kh??ng ???????c nh???p v?????t qu?? s??? l?????ng ????n h??ng g???i ?????n,ch??? ???????c ph??p nh???p s??? l?????ng nh??? h??n");
					 soluongnhan.item(i).value=sl_cu;
					
				 }
				 else
					 {
				   tt = parseFloat(sl) * parseFloat(dg);		
				   }		   
			}
			 thanhtien.item(i).value=tt;
				 //var thanh_tien = thanhtien.item(i).value.replace(".", "");
			
			tongtien = parseFloat(tongtien) +  parseFloat(tt);
		}
	}
	//tinh tong tien chua thuc hien duoc
	TinhTongtien();
		setTimeout(tinhtongtien, 20);//goi lai sau 20ms
}

</script>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dhForm" method="post" action="../../MuaHangNhaPPKhacSvl">
<input type="hidden" name="userId" value='<%=userId %>'>
<input type="hidden" name="nppId_mua" value='<%= dhBean.getNppId_Mua() %>'>
<input type="hidden" name="nppId_ban" value='<%= dhBean.getNppId_Ban() %>'>
<input type="hidden" name="action1" value='1'>
<INPUT type="hidden" name="id" value='<%= dhBean.getId() %>'>
<INPUT type="hidden" name="trangthai" id="trangthaiDh" value='<%= dhBean.getTrangthai() %>'>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
				<TABLE border =0 width = "100%" cellpadding="2" cellspacing="0">
				<TBODY>
					<TR height="22">
						<TD align="left" >
							<TABLE width="100%" cellpadding="0" cellspacing="0" style="margin:5px " >
								<TR>
									<TD align="left">
									   <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
										   <TR height="22">
			 								   <TD align="left"  class="tbnavigation">&nbsp;Qu???n l?? t???n kho >Mua h??ng t??? nh?? cung c???p kh??c > Ch???nh s???a ????n h??ng & nh???n h??ng </TD>								   
			 								   <TD align="right" class="tbnavigation">Ch??o m???ng  <%= tennhapp %> &nbsp; </TD>
					    				 </TR>
									  </TABLE>
								  </TD>
							  </TR>	
						  	</TABLE>
							<TABLE width="100%" border="0" cellpadding="1" cellspacing="0">
								<TR ><TD >
									<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
										<TR class = "tbdarkrow">
											<TD width="30" align="left"><A href = "../../MuaHangNhaPPKhacSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
										    <TD width="2" align="left" >&nbsp;</TD>
										    <TD width="30" align="left"><A href="javascript: chotdonhang();"><img src="../images/Chot.png" alt="Nh???n h??ng"  title="Nh???n h??ng" border="1" longdesc="Nh???n h??ng" style="border-style:outset"></A></TD>
										     <TD width="2" align="left" >&nbsp;</TD>
							    			<TD width="30" align="left"><A href="Print.jsp" ><img src="../images/Printer30.png" alt="In" title="In chung tu" longdesc="In chung tu" border=1 style="border-style:outset"></A></TD>
								    		<TD align="left" >&nbsp;</TD>
										</TR>
									</TABLE>
								</TD></TR>
							</TABLE>												
							<TABLE border="0" width="100%" cellpadding = "0" cellspacing = "0" >
								<tr>
								<TD align="left" colspan="4" class="legendtitle">
									<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>			
				    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width:100%"  rows="1" readonly="readonly"> <%= dhBean.getthongbao() %></textarea>
									</FIELDSET>
							   </TD>
								</tr>
								<TR>
									<TD  align="left">						
										<FIELDSET>
										<LEGEND class="legendtitle">&nbsp;????n h??ng b??n </LEGEND>
										<TABLE cellpadding = "3" cellspacing = "0" width = "100%" border = 0>
												<TR >
												<TD width="22%" class="plainlabel">S??? ????n h??ng </TD>
												<TD colspan="7" class="plainlabel"><%= dhBean.getId() %> </TD>
												</TR>							
											
											<TR >
												<TD width="22%" class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %> b??n h??ng</TD>
												<TD colspan="3" class="plainlabel"><%=  dhBean.getTenNPPBan() %> </TD>
																								
												  <TD class="plainlabel">Ng??y nh???n h??ng </TD>
												  <TD colspan="3" class="plainlabel">
												  <table border=0 cellpadding="0" cellspacing="0">
	                                                <TR><TD>
											    	<input type="text" name="tungay" size="20" value="<%= dhBean.getNgayNhanhang() %>" readonly >
												</TD>
												<TD>
												</TD></TR>
                                         		 </table>
												</TR>	
												
												<TR class="tblightrow">
												<TD  class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %> mua h??ng</TD>
												<TD colspan="3" class="plainlabel"><%= tennhapp %> </TD>
																								
											    <TD  class="plainlabel">T???ng s??? ti???n  </TD>	     
										        <TD colspan="3"  class="plainlabel"><input name="SoTienChuaVAT1" id="SoTienChuaVAT1" type="text" value="<%= dhBean.getTongGiaTri() %>" > VND </TD>
									       	</TR>
									       											   						
											<TR class="plainlabel">
											<TD  class="plainlabel">Ch???n k??nh b??n h??ng</TD>
												<TD colspan="3" class="plainlabel">
												 
												<select name="kenhbh"  >
												<option value=""> </option>
												<%
												if(rskenh!= null){
													  try{ while(rskenh.next()){ 
													
														  if(rskenh.getString("kbh_fk").trim().equals(dhBean.getIdKenh_Nhan()))
														  {
															%>  
															<option  selected="selected" value='<%=rskenh.getString("kbh_fk").trim()%>'  ><%=rskenh.getString("ten").trim() %></option><%
														  }
														  else
														  {
														%>
											     				<option value="<%=rskenh.getString("kbh_fk").trim()%>"> <%=rskenh.getString("ten").trim() %></option>
											     			<% 
														  } } }catch(Exception e){
										     				System.out.println("Error : 194 DonHangNhaPPUpdate ; "+ e.toString());
										     			}} %>	
												</select>
												</TD>
																								
												  <TD  class="plainlabel"></TD>
												  <TD colspan="3"  class="plainlabel"></TD>										  												
												</TR>
												
											<TR class="plainlabel">
											<TD  class="plainlabel"> Ch???n kho h??ng nh???n</TD>
												<TD colspan="3" class="plainlabel"> 
												<SELECT name="khobh" id="khobanhang">
										 			 <option value=""> </option>
													  <%
													  if(rskho!= null){
														  try{ while(rskho.next()){ 
														
															  if(rskho.getString("pk_Seq").trim().equals(dhBean.getIdKho_Nhan()))
															  {
																%>  <option  selected="selected" value='<%=rskho.getString("pk_Seq").trim()%>'  ><%=rskho.getString("ten").trim()+"-"+ rskho.getString("diengiai").trim()%></option><%
															  }
															  else
															  {
																	%>
												     				<option value="<%=rskho.getString("pk_Seq").trim()%>"> <%=rskho.getString("ten").trim()+"-"+rskho.getString("diengiai").trim() %></option>
												     			<% 
															  } } }catch(Exception e){
											     				System.out.println("Error : 194 DonHangNhaPPUpdate ; "+ e.toString());
											     			}} %>	 
                                    			</SELECT> 
												</TD>
												
												<%
										  /* 	
										  	double tienchuathue=(dhBean.getTongGiaTri());
											 double tiencothue=tienchuathue+tienchuathue/100*Double.parseDouble(dhBean.getVAT()); */
											 
											  %>											
											  <TD  class="plainlabel"></TD>
											    <TD colspan="3"  class="plainlabel"></TD>										  	
											</TR>

										</TABLE>
									</FIELDSET>
								  </TD>
							   </TR>	
							   <TR>
							   		<TD>
										<TABLE width = "100%" border="1" cellpadding="2px" cellspacing="1" >
										<tbody id="san_pham">
												<TR class="tbheader" >
													<TH width="15%">M?? s???n ph???m </TH>
													<TH width="28%">T??n s???n ph???m </TH>
													<TH width="7%">DVT</TH>
													<TH width="10%">S??? l?????ng </TH>
													<TH width="10%">S??? l?????ng nh???n </TH>
													<TH width="10%">????n gi?? </TH>
													<TH width="10%">Th??nh ti???n </TH>
													
												</TR>
									<% 
							if(splist != null){
							ISanPhamDhNpp sanpham =new SanPhamDhNpp();
							int size = splist.size();
							int m = 0;
							while (m < size){
								sanpham = splist.get(m); 
								%>
									<TR class= 'tblightrow' >
									<TD align="left" >
									<input name="masp" type="text" readonly value="<%=sanpham.getMaSanPham()%>" style="width:100%">
									</TD>
									<TD align="left" >
										<input name="tensp" type="text" readonly value="<%=sanpham.getTenSanPham()%>"  style="width:100%"></TD>
									 <TD align = "center" ><input name="donvitinh" type="text" value="<%= sanpham.getDVT() %>" readonly  style="text-align:center ;width:100%"></TD>
						        	<TD align="left" >
										<input name="soluong" type="text" readonly value="<%=sanpham.getSoLuong()%>" style="width:100%"></TD>
										<TD align="left" >
										<!-- Lay m???c ?????nh s??? l?????ng nh???n b??ng s??? l?????ng c???a ????n h??ng g???i t???i -->
										<input name="soluongnhan" type="text"  value="<%=sanpham.getSoLuongNhan() %>" style="width:100%"  onkeypress="return keypress(event);"></TD>
									 <TD align = "center" >
								    	<input type="text" name="dongia" value="<%= sanpham.getGiaMua() %>" readonly style="text-align:right;width:100%">
								   <TD align = "center" >
								    	<input type="text" name="thanhtien" value="<%= sanpham.getThanhTien() %>" readonly  style="text-align:right;width:100%">
								     </TD>
								</TR>
								<% m++; }}%>
								<TR><TD colspan="8" class="tbfooter">&nbsp;</TD></TR>																																																																																																															
								</tbody></TABLE>							
								</TD>
							  </TR> 
						  </TABLE>
						</TD>
					</TR>				
				</TBODY>
			</TABLE>
	</td>
  </tr>
</TABLE>
</form>
<script type="text/javascript">
<!--
tinhtongtien();

//-->
</script> 
</BODY>
</HTML>

<% 
try{
	if(dhBean != null){
		dhBean.DBclose();
		dhBean = null;
	}
	if(rskho!=null){
		rskho.close();
	}
	if(rskenh!=null){
		rskenh.close();
	}
	if(splist!=null){
		splist.clear();
	}
	session.setAttribute("obj",null);

}catch(Exception er){
	
}
%>
<%}%>


