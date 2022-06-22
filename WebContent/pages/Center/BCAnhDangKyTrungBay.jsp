<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.sql.ResultSet"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");
	IStockintransit obj = (IStockintransit)session.getAttribute("obj");
	ResultSet npp = obj.getnpp();
	String nppId = obj.getnppId();
	ResultSet vung = obj.getvung();
//	ResultSet tinhthanh = obj.getTinhthanh();
	ResultSet khuvuc = obj.getkhuvuc();
	ResultSet ddkd = obj.getRsddkd();
	ResultSet cttbRs = obj.getCttbRs();
	ResultSet	dataRs =obj.getDataRs();
	
	Utility Util = new Utility(); 
	int[] quyen = new  int[6];
	quyen = Util.Getquyen("BCAnhDangKyTrungBay", "", userId);
	System.out.println("Loi :" + obj.getMsg());
	String url = Util.getChuyenNguUrl("BCAnhDangKyTrungBay", "",session);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery-1.js"></script>

<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
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
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button1").hover(function(){
                $(".button1 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        });
		
</script>

<!-- Khai bao su dung colorbox ajax -->
<link media="screen" rel="stylesheet" href="../css/colorbox.css">
<script src="../scripts/colorBox/jquery.colorbox.js"></script>

    
    
<script type="text/javascript">

	function seach() {
		document.forms['frm'].action.value = 'seach';
		document.forms["frm"].submit();
	}
	function submitform() 
	{
		var cttbBox = document.getElementById("cttbId");
		var cttbValue = cttbBox.options[cttbBox.selectedIndex].value;
		if( cttbValue =='')	
		{
			alert('Vui lòng chọn CTTB!');
			return;
		}
		document.forms['frm'].action.value= 'tao';
		document.forms["frm"].submit();
	}
	function xemtrenweb() 
	{

		document.forms['frm'].action.value= 'xemtrenweb';
		document.forms["frm"].submit();
	}
	function search() 
	{

		document.forms['frm'].action.value= 'search';
		document.forms["frm"].submit();
	}
	function thongbao()
	{
		if(document.getElementById("msg").value != '')
			alert(document.getElementById("msg").value);
	}
	
	function DuyetDangKyTrungBay(khId, dktrungbay_fk){
		var userId = document.getElementById("userId").value;

		console.log("userId = " + userId);
		var xmlhttp;
		if (window.XMLHttpRequest)
		{
		  xmlhttp=new XMLHttpRequest();
		}
		else
		{
		  xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		xmlhttp.onreadystatechange=function()
		{
		   	if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
		   	{
			  	if(xmlhttp.responseText.length > 10)
			  	{
				  	alert('Không thể cập nhật thông tin. \n' + xmlhttp.responseText);
			  	}
			  	else
			  	{
			  		var a = document.getElementById(khId);
			  		a.remove();
				  	alert('Duyệt đăng ký thành công.');
				  
				} 
		    }
		};
		
		xmlhttp.open("POST","../../AjaxDuyetDangKyTrungBay?userId="+userId+"&dktrungbay_fk=" + dktrungbay_fk + "&khId=" + khId, true);
		xmlhttp.send();
	}
</script>
<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
    	$(document).ready(function() { 
    		$("select:not(.notuseselect2)").select2({ width: 'resolve' });     
    	});
    </script>	
    
<style>
.Text{
        font-family: Verdana, Arial, Sans-serif, 'Times New Roman';
        font-size: 8pt;
        font-weight: normal;
        font-style: normal;
        color: #333333;
        text-decoration: none;
}
.toolTip {
        font-family: Verdana, Arial, Sans-serif, 'Times New Roman';
        font-size: 8pt;
        filter:alpha(opacity=80);
        -moz-opacity: 0.8;
        opacity: 0.8;
        /* comment the above 3 line if you don't want transparency*/
}

</style>
    

   

<BODY  leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BCAnhDangKyTrungBay">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
	<input type="hidden" name="action" value='1'>
	<input type="hidden" name="userId" id = "userId" value='<%=userId%>'>
	<input type="hidden" name="view" value='<%=obj.getLoaiMenu()%>'>
	<input type="hidden" name="msg" id="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>
	<script type="text/javascript">
	thongbao();
	</script>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation"><%=url %></div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %></div>
			</div>

			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
<!-- session.getAttribute("errors") -->
					<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" value="<%=session.getAttribute("errors") %>" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold"></textarea>
				</fieldset>
			</div>
			
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("Báo cáo đăng ký ảnh trưng bày",session,jedis) %></legend>
					<div style="width: 100%; float: none" align="left" class="plainlabel">
							<TABLE width="80%" cellpadding="6" cellspacing="0">
								<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
										<TD class="plainlabel">	<input type="text" name="tungay" id="tungay" class="days" value='<%=  obj.gettungay() %>'  />
											</TD>
										<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
										<TD class="plainlabel">
												<input type="text" name="denngay" id="denngay" class="days" value='<%= obj.getdenngay() %>' />
											</TD>
									</TR>
									<% if(obj.getLoaiMenu().equals("TT")){%>
										<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("Miền",session,jedis) %></TD>
										<TD class="plainlabel">
											<select name="vungId" id="vungId" onchange="search();" style="width: 300px">
												<option value="" selected>All</option>
												<%if (vung != null)
														while (vung.next()) {
															if (vung.getString("pk_seq").equals(obj.getvungId())) {%>
														<option value="<%=vung.getString("pk_seq")%>" selected><%=vung.getString("ten")%></option>
													<%} else {%>
														<option value="<%=vung.getString("pk_seq")%>"><%=vung.getString("ten")%></option>
												<%}}%>
											</select>
										</TD>
										<%-- <TD class="plainlabel">Tỉnh thành</TD>
										<TD class="plainlabel">
											<select name="tinhthanhId" id="tinhthanhId" onchange="seach();" style="width: 300px">
												<option value="" selected>All</option>
												<%if (tinhthanh != null)
														while (tinhthanh.next()) {
															if (tinhthanh.getString("pk_seq").equals(obj.getTinhthanhid())) {%>
																<option value="<%=tinhthanh.getString("pk_seq")%>" selected><%=tinhthanh.getString("ten")%></option>
														<%} else {%>
															<option value="<%=tinhthanh.getString("pk_seq")%>"><%=tinhthanh.getString("ten")%></option>
														<%}}%>
											</select>
										</TD> --%>
										<TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %></TD>
										<TD class="plainlabel">
											<select name="kvId" id="kvId" onchange="seach();" style="width: 300px">
												<option value="" selected><%=Utility.GLanguage("All",session,jedis) %></option>
												<%if (khuvuc != null)
														while (khuvuc.next()) {
															if (khuvuc.getString("pk_seq").equals(obj.getkhuvucId())) {%>
																<option value="<%=khuvuc.getString("pk_seq")%>" selected><%=khuvuc.getString("ten")%></option>
														<%} else {%>
															<option value="<%=khuvuc.getString("pk_seq")%>"><%=khuvuc.getString("ten")%></option>
														<%}}%>
											</select>
										</TD>
									</TR>
								
									<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </TD>
										<TD class="plainlabel" colspan="3" > 
											<select name="nppId" onchange="seach();" id="nppId" style="width: 300px">
												<option value="" selected><%=Utility.GLanguage("All",session,jedis) %></option>
												<%if (npp != null)
														while (npp.next()) {
															if (npp.getString("pk_seq").equals(obj.getnppId())) {%>
																<option value="<%=npp.getString("pk_seq")%>" selected><%=npp.getString("ten")%></option>
														<%} else {%>
															<option value="<%=npp.getString("pk_seq")%>"><%=npp.getString("ten")%></option>
														<%}}%>
											</select>
										</TD>
										
										
								</TR>
								<%}else{ %>
								<input type="hidden" name="nppId" value='<%=obj.getnppId()%>'>
								<%} %>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="ddkdId" id="ddkdId"	onchange="seach();" style="width: 300px">
											<option value="" selected><%=Utility.GLanguage("All",session,jedis) %></option>
											<%if (ddkd != null)
													while (ddkd.next()) {
														if (ddkd.getString("pk_seq").equals(obj.getDdkd())) {%>
														<option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
													<%} else {%>
														<option value="<%=ddkd.getString("pk_seq")%>"><%=ddkd.getString("ten")%></option>
													<%}}%>
										</select>
									</TD>
									<TD class="plainlabel"><%=Utility.GLanguage("Chương trình trưng bày",session,jedis) %></TD>
									<TD class="plainlabel" >
										<select name="cttbId" id="cttbId"	onchange="seach();" style="width: 300px">
											<option value="" selected></option>
											<%if (cttbRs != null)
													while (cttbRs.next()) {
														if (cttbRs.getString("pk_seq").equals(obj.getCttbId())) {%>
														<option value="<%=cttbRs.getString("pk_seq")%>" selected><%=cttbRs.getString("diengiai")%></option>
													<%} else {%>
														<option value="<%=cttbRs.getString("pk_seq")%>"><%=cttbRs.getString("diengiai")%></option>
													<%}}%>
										</select>
									</TD>
								</TR>
							
								<TR>
									<td ><a class="button" href="javascript:submitform();"> 
										<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Tạo báo cáo",session,jedis) %>
									</a></td>
									<td colspan="3"><a class="button" href="javascript:xemtrenweb();"> 
										<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Xem trên web",session,jedis) %>
									</a></td>
								</TR>
							</TABLE>
						</div>
					
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="2">
					<TR>
						<TD width="100%">
							<TABLE class="" width="100%">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH width="5%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
												<TH width="15%" align="center"><%=Utility.GLanguage("CN/ĐT",session,jedis) %></TH>
												<TH width="5%" align="center"><%=Utility.GLanguage("Mã NVBH",session,jedis) %></TH>
												<TH width="10%" align="center"><%=Utility.GLanguage("NVBH",session,jedis) %></TH>
												<TH width="5%" align="center"><%=Utility.GLanguage("MÃ KH",session,jedis) %></TH>
												<TH width="15%" align="center"><%=Utility.GLanguage("KHÁCH HÀNG",session,jedis) %></TH>
												<TH width="5%" align="center"><%=Utility.GLanguage("DÀI",session,jedis) %></TH>
												<TH width="5%" align="center"><%=Utility.GLanguage("RỘNG",session,jedis) %></TH>
												<TH width="5%" align="center"><%=Utility.GLanguage("CAO",session,jedis) %></TH>
												<TH width="5%" align="center"><%=Utility.GLanguage("NGÀY CHỤP",session,jedis) %></TH>
												<TH width="10%" align="center"><%=Utility.GLanguage("HÌNH",session,jedis) %></TH>
												<!-- <TH width="10%" align="center">HÌNH 2</TH> -->
												<!-- <TH width="10%" align="center">Duyệt ĐK</TH> -->
											</TR>
										
							<%  														
		                        int m = 0;
		                        String lightrow = "tblightrow";
		                        String darkrow = "tbdarkrow";
								if(dataRs !=null)
								{%>
								<% try{								
		                               while (dataRs.next()){
		                            	   	String nut = "#nutmopopup1" + Integer.toString(m + 1);
											String nut2 = "#nutmopopup2" + Integer.toString(m + 2);
											String popup = "#noidungpopup1"	+ Integer.toString(m + 1);
											
											String popup2 = "#noidungpopup2"+ Integer.toString(m + 2);
											
											String nut_id = "nutmopopup1" + Integer.toString(m + 1);
											String nut_id2 = "nutmopopup2" + Integer.toString(m + 2);
											String popup_id = "noidungpopup1"+ Integer.toString(m + 1);
											String popup_id2 = "noidungpopup2"+ Integer.toString(m + 2);
		                            		
		                                  	if (m % 2 != 0) {%>                     
		                                   	<TR class= <%=lightrow%> >
		                                   <%} else {%>
		                                      	<TR class= <%= darkrow%> >
		                                   	<%}%>
		                                   		
		                                   		<TD align="center"><%=m+1 %></TD>
												<TD align="center"><%= dataRs.getString("NPP")%></TD>
												<TD align="center"><%= dataRs.getString("tdvMa")%></TD>
												<TD align="center"><%= dataRs.getString("NVBH")%></TD>
												<TD align="center"><%= dataRs.getString("khMa")%></TD>
												<TD align="center"><%= dataRs.getString("KhachHang")%></TD>
												
												<TD align="center"><%= dataRs.getString("Dai")%></TD>
												<TD align="center"><%= dataRs.getString("Rong")%></TD>
												<TD align="center"><%= dataRs.getString("Cao")%></TD>
												
												
												<TD align="center"><%= dataRs.getString("NgayChup")%></TD>
												
												<td>
													<a id="<%=nut_id%>"   href="#">
														<img style=" width: 100px; height: 100px;" src="<%=getServletContext().getInitParameter("pathhinhJSP") + "AnhTrungBay/"+dataRs.getString("ANHCHUP") %>" >																																							
													</a>
													<script>
														$(document).ready(function()
														{
															$("<%=nut%>").colorbox({width:"100%", inline:true, href:"<%=popup%>", top: "50"});        	
														});
														
														
														$(document).ready(function(){
														
															$("<%=nut%>").colorbox({rel:'group4', slideshow:false});
														});
														
													 </script>
													 <div style='display:none'>
														<div id="<%=popup_id%>" style='padding:0px 5px; background:#fff;'>
															<table  cellpadding="4px" cellspacing="2px" width="100%" align="center" >
																
																<TR>
																
																	<td >
																		<img style="top: -4px; max-width: 500px; max-height: 500px;" src="<%= getServletContext().getInitParameter("pathhinhJSP")  +"/AnhTrungBay/"+ dataRs.getString("ANHCHUP")%>" >																																							
																	</td>
																
																</tr>
															</table>
														</div>
													</div>
												</td>
												
										<%-- 		<td>
													<a id="<%=nut_id2%>"   href="#">
														<img style=" width: 100px; height: 100px;" src="<%=getServletContext().getInitParameter("pathhinhJSP") + "AnhTrungBay/"+dataRs.getString("ANHCHUP2") %>" >																																							
													</a>
													<script>
														$(document).ready(function()
														{
															$("<%=nut2%>").colorbox({width:"100%", inline:true, href:"<%=popup2%>", top: "50"});        	
														});
														
														
														$(document).ready(function(){
														
															$("<%=nut2%>").colorbox({rel:'group4', slideshow:false});
														});
														
													 </script>
													 <div style='display:none'>
														<div id="<%=popup_id2%>" style='padding:0px 5px; background:#fff;'>
															<table  cellpadding="4px" cellspacing="2px" width="100%" align="center" >
																
																<TR>
																
																	<td >
																		<img style="top: -4px; max-width: 500px; max-height: 500px;" src="<%= getServletContext().getInitParameter("pathhinhJSP")  +"/AnhTrungBay/"+ dataRs.getString("ANHCHUP2")%>" >																																							
																	</td>
																
																</tr>
															</table>
														</div>
													</div>
												</td> --%>
												
												<%-- <TD align="center">
												<%if(dataRs.getInt("SUATDUYETDK") < 1 && quyen[Util.CHOT] != 0){ %>
													<A id = "<%= dataRs.getString("KhId") %>" href="javascript:DuyetDangKyTrungBay(<%= dataRs.getString("KhId") %>, <%= dataRs.getString("dktrungbay_fk") %>);"><img src="../images/Chot.png" alt="Duyệt" width="20" height="20" longdesc="Duyệt" border=0></A>
												<%} %>	
												</TD> --%>
												
											</TR>
									<%m++; }}catch(java.sql.SQLException e){}
								}%>	
																				
										</TABLE>
										
			
								</TABLE>
							</TD>
						</TR>
					</TABLE>	
					
				</fieldset>
			</div>
		</div>
		<br /><%geso.dms.center.util.Utility.JedisClose(jedis); %>
	</form>
</body>
</HTML>

<% try {
	
	obj.DBclose();
	
}catch(Exception e){}  %>

