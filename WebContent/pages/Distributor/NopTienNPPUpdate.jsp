<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.noptiennpp.*" %>

<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DateFormat" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.SimpleDateFormat" %>
<%@page import="geso.dms.center.util.Utility"%>
<%@page import="java.util.Locale" %>
<% Locale.setDefault(new Locale("en", "US")); %>
<% HttpSession s = request.getSession();
   if (s.isNew()){
	   s.invalidate();
	   System.out.println("New session");
   }else{
	   System.out.println("Old session");
   }
   
   
   
%>

<% INopTienNPP dhBean = (INopTienNPP)s.getAttribute("dhBean"); 
   /* IDonhangList dhlist = (IDonhangList)s.getAttribute("dhList"); */
%>
<% List<ISanpham> splist = (List<ISanpham>)dhBean.getSpList(); %>

<% String userId = (String) s.getAttribute("userId");  %>


<%NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<%NumberFormat formatter2 = new DecimalFormat("#,###,###.####");
String userTen = (String) session.getAttribute("userTen"); 
Utility u =new Utility();
String disabled = "";
String loaipage = "Tạo mới";
if(dhBean.getIsDisplay()==1)
	loaipage = "Hiển thị";
else if( dhBean.getId()!= null && dhBean.getId().length() > 0 )
	loaipage = "Cập nhật";
	

String url = u.getUrl("NopTienNPPSvl","") + " > " + loaipage;

	
if( dhBean.getId()!= null && dhBean.getId().length() > 0 )
	disabled ="disabled";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
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
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>  	
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript">
	jQuery(function($){ 
		 $('.addspeech').speechbubble();
	})
</script>

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

<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/ajax-dynamic-list.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>

<script language="javascript" type="text/javascript">

	function capnhatstt_barcode()
	{
		
		var sanphamArr = new Array();
		var sttArr = new Array();
		
		var barcode_spid = document.getElementsByName("barcode_spid");
		var barcode_spstt = document.getElementsByName("barcode_spstt");
		if(barcode_spid)
			for(var i = 0; i < barcode_spid.length; i++)
			{
				var spId =  barcode_spid.item(i).value;
				var stt= getSttSp(sanphamArr,sttArr, spId );
				barcode_spstt.item(i).value = stt;
			}
		console.log('a');	
		//setTimeout(replaces, 300);
		
		
	}
	function getSttSp(sanphamArr,sttArr, spId )
	{
		
		for(z = 0; z < sanphamArr.length; z++) {
			var id = sanphamArr[z];
			if(spId == id )
			{
				var stt =parseInt(sttArr[z]) + 1;
				sttArr[z] = stt;
				return stt;
			}
		}
		sanphamArr.push(spId);
		sttArr.push(1);
		
		return 1;
	}

	function goBack() {
	    window.history.back();
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
			{//Phím Delete và Phím Back va dau cham, phim Tab
				return;
			}
			return false;
		}
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
	function checkTren1k(v)
	{
		
		return v;
	}
	function Dinhdang(element)
	{
		element.value = DinhDangTien(element.value);
		
		if(element.value== '' )
		{
			element.value= '';
		}
		element.value =checkTren1k(element.value);
		
	}	
	
	function themsanpham()
	{
		
	}
	function deleteRow(o) {
		var p=o.parentNode.parentNode;
        p.parentNode.removeChild(p);
        capnhatstt_barcode();
	}
	function searchSp() {
		  var input, filter, table, tr, td, i, txtValue;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  
		  if(table.rows.length > 1)
		  for (i = 1; i <table.rows.length ; i++) {
			  
		      var r=table.rows[i];
		      txtValue = table.rows[i].cells[0].children[0].value;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		    	  r.style.display = "";
		        } else {
		        	 r.style.display = "none";
		        }
		      
		  }
		  
	
		  
		}
	function XoaSp() {
		
		var slchon = document.getElementsByName("slchon");
		for( var x = 0; x < slchon.length; x++)
		{
			slchon.item(x).value = '';
		}
	}
	function ThemSp() {
		
		var slchon = document.getElementsByName("slchon");
		var idSp = document.getElementsByName("idSp");
		var maSp = document.getElementsByName("maSp");
		var tenSp = document.getElementsByName("tenSp");
		for( var x = 0; x < slchon.length; x++)
		{
			
			var sl = slchon.item(x).value ;
			var ma = maSp.item(x).value ;
			var ten = tenSp.item(x).value ;
			var id = idSp.item(x).value ;
			if(sl!= '' && parseFloat(sl) > 0 )
			{
				var tbBarcode=  document.getElementById("tbBarcode");
				
				for( var y =0; y < parseInt(sl); y++ )
				{
					var row = tbBarcode.insertRow(1);
					var cell1 = row.insertCell(0);
					var cell2 = row.insertCell(1);
					var cell3 = row.insertCell(2);
					var cell4 = row.insertCell(3);
					var cell5 = row.insertCell(4);
					
					cell1.innerHTML  =	"<input name=\"barcode_spid\" type=\"hidden\" value=\""+ id +"\"  style=\"width:100%;text-align:center\">" + 
										"<input tabindex=\"-1\" readonly name=\"barcode_spma\" type=\"input\" value=\""+ ma +"\"  style=\"width:100%;text-align:center\">";
					cell2.innerHTML  =  "<input tabindex=\"-1\" readonly name=\"barcode_spten\" type=\"input\" value=\""+ ten +"\"  style=\"width:100%;text-align:center\">";
					cell3.innerHTML  =  "<input tabindex=\"-1\" readonly name=\"barcode_spstt\" type=\"input\" value=\"\"  style=\"width:100%;text-align:center\">";
					cell4.innerHTML  =  "<input  name=\"barcode\" type=\"input\" value=\"\"  style=\"width:100%;text-align:center\">";
					cell5.innerHTML  =  "<input  tabindex=\"-1\" onclick = \"javascript:deleteRow(this)\" name=\"xoa_line\" type=\"image\"   src=\"../images/Delete20.png\" style=\"width:20%;text-align:center\">" ;
						
					;
					//cell5.innerHTML  =  "<a href=\"javascript:deleteRow(this)\"><img src=\"../images/Delete20.png\" alt=\"Xoa\" width=\"20\" height=\"20\" longdesc=\"Xoa\" border=0 ></A>									";
				}
			}
		}
		 XoaSp();
		 document.getElementById("myInput").value= '';
		 searchSp();
		$.colorbox.close();
		capnhatstt_barcode();
	}
	
	function saveform()
	{
		
		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['dhForm'].action.value='save';
	     document.forms['dhForm'].submit();	
	}
	function submit()
	{
		 removeDisable();
	 	 document.forms['dhForm'].action.value='filter';
	     document.forms['dhForm'].submit();	
	}
	
	function changeDh()
	{
		removeDisable();
	 	 document.forms['dhForm'].action.value='changeDh';
	     document.forms['dhForm'].submit();	
	}
	function removeDisable()
	{
		  	document.getElementById("khId").removeAttribute('disabled');
			 document.getElementById("dhIds").removeAttribute('disabled');
	}
	function next_bar(e)
	{
		
		var barcode = document.getElementsByName("barcode");
		var arr = Array.prototype.slice.call(barcode); // Now it's an Array.
		var i = arr.indexOf(e) + 1; 
		var next = barcode.item(i);
		 next.focus();
	}
	
	
</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { 
      $("select:not(.notuseselect2)").select2({ width: 'resolve' });     
     });
</script>  	

<!-- Khai bao su dung colorbox ajax -->
<link media="screen" rel="stylesheet" href="../css/colorbox.css">
<script src="../scripts/colorBox/jquery.colorbox.js"></script>
<style type="text/css">
	#myInput {
 
  background-position: 10px 10px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

</style>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0"  >
	<form name="dhForm" method="post" action="../../NopTienNPPUpdateSvl">
			<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="id" id="nppId" value='<%= dhBean.getId() %>'>
		<input type="hidden" name="userId" value='<%=userId%>'> 
		<input type="hidden" name="action" value='1'> 

		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<TABLE border=0 width="100%" cellpadding="2" cellspacing="0">
						<TBODY>
							<TR height="22">
								<TD align="left">
									<TABLE width="100%" cellpadding="0" cellspacing="0">
										<TR>
											<TD align="left">
												<TABLE width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<TR height="22">
														<TD align="left" class="tbnavigation"><%=url%>
														</TD>
														<TD align="right" class="tbnavigation">Chào mừng <%= userTen %> 	&nbsp;</TD>
													</TR>
												</TABLE></TD>
										</TR>
									</TABLE>
									<TABLE width="100%" border="0" cellpadding="1" cellspacing="0">
										<TR>
											<TD>
												<TABLE width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<TR class="tbdarkrow">
														<TD width="30" align="left"><A
															href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NopTienNPPSvl?userId="+userId+"") %>"><img src="../images/Back30.png" alt="Quay ve" title="Quay ve" border="1" longdesc="Quay ve"	style="border-style: outset">
														</A>
														</TD>
														<TD width="2" align="left">&nbsp;</TD>
														<TD width="30" align="left">
														<%if(dhBean.getIsDisplay()==0){ %>
															<div id="btnSave">
																<A href="javascript:saveform()"><img
																	src="../images/Save30.png" alt="Luu lai" title="Luu lai" border="1" longdesc="Luu lai"
																	style="border-style: outset">
																</A>
																<%} %>
															</div></TD>
														
														
														
														
															
														
														<TD align="left">&nbsp;</TD>
													</TR>
												</TABLE></TD>
										</TR>
									</TABLE>
									<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<TD align="left" colspan="4" class="legendtitle">
												<FIELDSET>
													<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
													<textarea name="dataerror"
														style="width: 100%; color: #F00; font-weight: bold"
														readonly="readonly" style="width: 100%" rows="1"><%= dhBean.getMessage() %></textarea>
												</FIELDSET></TD>
										</tr>
										<TR>
											<TD align="left">
												<FIELDSET>
													<LEGEND class="legendtitle">&nbsp;Đơn hàng bán </LEGEND>
													<TABLE cellpadding="3" cellspacing="0" width="100%"
														border=0>

														<TR>
															<TD class="plainlabel" width="20%">Ngày chứng từ</TD>
															<TD class="plainlabel"><input type="text"
																class="days" size="11" 
																id="tungay" name="ngaygiaodich"
																value="<%= dhBean.getNgaygiaodich() %>" maxlength="10"
																readonly /></TD>

															<TD class="plainlabel"width="15%"> Ghi chú </TD>
																<TD class="plainlabel"  > 
													 				<input type="text"  name="ghichu" value="<%= dhBean.getGhichu() %>"  style="width: 500" />
																</TD>
															
														</TR>
														<TR>
															<TD class="plainlabel"> Đối tượng </TD>
															
															
															<%if(dhBean.getId() != null && dhBean.getId().length() > 0) {
																String textDoiTuong = "";
																if(dhBean.getDoituong().equals("0")) textDoiTuong = "NPP";
																else if(dhBean.getDoituong().equals("1")) textDoiTuong = "Khách hàng";
																%>
															<TD class="plainlabel" colspan="3" >
									                    		
																		<input type="text"  value="<%= textDoiTuong %>" readonly  />
																		<input type="hidden"  name="doituong" value="<%=dhBean.getDoituong()%>"  />
												
															</TD>
															<%}else{ %>
									                    	<TD class="plainlabel" colspan="3" >
									                    		<select onchange="submit()" name="doituong" >
																		<option value="0" <%= dhBean.getDoituong().equals("0") ? "selected":""  %>    > NPP </option>
																		<option value="1" <%= dhBean.getDoituong().equals("1") ? "selected":""  %>    > Khách hàng </option>
																		
																</select>
															</TD>
															<%} %>
														</TR>

					     
										                
													</TABLE>
												</FIELDSET></TD>
										</TR>
										
										<TR>
											<TD>
												<TABLE id="tbBarcode" width="100%" border="0" cellpadding="0"
													cellspacing="1">
															
													<tbody id="san_pham">
														
														
													
														<TR class="tbheader">														
															<TH width="10%">MÃ ĐỐI TƯỢNG</TH>
															<TH width="40%">TÊN ĐỐI TƯỢNG</TH>
															<TH width="10%">SỐ TIỀN NỘP</TH>
														</TR>
														
														<%
														ResultSet nppRs = dhBean.getNppRs();
														Hashtable<String, Double> npp_sotien = dhBean.getNpp_sotien();
														if( nppRs != null)
														while(nppRs.next()){
															
															String sotien = "";
															if(npp_sotien.containsKey(nppRs.getString("pk_seq")))
																sotien =  formatter2.format(npp_sotien.get(nppRs.getString("pk_seq")) );
															
															%>
														<TR class= 'tblightrow'  >
																
															<TD>
																	<input  name="doituongIds" type="hidden" value="<%=nppRs.getString("pk_seq") %>"  style="width:100%;text-align:center">
																	<input tabindex="-1" readonly name="manpp" type="text" value="<%=nppRs.getString("mafast")  %>"  style="width:100%;text-align:center">
															</TD>
															<TD>
																	<input tabindex="-1" readonly name="tennpp" type="text" value="<%=nppRs.getString("ten")%>"  style="width:100%;text-align:center">
															</TD>
															
															
															<TD  >
																<input  name="sotien" type="text" value="<%=sotien%>"  style="width:100%;text-align:center" onkeyup="Dinhdang(this)">					
															</TD>

														</TR>
														 <%} %> 
								        
													</tbody>
												</TABLE>
											</TD>
										</TR>
									</TABLE></TD>
							</TR>
						</TBODY>
					</TABLE></td>
			</tr>

		</TABLE>
<script type="text/javascript">
//replaces();
	
	
	
	
	
	
	
	
</script>
	</form>
</BODY>

</HTML>
<%
try{
	
	
	if(dhBean != null)
	{
		dhBean.DBclose();
	}
	dhBean=null;
	s.setAttribute("dhBean",null);
}catch(Exception er){
	System.out.println("Error DonHang1080:"+er.toString());
}
%>

