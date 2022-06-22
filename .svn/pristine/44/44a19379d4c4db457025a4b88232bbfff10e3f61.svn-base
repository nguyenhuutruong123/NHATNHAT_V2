<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.khuyenmaidacbiet.*" %>
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

<% IKhuyenMaiDacBiet dhBean = (IKhuyenMaiDacBiet)s.getAttribute("dhBean"); 
   /* IDonhangList dhlist = (IDonhangList)s.getAttribute("dhList"); */
%>
<% List<ISanpham> splist = (List<ISanpham>)dhBean.getSpList(); %>

<% String userId = (String) s.getAttribute("userId");  %>

<% Utility Util = new Utility(); %>
<%NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<%NumberFormat formatter2 = new DecimalFormat("#,###,###.####");
String userTen = (String) session.getAttribute("userTen"); 


String loaipage = "Tạo mới";
if(dhBean.getIsDisplay()==1)
	loaipage = "Hiển thị";
else if( dhBean.getId()!= null && dhBean.getId().length() > 0 )
	loaipage = "Cập nhật";
	

String url = Util.getChuyenNguUrl("KhuyenMaiDacBietSvl", "",session);

	
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
/* #colorbox { top: 100px !important; } */

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
		if(v!= '' )
		{
			v= v.replace(/,/g, "");
			if(parseFloat(v) > 999)
				return 999;
		}
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
		  var input, filter, table, tr, td, i, txtValue,txtValue2;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  
		  if(table.rows.length > 1)
		  for (i = 1; i <table.rows.length ; i++) {
			  
		      var r=table.rows[i];
		      txtValue = table.rows[i].cells[0].children[0].value;
		      txtValue2 = table.rows[i].cells[1].children[0].value;
		      if (txtValue.toUpperCase().indexOf(filter) > -1 || txtValue2.toUpperCase().indexOf(filter) > -1) {
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
	function ThemSp(idSp,maSp,tenSp) 
	{
		
		var nppChonSP = document.getElementById("nppChonSP").value; 
		
		
		
		document.getElementById(  nppChonSP + "_idsp" ).value=  idSp;
		document.getElementById("nutmopopup" +  nppChonSP ).innerText=  maSp + ' - ' +  tenSp;
		
		document.getElementById("nppChonSP").value = '0';
		document.getElementById("myInput").value= '';
		
		searchSp();
		
		$.colorbox.close();
		
	}
	
	function saveform()
	{
		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['dhForm'].action.value='save';
	     document.forms['dhForm'].submit();	
	}
	
	function next_bar(e)
	{
		
		var barcode = document.getElementsByName("barcode");
		var arr = Array.prototype.slice.call(barcode); // Now it's an Array.
		var i = arr.indexOf(e) + 1; 
		var next = barcode.item(i);
		 next.focus();
	}
	function Check_(e)
	{
		var cbName = document.getElementsByName("cbName");
		var chonName = document.getElementsByName("chonName");
		
		var arr = Array.prototype.slice.call(cbName); 
		var i = arr.indexOf(e);
		
		if(e.checked)
			chonName.item(i).value = "1";
		else
			chonName.item(i).value = "0";
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

	<form name="dhForm" method="post" action="../../KhuyenMaiDacBietUpdateSvl">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="id" id="nppId" value='<%= dhBean.getId() %>'>
		<input type="hidden" name="userId" value='<%=userId%>'> 
		<input type="hidden" name="action" value='1'> 
		<input type="hidden" id="nppChonSP" name="nppChonSP" value='0'> 

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
														<TD align="left" class="tbnavigation"><%=url%> > <%=Utility.GLanguage("Cập nhật",session,jedis) %>
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
															href="../../KhuyenMaiDacBietSvl?userId=<%=userId %>"><img src="../images/Back30.png" alt="Quay ve" title="Quay ve" border="1" longdesc="Quay ve"	style="border-style: outset">
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
													<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Đơn hàng bán",session,jedis) %> </LEGEND>
													<TABLE cellpadding="3" cellspacing="0" width="100%"
														border=0>

														<TR>
															<TD class="plainlabel"width="10%"><%=Utility.GLanguage("SCHEME",session,jedis) %></TD>
																<TD class="plainlabel"  > 
													 				<input type="text"  name="scheme" value="<%= dhBean.getScheme() %>"  />
																</TD>

															<TD class="plainlabel"width="10%"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TD>
																<TD class="plainlabel"  > 
													 				<input type="text"  name="diengiai" value="<%= dhBean.getGhichu() %>"   />
																</TD>
															
														</TR>
														<TR>
															<TD class="plainlabel" width="20%"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
															<TD class="plainlabel"><input type="text"
																class="days" size="11" 
																id="tungay" name="tungay"
																value="<%= dhBean.getTungay() %>" maxlength="10"
																readonly /></TD>

															<TD class="plainlabel" width="20%"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
															<TD class="plainlabel"><input type="text"
																class="days" size="11" 
																id="denngay" name="denngay"
																value="<%= dhBean.getDenngay() %>" maxlength="10"
																readonly /></TD>
															
														</TR>
														<TR>
															<TD class="plainlabel" width="15%"><%=Utility.GLanguage("Kho trả",session,jedis) %></TD>
															<TD class="plainlabel" >
															
												
											
															
															
																<% ResultSet khoRs = (ResultSet)dhBean.getKhoRs(); %>
																<select name="khoId" >

																	<%if(khoRs != null) while(khoRs.next()){
																	
																		String selected = "";
																		if(khoRs.getString("pk_seq").equals(dhBean.getKhoId()))
																			selected = "selected";
																	%>
																			<option value="<%=khoRs.getString("pk_seq") %>"  <%=selected %>  ><%=khoRs.getString("ten") %></option>
																	<%} %>				
																</select>
															
															</TD>
														
														
															<TD class="plainlabel"width="10%">% <%=Utility.GLanguage("Chiết khấu",session,jedis) %></TD>
																<TD class="plainlabel" > 
													 				<input type="text"  name="ptck" value="<%= dhBean.getPt_chietkhau() %>"  />
																</TD>

															
															
														</TR>	
														
														<TR>
															<TD class="plainlabel" width="15%"><%=Utility.GLanguage("Kho nhận",session,jedis) %></TD>
															<TD class="plainlabel" >
															
												
											
															
															
																<% ResultSet khoRsnhan = (ResultSet)dhBean.getKhoRs(); %>
																<select name="khonhanId" >

																	<option></option>
																	<%if(khoRsnhan != null) while(khoRsnhan.next()){
																	
																		String selected = "";
																		if(khoRsnhan.getString("pk_seq").equals(dhBean.getKhonhan_fk()))
																			selected = "selected";
																	%>
																			<option value="<%=khoRsnhan.getString("pk_seq") %>"  <%=selected %>  ><%=khoRsnhan.getString("ten") %></option>
																	<%} %>				
																</select>
															
															</TD>
														
														
															<TD class="plainlabel"width="10%"></TD>
																<TD class="plainlabel" > 
													 				</TD>

															
															
														</TR>	
														
											
														<TR>
														
														
															<TD class="plainlabel"  colspan="4" >
                    	
												                    	
													         			
													           	 		<div style='display:none'>
																			<div id="noidungpopup" style='padding:0px 5px; background:#fff;'>
																					<input type="text" id="myInput" onkeyup="searchSp()" placeholder="Tìm kiếm sản phẩm.." title="Type in a name">
																				 	<table width="50%" align="center" cellpadding="0" cellspacing="1"  id="myTable">
													                    						
													                    						
													                    						
													                    						
													                    						<% ResultSet spRs = (ResultSet)dhBean.getSanphamRs(); 
													                    							while(spRs.next())
												                    								{
												                    									String id = spRs.getString("pk_seq");
												                    									String ten =  spRs.getString("ten");
												                    									String ma =   spRs.getString("ma");
												                    									String timkiem =   spRs.getString("timkiem");
												                    									
												                    							%>
												                    							<tr >	
												                    								
												                    								<td   style="text-align: left;" >
												                    									<input name ="timkiem" id ="timkiem" type="hidden" value="<%=timkiem %>" >
												                    									<input name ="idSp" id ="idSp" type="hidden" value="<%=id %>" >
												                    									<input name ="maSp" id ="maSp"  type="text" value="<%=ma %>" style="text-align: left;width: 100%" readonly> </td>
												                    								<td   style="text-align: left;" >
												                    										<input name="tenSp" id ="tenSp"   type="text" value="<%=ten %>" style="text-align: center;width: 100%" readonly> </td>
												                    								<td   style="text-align: left;" >
												                    								<a class="button2" href="javascript:ThemSp('<%=id %>','<%=ma %>','<%=ten %>' )" >
    	                           																			<img style="top: -4px;" src="../images/Search30.png" alt="">Chọn</a>
												                    								</td>
												                    							</tr>
												                    						
													
												                    							
												                    						
												                    							<%} %>
													                    					 </table>  
																			
																			</div>
																		</div>				
													           	 				
												                    			
												                    	
												                    		                   
												                    													
												                    </TD>
														</TR>
														
														
														
												
										                
										                    
										                
													</TABLE>
												</FIELDSET></TD>
										</TR>
										
										<TR>
											<TD>
												<TABLE id="tbBarcode" width="100%" border="0" cellpadding="0"
													cellspacing="1">
															
														
														
													
														<TR class="tbheader">														
															<TH width="10%"><%=Utility.GLanguage("MÃ NPP",session,jedis) %></TH>
															<TH width="40%"><%=Utility.GLanguage("TÊN NPP",session,jedis) %></TH>
															<TH width="30%"><%=Utility.GLanguage("Sản phẩm",session,jedis) %></TH>
															<TH width="10%"><%=Utility.GLanguage("CHỌN",session,jedis) %></TH>
															
														</TR>
														
														<%
														ResultSet nppRs = (ResultSet)dhBean.getNppRs();
														if(nppRs != null)
														while(nppRs.next()) { 
															String chon = nppRs.getString("chon");
															String textSP = "Chọn sản phẩm";
															if(chon.equals("1"))
																textSP =   nppRs.getString("spMa") +" - " +  nppRs.getString("spTen");
															String spId =nppRs.getString("spId");
															
														
														%>
														<TR class= 'tblightrow'  >
																
															<TD>
																	<input  name="nppId" type="hidden" value="<%=nppRs.getString("pk_seq") %>"  style="width:100%;text-align:center">
																	<input tabindex="-1" readonly name="manpp" type="text" value="<%=nppRs.getString("mafast")  %>"  style="width:100%;text-align:center">
															</TD>
															<TD>
																	<input tabindex="-1" readonly name="tennpp" type="text" value="<%=nppRs.getString("ten")%>"  style="width:100%;text-align:center">
															</TD>
															<TD>
																	<input  name="spId"  id="<%=nppRs.getString("pk_seq") %>_idsp"  type="hidden" value="<%=spId %>"  style="width:100%;text-align:center">
																	<!-- <input tabindex="-1" readonly  name="sanpham" type="text" value="Chọn sản phẩm"  style="width:100%;text-align:center"> -->
																	
																	<a class="button2" id="nutmopopup<%=nppRs.getString("pk_seq") %>"  style="width:100%;text-align:center"   href="#">
																		<%= textSP %>
																															
																		<script>
																			$(document).ready(function()
																			{
																				var p = $("#nutmopopup<%=nppRs.getString("pk_seq") %>").first();
																				var position = p.position().top - (p.position().top /1.1);
																			 //   alert(position.top);
																			    
																			    $("#nutmopopup<%=nppRs.getString("pk_seq") %>").colorbox({width:"50%", height :"100%", inline:true, href:"#noidungpopup",top: position });
																			    
																				
																				$("#nutmopopup<%=nppRs.getString("pk_seq") %>").click(function( e ){
																					
																					
																				    
																					document.getElementById("nppChonSP").value = <%=nppRs.getString("pk_seq") %>;
																                });
																			});
																			
																																		
																		 </script>
																	</a>	
															
															</TD>
															
															<TD  >
																<input  name="cbName" type="checkbox" value="" onclick="Check_(this)"  style="width:100%;text-align:center" <%=chon.equals("1") ? "checked":"" %> >
																<input  name="chonName" type="hidden" value="<%=chon %>"   style="width:100%;text-align:center">					
															</TD>

														</TR>
														 <%} %> 
								        
													
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

