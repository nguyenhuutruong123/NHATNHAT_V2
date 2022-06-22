<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="geso.dms.distributor.beans.buttoantonghop.imp.*"%>
<%@ page import="geso.dms.distributor.beans.buttoantonghop.*"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="geso.dms.center.util.*"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Hashtable"%>

<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if (!util.check(userId, userTen, sum)) {
		response.sendRedirect("/TraphacoERP/index.jsp");
	} else {
%>

<%
	System.out.println("");
		NumberFormat formatter = new DecimalFormat("#,###,###");
		IErpButToanTongHop btthBean = (IErpButToanTongHop) session
				.getAttribute("btthBean");
		int count = btthBean.getCount();
		System.out.println("Count: " + count);
	
		String[] TaiKhoanNoIds = btthBean.getTaiKhoanNoIds();
		String[] nghiepvuketoanIds = btthBean.getNghiepvuketoanIds();
		String[] TaiKhoanCoIds = btthBean.getTaiKhoanCoIds();
		
		String[] DoiTuongNoIds = btthBean.getDoiTuongNoIds();
		String[] DoiTuongCoIds = btthBean.getDoiTuongCoIds();
		
		String[] loaiDoiTuongNo = btthBean.getDoituongNo();
		String[] loaiDoiTuongCo = btthBean.getDoituongCo();
		
		
		String[] loaiYeuToNo = btthBean.getYeuToNo();
		String[] loaiYeuToCo = btthBean.getYeuToCo();
		
		String[] YeuToNoIds = btthBean.getYeuToNoIds();
		String[] YeuToCoIds = btthBean.getYeuToCoIds();
		
		String[] Sotien = btthBean.getSotien();
		if (Sotien == null) {
			Sotien = new String[count];
			for (int i = 0; i < count; i++) {
				Sotien[i] = "";
			}
		}
		
		String[] dg = btthBean.getDg();
		if (dg == null) {
			dg = new String[count];
			for (int i = 0; i < count; i++) {
				dg[i] = "";
			}
		}

		

		ResultSet nghiepvuketoanRs = btthBean.getNghiepvuketoanRs();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE>TraphacoERP - Project</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../scripts/dropdowncontent2.js"></script>

<style type="text/css">
#mainContainer {
	width: 660px;
	margin: 0 auto;
	text-align: left;
	height: 100%;
	border-left: 3px double #000;
	border-right: 3px double #000;
}

#formContent {
	padding: 5px;
}
/* END CSS ONLY NEEDED IN DEMO */

/* Big box with list of options */
#ajax_listOfOptions {
	position: absolute; /* Never change this one */
	width: auto; /* Width of box */
	height: 200px; /* Height of box */
	overflow: auto; /* Scrolling features */
	border: 1px solid #317082; /* Dark green border */
	background-color: #C5E8CD; /* White background color */
	color: black;
	text-align: left;
	font-size: 1.0em;
	z-index: 100;
}

#ajax_listOfOptions div {
	/* General rule for both .optionDiv and .optionDivSelected */
	margin: 1px;
	padding: 1px;
	cursor: pointer;
	font-size: 1.0em;
}

#ajax_listOfOptions .optionDiv { /* Div for each item in list */
	
}

#ajax_listOfOptions .optionDivSelected { /* Selected item in the list */
	background-color: #317082; /*mau khi di chuyen */
	color: #FFF;
}

#ajax_listOfOptions_iframe {
	background-color: #F00;
	position: absolute;
	z-index: 5;
}

form {
	display: inline;
}

#dhtmltooltip {
	position: absolute;
	left: -300px;
	width: 150px;
	border: 1px solid black;
	padding: 2px;
	background-color: lightyellow;
	visibility: hidden;
	z-index: 100;
	/*Remove below line to remove shadow. Below line should always appear last within this CSS*/
	filter: progid : DXImageTransform.Microsoft.Shadow ( color = gray,
		direction = 135 );
}

#dhtmlpointer {
	position: absolute;
	left: -300px;
	z-index: 101;
	visibility: hidden;
}

}
.mySCHME tr,td input {
	color: red;
}

.mySCHME input {
	color: red;
}
</style>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script src="../scripts/ui/jquery.scrollTableBody-1.0.0.js"
	type="text/javascript"></script>
<script type="text/javascript" src="../scripts/erp-spList.js"></script>

<script type="text/javascript" src="../scripts/ajax.js"></script>

<script type="text/javascript">
	$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		}); 
		
		

		<%if( btthBean.getScrollTop() != 0 ){%>		
		$('html, body').animate({scrollTop: <%=  btthBean.getScrollTop()   %>}, "fast");			
		<%}%>
		
	});	
</script>

<script language="JavaScript" type="text/javascript">
	function submitform()
	{
	    document.forms["btth"].submit();
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
			if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
			{//Phím Delete và Phím Back
				return;
			}
			return false;
		}
	}
	
	
	
	function save()
	{	
		 if(document.getElementById("NgayButToan").value == "")
		 {
			 alert('Vui lòng chọn ngày bút toán');
			 return;
		 }
		 if(document.getElementById("DienGiai").value == "")
		 {
			 alert('Vui lòng nhập diễn giải');
			 return;
		 }		
		 
		 
	  document.forms["btth"].action.value = "save";
	  document.forms["btth"].submit(); 
    }
	
	


	function Dinhdang(stt){
		var sotien = document.getElementById("Sotien_" + stt);
		sotien.value = DinhDangTien(sotien.value);
	}
	
	function DinhDangTien(num) 
	 {
	    num = num.toString().replace(/\$|\,/g,'');
	    if(isNaN(num))
	    num = "0";
	    sign = (num == (num = Math.abs(num)));
	    num = Math.floor(num*100+0.50000000001);
	    num = Math.floor(num/100).toString();
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	    num = num.substring(0,num.length-(4*i+3))+','+
	    num.substring(num.length-(4*i+3));
	    return (((sign)?'':'-') + num);
	}

	 
	function roundNumber(num, dec) 
	{
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	}

	function goBack()
 	{
  		window.history.back();
 	}
	
	
	function TinhTien()
	{
		
		
		var Sotien = document.getElementsByName("Sotien");

		var tongtien = 0;
		for(var i = 0; i < Sotien.length; i++)
		{
			
			if(Sotien.item(i).value != '')
			{
				var So_tien = Sotien.item(i).value.replace(/,/g, "");
	
				if(!isNaN(So_tien))
					tongtien = tongtien + parseFloat(So_tien) ;
			
			}
		}
		
		tongtien = Math.round( tongtien );
		document.getElementById("TongTienVND").value = DinhDangTien(tongtien);
		setTimeout(TinhTien, 300);
		
	}
	function ChangeAllText()
	{
		
		
		
		var DienGiaiTong = document.getElementById("DienGiai").value;
		var dgDetail = document.getElementsByName("dg");
		var TaiKhoanNoIds = document.getElementsByName("TaiKhoanNoIds");
		for(var i = 0; i < dgDetail.length; i++ )
		{
			if(TaiKhoanNoIds.item(i).value != '')
				dgDetail.item(i).value = DienGiaiTong;
		}
	}
	
	function submitNghiepVuform(i)
	{
		
		var nghiepvuketoanId =  $("#nghiepvuketoanIds_"+i);
		var divPosition = nghiepvuketoanId.offset();	
		document.forms["btth"].scrollTop.value =divPosition.top ;
		
		var DienGiaiTong = document.getElementById("DienGiai").value;
		
		var dgDetail =  document.getElementById("Diengiai_"+i).value;  
		if( dgDetail == '' )
			document.getElementById("Diengiai_"+i).value = DienGiaiTong;
		document.forms["btth"].submit();
	}
	
	
</SCRIPT>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>


$(document).ready(function() { $("select").select2(); });

/* function formatRepo (repo) {
    if (repo.loading) return repo.text;

    var markup = '<div class="clearfix">' +
    '<div class="col-sm-1">' +
    '<img src="' + repo.owner.avatar_url + '" style="max-width: 100%" />' +
    '</div>' +
    '<div clas="col-sm-10">' +
    '<div class="clearfix">' +
    '<div class="col-sm-6">' + repo.full_name + '</div>' +
    '<div class="col-sm-3"><i class="fa fa-code-fork"></i> ' + repo.forks_count + '</div>' +
    '<div class="col-sm-2"><i class="fa fa-star"></i> ' + repo.stargazers_count + '</div>' +
    '</div>';

    if (repo.description) {
      markup += '<div>' + repo.description + '</div>';
    }

    markup += '</div></div>';

    return markup;
  }

  function formatRepoSelection (repo) {
    return repo.full_name || repo.text;
  }

$(document).ready(function(){

    $(".js-data-example-ajax").select2({
      ajax: {
        url: "https://api.github.com/search/repositories",
        dataType: 'json',
        delay: 250,
        data: function (params) {
          return {
            q: params.term, // search term
            page: params.page
          };
        },
        processResults: function (data, page) {
          // parse the results into the format expected by Select2.
          // since we are using custom formatting functions we do not need to
          // alter the remote JSON data
          return {
            results: data.items  
          };
        },
        cache: true
      },
      escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
      minimumInputLength: 1,
      templateResult: formatRepo, // omitted for brevity, see the source of this page
       templateSelection: formatRepoSelection // omitted for brevity, see the source of this page  
        
    });
  });    */
     
     
	
     
</script>




</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0"
	onload="TinhTien()">
	<form name="btth" method="post"
		action="../../ErpButToanTongHopUpdateSvl">
		<input type="hidden" name="scrollTop" value='0'>
		<input type="hidden" name="userId" value='<%=userId%>'> 
		<input type="hidden" name="id" value='<%= btthBean.getId() %>' >
		<input type="hidden" name="view" value='<%=btthBean.getView()%>'> 
		<input type="hidden" name="action" value="0">
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<TR>
							<TD align="left" class="tbnavigation">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản
											lý kế toán &gt; Bút toán tổng hợp &gt; Cập nhật</TD>
										<TD colspan="2" align="right" class="tbnavigation">Chào
											mừng bạn <%=userTen%></TD>
									</tr>
								</table></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<TR>
							<TD>
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<TR class="tbdarkrow">
										<TD width="30" align="left"><A
											href="javascript:goBack();"><img
												src="../images/Back30.png" alt="Quay ve" title="Quay ve"
												border="1" longdesc="Quay ve" style="border-style: outset">
										</A>
										</TD>
										<TD width="2" align="left"></TD>
										<TD width="30" align="left">
											<div id="btnSave">
												<A href="javascript: save()"><IMG
													src="../images/Save30.png" title="Luu lai" alt="Luu lai"
													border="1" style="border-style: outset">
												</A>
											</div></TD>
										<TD>&nbsp;</TD>
									</TR>
								</TABLE></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
									<LEGEND class="legendtitle">Thông báo </LEGEND>

									<textarea name="dataerror"
										style="width: 100%; font-weight: bold"
										style="width: 100% ; color:#ffffff ; font-weight:bold"
										readonly="readonly" rows="1"><%=btthBean.getMsg()%></textarea>
								</FIELDSET></TD>
						</tr>

						<TR>
							<TD height="100%" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle" style="color: black">Bút
										toán tổng hợp </LEGEND>
									<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
										<TR>
											<TD class="plainlabel" valign="middle" width="120px">Ngày
												bút toán</TD>
											<TD class="plainlabel" valign="middle"><input
												type="text" class="days" name="NgayButToan" id="NgayButToan"
												value="<%=btthBean.getNgayButToan()%>"></TD>
										</TR>
										<TR>
											<TD class="plainlabel" valign="middle" width="120px">Diễn
												giải</TD>
											<TD class="plainlabel" valign="middle"><input
												type="text" name="DienGiai" id="DienGiai"
												value="<%=btthBean.getDienGiai()%>" onchange="ChangeAllText()" ></TD>
										</TR>
										<TR>
										<TD class="plainlabel" valign="middle" width="120px">Tổng tiền VNĐ</TD>
											<TD class="plainlabel" valign="middle" >
												<input readonly="readonly" type="text" name="TongTienVND" id="TongTienVND"
													value="">
											</TD>
										</TR>

									</TABLE>

									<table cellpadding="0px" cellspacing="1px" width="100%">
										<tr class="tbheader">
											<th align="center" width="8%" >Nghiệp vụ</th>
											<th align="center" width="8%">TK Nợ</th>
											<th align="center" width="8%">ĐT Nợ</th>
											<th align="center" width="5%">YT nợ</th>
											<th align="center" width="8%">TK Có</th>
											<th align="center" width="8%">ĐT Có</th>
											<th align="center" width="5%">YT có</th>
											<th align="center" width="25%"><%=Utility.GLanguage("Diễn giải",session,jedis) %></th>
											<th align="center" width="15%">Số tiền</th>
										</tr>
										<%
											int stt = count;
												int pk_seq = 0;
												String dungchoNo = "";
												String dungchoNoSelected = "";
												String dungchoCo = "";
												String dungchoCoSelected = "";
												for (int i = 0; i < count; i++) {

													if (nghiepvuketoanIds != null
															&& nghiepvuketoanIds[i].trim().length() > 0) {
														stt++;
										%>
										<TR>
											<TD align="center"><input style="text-align: right; width: 100px;" readonly="readonly" type="hidden" name="pk_seq" id="<%=pk_seq%>" value='<%=pk_seq%>' /> 
												<select style="width: 150px" name="nghiepvuketoanIds" id="nghiepvuketoanIds_<%=i%>" onchange="submitNghiepVuform('<%=i%>');">
													<option value=" "></option>
													<%
														nghiepvuketoanRs.beforeFirst();
														while (nghiepvuketoanRs.next()) {
											
														if (nghiepvuketoanRs.getString("ma").equals(nghiepvuketoanIds[i])) {
													%>
															<option selected="selected"value="<%=nghiepvuketoanRs.getString("ma")%>"><%=nghiepvuketoanRs.getString("ma")%>--<%=nghiepvuketoanRs.getString("ten")%>
															</option>
													<%
														} else {
													%>
																<option value="<%=nghiepvuketoanRs.getString("ma")%>"><%=nghiepvuketoanRs.getString("ma")%>--<%=nghiepvuketoanRs.getString("ten")%></option>
													<%
														}}
													%>
											</select></TD>
											
											
											<TD align="center">
													<input style="text-align: left; width: 90%;" type="text"name="TaiKhoanNoIds" id="TaiKhoanNoIds_<%=i%>"
													value="<%=TaiKhoanNoIds[i]%>" readonly />
											</TD>
											

											<TD align="center"> 
												<select style="width: 150px" name="doituongnoIds" id="doituongnoIds_<%=i%>">
													<option value=""></option>	
																									
													<%
													try
													{
													System.out.println("DoiTuongNoIds["+i+"] = " + DoiTuongNoIds[i] +",loaiDoiTuongNo["+i+"]"+ loaiDoiTuongNo[i]);
														if(loaiDoiTuongNo[i].trim().length() > 0){
														ResultSet rs = btthBean.getDoituongMap().get(loaiDoiTuongNo[i]);
														
														System.out.println("JSP tmp.containsKey(KHO) = "+ btthBean.getDoituongMap().containsKey("KHO") +", rs == null" + (rs == null));
														if(rs!= null)
														{	rs.beforeFirst();
														while (rs.next()) 
														{
															if (DoiTuongNoIds[i]!= null &&  rs.getString("ma").equals(DoiTuongNoIds[i])) {
													%>
																<option selected="selected"
																	value="<%=rs.getString("ma")%>"><%=rs.getString("ma")%>--<%=rs.getString("ten")%>
																</option>
													<%
															} else {
													%>
																<option value="<%=rs.getString("ma")%>"><%=rs.getString("ma")%>--<%=rs.getString("ten")%>
																</option>
													<%
														}}}}}catch(Exception e)
														{	System.out.println("messs" + e.getMessage());
															e.printStackTrace();}
													%>
												</select>																				
											</TD>
											<TD align="center"> 
												<select style="width: 70px"  name="yeutonoIds" id="yeutonoIds_<%=i%>">
													<option value=""></option>													
													<%if(loaiYeuToNo[i].trim().length() > 0){
														ResultSet rs = btthBean.getNhomhangRs();
														rs.beforeFirst();
														while (rs.next()) 
														{
															if (YeuToNoIds[i]!= null &&  rs.getString("ma").equals(YeuToNoIds[i])) {
													%>
																<option selected="selected"
																	value="<%=rs.getString("ma")%>"><%=rs.getString("ma")%>--<%=rs.getString("ten")%>
																</option>
													<%
															} else {
													%>
																<option value="<%=rs.getString("ma")%>"><%=rs.getString("ma")%>--<%=rs.getString("ten")%>
																</option>
													<%
														}}}
													%>
												</select>																				
											</TD>
											<TD align="center"><input style="text-align: left; width: 90%;" type="text" name="TaiKhoanCoIds" id="TaiKhoanCoIds_<%=i%>"
													value="<%=TaiKhoanCoIds[i]%>" readonly />
											</TD>
											<TD align="center"> 
												
												<select style="width: 150px" name="doituongcoIds" id="doituongcoIds_<%=i%>"
												>
													<option value=""></option>													
													<%
													try
													{
													if(loaiDoiTuongCo[i].trim().length() > 0){
													
														
														ResultSet rs2 = btthBean.getDoituongMap().get(loaiDoiTuongCo[i]);
														rs2.beforeFirst();
																	while (rs2.next()) 
																	{
																		if (DoiTuongCoIds[i]!= null &&  rs2.getString("ma").equals(DoiTuongCoIds[i])) {
													%>
													<option selected="selected"
														value="<%=rs2.getString("ma")%>"><%=rs2.getString("ma")%>--<%=rs2.getString("ten")%>
													</option>
													<%
														} else {
													%>
													<option value="<%=rs2.getString("ma")%>"><%=rs2.getString("ma")%>--<%=rs2.getString("ten")%>
													</option>
													<%
														}
													%>

													<%
														}}}catch(Exception e){System.out.println("messs" + e.getMessage());e.printStackTrace();}
													%>
											</select>
											
											
											</TD>
											
											<TD align="center"> 
												<select style="width: 70px"  name="yeutocoIds" id="yeutocoIds_<%=i%>">
													<option value=""></option>													
													<%if(loaiYeuToCo[i].trim().length() > 0){
														ResultSet rs = btthBean.getNhomhangRs();
														rs.beforeFirst();
														while (rs.next()) 
														{
															if (YeuToCoIds[i]!= null &&  rs.getString("ma").equals(YeuToCoIds[i])) {
													%>
																<option selected="selected"
																	value="<%=rs.getString("ma")%>"><%=rs.getString("ma")%>--<%=rs.getString("ten")%>
																</option>
													<%
															} else {
													%>
																<option value="<%=rs.getString("ma")%>"><%=rs.getString("ma")%>--<%=rs.getString("ten")%>
																</option>
													<%
														}}}
													%>
												</select>																				
											</TD>
											
											<TD align="center"><input style="text-align: left;width: 90%;" type="text" name="dg" id="Diengiai_<%=i%>"
												value='<%=dg[i]%>' />
											</TD>

											<TD align="center"><input style="text-align: right; width: 90%;" type="text"
												name="Sotien" id="Sotien_<%=i%>"
												value='<%=Sotien[i] == null ? "0" : formatter.format(Double.parseDouble(Sotien[i].replaceAll(",", "")))%>'onkeypress="return keypress(event);"onChange="Dinhdang(<%=i%>);" />
											</TD>



											

										

										</TR>
										<%
											pk_seq++;
													}

												} // KẾT THÚC VÒNG LẶP FOR HIỆN RA CÁC DÒNG ĐÃ NHẬP
										%>


										<%
											int bien = stt + 1;
												for (int i = stt; i < bien; i++) {
										%>
										<TR>
											<%
												stt++;
											%>
											
											<TD>
											<select style="width: 150px" name="nghiepvuketoanIds" id="nghiepvuketoanIds_<%=i%>"
												onchange="submitNghiepVuform('<%=i%>');">
													<option value=" "></option>
													<%
														nghiepvuketoanRs.beforeFirst();
																while (nghiepvuketoanRs.next()) {
													%>

													<option value="<%=nghiepvuketoanRs.getString("ma")%>">
														<%=nghiepvuketoanRs.getString("ma")%>
														--
														<%=nghiepvuketoanRs.getString("ten")%>
													</option>
													<%
														}
													%>
											</select></TD>

											<TD align="center"><input
												style="text-align: left; width: 90%;" type="text"
												name="TaiKhoanNoIds" id="TaiKhoanNoIds_<%=i%>"
												value="" readonly /></TD>
											<TD align="center"><input
												style="text-align: left; width: 90%;" type="text"
												name="TaiKhoanCoIds" id="TaiKhoanCoIds_<%=i%>"
												value="" readonly /></TD>

											<TD align="center"><input style="text-align: left; width: 90%;" type="text"name="doituongnoIds" id="doituongnoIds_<%=i%>"value="" readonly /></TD>
											<TD align="center"><input style="text-align: left; width: 90%;" type="text"name="doituongcoIds" id="doituongcoIds_<%=i%>"value="" readonly /></TD>
												
											<TD align="center"><input style="text-align: left; width: 90%;" type="text"name="yeutonoIds" id="yeutonoIds_<%=i%>"value="" readonly /></TD>
											<TD align="center"><input style="text-align: left; width: 90%;" type="text"name="yeutocoIds" id="yeutocoIds_<%=i%>"value="" readonly /></TD>		
													
											<TD align="center"><input style="text-align: right; width: 90%;"type="text" name="dg" id="Diengiai_<%=i%>"value='' /></TD>	
											<TD align="center"><input style="text-align: right; width: 90%;" type="text" name="Sotien" id="Sotien_<%=i%>"value='' readonly="readonly" /></TD>
											
											

										</TR>
										<%
											pk_seq++;
												}
										%>


									</TABLE>

								</FIELDSET></TD>
						</TR>
					</TABLE></TD>
			</TR>
		</TABLE>
	</form>

</BODY>
</HTML>


<%
	
		btthBean.DBClose();
		session.setAttribute("btthBean", null);

	}
%>
