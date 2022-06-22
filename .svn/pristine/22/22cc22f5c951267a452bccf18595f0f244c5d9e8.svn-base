<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geso.dms.center.beans.tieuchithuongkmdiem.imp.*" %>
<%@page import="geso.dms.center.beans.tieuchithuongkmdiem.*" %>
<%@ page  import = "geso.dms.center.beans.dieukienkhuyenmai.ISanpham" %>
<%@ page  import = "geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.List" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.SQLException" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<%
 	ITieuchithuongKMDiem obj =(ITieuchithuongKMDiem)session.getAttribute("tctskuBean");
	ResultSet sanphamRs = obj.getSanphamRs();
	ResultSet nhomSpRs = obj.getNhomsanphamRs();
	ResultSet vungRs = obj.getVungRs();
	ResultSet kvRs = obj.getKhuvucRs();
	ResultSet kenhRs = obj.getKenhRs();
	ResultSet nppRs = obj.getNppRs();
	ResultSet khoRs = obj.getKhoRs();
	String[] Mucphanbo = (String[])obj.gethttt1();
	String[] diengiaiMuc = (String[])obj.getDiengiaiMuc();
	String[] tumuc = (String[])obj.getTumuc();
	String[] denmuc = (String[])obj.getDenmuc();
	String[] thuongSR = (String[])obj.getThuongSR();
	String[] thuongTDSR = (String[])obj.getThuongTDSR();
	
	
	String[] diengiaiMuc3 = (String[])obj.getDiengiaiMuc3();
	String[] thuongSR3 = (String[])obj.getThuongSR3();
	String[] thuongTDSR3 = (String[])obj.getThuongTDSR3();
	String[] thuongSS3 = (String[])obj.getThuongSS3();
	String[] thuongTDSS3 = (String[])obj.getThuongTDSS3();
	String[] thuongASM3 = (String[])obj.getThuongASM3();
	String[] thuongTDASM3 = (String[])obj.getThuongTDASM3();
	
	String[] maspTra = (String[])obj.getMaspTra();
	String[] tenspTra = (String[])obj.getTenspTra();
	String[] soluongspTra = (String[])obj.getSoluongspTra();
	Hashtable<String, String> maspTraTT = (Hashtable<String, String>)obj.getMaspTraTT();
	Hashtable<String, String> tenspTraTT = (Hashtable<String, String>)obj.getTenspTraTT();
	Hashtable<String, String> soluongTT = (Hashtable<String, String>)obj.getSoluongTT();
	Hashtable<String, String> quydoiTT = (Hashtable<String, String>)obj.getQuydoiTT();
	String[] idspTra = (String[])obj.getIdspTra();
	String[] dongiaspTra = (String[])obj.getDongiaspTra();
	
	Hashtable<String, String> muc_tiendo = obj.getMuc_Tiendo();
	Hashtable<String, String> muc_spTRA = obj.getMuc_SpTra();
	Hashtable<String, String> phanbo = obj.getPhanbo();
	
	Hashtable<String, String> dieukien = obj.getDieukien();
	Hashtable<String, String> quydoi = obj.getQuydoi();
	Hashtable<String, String> phanbo1 = obj.getPhanboTheoMucNPP1();
	Hashtable<String, String> phanbo2 = obj.getPhanboTheoMucNPP2();
	Hashtable<String, String> phanbo3 = obj.getPhanboTheoMucNPP3();
	Hashtable<String, String> phanbo4 = obj.getPhanboTheoMucNPP4();
	Hashtable<String, String> phanbo5 = obj.getPhanboTheoMucNPP5();
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
	<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>
	
	<script type="text/javascript" src="../scripts/ajax.js"></script>
	<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
	<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
	
	<link media="screen" rel="stylesheet" href="../css/colorbox.css">
    <script src="../scripts/colorBox/jquery.colorbox.js"></script>
    <script>
        $(document).ready(function()
        {
        	<% for(int i = 0; i < 5; i++)
        	{ %>
        	
        		$(".muc" + <%= i %> ).colorbox({width:"500px", inline:true, href:"#muc" + <%= i %>});
                //Example of preserving a JavaScript event for inline calls.
                $("#click").click(function(){ 
                    $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("DuongBienHoa - Project.");
                    return false;
                });
                
                $(".sanphamTRA" + <%= i %> ).colorbox({width:"600px", inline:true, href:"#sanphamTRA" + <%= i %> });
                //Example of preserving a JavaScript event for inline calls.
                $("#click").click(function(){ 
                    $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("DuongBienHoa - Project.");
                    return false;
                });
        	<% } %>
        	
        });
    </script>
	
	<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
	<style type="text/css">
		#mainContainer{
			width:600px;
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
			z-index:100000000000;
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
			z-index:5000000000000;
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
	<script type="text/javascript" src="../scripts/ajax.js"></script>
	<script type="text/javascript" src="../scripts/dkkhuyenmai_sanpham.js"></script>
	
	<!-- <script type="text/javascript" src="../scripts/ajax.js"></script>
	<script type="text/javascript" src="../scripts/nhomspthuong.js"></script> -->

	<script type="text/javascript">
		$(document).ready(function() {
			$(".days").datepicker({
				changeMonth : true,
				changeYear : true
			});
			
			//$("ul.tabs").tabs("div.panes > div");
		});
	</script> 
	
	<script>
	$(function() {
	 	$("ul.tabs").tabs("div.panes > div");
	});
	</script>
<script>
	$(document).ready(function() {

	    //When page loads...
	    $(".tab_content").hide(); //Hide all content
	    var index = $("ul.tabs li.current").show().index(); 
	    $(".tab_content").eq(index).show();
	    //On Click Event
	    $("ul.tabs li").click(function() {
	  
	        $("ul.tabs li").removeClass("current"); //Remove any "active" class
	        $(this).addClass("current"); //Add "active" class to selected tab
	        $(".tab_content").hide(); //Hide all tab content  
	        var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content  
	        $(activeTab).show(); //Fade in the active ID content
	        return false;
	    });

	});
	</script>

	<SCRIPT language="JavaScript" type="text/javascript">
	function loadSPTheoNhom()
	{
		var active =$(".tabs li.current").index();
		document.forms["tctsku"].activeTab.value = active;
		document.forms["tctsku"].action.value = "loadSP_NHOM";
		document.forms["tctsku"].submit(); 
	}
		
		function replaces()
		{
			var masp = document.getElementsByName("maspTra");
			var tensp = document.getElementsByName("tenspTra");
			var masptt = document.getElementsByName("maspTraTT");
			var tensptt = document.getElementsByName("tenspTraTT");
			
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
						tensp.item(i).value = sp.substr(parseInt(sp.indexOf(" - ")) + 3);					
					}
				}
				else
					tensp.item(i).value = "";
			}
			
			for(i=0; i < masptt.length; i++)
			{
				if(masptt.item(i).value != "")
				{
					var sp = masptt.item(i).value;
					var pos = parseInt(sp.indexOf(" - "));
					if(pos > 0)
					{
						masptt.item(i).value = sp.substring(0, pos);
						tensptt.item(i).value = sp.substr(parseInt(sp.indexOf(" - ")) + 3);					
					}
				}
				else
					tensptt.item(i).value = "";
			}
			
			for(k = 0; k < 5; k++)
			{
				var masp = document.getElementsByName('sanphamTRA' + k + '.masanpham');
				var tensp = document.getElementsByName('sanphamTRA' + k + '.tensanpham');
				
				for(p=0; p < masp.length; p++)
				{
					if(masp.item(p).value != "")
					{
						var sp = masp.item(p).value;

						var pos = parseInt(sp.indexOf(" - "));
						if(pos > 0)
						{
							masp.item(p).value = sp.substring(0, pos);
							tensp.item(p).value = sp.substr(parseInt(sp.indexOf(" - ")) + 3);					
						}
					}
					else
					{
						tensp.item(p).value = "";
					}			
				}
			}

			setTimeout(replaces, 200);
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
	  var thang = document.getElementById("thang").value;
	  var nam = document.getElementById("nam").value;

	  if( thang == '' )
	  {
		  alert("Bạn phải nhập từ ngày");
		  return;
	  }
	  	
	  if( nam == '' )
	  {
		  alert("Bạn phải nhập đến ngày");
		  return;
	  }
	  var active =$(".tabs li.current").index();
		document.forms["tctsku"].activeTab.value = active;
	  document.forms["tctsku"].action.value = "save";
	  document.forms["tctsku"].submit(); 
    }
	
	function submitform()
	{
		var active =$(".tabs li.current").index();
		document.forms["tctsku"].activeTab.value = active;
		document.forms["tctsku"].action.value = "submit";
		document.forms["tctsku"].submit(); 
	}
		
	function FormatNumber(e)
	{
		e.value = DinhDangTien(e.value.replace(/,/g,""));
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
	
	function SelectALL()
	{
		var checkAll = document.getElementById("checkAll");
		var spIds = document.getElementsByName("spIds");
		
		if(checkAll.checked == true)
		{
			for(i = 0; i < spIds.length; i++)
				spIds.item(i).checked = true;
		}
		else
		{
			for(i = 0; i < spIds.length; i++)
				spIds.item(i).checked = false;
		}
		
	}
	
	function SelectALL2()
	{
		var checkAll = document.getElementById("checkAll2");
		var spIds = document.getElementsByName("nppIds");
		
		if(checkAll.checked == true)
		{
			for(i = 0; i < spIds.length; i++)
				spIds.item(i).checked = true;
		}
		else
		{
			for(i = 0; i < spIds.length; i++)
				spIds.item(i).checked = false;
		}
		
	}
	
	function DongLai()
	{
		$("#cboxClose").click();
	}
	
	/* function ajaxOption(id, str)
	{
		String vungId = document.getElementById("vungId").value;
		String kvId = document.getElementById("khuvucId").value;
		
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
		xmlhttp.open("POST","../../TieuchithuongKMDiemSvl?vungId=" + vungId + "&khuvucId=" + kvId + "&type=Ajax",true);
		xmlhttp.send();
	} */

</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="tctsku" method="post" action="../../TieuchithuongKMDiemUpdateSvl" >
<input type="hidden" name="userId" value='<%= userId %>' >
<input type="hidden" name="phanloai" value='<%= obj.getPhanloai() %>' >
<input type="hidden" id="activeTab" name="activeTab" value='<%=obj.getActiveTab()%>'>
<input type="hidden" name="id" value='<%= obj.getId() %>' >
<input type="hidden" name="action" value="0">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý khuyến mại > Khuyến mại theo điểm > Cập nhật</TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %></TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../TieuchithuongKMDiemSvl?userId=<%=userId%>&phanloai=<%= obj.getPhanloai() %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
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
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= obj.getMsg() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Thông tin khuyến mại</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
						   <TR>
								<TD width="120px" class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
								<TD width="250px" class="plainlabel" >
									<input type="text" name="thang" id="thang" class="days" value="<%= obj.getThang() %>" readonly="readonly" >
								</TD>
						  	  	<TD width="120px" class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel" >
									<input type="text" name="nam" id="nam" class="days" value="<%= obj.getNam() %>" readonly="readonly" >
								</TD>
						  </TR>
						  <TR>
						  	  	<TD class="plainlabel">Scheme <FONT class="erroralert"> *</FONT></TD>
						  	  	<TD class="plainlabel" >
						  	  		<input type="text" name="scheme" id="scheme" value="<%= obj.getScheme() %>"> 
						  	  	</TD>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TD>
						  	  	<TD class="plainlabel" >
						  	  		<input type="text" name="diengiai" id="diengiai" value="<%= obj.getDiengiai() %>"> 
						  	  	</TD>
						  </TR>
						  
						 
						  
						</TABLE>
						
						
						<ul class="tabs">
						
							<li <%=obj.getActiveTab().equals("0") ? "class='current'" : "" %>><a href="#tab2"><%=Utility.GLanguage("Sản phẩm",session,jedis) %> mua</a></li>
							<li <%=obj.getActiveTab().equals("1") ? "class='current'" : "" %>><a href="#tab3">Phân bổ</a></li>
						</ul>
						
						<div class="panes">
						<div id="tab2" class="tab_content">
								
								<TABLE class="tabledetail" width="100%" border="0" cellspacing="1px" cellpadding="0px">
				                    
				           <%--          <tr>
				                		<td style="font-size: 12px; padding: 8px; " colspan="2" >Nhóm sản phẩm &nbsp;&nbsp;   
				                		
				                			<select name="nspId" style="width: 200px;" onchange="loadSPTheoNhom();"  >
												<option value="" ></option>
												<%
													if(nhomSpRs != null)
													{
														while(nhomSpRs.next())
														{
															if(obj.getNhomsanphamIds().indexOf(nhomSpRs.getString("pk_seq")) >= 0 )
															{
																%>
																<option value="<%= nhomSpRs.getString("pk_seq") %>" selected="selected"  ><%= nhomSpRs.getString("ten") %></option>
															<% } else { %>
																<option value="<%= nhomSpRs.getString("pk_seq") %>"  ><%= nhomSpRs.getString("ten") %></option>
															<%}
														}
														nhomSpRs.close();
													}
												%>							
											</select>
										</td>
									</tr>	 --%>
				                    
				                    <TR class="tbheader">
				                        <td align="center" width="20%">Mã sản phẩm</td>
				                    	<td align="center" width="35%">Tên sản phẩm</td>
				                		<td align="center" width="20%">Số lượng</td>
				                    	<td align="center" width="25%">Quy đổi</td>
				                
				                    </TR>
				                    
				                    
				                    <%-- <%
				                    	if(sanphamRs != null)
				                    	{
				                    		while(sanphamRs.next())
				                    		{
				                    			%>
				                    			<tr>
				                    				<td><input type="text" value="<%= sanphamRs.getString("ma") %>" style="width: 100%;" readonly="readonly" ></td>
				                    				<td><input type="text" value="<%= sanphamRs.getString("ten") %>" style="width: 100%;" readonly="readonly" ></td>
				                    				<td align="center">
				                    					<% if(obj.getSpIds().contains(sanphamRs.getString("pk_seq"))) { %>
				                    						<input type="checkbox" name="spIds" value="<%= sanphamRs.getString("pk_seq") %>" checked="checked" >
				                    					<% } else { %>
				                    						<input type="checkbox" name="spIds" value="<%= sanphamRs.getString("pk_seq") %>"  >
				                    					<% } %>
				                    				</td>
				                    				
				                    			</tr>
				                    			
				                    		<% }
				                    		sanphamRs.close();
				                    	}
				                    %> --%>
				                    
				                    <% 
				                 int   count = 0;
				                    if(maspTra != null)
				                    {
				                    for(int i = 0; i < maspTra.length; i++) { %>
				                    	<tr>
				                    		<td>
				                    			<input type="text" name="maspTra" style="width: 100%" value="<%= maspTra[i] %>"  onkeyup="ajax_showOptions(this,'abc',event)"  AUTOCOMPLETE="off" >
				                    		</td>
				                    		<td>
				                    			<input type="text" name="tenspTra" style="width: 100%" readonly="readonly" value="<%= tenspTra[i] %>" >
				                    		</td>	
											<td style="display: none">
				                    			<input type="text" name="maspTraTT" style="width: 100%" value="<%= maspTraTT.get(maspTra[i]) %>" onkeyup="ajax_showOptions(this,'abc',event)"  AUTOCOMPLETE="off"  >
				                    		</td>		
											<td style="display: none">
				                    			<input type="text" name="tenspTraTT" style="width: 100%" value="<%= tenspTraTT.get(maspTra[i]) %>" readonly="readonly" >
				                    		</td>	
				                    		<td>
				                    			<input type="text" name="soluongTT" style="width: 100%" value="<%= soluongTT.get(maspTra[i]) %>"  >
				                    		</td>
				                    		<td>
				                    			<input type="text" name="quydoiTT" style="width: 100%" value="<%= quydoiTT.get(maspTra[i]) %>"  >
				                    		</td>	
				                    	</tr>
				                    	 <% count++; } } %>
				                   <%
				                    	while(count < 50)
				                    	{
				                    		%>
				                    		
				                    		<tr>
					                    		<td>
					                    			<input type="text" name="maspTra" style="width: 100%" value=""  onkeyup="ajax_showOptions(this,'abc',event)"  AUTOCOMPLETE="off" >
					                    		</td>
					                    		<td>
					                    			<input type="text" name="tenspTra" style="width: 100%" readonly="readonly" value="" >
					                    		</td>
					                    		<td style="display: none">
					                    			<input type="text" name="maspTraTT" style="width: 100%" value=""  onkeyup="ajax_showOptions(this,'abc',event)"  AUTOCOMPLETE="off" >
					                    		</td>
					                    		<td style="display: none">
					                    			<input type="text" name="tenspTraTT" style="width: 100%" readonly="readonly" value="" >
					                    		</td>
					                    		<td>
					                    			<input type="text" name="soluongTT" style="width: 100%"  value="" >
					                    		</td>
					                    		<td>
					                    			<input type="text" name="quydoiTT" style="width: 100%"  value="" >
					                    		</td>
					                    	</tr>
				                    		
				                    <% count++; } %>
									
				                    <TR>
				                        <TD align="center" colspan="15" class="tbfooter">&nbsp;</TD>
				                    </TR>
								</TABLE>
								
							</div>
							
									
							<div id="tab3" class="tab_content">
								
								<TABLE class="tabledetail" width="100%" border="0" cellspacing="1px" cellpadding="0px">
				                    
				                    <tr>
				                		<td style="font-size: 12px; padding: 8px; " colspan="2" valign="middle" >
				                	<b><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></b> &nbsp;&nbsp;   
				                		
				                			<select name="kenhId" style="width: 200px;" multiple="multiple"  >
												<option value="1" >Tất cả</option>
												<%
													if(kenhRs != null)
													{
														while(kenhRs.next())
														{
															if(obj.getKenhIds().indexOf(kenhRs.getString("pk_seq")) >= 0 )
															{
																%>
																<option value="<%= kenhRs.getString("pk_seq") %>" selected="selected"  ><%= kenhRs.getString("ten") %></option>
															<% } else { %>
																<option value="<%= kenhRs.getString("pk_seq") %>"  ><%= kenhRs.getString("ten") %></option>
															<%}
														}
														kenhRs.close();
													}
												%>							
											</select>
				                		&nbsp;&nbsp; 
				                	<b><%=Utility.GLanguage("Vùng",session,jedis) %> </b>&nbsp;&nbsp;   
				                		
				                			<select name="vungId" style="width: 200px;" multiple="multiple"  >
												<option value="" >Tất cả</option>
												<%
													if(vungRs != null)
													{
														while(vungRs.next())
														{
															if(obj.getVungIds().indexOf(vungRs.getString("pk_seq")) >= 0 )
															{
																%>
																<option value="<%= vungRs.getString("pk_seq") %>" selected="selected"  ><%= vungRs.getString("ten") %></option>
															<% } else { %>
																<option value="<%= vungRs.getString("pk_seq") %>"  ><%= vungRs.getString("ten") %></option>
															<%}
														}
														vungRs.close();
													}
												%>							
											</select>
				                		&nbsp;&nbsp; 
				                	<b><%=Utility.GLanguage("Khu vực",session,jedis) %> </b>
				                		&nbsp;&nbsp; 
				                			<select name="khuvucId" style="width: 200px;" multiple="multiple" >
												<option value="" >Tất cả</option>
												<%
													if(kvRs != null)
													{
														while(kvRs.next())
														{
															if(obj.getKhuvucIds().indexOf(kvRs.getString("pk_seq")) >= 0 )
															{
																%>
																<option value="<%= kvRs.getString("pk_seq") %>" selected="selected"  ><%= kvRs.getString("ten") %></option>
															<% } else { %>
																<option value="<%= kvRs.getString("pk_seq") %>"  ><%= kvRs.getString("ten") %></option>
															<%}
														}
														kvRs.close();
													}
												%>							
											</select>
				                		</td>	
			                		</tr>
			                		<tr>
				                		<td colspan="2" valign="middle" >
				                			<a class="button" href="javascript:submitform();">
        										<img style="top: -4px;" src="../images/button.png" alt=""> Hiển thị Npp theo điều kiện</a>
				                		</td>
				                		
				                    </tr>
				                    	<%-- <tr align="center">
				                		
				                		</tr> --%>
				                    <TR class="tbheader">
				                        <td align="center" width="20%">Mã nhà phân phối</td>
				                    	<td align="center" width="60%">Tên nhà phân phối</td>
				                    	<td align="center" width="10%">Chọn <input type="checkbox" name="checkAll2" id="checkAll2" onchange="SelectALL2();" > </td>
				                    </TR>
				                    <%
				                    try{
				                    	if(nppRs != null)
				                    	{
				                    		while(nppRs.next())
				                    		{
				                    				
				                    					%>
				                    					<tr>
					                    					<td>
					         									<input type="hidden" name="nppIds_ma" value="<%= nppRs.getString("pk_seq") %>"  >
					                    						<input type="text" value="<%= nppRs.getString("ma") %>" style="width: 100%;" readonly="readonly" >
					                    					</td>
					                    					<td>
					                    						<input type="text" value="<%= nppRs.getString("ten") %>" style="width: 100%;" readonly="readonly" >
					                    					</td>
					                    					
					                    					<td align="center">
						                    					<% if(obj.getNppIds().contains(nppRs.getString("pk_seq"))) { %>
						                    						
						                    						<input type="checkbox" name="nppIds" value="<%= nppRs.getString("pk_seq") %>" checked="checked" >
						                    					<% } else { %>
						                    						<input type="checkbox" name="nppIds" value="<%= nppRs.getString("pk_seq") %>"  >
						                    					<% } %>
					                    							
					                    					</td>
				                    				
				                    					</tr>
				                    			
				                    		<% }
				                    		nppRs.close();
				                    	}
				                    }
				                    catch(Exception ex)
				                    {
				                    	ex.printStackTrace();
				                    }
				                    %>
									
				                    <TR>
				                        <TD align="center" colspan="15" class="tbfooter">&nbsp;</TD>
				                    </TR>
								</TABLE>
								
							</div>

						
						
						</div>
									
						</FIELDSET>						
					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
	</TABLE>
</form>
<script type="text/javascript">
	replaces();
</script>
</BODY>
</HTML>
<%}%>
 