<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.bundle.*" %>
<%@ page  import = "geso.dms.center.util.Utility" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DateFormat" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.SimpleDateFormat" %>

<% 
	NumberFormat formatter = new DecimalFormat("#,###,###");
	NumberFormat formatter2 = new DecimalFormat("#,###,###.###");  
	HttpSession s = request.getSession();
   if (s.isNew()){
	   s.invalidate();
	   System.out.println("New session");
   }else{
	   System.out.println("Old session");
   }   	  
%>
<% IBoBundle dhBean = (IBoBundle)s.getAttribute("dhBean");
%>
<% List<ISanpham> splist = (List<ISanpham>)dhBean.getSpList(); %>

<% ResultSet kbhRs = (ResultSet)dhBean.getKbhRs();%>
<% ResultSet kho = (ResultSet)dhBean.getKhoList(); 

ResultSet dvkdRs = (ResultSet)dhBean.getDvkdRs();
ResultSet spRs = (ResultSet)dhBean.getSpRs();
%>


<% String userId = (String) s.getAttribute("userId"); %>
<% Hashtable<String, Integer> spThieuList = dhBean.getSpThieuList(); %>
<%
Utility util = new Utility();
String userTen = (String) session.getAttribute("userTen"); 
%>

<% ResultSet nppRs = (ResultSet)dhBean.getNppRs(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
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
	
}
</style>
<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript">
	jQuery(function($){ 
		 $('.addspeech').speechbubble();})
</script>



<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>


<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/bobundle-ajax-list.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
<script type="text/javascript">
$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});            
       });	
	
   </script>
<script language="javascript" type="text/javascript">

	function ajaxOption_getlo(id, sanphamid,vitri)
	{

		var ngaygiaodich = document.getElementById("ngaygiaodich").value;
		var khoId = document.getElementById("khoTen").value;
		var nppId = document.getElementById("nppId").value;
		var kbhId = document.getElementById("kbhId").value;
		var dvkdId = document.getElementById("dvkdId").value;

		
		
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
			 //alert(xmlhttp.responseText);
			 //var idTableSp = id.substring(0, parseInt(id.indexOf(".")));
		     document.getElementById(id).innerHTML = xmlhttp.responseText;
		  }
		}
		xmlhttp.open("POST","../../BundleAjaxGetLoSvl?sanphamid="+ sanphamid+"&khoId="+khoId+"&vitri="+vitri+"&ngaygiaodich="+ngaygiaodich+"&nppId="+nppId+"&kbhId="+kbhId+"&dvkdId="+dvkdId, true);

		xmlhttp.send();
	}

	function Capnhatsolo (m){
		var soluong = document.getElementsByName("soluong");
		var so_luong = soluong.item(m).value;
		if(so_luong.length == 0)
			so_luong = "0";		
							
		while(so_luong.match(","))
		{
			so_luong = so_luong.replace(",","");
		}
		//alert('so_luong= ' + so_luong );
		if(parseFloat(so_luong) > 0)
		{				
			
			Phanbosoluongvao_Lo (m,parseFloat(so_luong));
		}
		
	}
	
	function Capnhatsoluong_tongtheolo (index){
		var soluongtong = document.getElementsByName("soluong");
		var soluong = document.getElementsByName(index+".soluong");
		var sltong=0;
		for(i=0; i < soluong.length; i++)
		{
			var so_luong = soluong.item(i).value;
			if(so_luong.length == 0){
				so_luong = "0";		
			}
			sltong+=parseFloat(so_luong);
		}
		soluongtong.item(index).value=sltong;
		
	}
	
	function Phanbosoluongvao_Lo(index,soluongphanbo)
	{
		//alert('index= '+ index);
		var solo = document.getElementsByName(index+".solo");
		var soluongton = document.getElementsByName(index+".soluongton");
		var soluong = document.getElementsByName(index+".soluong");
		
		for(i=0; i < solo.length; i++)
		{
			var slton=soluongton.item(i).value;
			if(slton>soluongphanbo){
				soluong.item(i).value =Math.round(soluongphanbo,0);
				return;
			} else{
				soluongphanbo=soluongphanbo - slton;
				soluong.item(i).value =Math.round(slton,0);
			}
		}
		
	}	


	function replaces()
	{
		
		
		var masp = document.getElementsByName("masp");
		var tensp = document.getElementsByName("tensp1");
		var donvitinh = document.getElementsByName("donvitinh1");
		var soluong = document.getElementsByName("soluong");
		var tonkho = document.getElementsByName("tonkho1");
		
		var ngaygiaodich = document.getElementById("ngaygiaodich").value;
		var i;
		for(i=0; i < masp.length; i++)
		{
			if(masp.item(i).value != "")
			{
				var sp = masp.item(i).value;
				var pos = parseInt(sp.indexOf(" - "));
				if(pos > 0)
				{
					masp.item(i).value = sp.substring(0, pos);
					sp = sp.substr(parseInt(sp.indexOf(" - ")) + 3);
					
					tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf(" [")));
					sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
					
					donvitinh.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
				
					tonkho.item(i).value = DinhDangTien(sp.substring(0, parseInt(sp.indexOf("]"))));
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					ajaxOption_getlo("subcontent"+i,masp.item(i).value ,i);
				}
				
				var so_luong = soluong.item(i).value.replace(/,/g, "");			
				/* if(checkMasp(masp.item(i).value) == true)
				{
					masp.item(i).parentNode.parentNode.bgColor = "#9FC";
				} */
				
			}
			else
			{
				tensp.item(i).value = "";
				donvitinh.item(i).value = "";
				soluong.item(i).value = "";
				tonkho.item(i).value = "";
			}
		}	
		setTimeout(replaces, 300);
	}

	
	
	
	function checkMasp(masanpham)
	{
		var masp = document.getElementsByName("masp");
		for(i = 0; i < masp.length ; i++)
		{
			if(masp.item(i).value == masanpham)
				return true;
		}
		return false;
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

    function format2(n, dec, currency) {
    	var _n = parseFloat(n.toString().replace(/\$|\,/g,'')); 
    	if(isNaN(_n)) 
    	{
    		_n = 0;
    	}
    	
    	if(dec == -1) 
    	{
    		return currency + _n.replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
    	}  else {
    		return currency + _n.toFixed(dec).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
    	}
    	
        
    }
	
	function roundNumber(num, dec) 
	{
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
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
	

	

	 function checkSanPham()
	 {
		 var masp2 = document.getElementsByName("masp");
		 for(var hh = 0; hh < masp2.length; hh++)
		 {
			if(masp2.item(hh).value != "")
			{
				return true;
			}
		 }
		 return false;
	 }
	 
	 

	 function congDonSPCungMa()
	 {
		var masp = document.getElementsByName("masp");
		var soluong = document.getElementsByName("soluong");

		var ii;
		for(ii = 0; ii < (masp.length - 1) ; ii++)
		{
			for( j = ii + 1; j < masp.length; j++)
			{
				if(masp.item(ii).value != "" && masp.item(ii).value == masp.item(j).value  )
				{		
					if(soluong.item(j).value == "")
						soluong.item(j).value = "0";
					
					soluong.item(ii).value = parseInt(soluong.item(ii).value) + parseInt(soluong.item(j).value);
					masp.item(j).value = "";
				}
			}
		}

		 var tensp1 = document.getElementsByName("tensp1");
		 var donvitinh1 = document.getElementsByName("donvitinh1");
	 	 var tonkho1 = document.getElementsByName("tonkho1");
		 
		 var tensp = document.getElementsByName("tensp");
		 var donvitinh = document.getElementsByName("donvitinh");
	 	 var tonkho = document.getElementsByName("tonkho");
	 	 
	 	for(var pos = 0; pos < masp.length; pos++)
	 	{
	 		if(masp.item(pos).value != "")
	 		{
		 		tensp.item(pos).value = tensp1.item(pos).value;
		 		donvitinh.item(pos).value = donvitinh1.item(pos).value;
		 		tonkho.item(pos).value = tonkho1.item(pos).value;
	 		}
	 	}

	 }
		
		function ThemSanPham(pos)
		{
			 var sl = window.prompt("Nhấp số lượng sản phẩm muốn thêm", '');
			 if(isNaN(sl) == false && sl < 30)
			 {
				 for(var i=0; i < sl ; i++)
					 addRow(pos);
			 }
			 else
			 {
				 alert('Số lượng bạn nhập không hợp lệ. Mọi lần bạn chỉ được thêm tối đa 30 sản phẩm');
				 return;
			 }
		 }

		function ajaxOption(id, str)
		{
			var xmlhttp;
			if (str == "")
			{
			   document.getElementById(id).innerHTML = "";
			   return;
			}
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
			xmlhttp.open("POST","../../DonHangAjaxSvl?q=" + str + "&id=" + id,true);
			xmlhttp.send();
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
	
	function Dinhdang(element)
	{
		element.value = DinhDangTien(element.value);
		if(element.value== '' )
		{
			element.value= '';
		}
	}	
	
	
	function FormatTien(e)
	{

		if(e.value == '-' )
			return;
		
		if(e.value == '')
			e.value = '0';
		else
		{
			e.value = DinhDangDonGia(e.value);
		}
	}
	
	function DinhDangDonGia(num) 
	{
		num = num.toString().replace(/\$|\,/g,'');
		 
		var sole = '';
		if(num.indexOf(".") >= 0)
		{
			sole = num.substring(num.indexOf('.'));
		}
		
		if(isNaN(num))
		num = "0";
		
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		num = Math.floor(num/100).toString();
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3)) + ',' + num.substring(num.length-(4*i+3));

		var kq;
		if(sole.length >= 0)
		{
			kq = (((sign)?'':'-') + num) + sole;
		}
		else
			kq = (((sign)?'':'-') + num);
		
		//alert(kq);
		return kq;
	}
</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {		
			$(".select2").select2();
        }); 		
		
</script>


</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0" 
	onload="replaces()"
 >
<form name="dhForm" method="post" action="../../BoBundleUpdateSvl">
<input type="hidden" name="userId" value='<%=userId %>'>
<INPUT type="hidden" name="id" value='<%= dhBean.getId() %>' id="dhID">
<input type="hidden" name="action" value='1'>
<INPUT type="hidden" name="trangthai" value=''>   
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
				<TABLE border =0 width = "100%" cellpadding="2" cellspacing="0">
				<TBODY>
					<TR height="22">
						<TD align="left" >
							<TABLE width="100%" cellpadding="0" cellspacing="0">
								<TR>
									<TD align="left">
									   <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
										   <TR height="22">
			 								   <TD align="left"  class="tbnavigation">Quản lý kho chi nhánh > Chức năng > Bó sản phẩm > Hiển thị </TD>								   
			 								   <TD align="right" class="tbnavigation">Chào mừng  <%= userTen%> &nbsp; </TD>
					    				 </TR>
									  </TABLE>
								  </TD>
							  </TR>	
						  	</TABLE>
							<TABLE width="100%" border="0" cellpadding="1" cellspacing="0">
								<TR ><TD >
									<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
										<TR class = "tbdarkrow">
											<TD width="30" align="left"><A href = "../../DonhangSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
										    <TD width="2" align="left" >&nbsp;</TD>
										    <TD width="30" align="left" >
										    <!-- 
										    <div id="btnSave">
										    	<A href="javascript:saveform()" ><img src="../images/Save30.png" alt="Luu lai"  title="Luu lai" border="1" longdesc="Luu lai" style="border-style:outset"></A>
										    </div>
										    -->
										    </TD>
										    <TD width="2" align="left" >&nbsp;</TD>
							    			<TD width="30" align="left"><A href="Print.jsp" ><img src="../images/Printer30.png" alt="In" title="In chung tu" longdesc="In chung tu" border=1 style="border-style:outset"></A></TD>
								    		<TD align="left" >&nbsp;</TD>
										</TR>
									</TABLE>
								</TD></TR>
							</TABLE>												
							<TABLE border="0" width="100%" cellpadding = "0" cellspacing = "0">
								<tr>
								<TD align="left" colspan="8" class="legendtitle">
									<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>			
				    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width:100%" readonly="readonly" rows="1"><%= dhBean.getMessage()  %></textarea>
										<% dhBean.setMessage(""); %>
									</FIELDSET>
							   </TD>
								</tr>
								<TR>
									<TD align="left">						
										<FIELDSET>
										<LEGEND class="legendtitle">&nbsp;Đơn hàng bán </LEGEND>
										<TABLE cellpadding = "3" cellspacing = "0" width = "100%" border = 0>
											
											<TR>
											  
											  <TD width="170px" class="plainlabel">Ngày giao dịch </TD>
											  
											  <TD class="plainlabel" width="170px" colspan="7">
											 	  <input type="text"  class="days" size="11"  onchange="submitform()"
                                    					id="ngaygiaodich" name="ngaygiaodich" value="<%= dhBean.getNgaygiaodich() %>" maxlength="10" readonly />
											  </TD>
											  														
											</TR>
											
											<TR>
														<TD  class="plainlabel">Nhà phân phối</TD>
														<TD  class="plainlabel" >
															<SELECT disabled class="select2"  name="nppId" id ="nppId" onchange="submitform()">
																<option value=""></option>
																<%  if(nppRs != null)
																	{
																		try
																		{
																		while(nppRs.next())
																		{ 
													    					if(nppRs.getString("pk_seq").equals(dhBean.getNppId()))
													    					{ %>
																				<option value='<%= nppRs.getString("pk_seq") %>' selected><%=nppRs.getString("ten") %></option>
																			<%}else{ %>
																				<option value='<%= nppRs.getString("pk_seq") %>'><%= nppRs.getString("ten") %></option>
																			<%}} nppRs.close(); 
																		}catch(Exception e){e.printStackTrace();} 
																	}	%>	
															</SELECT>
														</TD>
														
														 <TD class="plainlabel">Đơn vị kinh doanh</TD>
															<TD class="plainlabel" colspan="5"> 
												 			<SELECT disabled name="dvkdId" id="dvkdId" onchange="submitform()">
																  <% if(dvkdRs != null){
																	  
																	  try{
																		  
																		  while(dvkdRs.next()){
														    			if(dvkdRs.getString("PK_SEQ").equals(dhBean.getDvkdId())){ session.setAttribute("dvkdId", dvkdRs.getString("PK_SEQ")); %>
														      				<option value='<%= dvkdRs.getString("PK_SEQ") %>' selected ><%= dvkdRs.getString("DIENGIAI") %></option>
														      			<%}else{%>
														      				<option value='<%= dvkdRs.getString("PK_SEQ") %>' ><%= dvkdRs.getString("DIENGIAI") %></option>
														      			<% }
														    			
																		  }
																		  
																	  }catch(java.sql.SQLException e){} 
														      			
																  }%>
			                                    			</SELECT>
			                                    		</TD>
														
										 		 </TR>
										
										
											                                    
											<TR class="tblightrow">
												<TD class="plainlabel">Kho hàng </TD>
												<TD class="plainlabel"> 
									 			<SELECT disabled name="khoTen" id="khoTen" onchange="submitform()">
													  <% if(kho != null){
														  try{while(kho.next()){
											    			if(kho.getString("khoId").equals(dhBean.getKhoId())){ session.setAttribute("khoId", kho.getString("khoId")); %>
											      				<option value='<%= kho.getString("khoId") %>' selected onMouseover="ddrivetip('<%= kho.getString("diengiai") %>', 300)"; onMouseout="hideddrivetip()"><%= kho.getString("Ten") + " " %></option>
											      			<%}else{ if(kho.getString("Ten").indexOf("PR") < 0){ %>
											     				<option value='<%= kho.getString("khoId") %>' onMouseover="ddrivetip('<%= kho.getString("diengiai") %>', 300)"; onMouseout="hideddrivetip()"><%= kho.getString("Ten") + " " %></option>
											     			<%}}}}catch(java.sql.SQLException e){} }%>
                                    			</SELECT></TD>
                                    			 <TD class="plainlabel"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>
															<TD class="plainlabel" colspan="5"> 
												 			<SELECT disabled name="kbhId" id="kbhId" onchange="submitform()">
																  <% if(kbhRs != null){
																	  try{while(kbhRs.next()){
														    			if(kbhRs.getString("PK_SEQ").equals(dhBean.getKbhId())){ session.setAttribute("kbhId", kbhRs.getString("PK_SEQ")); %>
														      				<option value='<%= kbhRs.getString("PK_SEQ") %>' selected onMouseover="ddrivetip('<%= kbhRs.getString("DIENGIAI") %>', 300)"; onMouseout="hideddrivetip()"><%= kbhRs.getString("DIENGIAI") + " " %></option>
														      			<%}else{%>
														      				<option value='<%= kbhRs.getString("PK_SEQ") %>' onMouseover="ddrivetip('<%= kbhRs.getString("DIENGIAI") %>', 300)"; onMouseout="hideddrivetip()"><%= kbhRs.getString("DIENGIAI") + " " %></option>
														      			<% }}}catch(java.sql.SQLException e){} }%>
			                                    			</SELECT>
			                                    		</TD>                                   			
																						
											</TR>
											

										<TR>
						                	<TD class="plainlabel" >Ghi chú </TD>
						                    <TD class="plainlabel" colspan="7" >
						                    	<input type="text"  name="ghichu" value="<%= dhBean.getGhiChu() %>" style="width: 700px;" />
						                    </TD>
						                </TR>
						                	
						                	
						                
						                <TR class="tblightrow">
						                	<TD class="plainlabel" >Gói Sản phẩm </TD>
					                		<TD class="plainlabel" >
					                			<select name="spId"   class="select2"   >
					                            	<option value="" ></option>
					                            	<% if(spRs != null) { 
					                            		
					                            		while(spRs.next())
					                            		{
					                            			if(spRs.getString("pk_seq").equals(dhBean.getSpId())) {
					                            			 %>
					                            				<option value="<%= spRs.getString("pk_seq") %>" selected="selected" ><%= spRs.getString("ten") %></option>
					                            			<% } else { %> 
					                            				<option value="<%= spRs.getString("pk_seq") %>" ><%= spRs.getString("ten") %></option>
					                            			<% }
					                            	} } %>
					                            </select>
					                		</TD>
					                		<TD class="plainlabel" >Số lô</TD>
					                		<TD class="plainlabel" >
					                			<input type="text" name="solo" value="<%= dhBean.getSolo() %>" style="width: 100%" > 
					                		</TD>
					                		<TD class="plainlabel">Ngày hết hạn</TD>
					                		<TD class="plainlabel" >
					                			<input type="text" name="ngayhethan" value="<%=dhBean.getNgayhethan() %>" style="width: 100%" readonly="readonly"    class="days" size="11"> 
					                		</TD>
					                		<TD class="plainlabel">Số lượng</TD>
					                		<TD class="plainlabel" > 
					                			<input type="text" name="soluongbundle" value="<%= dhBean.getSoluong() %>" style="width: 100%; text-align: right;"  > 
					                		</TD>
					                		
					                	</TR>
									
										  										  
										</TABLE>
									</FIELDSET>
								  </TD>
							   </TR>	
							   
							   
							  	  		
							   <TR>
							   		<TD>
										<TABLE width = "100%" border="0" cellpadding="0" cellspacing="1" id="tbSanPham">
										<tbody id="san_pham">
												<TR class="tbheader" >
													<TH width="10%">Mã nguyên liệu</TH>
													<TH width="20%">Tên nguyên liệu</TH>
													<TH width="8%">Tồn hiện tại</TH>
													<TH width="7%">Số lượng</TH>
													<TH width="8%">ĐVT</TH>
													 <TH width="5%">Chọn lô</TH>
												</TR>
									<% 
							int m = 0;
							if(splist != null){
							ISanpham sanpham = null;
							int size = splist.size();
							while (m < size){
								sanpham = splist.get(m); 
								%>
									<TR class= 'tblightrow' >
										<TD align="left" >
											<input name="masp" type="text" value="<%=sanpham.getMasanpham()%>" onkeyup="ajax_showOptions(this,'abc',event)" style="width:100%" AUTOCOMPLETE="off">
										</TD>
									<TD align="left" >
										<input name="tensp1" type="text" disabled="disabled" value="<%=sanpham.getTensanpham()%>" style="width:100%; color: black; cursor:pointer;" >			        	
								        <input name="tensp" type="hidden" value="<%=sanpham.getTensanpham()%>" style="width:100%" >
									</TD>
									<TD align = "center" >
								    	<input name="tonkho1" disabled="disabled" type="text" value="<%= formatter.format(Math.round(Double.parseDouble(sanpham.getTonhientai().replaceAll(",", "")))) %>"  style="width:100%;text-align:center; color: black;">
								    	<input name="tonkho" type="hidden" value="<%= sanpham.getTonhientai() %>"  style="width:100%;text-align:center">
								    </TD>	
						        	<% if (spThieuList.containsKey(sanpham.getMasanpham())){ %>

									    <TD align = "center" ><div class="addspeech" title="San pham nay con toi da <%= spThieuList.get(sanpham.getMasanpham()) %> san pham, vui long chon lai so luong">
								        <input onchange="Capnhatsolo('<%=m%>')" name="soluong" id="soluong_<%= m %>" type="text" value="<%= formatter.format(Math.round(Double.parseDouble(sanpham.getSoluong()))) %>" onkeyup="Dinhdang(this)" style="width:100%; color:red ; cursor:pointer; background-color:#0FF; text-align:right">
								        </div></TD>
								    <%}else{ %>
							        	<TD align = "center" >
								        <input onchange="Capnhatsolo('<%=m%>')" name="soluong" id="soluong_<%= m %>" type="text" value="<%= formatter.format(Math.round(Double.parseDouble(sanpham.getSoluong()))) %>" style="width:100%;text-align:right" onkeyup="Dinhdang(this)">
								        </TD>
								    <%} %>

								    <TD align = "center" >
								    	<input name="donvitinh1" disabled="disabled" type="text" value="<%= sanpham.getDonvitinh() %>"  style="width:100%;text-align:center; color: black;">
								    	<input name="donvitinh" type="hidden" value="<%= sanpham.getDonvitinh() %>"  style="width:100%;text-align:center">
								    </TD>
								
								  
								     <td>
								      <a href="" id="<%=m%>pubup" rel="subcontent<%= m %>">
	           	 							<img alt="Số lô - Vị trí hàng hóa xuất" src="../images/vitriluu.png"></a>
	           	 					<DIV id="subcontent<%= m %>" style=" position:absolute; visibility: hidden; border: 9px solid #80CB9B;
				                             background-color: white; max-height:500px;overflow:auto ; width: 500px; padding: 4px;">
				                    <table width="90%"  align="center">
				                        <tr>
				                            <th width="100px">Số lô</th>
				                            <th width="100px">Ngày nhập kho</th>
				                            <th width="100px">Ngày hết hạn </th>
				                            <th width="50px">Số lượng tồn</th>
				                             <th width="50px">Số lượng </th>
				                        </tr>
				                        <%
				                        List<ISpDetail> spDetailList = sanpham.getSpDetailList();
				                        	int stt = 1; 
				                        	if(spDetailList.size() > 0)
				                        	{
				                        		for(int sd = 0; sd < spDetailList.size(); sd ++)
				                        		{
				                        			ISpDetail spDetail = spDetailList.get(sd);
				                        		%>
				                        			<tr>
							                            <td>
							                            	<input type="text" style="width:100%" name="<%=m+ ".solo" %>" value="<%= spDetail.getSolo() %>" readonly="readonly" /></td>
							                            <td>
							                            	<input type="text" style="width:100%" name="<%=m + ".ngaynhapkho" %>" value="<%= spDetail.getNgaynhapkho() %>" readonly="readonly" /></td>
							                            <td>
							                            	<input type="text" style="width:100%" name="<%= m+ ".ngayhethan" %>" value="<%= spDetail.getNgayhethan() %>" readonly="readonly" /></td>
							                            
							                             <td>
							                            	<input type="text" style="width:100%"  name="<%=  m + ".soluongton" %>" value="<%= spDetail.getSoluongton() %>" readonly="readonly"    /></td>
						                            	 <td>
						                            	<input onchange="Capnhatsoluong_tongtheolo('<%=m%>')" type="text" style="width:100%"  name="<%= m + ".soluong" %>" value="<%= spDetail.getSoluong() %>"  />  
						                            	   
						                            	</td>
						                            	
							                            	
							                        </tr>
				                        		<%}
				                        	}
				                        	if (spDetailList != null)
				                        		spDetailList.clear();
				                        %>
				                    </table>
				                     <div align="right"><a href="javascript:dropdowncontent.hidediv('subcontent<%= m %>');">Hoàn tất</a></div>
				                </DIV>
								    </td>
								
									  
								
								</TR> 
								<% m++; }}%>
																
								<%
									int i = m;
									
									while(i <= (40 + m) ) { //Integer.parseInt(dhBean.getSize())){
								%>
								<TR class= 'tblightrow'>
									<TD align="center" >
										<input name="masp" type="text" value="" onkeyup="ajax_showOptions(this,'abc',event)" style="width:100%" AUTOCOMPLETE="off">
									</TD>
									<TD align="left" >
										<input name="tensp1" type="text" disabled="disabled" value="" style="width:100%; color:black; ">
										<input name="tensp" type="hidden" value="" style="width:100%">
									</TD>
									<TD align = "center" >
								    	<input name="tonkho1" disabled="disabled" type="text"  style="width:100%;text-align:center; color:black; ">
								    	<input name="tonkho" type="hidden"  style="width:100%;text-align:center">
								    </TD>
								    <TD align = "center" >
							        	<input onchange="Capnhatsolo('<%=i%>')"  name="soluong" id="soluong_<%= i %>" type="text" value="" style="width:100%; text-align:right" onkeyup="Dinhdang(this)">
							        </TD>						        
								     <TD align = "center" >
								    	<input name="donvitinh1" disabled="disabled" type="text"  style="width:100%;text-align:center; color:black; ">
								    	<input name="donvitinh" type="hidden"  style="width:100%;text-align:center">
								    </TD>
								    
									 <td>
									      <a href="" id="<%= i %>pubup" rel="subcontent<%= i %>">
		           	 							<img alt="Số lô - Vị trí hàng hóa xuất" src="../images/vitriluu.png"></a>
		           	 						<DIV id="subcontent<%= i %>" style=" position:absolute; visibility: hidden; border: 9px solid #80CB9B;
					                             background-color: white; max-height:500px;overflow:auto ; width: 500px; padding: 4px;">
					                    	 <div align="right"><a href="javascript:dropdowncontent.hidediv('subcontent<%= i %>');">Hoàn tất</a></div>
					                		</DIV>
									    </td>   
								    
								    
								</TR> 
								<% i++;} %>
								</tbody></TABLE>
										&nbsp;&nbsp;&nbsp;&nbsp;<%-- <a class="button2" href="javascript:ThemSanPham(<%=i%>)">
				                         <img style="top: -4px;" src="../images/add.png" alt="">Thêm sản phẩm</a> --%>																																																																																																						
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
<script type="text/javascript">

	
	
	
	<% 
	  m=0;
	if(splist!=null){
		for( i = 0; i < splist.size(); i++)
		{
		%>
			dropdowncontent.init("<%=m%>pubup", "left-top", 500, "click");
		<%
		m++;
		}
	}%>
	

	
	
	
	<%for( i=0;i<40;i++){%>
		 dropdowncontent.init("<%=m%>pubup", "left-top", 500, "click");
		
	<%
	 m++;
	}%>

</script>
</form>
</BODY>

</HTML>
<%
try{
	
	if(kho!=null){
		kho.close();
	}
	spThieuList=null;
	
	if(dhBean != null)
	{
		dhBean.DBclose();
	}
	dhBean=null;
	 s.setAttribute("dhBean",null);
}catch(Exception er){
	er.printStackTrace();
}
%>
