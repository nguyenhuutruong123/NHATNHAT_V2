<%@page import="geso.dms.center.beans.duyettratrungbay.IDuyetAnhTrungBay"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.distributor.util.Utility"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	IDuyetAnhTrungBay obj = (IDuyetAnhTrungBay) session.getAttribute("obj");
	ResultSet npp = obj.getNpp();
	int solantt = obj.getSolantt();
	ResultSet vung = obj.getVung();
	ResultSet khuvuc = obj.getKv();
	ResultSet ddkd = obj.getDdkdRs();
	ResultSet cttbRs = obj.getSchemeRS();
	ResultSet anhtrungbayRs = obj.getAnhtrungbayRs();
	Utility Util = new Utility();
	int[] quyen = new  int[6];
	quyen = Util.Getquyen("DuyetAnhTrungBaySvl","",userId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery-1.js"></script>

<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

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
		
		/* if (document.getElementById("Sdays").value == "") 
		{
			alert("Vui lòng chọn ngày bắt đầu");
			return ;
		}
		if (document.getElementById("Edays").value == "")
		{
			alert("Vui lòng chọn ngày kết thúc");
			return ;
		} */	
		if (document.getElementById("cttbId").value == "") 
 		{
 			alert("Vui lòng chọn chương trình trưng bày");
 			return ;
 		}
	
		document.forms['frm'].action.value= 'searchbc';
		document.forms["frm"].submit();
	}
	function download() 
	{		
		if (document.getElementById("Sdays").value == "") 
		{
			alert("Vui lòng chọn ngày bắt đầu");
			return ;
		}
		if (document.getElementById("Edays").value == "")
		{
			alert("Vui lòng chọn ngày kết thúc");
			return ;
		}		
	
		document.forms['frm'].action.value= 'exceldownload';
		document.forms["frm"].submit();
	}
	
	function SaveDuyet(khId) 
	{

		var userId = document.getElementById("userId").value;
		var op= document.getElementById("cttbId");
		
		var cttbId = op.options[op.selectedIndex].value;

		console.log("userId = " + userId);
		
		var solantt = document.getElementById("solantt").value;
		var duyet = "";
		for(i = 0; i < solantt; i++){
			var check = document.getElementById(khId + "_DUYET_" + i).checked;
			duyet += check +",";
		}
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
				  	alert('Cập nhật thông tin thành công.');
				  
				} 
		    }
		};
		
		xmlhttp.open("POST","../../AjaxDuyetAnhTrungBay?userId="+userId+"&cttbId=" + cttbId + "&khId=" + khId + "&duyet=" + duyet, true);
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
.Text {
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
	filter: alpha(opacity = 80);
	-moz-opacity: 0.8;
	opacity: 0.8;
	/* comment the above 3 line if you don't want transparency*/
}
</style>
<script language="javascript" defer="false">
//browser detection
    var agt=navigator.userAgent.toLowerCase();
    var is_major = parseInt(navigator.appVersion);
    var is_minor = parseFloat(navigator.appVersion);

    var is_nav  = ((agt.indexOf('mozilla')!=-1) && (agt.indexOf('spoofer')==-1)
                && (agt.indexOf('compatible') == -1) && (agt.indexOf('opera')==-1)
                && (agt.indexOf('webtv')==-1) && (agt.indexOf('hotjava')==-1));
    var is_nav4 = (is_nav && (is_major == 4));
    var is_nav6 = (is_nav && (is_major == 5));
    var is_nav6up = (is_nav && (is_major >= 5));
    var is_ie     = ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));
</script>
<script language="JavaScript">
//tooltip Position
var offsetX = -165;
var offsetY = -165;
var opacity = 200;
var toolTipSTYLE;

function initToolTips(){
  if(document.getElementById){
          toolTipSTYLE = document.getElementById("toolTipLayer").style;
  }
  if(is_ie || is_nav6up)
  {
    toolTipSTYLE.visibility = "visible";
    toolTipSTYLE.display = "none";
    document.onmousemove = moveToMousePos;
  }
}
function moveToMousePos(e)
{
  if(!is_ie){
    x = e.pageX;
    y = e.pageY;
  }else{
    x = event.x + document.body.scrollLeft;
    y = event.y + document.body.scrollTop;
  }

  toolTipSTYLE.left = x + offsetX+'px';
  toolTipSTYLE.top = y + offsetY+'px';
  return true;
}


function toolTip(msg, fg, bg)
{
  if(toolTip.arguments.length < 1) // if no arguments are passed then hide the tootip
  {
    if(is_nav4)
        toolTipSTYLE.visibility = "hidden";
    else
        toolTipSTYLE.display = "none";
  }
  else // show
  {
    if(!fg) fg = "#777777";
    if(!bg) bg = "#ffffe5";
    var content = '<table border="0" cellspacing="0" cellpadding="0" class="toolTip"><tr><td bgcolor="' + fg + '">' +
                                  '<table border="0" cellspacing="1" cellpadding="0"<tr><td bgcolor="' + bg + '">'+
                                  '<font face="sans-serif" color="' + fg + '" size="-2">' + msg +
                                  '</font></td></tr></table>'+
                                  '</td></tr></table>';
   if(is_nav4)
    {
      toolTipSTYLE.document.write(content);
      toolTipSTYLE.document.close();
      toolTipSTYLE.visibility = "visible";
    }

    else if(is_ie || is_nav6up)
    {
      document.getElementById("toolTipLayer").innerHTML = content;
      toolTipSTYLE.display='block'
    }
  }
}


	function show(pos,data)
	{
		if(pos<3)
		{
			offsetX = -165;
			offsetY = 5;	
		}
		else
		{
			offsetX = -165;
			offsetY = -165;
		}
	    s = '<table width="100%" cellspacing="2" cellpadding="0" border="0">';
	    s += '<tr><td><img src="'+data+'" height="500" width="400" border="0" /> </td></tr>'; 
	  //  s += '<tr><td colspan="2" class="Text"><hr/>this is a test for simple tooltip. <br/>You can add text and images to the tooltip</td></tr>';
	    s += '</table>';
	    
	    toolTip(s);
	 	
	}

</script>
<BODY onload="initToolTips();" leftmargin="0" bottommargin="0"
	topmargin="0" rightmargin="0">
	<div id="toolTipLayer" style="position: absolute; visibility: hidden; left: 0; right: 0"></div>
	<form name="frm" method="post" action="../../DuyetAnhTrungBaySvl">
		<input	type="hidden" name="userId" id = "userId" value='<%=obj.getUserId()%>'>
		<input	type="hidden" name="action" value="">
		<input	type="hidden" id="solantt" value="<%=obj.getSolantt()%>">
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Quản lý trưng bày &#62; Báo cáo &#62; Báo cáo ảnh trưng bày</div>
				<div align="right" style="padding: 5px" class="tbnavigation"> <%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%></div>
			</div>

			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>

					<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" value="<%=session.getAttribute("errors")%>"
						name="errors" rows="1"
						style="width: 100%; color: #F00; font-weight: bold"></textarea>
				</fieldset>
			</div>

			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Báo cáo đơn hàng bán trong kỳ</legend>
					<div style="width: 100%; float: none" align="left"
						class="plainlabel">
						<TABLE width="70%" cellpadding="6" cellspacing="0">
							<%-- <TR>
								<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
								<TD class="plainlabel"><input type="text" name="Sdays"
									id="Sdays" class="days"
									value='<%=obj.getTungay().equals("") ? now : obj.gettungay()%>'
									onchange="xemtrenweb();" /></TD>
								<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
								<TD class="plainlabel"><input type="text" name="Edays"
									id="Edays" class="days"
									value='<%=obj.getDenngay().equals("") ? now : obj.getdenngay()%>'
									onchange="xemtrenweb();" /></TD>
							</TR> --%>

							<TR>

								<TD class="plainlabel">Miền</TD>
								<TD class="plainlabel"><select name="vungId" id="vungId"
									onchange="seach();" style="width: 300px">
										<option value="" selected>All</option>
										<%
											if (vung != null)
													while (vung.next()) {
														if (vung.getString("pk_seq").equals(obj.getVungId())) {
										%>
										<option value="<%=vung.getString("pk_seq")%>" selected><%=vung.getString("ten")%></option>
										<%
											} else {
										%>
										<option value="<%=vung.getString("pk_seq")%>"><%=vung.getString("ten")%></option>
										<%
											}
													}
										%>
								</select></TD>
								<TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TD>
								<TD class="plainlabel"><select name="nppId"
									onchange="seach();" id="nppId" style="width: 300px">
										<option value="" selected>All</option>
										<%
											if (npp != null)
													while (npp.next()) {
														if (npp.getString("pk_seq").equals(obj.getNppId())) {
										%>
										<option value="<%=npp.getString("pk_seq")%>" selected><%=npp.getString("ten")%></option>
										<%
											} else {
										%>
										<option value="<%=npp.getString("pk_seq")%>"><%=npp.getString("ten")%></option>
										<%
											}
													}
										%>
								</select></TD>

							</TR>
				
							<TR>


								<TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %></TD>
								<TD class="plainlabel"><select name="khuvucId"
									id="khuvucId" onchange="seach();" style="width: 300px">
										<option value="" selected>All</option>
										<%
											if (khuvuc != null)
												while (khuvuc.next()) {
													if (khuvuc.getString("pk_seq").equals(obj.getKvId())) {
										%>
										<option value="<%=khuvuc.getString("pk_seq")%>" selected><%=khuvuc.getString("diengiai")%></option>
										<%
											} else {
										%>
										<option value="<%=khuvuc.getString("pk_seq")%>"><%=khuvuc.getString("diengiai")%></option>
										<%
											}
												}
										%>
								</select></TD>
								<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
								<TD class="plainlabel"><select name="ddkdId" id="ddkdId"
									onchange="seach();" style="width: 300px">
										<option value="" selected>All</option>
										<%
											if (ddkd != null)
												while (ddkd.next()) {
													if (ddkd.getString("pk_seq").equals(obj.getDdkdId())) {
										%>
										<option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
										<%
											} else {
										%>
										<option value="<%=ddkd.getString("pk_seq")%>"><%=ddkd.getString("ten")%></option>
										<%
											}
												}
										%>
								</select></TD>

							</TR>

							<TR>
								<TD class="plainlabel">Chương trình trưng bày</TD>
								<TD class="plainlabel" colspan="3"><select name="cttbId" id="cttbId" onchange="seach();" style="width: 300px">
										<option value="" selected>All</option>
										<%
											if (cttbRs != null)
												while (cttbRs.next()) {
													if (cttbRs.getString("pk_seq").equals(obj.getSchemeId())) {
										%>
										<option value="<%=cttbRs.getString("pk_seq")%>" selected><%=cttbRs.getString("SCHEME")%></option>
										<%
											} else {
										%>
										<option value="<%=cttbRs.getString("pk_seq")%>"><%=cttbRs.getString("SCHEME")%></option>
										<%
											}
									}
										%>
								</select></TD>
							</TR>

							<TR>
								<td colspan="4"><a class="button"
									href="javascript:submitform();"> <img style="top: -4px;"
										src="../images/button.png" alt=""> Lọc dữ liệu </a> 
								</td>
							</TR>


						</TABLE>
					</div>

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="2">
						<TR>
							<TD width="100%">
								<TABLE class="" width="100%">
									<TR>
										<TD width="98%">
											<TABLE width="100%" border="0" cellspacing="1"
												cellpadding="4">
												<TR class="tbheader">
													<TH width="15%" align="center">CTTB</TH>
													<TH width="15%" align="center">Nhà phân phối</TH>
													<TH width="15%" align="center"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TH>
													<TH width="5%" align="center">Mã KH</TH>
													<TH width="15%" align="center">Khách hàng</TH>
													<TH width="5%" align="center">Ảnh</TH>
												</TR>

												<%
													int m = 0;
													String lightrow = "tblightrow";
													String darkrow = "tbdarkrow";
													if (anhtrungbayRs != null) {
												%>
												<%
													try {
															while (anhtrungbayRs.next()) {
																String nut = "#nutmopopup" + Integer.toString(m + 1);
																String nut2 = "#nutmopopup" + Integer.toString(m + 2);
																String popup = "#noidungpopup"	+ Integer.toString(m + 1);
																
																String popup2 = "#noidungpopup"+ Integer.toString(m + 2);
																
																String nut_id = "nutmopopup" + Integer.toString(m + 1);
																String nut_id2 = "nutmopopup" + Integer.toString(m + 2);
																String popup_id = "noidungpopup"+ Integer.toString(m + 1);
																String popup_id2 = "noidungpopup"+ Integer.toString(m + 2);

																if (m % 2 != 0) {
												%>
												<TR class=<%=lightrow%>>
													<%
														} else {
													%>
												
												<TR class=<%=darkrow%>>
													<%
														}
													%>

													<TD align="center"><%=anhtrungbayRs.getString("SCHEME")%></TD>
													<TD align="center"><%=anhtrungbayRs.getString("NPP")%></TD>
													<TD align="center"><%=anhtrungbayRs.getString("DDKD")%></TD>
													<TD align="center"><%=anhtrungbayRs.getString("MAFAST")%></TD>
													<TD align="center"><%=anhtrungbayRs.getString("TEN")%></TD>

													<TD align="center">
													<TABLE border = 0 cellpadding="0" cellspacing="0">
														<TR>
														<TD>																
																<!-- Noi nay chua anh va nut DONG -->
																<a id="<%=nut_id%>"   href="#">
																	<img src="../images/Display20.png" width="20" height="20" alt="Xem" ; >
																	
																	<script>
																		$(document).ready(function()
																		{
																			$("<%=nut%>").colorbox({width:"100%", inline:true, href:"<%=popup%>", top: "50"});        	
																		});
																		
																		
																		$(document).ready(function(){
																		
																			$("<%=nut%>").colorbox({rel:'group4', slideshow:false});
																		});
																		
																		
																		
																		
																	 </script>
																</a>
																<!-- Khung Colorbox tam thoi an di -->
																<div style='display:none'>
																	<div id="<%=popup_id%>" style='padding:0px 5px; background:#fff;'>
																		<table   cellpadding="4px" cellspacing="2px" width="100%" align="center" >
																			<%String anh = anhtrungbayRs.getString("ANH");
																			String duyet = anhtrungbayRs.getString("DUYET");
																			String[] anhs = new String[0];
																			if(anh != null)
																				anhs = anh.split(",");
																			String[] duyets = new String[0];
																			if(duyet != null)
																				duyets = duyet.split(",");
																			%>
																			
																			<tr >
																				
																				<TD align="left" colspan="8"> Mã: <%=anhtrungbayRs.getString("MAFAST")%> - Tên: <%=anhtrungbayRs.getString("TEN")%></TD>
																	
																			</TR>
																			<TR>
																			<%if(anh != null){ 
																				for(int i=0; i< anhs.length; i++){
																			%>
																				<td >
																					<p>
																						<img style="top: -4px; max-width: 400px; max-height: 400px;" src="<%= getServletContext().getInitParameter("pathhinhJSP")  +"/AnhTrungBay/"+ anhs[i].trim().split("-")[0] %>" >																																							
																					</p>
																					<p><%=anhs[i].trim().split("-")[1]%></p>
																				</td>
																				
																				<%} %>
																			<%} %>
																			</tr>
																			<%if(quyen[Utility.CHOT] != 0){ %>
																			<TR>
																				<td colspan="8">
																					<fieldset>
																						<legend>Duyệt ảnh trưng bày</legend>
																						<table>
																						<%for(int i=0; i<solantt; i++){ %>
																							<tr>
																								<td>Duyệt lần <%=i+1 %></td>
																								<td><input type="checkbox" id="<%=anhtrungbayRs.getString("PK_SEQ")+ "_DUYET_" + i%>" <%if(duyets.length >0 && duyets[i].trim().split("-")[0].equals("1")){ %> checked="checked"<%} %> <%if(duyets.length >0 && duyets[i].trim().split("-")[1].equals("1")){ %> onclick="return false;"<%} %>></td>
																							</tr>
																						<%} %>
																							<TR>
																								<td></td>
																								<TD colspan="1">
																									<a class="button" href="javascript:SaveDuyet(<%= anhtrungbayRs.getString("PK_SEQ") %>);">
				        																				<img style="top: -4px;" src="../images/button.png" alt="">Lưu duyệt</a>
																								</TD>
																							</TR>
																						</table>
																						
																					</fieldset>
																				</td>
																			</TR>
																			<%} %>
																		</table>
																	</div>
																</div>
																
														</TD>
														</TR>
													</TABLE>
												</TD>

												</TR>
												<%
													m++;
															}
														} catch (java.sql.SQLException e) {
														}
													}
												%>

											</TABLE>
								</TABLE></TD>
						</TR>
					</TABLE>

				</fieldset>
			</div>
		</div>
		<br />
	</form>
</body>
</HTML>