<%@page import="com.itextpdf.text.pdf.AcroFields.Item"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geso.dms.center.beans.tieuchithuong.imp.*" %>
<%@page import="geso.dms.center.beans.tieuchithuong.*" %>
<%@ page  import = "geso.dms.center.beans.dieukienkhuyenmai.ISanpham" %>
<%@ page  import = "geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.SQLException" %>
<%@ page import = "java.text.DecimalFormat" %>
<%@ page import = "java.text.NumberFormat" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{ %>

<%
 	ITieuchithuongTL obj =(ITieuchithuongTL)session.getAttribute("tctskuBean");
	
	ArrayList<ITichLuyItem> tichluyItemList = obj.getTichluyItemList();
	ArrayList<String> spMuaList = obj.getSpMuaList();
	
	ResultSet nhomSpRs = obj.getNhomsanphamRs();

	ResultSet khoRs = obj.getKhoRs();
	NumberFormat format = new DecimalFormat("#,###,###");
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
        	<% for(int i = 0; i < tichluyItemList.size(); i++)
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
	
	function newline()
	{
		var active =$(".tabs li.current").index();
		document.forms["tctsku"].activeTab.value = active;
		document.forms["tctsku"].action.value = "newline";
		document.forms["tctsku"].submit(); 
	}
	function remove(i)
	{
		var active =$(".tabs li.current").index();
		document.forms["tctsku"].activeTab.value = active;
		document.forms["tctsku"].action.value = "remove";
		document.forms["tctsku"].itemRemove.value = i;
		document.forms["tctsku"].submit(); 
	}
		
		function replaces()
		{
			var masp = document.getElementsByName("maspTra");
			var tensp = document.getElementsByName("tenspTra");
			var slspTra = document.getElementsByName("slspTra");
			/* var masptt = document.getElementsByName("maspTraTT");
			var tensptt = document.getElementsByName("tenspTraTT");
			 */
			var i;
			// alert(masp.length);
			for(i=0; i < masp.length; i++)
			{
			
				if(masp.item(i).value != "")
				{
					//alert(i);
					var sp = masp.item(i).value;
					var pos = parseInt(sp.indexOf(" - "));
					if(pos > 0)
					{
						masp.item(i).value = sp.substring(0, pos);
						tensp.item(i).value = sp.substr(parseInt(sp.indexOf(" - ")) + 3);					
					}
				}
				 else
					 {
						tensp.item(i).value = ''; 
						slspTra.item(i).value = '';
					 }
			}
			
			/* for(i=0; i < masptt.length; i++)
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
			} */
			
			for(k = 0; k < <%=tichluyItemList.size() %>; k++)
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
			//alert('hello');
			setTimeout(replaces, 200);
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
				if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
				{//Ph??m Delete v?? Ph??m Back
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
		  alert("B???n ph???i nh???p t??? ng??y");
		  return;
	  }
	  	
	  if( nam == '' )
	  {
		  alert("B???n ph???i nh???p ?????n ng??y");
		  return;
	  }
	  var tungay = document.getElementById("tungay").value;
	  var denngay = document.getElementById("denngay").value;

	  if( tungay == '' )
	  {
		  alert("B???n ph???i nh???p t??? ng??y DS");
		  return;
	  }
	  	
	  if( denngay == '' )
	  {
		  alert("B???n ph???i nh???p ?????n ng??y DS");
		  return;
	  }
	  
	  var active =$(".tabs li.current").index();
		document.forms["tctsku"].activeTab.value = active;
	  document.forms["tctsku"].action.value = "save";
	  document.forms["tctsku"].submit(); 
    }
	
	function xuatexcel()
	{
	  
	  	var active =$(".tabs li.current").index();
		document.forms["tctsku"].activeTab.value = active;
	  document.forms["tctsku"].action.value = "excel";
	  document.forms["tctsku"].submit(); 
    }
	


	function upload(){
		
			var active =$(".tabs li.current").index();
			document.forms["tctsku"].activeTab.value = active;
	 	 	document.forms["tctsku"].action.value = "upload";
	  		document.forms["tctsku"].submit(); 
		 	document.forms["tctsku"].dataerror.value=" ";		 
			if(document.forms["tctsku"].filename.value==""){				   
				   document.forms["tctsku"].dataerror.value="Ch??a t??m file upload l??n. Vui l??ng ch???n file c???n upload.";
			   }else
			   {
				 document.forms["tctsku"].setAttribute('enctype', "multipart/form-data", 0);
				 document.forms["tctsku"].submit();	
			   }
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
		xmlhttp.open("POST","../../TieuchithuongTLSvl?vungId=" + vungId + "&khuvucId=" + kvId + "&type=Ajax",true);
		xmlhttp.send();
	} */

</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="tctsku" method="post" action="../../TieuchithuongTLUpdateSvl" >
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>' >
<input type="hidden" name="phanloai" value='<%= obj.getPhanloai() %>' >
<input type="hidden" id="activeTab" name="activeTab" value='<%=obj.getActiveTab()%>'>
<input type="hidden" name="id" value='<%= obj.getId() %>' >
<input type="hidden" name="itemRemove" value='-1' >
<input type="hidden" name="action" value="0">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? khuy???n m???i > T??ch l??y > Khuy???n m???i t??ch l??y  > C???p nh???t</TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %></TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href= "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuchithuongTLSvl?userId="+userId+"&phanloai="+ obj.getPhanloai()+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
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
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= obj.getMsg() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Th??ng tin khuy???n m??i</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
						   <TR>
								<TD width="120px" class="plainlabel" >Ng??y ????ng k?? <br />&nbsp;&nbsp;&nbsp;&nbsp; T??? ng??y<FONT class="erroralert"> *</FONT></TD>
								<TD width="250px" class="plainlabel" >
									<input type="text" name="thang" id="thang" class="days" value="<%= obj.getThang() %>" readonly="readonly" >
								</TD>
						  	  	<TD width="120px" class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel" >
									<input type="text" name="nam" id="nam" class="days" value="<%= obj.getNam() %>" readonly="readonly" >
								</TD>
						  </TR>
						  <TR>
								<TD width="120px" class="plainlabel" >Ng??y t??nh DS <br />&nbsp;&nbsp;&nbsp;&nbsp; T??? ng??y<FONT class="erroralert"> *</FONT></TD>
								<TD width="250px" class="plainlabel" >
									<input type="text" name="tungay"  id="tungay" class="days" value="<%= obj.getNgayDS_Tungay() %>" readonly="readonly" >
								</TD>
						  	  	<TD width="120px" class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel" >
									<input type="text" name="denngay" id="denngay" class="days" value="<%= obj.getNgayDS_Denngay() %>" readonly="readonly" >
								</TD>
						  </TR>
						 
						  <TR>
						  	  	<TD class="plainlabel">Scheme <FONT class="erroralert"> *</FONT></TD>
						  	  	<TD class="plainlabel" >
						  	  		<input type="text" name="scheme" id="scheme" value="<%= obj.getScheme() %>"> 
						  	  	</TD>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TD>
						  	  	<TD class="plainlabel" colspan="3">
						  	  		<input type="text" name="diengiai" id="diengiai" value="<%= obj.getDiengiai() %>"> 
						  	  	</TD>
						  	  
						  </TR>
						 
						  
						  <tr>
		                		<td class="plainlabel" >Kho tr??? <FONT class="erroralert"> *</FONT> </td>
		                		<TD class="plainlabel" colspan="1" >
		                			<select name="khoId" style="width: 200px;"   >
										<option value="" ></option>
										<%
											if(khoRs != null)
											{
												while(khoRs.next())
												{
													if(obj.getKhoIds().indexOf(khoRs.getString("pk_seq")) >= 0 )
													{
														%>
														<option value="<%= khoRs.getString("pk_seq") %>" selected="selected"  ><%= khoRs.getString("ten") %></option>
													<% } else { %>
														<option value="<%= khoRs.getString("pk_seq") %>"  ><%= khoRs.getString("ten") %></option>
													<%}
												}
												khoRs.close();
											}
										%>							
									</select>
		                		</td>	
		                		
		                		<TD class="plainlabel">Ghi ch??</TD>
						  	  	<TD class="plainlabel" colspan="2">
						  	  		<input type="text" name="ghichu" id="ghichu" value="<%= obj.getGhichu() %>"> 
						  	  		
						  	  	</TD>
		                    </tr>
		                    
		                     <Tr>
		                    	<TD class="plainlabel"> T??nh theo </TD>
		                    	<TD class="plainlabel" colspan="4" >
		                			<select name="tinhtheosl" style="width: 200px;"   >
										
										<%
											String[] data = { "Doanh s???","S??? l?????ng","??i???m" };
										
											for(int x=0 ; x< data.length;x++)
											{
												System.out.println("x =   "+ x +", getTinhtheoSl = "+ obj.getTinhtheoSl());
												if((x+"").equals(obj.getTinhtheoSl()) )
												{
													%>
													<option value="<%= x %>" selected="selected"  ><%=data[x] %></option>
												<% } else { %>
													<option value="<%= x%>"  ><%=data[x] %></option>
												<%}
											}
										%>							
									</select>
		                		</td>	
		                    </Tr>
		                    
		                   <tr style="display: none;"  class="plainlabel">
						   		<td >
						  	  		<a class="button2" href="javascript:xuatexcel()">
											<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>
						  	  	</td> 
						  	  
						  	  <td colspan="3">
						  	  	<INPUT type="file" name="filename" size="40" value=''> 
						  	  		 <a class="button2" href="javascript:upload()">
											<img style="top: -4px;" src="../images/button.png" alt="">Ph??n b???</a>
						  	  </td>
						  	</tr>	
						</TABLE>
						
						
						<ul class="tabs">
							<li <%=obj.getActiveTab().equals("0") ? "class='current'" : "" %>><a href="#tab1">Ti??u ch?? t??ch l??y</a></li>
							<li <%=obj.getActiveTab().equals("1") ? "class='current'" : "" %>><a href="#tab2"><%=Utility.GLanguage("S???n ph???m",session,jedis) %> mua</a></li>			
						</ul>
						
						<div class="panes">
							
							<div id="tab1" class="tab_content">
								
								<TABLE class="tabledetail" width="100%" border="0" cellspacing="1px" cellpadding="0px">
				                    
				                    <TR height="4px" />
				                    <TR >
				                        <TH align="left" width="25%" colspan="6" >
				                        	<a class="button3" href="javascript:newline()">
                           						<img style="top: -4px;" src="../images/New.png" alt="">Th??m</a> 
				                        </TH>
										
				                    </TR>
				                    
				                    <TR class="tbheader">
				                        <TH align="center" width="2%" rowspan="2" >X??a</TH>
				                        <TH align="center" width="28%" rowspan="2" >M???c t??ch l??y</TH>
										<TH align="center" width="30%" colspan="2" >Doanh s??? ph??t sinh</TH>
				                        <TH align="center" width="15%" rowspan="2" >Tr??? t??ch l??y</TH>
				                        <TH align="center" width="15%" rowspan="2" >????n v???</TH>
				                    </TR>
			                    
				                    <TR class="tbheader">
					                  	<TH align="center" width="15%" >T??? m???c</TH>
				                        <TH align="center" width="15%" >?????n m???c</TH>
				                     	
				                    </TR>
				                    <% for(int i  = 0; i < tichluyItemList.size(); i ++) 
				                    {
				                    	ITichLuyItem item = tichluyItemList.get(i);
				                    %>
					                    <TR >
					                    	<td align="center" >
												 <a href="javascript:remove(<%=i%>)">
												 	<img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 ></A>									
							                    	
											</td>
						                  	<td>
												<input type="text" name="diengiaiMuc" value="<%= item.getDiengiai() %>" readonly="readonly"  >
											</td>
											<td>
													<input type="text" name="tumuc" value="<%= item.getTumucStr() %>" style="text-align: right;" onkeyup="FormatNumber(this);" onkeypress="return keypress(event);" >
											</td>
											<td>
													<input type="text" name="denmuc" value="<%= item.getDenmucStr() %>" style="text-align: right;" onkeyup="FormatNumber(this);" onkeypress="return keypress(event);" >
											</td>
											<td>
												<input type="text" name="chietkhau" value="<%= item.getTratichluyStr() %>" style="text-align: right;" onkeyup="FormatNumber(this);"  onkeypress="return keypress(event);" >
											</td>
											<td>
														<select name="donvi" style="width: 80%;" >
																<option value="0" <%= item.getLoaitra() == 0 ? "selected":""  %>    > % </option>
																<option value="1" <%= item.getLoaitra() == 1 ? "selected":""  %>    > VN?? </option>
																<option value="2" <%= item.getLoaitra() == 2 ? "selected":""  %>    > S???n ph???m </option>							
														</select>
														
														<a class="sanphamTRA<%= i %>" href="#">
							                        		<img style="top: -4px;" src="../images/vitriluu.png" title="S???n ph???m tr??? t??ch l??y"></a>
											                <div style='display:none; '>
										                        <div id='sanphamTRA<%= i %>' style='padding:0px 5px; background:#fff;'>
										                        	<h4 align="left"><%=Utility.GLanguage("S???n ph???m",session,jedis) %> tr??? m???c ( <%= i + 1 %> ) </h4>
																	<table cellpadding="4px" cellspacing="2px" width="100%" align="center">
										                            <tr>
									                                	<td width="150px" valign="top" align="left"><span style="font-size: 12px">H??nh th???c</span>
									                                	</td>
									                                    <td valign="top" align="left">
										                                    <select name="hinhthuc" id="hinhthuctra" style="width: 200px;" >
													                			<%-- <option value="0" <%= item.getHinhthuc() == 0 ? "selected":""  %>  >B???t k??? trong</option> --%>
													                			<option value="1" <%= item.getHinhthuc() == 1 ? "selected":""  %>  >Nh???p s??? l?????ng s???n ph???m</option>			
																			</select>
									                                    </td>
									                                </tr>
									                                
									                                
										                            <tr>
										                                	<td colspan="2">
										                                		<table align="left" cellpadding="0px" cellspacing="1px">
											                                		<tr class="tbheader">
											                                			<td width="100px" align="center" ><span style="font-size: 0.9em">M?? s???n ph???m</span></td>
											                                			<td width="300px" align="center" ><span style="font-size: 0.9em">T??n s???n ph???m</span></td>		                                			
											                                			<td width="100px" align="center" ><span style="font-size: 0.9em">S??? l?????ng</span></td>
											                                		</tr>
											                                	</table>
											                                	<div id="sanphamTRA<%= i %>.tbsanpham" style="width: 100%; max-height: 250px; overflow: auto">
											                                	<table align="left" cellpadding="0px" cellspacing="1px">
											                                	
											                                	<%
										                                			int count2 = 0;
										                                			if(item.getSanPhamRs() != null)
										                                			{ 
										                                				while(item.getSanPhamRs().next())
										                                				{
										                                					String soluong = "";
										                                					for(int xx = 0; xx < item.getSpList().size(); xx ++)
										                                					{
										                                						if(  item.getSpList().get(xx)[0].toString().equals( item.getSanPhamRs().getString("ma") ) )
										                                						{
										                                							soluong =  item.getSpList().get(xx )[1].toString().trim().length() > 0 ? item.getSpList().get(xx )[1].toString() :"";
										                                							break;
										                                						}
										                                					}
										                                				%>
										                                					
										                                					<tr>
										                                						
													                                			<td width="100px" align="center">
													                                				<input type="text" value="<%= item.getSanPhamRs().getString("ma") %>" style="width: 100px" name="sanphamTRA<%= i %>.masanpham" 
													                                						onkeyup="ajax_showOptions(this,'abc',event)" AUTOCOMPLETE="off">
													                                			</td>
													                                			<td width="300px" align="left">
													                                				<input type="text" value="<%= item.getSanPhamRs().getString("ten")  %>" name="sanphamTRA<%= i %>.tensanpham" style="width: 300px" readonly="readonly">
													                                			</td>                   			
													                                			<td width="100px" align="left">
													                                				<input type="text" value="<%= soluong %>" name="sanphamTRA<%= i %>.soluong" style="width: 100px; text-align: right;" onkeypress="return keypress(event);" >
													                                			</td>
													                                		</tr>
										                                					
										                                				<% 	count2++; } 
										                                			} %>
											                                	
											                                	<% for(int pos = count2; pos < 10; pos++){ %>
											                                		<tr>
											                                			<td width="100px" align="center">
											                                				<input type="text" value="" style="width: 100px" name="sanphamTRA<%= i %>.masanpham" 
											                                						onkeyup="ajax_showOptions(this,'abc',event)" AUTOCOMPLETE="off">
											                                			</td>
											                                			<td width="300px" align="left">
											                                				<input type="text" value="" name="sanphamTRA<%= i %>.tensanpham" style="width: 300px" readonly="readonly">
											                                			</td>                   			
											                                			<td width="100px" align="left">
											                                				<input type="text" value="" name="sanphamTRA<%= i %>.soluong" style="width: 100px; text-align: right;" onkeypress="return keypress(event);" >
											                                			</td>
											                                		</tr>
											                                	<%} %>
										                                		</table>
										                                		</div>
										                                	</td>
										                                </tr>
										                                <tr>
										                                	<td valign="top" align="left" colspan="2">
										        								<a class="button" href="javascript:DongLai();">
										        								<img style="top: -4px;" src="../images/button.png" alt=""> ????ng l???i </a>
										                                	</td>
										                                </tr>
										                            </table>
																</div>
											                </div>
														
													</td>	
														
					                    </TR>
				                    
				                    <%} %>
				                    
				                    
									
				                    <TR>
				                        <TD align="center" colspan="15" class="tbfooter">&nbsp;</TD>
				                    </TR>
								</TABLE>
								
							</div>
			
							
						<div id="tab2" class="tab_content">
								
								<TABLE class="tabledetail" width="100%" border="0" cellspacing="1px" cellpadding="0px">
				                    
				                    <tr>
				                		<td style="font-size: 12px; padding: 8px; " colspan="2" >Nh??m s???n ph???m &nbsp;&nbsp;   
				                		
				                			<select name="nspId" style="width: 200px;" onchange="loadSPTheoNhom();"  >
												<option value="" ></option>
												<%
													if(nhomSpRs != null)
													{
														while(nhomSpRs.next())
														{
															 %>
														<option value="<%= nhomSpRs.getString("pk_seq") %>" <%= obj.getNhomsanphamIds().contains(nhomSpRs.getString("pk_seq"))  ? "selected":""  %>   ><%= nhomSpRs.getString("ten") %></option>
															<%
														}
														nhomSpRs.close();
													}
												%>							
											</select>
										</td>
									</tr>	
				                    
				                    <TR class="tbheader">
				                        <td align="center" width="20%" >M?? s???n ph???m</td>
				                    	<td align="center" width="35%" >T??n s???n ph???m</td>
				                    	<td align="center" width="10%" >??i???m th?????ng</td> 
				                    <% 
				                    int count = 0;
				                    if(obj.getSanphamMuaRs() != null)
				                    {
					                   while(obj.getSanphamMuaRs().next()) 
					                   { %>
					                    	<tr>
					                    		<td>
					                    			<input type="text" name="maspTra" style="width: 100%" value="<%= obj.getSanphamMuaRs().getString("ma") %>"  onkeyup="ajax_showOptions(this,'abc',event)"  AUTOCOMPLETE="off" >
					                    		</td>
					                    		<td>
					                    			<input type="text" name="tenspTra" style="width: 100%" readonly="readonly" value="<%= obj.getSanphamMuaRs().getString("ten") %>" >
					                    		</td>	
												
												<td>
					                    			<input type="text" name="slspTra" style="width: 100%"  value="<%=format.format( obj.getSanphamMuaRs().getDouble("sl")) %>"  onkeyup="FormatNumber(this);"  onkeypress="return keypress(event);" >
					                    		</td>
											
					                    	</tr>
					                    	 <% count++; } 
				                    } %>
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
					                    		<td>
					                    			<input type="text" name="slspTra" style="width: 100%"  value=""   onkeyup="FormatNumber(this);"  onkeypress="return keypress(event);">
					                    		</td>
					                    		
					                    	</tr>
				                    		
				                    <% count++; } %>
									
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
<%
	obj.getDb().shutDown();
	
	
	}%>
 