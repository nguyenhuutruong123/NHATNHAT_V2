<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.tuyenbanhang.ITuyenbanhang" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% ITuyenbanhang tbhBean = (ITuyenbanhang)session.getAttribute("tbhBean"); %>
<% ResultSet ddkd = (ResultSet)tbhBean.getDaidienkd();  %>

<% ResultSet kh_tbh_dpt = (ResultSet)tbhBean.getKh_Tbh_DptList(); %>
<% ResultSet kh_tbh_cdpt = (ResultSet)tbhBean.getKh_Tbh_CdptList();  %>

<% ArrayList arr=tbhBean.html_tanso(); %>

<% Hashtable<Integer, String> kh_tbh_dptIds = (Hashtable<Integer, String>)tbhBean.getKh_Tbh_DptIds(); %>
<% Hashtable<Integer, String> kh_tbh_cdptIds = (Hashtable<Integer, String>)tbhBean.getKh_Tbh_CdptIds(); %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<!-- <script src="jquery.min.js"></script> -->
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<style type="text/css">
	.plainlabelNew {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 10pt;
	color: #000000;
	line-height: 15pt;
}
.plainlabelNew2 {
	display: none;
}
</style>
<SCRIPT language="javascript" type="text/javascript">
 function confirmLogout()
 {
    if(confirm("Bạn có muốn đăng xuất?"))
    {
		top.location.href = "home.jsp";
    }
    return
  }
 function submitform()
 { 
	var tbh_khdpt = document.getElementsByName("kh_tbh_dptList");
	var tbh_khcdpt = document.getElementsByName("kh_tbh_cdptList");
	var tsList = document.getElementsByName("tansoList");
	var i;
	for(i=0; i < tbh_khdpt.length; i++)
	{
		if(tbh_khdpt.item(i).checked == false)
			tbh_khdpt.item(i).checked = true; //de tao lai 1 Resual Set luu danh sach dang duoc chon
	}
	for(i=0; i < tbh_khcdpt.length; i++)
	{
		if(tbh_khcdpt.item(i).checked == false)
			tbh_khcdpt.item(i).checked = true;
	}
	
	document.forms["tbhForm"].submit();	
 }

	 function chuyentuyen()
	 {
		var ddkdTen = document.getElementById("ddkdTen");
		var tbhTen = document.getElementById("tbhTen");
		var nlv = document.getElementById("nlv");
		if(ddkdTen.value == "")
		{
			alert("Vui lòng chọn nhân viên bán hàng của tuyến bán hàng này...");
			return;
		}
		if(tbhTen.value == "")
		{
			alert("Vui lòng nhập diễn giải cho tuyến bán hàng này ...");
			return;
		}
		if(nlv.value == "")
		{
			alert("Vui lòng chọn ngày làm việc của tuyến...");
			return;
		}
		
	 	document.forms['tbhForm'].action.value='sangtrai';
	    document.forms['tbhForm'].submit();
	 
	 
 }
 function saveform()
 {
	var tbh_khdpt = document.getElementsByName("kh_tbh_dptList");
	var tbh_khcdpt = document.getElementsByName("kh_tbh_cdptList");
	var tsList = document.getElementsByName("tansoList");
	var ddkdTen = document.getElementById("ddkdTen");
	var tbhTen = document.getElementById("tbhTen");
	var nlv = document.getElementById("nlv");
	var i;
	for(i=0; i < tbh_khdpt.length; i++)
	{
		if(tsList.item(i).value == "")
		{
			alert("Vui lòng thiết lập tần số ghé thăm cho các khách hàng được phân tuyến");
			return;
		}
		if(tbh_khdpt.item(i).checked == false)
			tbh_khdpt.item(i).checked = true;
	}
	for(i=0; i < tbh_khcdpt.length; i++)
	{
		if(tbh_khcdpt.item(i).checked == false)
			tbh_khcdpt.item(i).checked = true;
	}
	if(ddkdTen.value == "")
	{
		alert("Vui lòng chọn nhân viên bán hàng của tuyến bán hàng này...");
		return;
	}
	if(tbhTen.value == "")
	{
		alert("Vui lòng nhập diễn giải cho tuyến bán hàng này ...");
		return;
	}
	if(nlv.value == "")
	{
		alert("Vui lòng chọn ngày làm việc của tuyến...");
		return;
	}
	
	
	document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";

	
 	document.forms['tbhForm'].action.value='save';
    document.forms['tbhForm'].submit();
 }
 //Phat_a.tuan
 function checkedAll(doituong, e) {
		$("input[name='"+doituong+"']").each(function() {
			this.checked = e.checked;
		}); 
	}
 function check_each(doituong){
		var count = 0;
		$("input[name='"+doituong+"']").each(function() {
			if (this.checked == true)
				count++;
		}); 
		$("#chontatca_"+doituong).show();
		if (count == $("input[name='"+doituong+"']").length)
			$("#chontatca_"+doituong).prop("checked", "checked");
		else $("#chontatca_"+doituong).removeProp("checked");
	}
 /*function checkedAll(chk,chontatca) 
 {
	console.log('size '+ $(''+chk).length);
	console.log('length '+chk.length);
	for(var i=0; i<chk.length; i++){
	 if(chontatca.checked==true){
			chk[i].checked = true;
		}else{
			chk[i].checked = false;
		}
	 }
 }*/
 </SCRIPT>
 <script language="javascript" type="text/javascript">
	function addRow(name, stt, makh, smartid, mafast , tenkh, diachi)     // ----- them vao khach hang phan tuyen ---//
	{  
		tableName = document.getElementById(name);
		var tr = document.createElement("TR");
		var sttAdd = document.createElement("TD");
		var makhAdd = document.createElement("TD");
		var smartidAdd = document.createElement("TD");
		var mafastAdd = document.createElement("TD");
		var tenkhAdd = document.createElement("TD");
		var diachiAdd = document.createElement("TD");
		var tansoAdd = document.createElement("TD");
		var checkAdd = document.createElement("TD");
		
		var txt = document.createElement("input");
		txt.setAttribute("type", "textbox");
		txt.setAttribute("name", "thutu");
		//txt.setAttribute("value", stt);
		txt.value = stt;
		txt.size = 5;//txt.size = 5;
		
		//sttAdd.innerHTML = stt;
		sttAdd.appendChild(txt);
		
		makhAdd.setAttribute("class","plainlabelNew2");
		makhAdd.innerHTML = makh;
		
		smartidAdd.setAttribute("class","plainlabelNew");
		smartidAdd.innerHTML = smartid;
		
		mafastAdd.setAttribute("class","plainlabelNew");
		mafastAdd.innerHTML = mafast;
		//alert(smartid);
		
		tenkhAdd.setAttribute("class","plainlabelNew");
		tenkhAdd.innerHTML = tenkh;
		//alert(tenkh);
		
		diachiAdd.setAttribute("class","plainlabelNew");
		diachiAdd.innerHTML = diachi;
		//alert('diachi la: ' + diachi);
		
		
		var selectBox = document.createElement("select");
		selectBox.name = "tansoList";
		selectBox.setAttribute("style", "width:130px");
		var option ;
		<% 
		
		for(int i = 0 ;  i< arr.size() ; i ++) { %>
			option = document.createElement('option');
			option.value = '<%=arr.get(i)%>';
			option.appendChild(document.createTextNode('<%=arr.get(i)%>'));
			 selectBox.appendChild(option);
	
	    <%}%>
		tansoAdd.appendChild(selectBox);
		
		var checkbox = document.createElement("input");
		checkbox.setAttribute("type", "checkbox");
		checkbox.name = "kh_tbh_dptList";
		checkbox.value = makh + ";" + smartid + ";" + mafast + ";" + tenkh + ";" + diachi;
		
		checkAdd.align = "center";
		checkAdd.appendChild(checkbox);
		
		tr.appendChild(sttAdd);
		tr.appendChild(makhAdd);
		tr.appendChild(smartidAdd);
		tr.appendChild(mafastAdd);
		tr.appendChild(tenkhAdd);
		tr.appendChild(diachiAdd);
		tr.appendChild(tansoAdd);
		tr.appendChild(checkAdd);
		
		if(stt % 2 != 0)
			tr.setAttribute("class","tblightrow");
		else
			tr.setAttribute("class","tbdarkrow");
		tr.setAttribute("id", stt );
		tableName.appendChild(tr);
	}
	
	function addRow2(name, stt, makh, smartid, mafast, tenkh, diachi) //---them vao khach hang chua dc phan tuyen---//
	{
		tableName = document.getElementById(name);
		var tr = document.createElement("TR");
		var makhAdd = document.createElement("TD");
		var smartidAdd = document.createElement("TD");
		var mafastAdd = document.createElement("TD");
		var tenkhAdd = document.createElement("TD");
		var diachiAdd = document.createElement("TD");
		var checkAdd = document.createElement("TD");
		
		makhAdd.setAttribute("class","plainlabelNew2");
		makhAdd.innerHTML = makh;	
		
		smartidAdd.setAttribute("class","plainlabelNew");
		smartidAdd.innerHTML = smartid;	
		
		mafastAdd.setAttribute("class","plainlabelNew");
		mafastAdd.innerHTML = mafast;	
		
		tenkhAdd.setAttribute("class","plainlabelNew");
		tenkhAdd.innerHTML = tenkh;	
		
		diachiAdd.setAttribute("class","plainlabelNew");
		diachiAdd.innerHTML = diachi;
		
		var checkbox = document.createElement("input");
		checkbox.setAttribute("type", "checkbox");
		checkbox.name = "kh_tbh_cdptList";
		checkbox.value = makh + ";" + smartid + ";" + mafast + ";" + tenkh + ";" + diachi;
		
		checkAdd.align = "center";
		checkAdd.appendChild(checkbox);
		

		tr.appendChild(makhAdd);
		tr.appendChild(smartidAdd);
		tr.appendChild(mafastAdd);
		tr.appendChild(tenkhAdd);
		tr.appendChild(diachiAdd);
		tr.appendChild(checkAdd);
		
		if(stt % 2 != 0)
			tr.setAttribute("class","tblightrow");
		else
			tr.setAttribute("class","tbdarkrow");
		tr.setAttribute("id", stt);
		tableName.appendChild(tr);
	}
	
	function removeRow(name, id)
	{
		var tableName = document.getElementById(name);
		tableName.removeChild(document.getElementById(id));
	}
	
	function ChuyenSangTrai(i)
	{	
		var tbh_khdpt = document.getElementsByName("kh_tbh_dptList");
		var tbh_khcdpt = document.getElementsByName("kh_tbh_cdptList");
		
				str = tbh_khdpt.item(i).value;
				var makh = str.substring(0, str.indexOf(";"));
				
				
				str = str.substr(str.indexOf(";") + 1);
				var smartid = str.substring(0,str.indexOf(";"));
				
				str = str.substr(str.indexOf(";") + 1);
				var mafast = str.substring(0,str.indexOf(";"));
				
				
				str = str.substr(str.indexOf(";") + 1);
				var tenkh = str.substring(0, str.indexOf(";"));
				
				str = str.substr(str.indexOf(";") + 1);
				var diachi = str.substring(0, str.length);
				//Phat_a.tuan;
				var sott;
				if (tbh_khcdpt.length-1 >= 0)
					sott=tbh_khcdpt.item(tbh_khcdpt.length-1).value;
				else sott = 0;
				
				addRow2('kh_tbh_cdpt',sott, makh, smartid, mafast, tenkh, diachi);
	}
	
	function AutoChuyenTrai()
	{
		var tbh_khdpt = document.getElementsByName("kh_tbh_dptList");
		var tbh_khcdpt = document.getElementsByName("kh_tbh_cdptList");
		
		var i;
		for(i=0; i < tbh_khdpt.length; i++)
		{
			
			if(tbh_khdpt.item(i).checked)
			{
				
				ChuyenSangTrai(i);
			}
		}
		i=tbh_khdpt.length;
		while(i>0){
				i=i-1;
				if(tbh_khdpt.item(i).checked)
				{
					document.getElementById("tb_kh_tbh_dpt").deleteRow(tbh_khdpt.item(i).parentNode.parentNode.rowIndex);
				}
			
		}
		
		
		 var tbh_thutu = document.getElementsByName("thutu");
		
		for(i=0; i < tbh_thutu.length; i++)
		{
		
			tbh_thutu.item(i).value=i;
		
		} 
	}
	
	function ChuyenSangPhai(i)
	{	
		var tbh_khcdpt = document.getElementsByName("kh_tbh_cdptList");
		var tbh_khdpt = document.getElementsByName("kh_tbh_dptList");
		var thutu = document.getElementsByName("thutu");
			    var str = new String;
				str = tbh_khcdpt.item(i).value;
				var makh = str.substring(0, str.indexOf(";"));
				str = str.substr(str.indexOf(";") + 1);
				var smartid = str.substring(0, str.indexOf(";"));
				str = str.substr(str.indexOf(";") + 1);
				var mafast = str.substring(0, str.indexOf(";"));
				str = str.substr(str.indexOf(";") + 1);
				var tenkh = str.substring(0, str.indexOf(";"));
				
				str = str.substr(str.indexOf(";") + 1);
				var diachi = str.substring(0, str.length);
				var sott=0; 
				if(thutu.length>0){
					  sott=  thutu.item(thutu.length-1).value;
				}
				
				addRow('kh_tbh_dpt',parseFloat(sott)+1, makh, smartid, mafast, tenkh, diachi);					
 
	}
	
	function AutoChuyenPhai()
	{
		var tbh_khcdpt = document.getElementsByName("kh_tbh_cdptList");
		var tbh_khdpt = document.getElementsByName("kh_tbh_dptList");
		
		var i;
		for(i=0; i < tbh_khcdpt.length; i++)
		{
			var str = new String;
			if(tbh_khcdpt.item(i).checked)
			{
				ChuyenSangPhai(i);
			}
		}
		i=tbh_khcdpt.length;
		while(i>0){
				i=i-1;
				if(tbh_khcdpt.item(i).checked)
				{
					
					document.getElementById("tb_kh_tbh_cdpt").deleteRow(tbh_khcdpt.item(i).parentNode.parentNode.rowIndex);

				}
			
		}
	
	}
	
	function LocDuLieu()
	{
		document.forms['tbhForm'].action.value='tim';
    	document.forms['tbhForm'].submit();
		
    	
	}
	/* function ajaxOption(id)
	{
		console.log("do do");
	    Utility util = new Utility();
		var nppId = document.getElementById("nppId").value;
		var khTen = util.replaceAEIOU(document.getElementById("khTen").value);
		var diachi = document.getElementById("diachi").value;
		var ddkdId = document.getElementById("ddkdTen").value; 
		var tbhId= document.getElementById("tbhId").value;
		var mafast= document.getElementById("mafast").value; 
		
 		console.log("ten:: "+khTen);
		var xmlhttp;
		
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
		   xmlhttp = new XMLHttpRequest();
		   
		}
		else
		{// code for IE6, IE5
		   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		xmlhttp.onreadystatechange=function()
		{
		   if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
		   {
		      document.getElementById(id).innerHTML = xmlhttp.responseText;
		   }
		}
		xmlhttp.open("GET","../../ajaxTuyenBh?id=" + id + "&nppId=" + nppId + "&khTen=" + khTen +"&mafast="+mafast+ "&diachi=" + diachi+"&ddkdId="+ddkdId+"&tbhId="+tbhId+"",true);   
		xmlhttp.send();
	} */
	//phat_a.tuan
	function ajaxOption(id)
	{
		var nppId = document.getElementById("nppId").value;
		var khTen = document.getElementById("khTen").value;
		var diachi = document.getElementById("diachi").value;
		var ddkdId = document.getElementById("ddkdTen").value; 
		var tbhId= document.getElementById("tbhId").value;
		var mafast= document.getElementById("mafast").value;
		$( document ).ready(function() {
			$.ajax({
		    	  method: "POST",
		    	  type: "POST", 
		    	  url: "../../ajaxTuyenBh",
		    	  data: { "nppId" : nppId, "khTen": khTen, "diachi" : diachi, "ddkdId" : ddkdId, "tbhId": tbhId, "mafast": mafast ,"id": id},
		    	  success: function(data) {
		              $("#tb_kh_tbh_cdpt").show();
		              $("#tb_kh_tbh_cdpt").html(data); 
		          },
		          error: function(xhr) {
		              alert("Lỗi chưa có thông tin");
		          }
		      });
		});
	}
		
</script>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="tbhForm" method="post" action="../../TuyenbanhangUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="nppId" id="nppId" value='<%= tbhBean.getNppId() %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="id"  id="tbhId" value="<%=tbhBean.getId() %>"> 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
	  <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
				<TR>
					<TD align="left" class="tbnavigation">
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <TR height="22">
						  	<TD align="left" colspan="2" class="tbnavigation">&nbsp;Thiết lập dữ liệu nền &gt; Dữ liệu Kinh doanh &gt; Tuyến bán hàng > Cập nhật
						  	<TD colspan="2" align="right" class="tbnavigation">Chào mừng  <%= tbhBean.getNppTen() %></TD>
						 </TR>
					  </table>
					</TD>
			  </TR>
			</TABLE>
			<TABLE width="100%" cellpadding="0" cellspacing="2">
				<TR >
					<TD >
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
							<TR class = "tbdarkrow">
								<TD width="30" align="left"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TuyenbanhangSvl?userId="+userId+"") %>" >			 		
									<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
								<TD width="2" align="left" ></TD>
				    			<TD width="30" align="left" >
				    			<div id="btnSave">
				    			<A href="javascript:saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
				    			</div>
				    			</TD>
								<TD width="2" align="left" ></TD>								
								<TD width="30" align="left" ><A href="javascript:printform()" ><img src="../images/Printer30.png" alt="In chung tu" width="30" height="30" border="1" longdesc="In chung tu" title="In chung tu" style="border-bottom-style:outset"></A></TD>
								<TD align="left" >&nbsp;</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
					
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="0">
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>			
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  cols="139%" rows="1" ><%= tbhBean.getMessage() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>
				<TR>
				  <TD height="100%" width="100%">
				<FIELDSET>
				<LEGEND class="legendtitle">&nbsp;Thông tin tuyến bán hàng &nbsp;</LEGEND>
							<TABLE  width="100%" cellspacing="0" cellpadding="6">
								<TR>
								  <TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
								  <TD class="plainlabel">
								    <SELECT name="ddkdTen" id="ddkdTen" onchange="LocDuLieu();">
								  	<option value=""></option>
								 	<% 
									  try{ while(ddkd.next()){ 
									    	if(ddkd.getString("ddkdId").equals(tbhBean.getDdkdId())){ %>
									      		<option value='<%=ddkd.getString("ddkdId") %>' selected><%=ddkd.getString("ddkdTen") %></option>
									      	<%}else{ %>
									     		<option value='<%=ddkd.getString("ddkdId") %>'><%=ddkd.getString("ddkdTen") %></option>
									     	<%}}}catch(java.sql.SQLException e){}
									   %>
							    	</SELECT></TD>
							  </TR>
								<TR>
									<TD width="24%" class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %> tuyến bán hàng </TD>
									<TD width="76%" class="plainlabel"><INPUT name="tbhTen" id="tbhTen"
										type="text" value="<%= tbhBean.getDiengiai() %>" size="60"></TD>
								</TR>
								
								<TR>
								  <TD class="plainlabel">Ngày làm việc </TD>
							      <TD class="plainlabel" valign="middle">
							        <select name="ngaylamviec" id="nlv"><option value=""></option>
							         <%
							         	String[] thu =  new String[]{"Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy","Chủ nhật"};
							 			for(int h = 0; h < thu.length; h++)
							 			{
							 				
							 				if(tbhBean.getNgaylamviec().equals(thu[h])){ %>
							 					<option value="<%= thu[h] %>" selected><%= thu[h] %></option>
							 			<%}else{%>
							 					<option value="<%= thu[h] %>"><%= thu[h] %></option>
							 		<%}} %>  						          
						             </select>
									 </TD>
							  </TR>
							</TABLE>
	
				    </FIELDSET>
				  </TD>	
				</TR>
			</TABLE>
			<TABLE width = 100% border="0" cellpadding="0" cellspacing ="0">
			<TR>
			 	<TD width = "50%" valign="top">
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%">
								<FIELDSET>
								<LEGEND class="legendtitle">Khách hàng được phân vào tuyến này	</LEGEND>
								  	<TABLE width="100%" align="left" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%">

							<TABLE class="tabledetail" width="100%" border="0" cellspacing="1" cellpadding="4" id="tb_kh_tbh_dpt">
							<tbody id="kh_tbh_dpt">
								<TR class="tbheader">
								  	<TH width="5%">Thứ tự </TH>
								  	<TH width="12%">Mã KH </TH>
								  	<TH width="10%">Mã DMS </TH>
									<TH width="30%">Tên KH</TH>
									<TH width="30%">Địa chỉ </TH>
									<TH width="5%">Tần số </TH>
									<TH width="5%">Chọn
									<input type="checkbox" id="chontatca_kh_tbh_dptList" value='1'
										onclick="checkedAll('kh_tbh_dptList', this);">
									</TH>
								</TR>
								<%
								int i = 0;
								String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								if (kh_tbh_dpt != null){
								try{while(kh_tbh_dpt.next()){ 
									if (i % 2 != 0) {%>						
										<TR class= <%=lightrow%> >
									<%} else {%>
										<TR class= <%= darkrow%> >
								<%}%>
								<TD align="center"><input name='thutu' type="text" value="<%=kh_tbh_dpt.getString("sott") %>" size="5" style="width: 100%"></TD>

								<TD align="left" > <%= kh_tbh_dpt.getString("smartid") %> </TD>
								<TD align="center" ><%= kh_tbh_dpt.getString("maFAST") %></TD>
								<TD align="left" > <%= kh_tbh_dpt.getString("ten") %> </TD>
								<TD align="center"><p align="left"> <%= kh_tbh_dpt.getString("diachi") %> </TD>
								<TD align="center"><select style="width:70px" name="tansoList" >
                                     <option value=""> </option>
                                  	
                                  	<%
                                  	if(kh_tbh_dpt!=null)
                                  	{
                                  	for( int ii=0; ii<arr.size();ii++){ %>
											<option value="<%=arr.get(ii)%>" <%=kh_tbh_dpt.getString("tanso").equals(arr.get(ii))?"selected":"" %> ><%=arr.get(ii) %></option>
											  	
									<%}} %>
									
                                   </select></TD>
                                   <%if (kh_tbh_dptIds.contains(kh_tbh_dpt.getString("khId"))){ %>
										   <TD align="center"><input name="kh_tbh_dptList" type="checkbox" value ="<%=kh_tbh_dpt.getString("khId")+";"+kh_tbh_dpt.getString("smartid")+";"+kh_tbh_dpt.getString("mafast")+";"+kh_tbh_dpt.getString("ten")+";"+kh_tbh_dpt.getString("diachi") %>" checked
										   onclick="check_each('kh_tbh_dptList');"></TD>
									<%}else{%>
										   <TD align="center"><input name="kh_tbh_dptList" type="checkbox" value ="<%=kh_tbh_dpt.getString("khId")+";"+kh_tbh_dpt.getString("smartid")+";"+kh_tbh_dpt.getString("mafast")+";"+kh_tbh_dpt.getString("ten")+";"+kh_tbh_dpt.getString("diachi") %>"
										   onclick="check_each('kh_tbh_dptList');"></TD>
									<%}%></TR>
						     	<% i++;}}catch(java.sql.SQLException e){} 
						     	}%>
							</tbody></TABLE>
							</TD>
						</TR>
			</TABLE>
				</FIELDSET>	</TD>
						</TR>
				    </TABLE>
				</TD>
				<TD width ="1%" valign="top"> 
					<TABLE border = "0" cellpadding="0" cellspacing="0">
						<TR>
							<TD>&nbsp;
								
						  </TD>
						</TR>					
						<TR>
							<TD>
								<img src="../images/Back30.png" border="0" style="cursor: pointer;" onClick="AutoChuyenPhai()">
							</TD>
						</TR>
						<TR>
							<TD>&nbsp;
								
						  </TD>
						</TR>
						<TR>
						<TD>&nbsp;				
						  </TD>
						</TR>

						<TR>
							<TD>
								<img src="../images/Next30.png" border="0" style="cursor: pointer;" onClick="AutoChuyenTrai()">
							</TD>
						</TR>
					</TABLE>
			  </TD>			
				<TD width = "40%" valign="top">
					<TABLE width="100%" border="0" cellpadding="0">
						<TR>
							<TD >
								<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>
								<TABLE width="100%" cellspacing="0" cellpadding = "6">
									<TR>
										<TD width="30%" class="plainlabel">Tên khách hàng </TD>
										<TD width="70%" valign="middle" class="plainlabel">
											<TABLE>
												<TR>
													<TD>
														<input name="khTen" id="khTen" type="text" value="<%= tbhBean.getTenkhachhang() %>" onchange="ajaxOption('bangcpt')" size="25" >
													</TD>
													<TD>
														<img src="../images/Search20.png" width="20" height="20" onClick="ajaxOption('bangcpt')" style="cursor: pointer;" >							  
													</TD>
												</TR>
											</TABLE>	  
									  </TD>
								
									</TR>
									<TR>
										<TD class="plainlabel">Địa chỉ </TD>
										<TD class="plainlabel">
											<TABLE>
												<TR>
													<TD>
														<input name="diachi" id="diachi" type="text" value="<%= tbhBean.getDiachi() %>" onchange="ajaxOption('bangcpt')" size="25" >
													</TD>
													<TD>
														<img src="../images/Search20.png" width="20" height="20" onClick="ajaxOption('bangcpt')" style="cursor: pointer;" >							  
													</TD>
												</TR>
											</TABLE>	  
								
										</TD>
									</TR>
									
									<TR>
										<TD class="plainlabel">Mã Fast </TD>
										<TD class="plainlabel">
											<TABLE>
												<TR>
													<TD>
														<input name="mafast" id="mafast" type="text" value="<%= tbhBean.getDiachi()%>" onchange="ajaxOption('bangcpt')" size="25" >
													</TD>
													<TD>
														<img src="../images/Search20.png" width="20" height="20" onClick="ajaxOption('bangcpt')" style="cursor: pointer;" >							  
													</TD>
												</TR>
											</TABLE>	  
								
										</TD>
									</TR>
									
								</TABLE>
								</FIELDSET>
							</TD>	
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD colspan = "6" width="100%" >
								<FIELDSET>
								<LEGEND class="legendtitle">Khách hàng chưa được phân vào tuyến này</LEGEND>
							<div id = "bangcpt">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="6" id="tb_kh_tbh_cdpt" >
							<tbody id="kh_tbh_cdpt">
								<TR class="tbheader">
								 	<TH width="5%">Mã KH</TH>
								 	<TH width="10%">Mã Fast </TH>
								 	<TH width="45%">Tên KH</TH>
									<TH width="35%">Địa chỉ </TH>
									<TH width="5%">Chọn
									<input type="checkbox" name="chontatca" id="chontatca_kh_tbh_cdptList" value='1' onclick="checkedAll('kh_tbh_cdptList', this);">
									<!-- onclick="checkedAll(document.tbhForm.kh_tbh_cdptList,document.tbhForm.chontatca);" -->
									</TH>
								</TR>
							<% 
							int j = 0; 
							if(kh_tbh_cdpt != null){
								try{while(kh_tbh_cdpt.next()){ 
									if (j % 2 != 0) { %>						
										<TR class= <%=lightrow%> >
									<%} else {%>
										<TR class= <%= darkrow%> >
								<%}%>
									<%-- <TD align="left" class="textfont"><%= kh_tbh_cdpt.getString("khId") %> </TD> --%>
									
									<TD align="left" class="textfont"><%= kh_tbh_cdpt.getString("smartid") %> </TD>
									<TD align="center" class="textfont"><%= kh_tbh_cdpt.getString("maFAST") %> </TD>
									<TD align="left" class="textfont"><%= kh_tbh_cdpt.getString("ten") %> </TD>
									<TD align="center"><div align="left"><%= kh_tbh_cdpt.getString("diachi") %> </div></TD>
                                    <%if (kh_tbh_cdptIds.contains(kh_tbh_cdpt.getString("khId"))){ %>
										   <TD align="center"><input name="kh_tbh_cdptList" type="checkbox" value ="<%=kh_tbh_cdpt.getString("khId")+";"+kh_tbh_cdpt.getString("smartid")+";"+kh_tbh_cdpt.getString("mafast")+";"+kh_tbh_cdpt.getString("ten")+";"+kh_tbh_cdpt.getString("diachi") %>" 
										   onclick="check_each('kh_tbh_cdptList');" checked></TD>
									<%}else{%>
										   <TD align="center"><input name="kh_tbh_cdptList" type="checkbox" value ="<%=kh_tbh_cdpt.getString("khId")+";"+kh_tbh_cdpt.getString("smartid")+";"+kh_tbh_cdpt.getString("mafast")+";"+kh_tbh_cdpt.getString("ten")+";"+kh_tbh_cdpt.getString("diachi") %>"
										   onclick="check_each('kh_tbh_cdptList');"></TD>
									<%}%>
									</TR>
							     	<%j++;}}catch(java.sql.SQLException e){} 
							     	
							     }%>					
							</tbody></TABLE>
							</div>
							</FIELDSET>	</TD>
						</TR>
				    </TABLE>
				</TD>
				
			</TR>
		</TABLE>
		

	    <!--end body Dossier--></TD>
	</TR>
	
</TABLE>
</form>
</BODY>


<%
if(ddkd!=null){
	ddkd.close();
}
if(kh_tbh_dpt!=null){
	kh_tbh_dpt.close();
}
if(kh_tbh_cdpt!=null){
	kh_tbh_cdpt.close();
}
if(kh_tbh_dptIds!=null){
	kh_tbh_dptIds.clear();
}
if(kh_tbh_cdptIds!=null){
	kh_tbh_cdptIds.clear();
}
if(tbhBean!=null){
	tbhBean.DBclose();
	tbhBean=null;
}



session.setAttribute("tbhBean",null);

}%>
</HTML>
