<%@page import="geso.dms.distributor.beans.nhaphangchinhanh.imp.Lochitiet"%>
<%@page import="geso.dms.distributor.beans.nhaphangchinhanh.imp.SpNhaphang"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.distributor.beans.nhaphangchinhanh.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import= "java.text.DecimalFormat" %>
<%@page import= "java.text.NumberFormat" %>

<% INhaphang lsxBean = (INhaphang)session.getAttribute("lsxBean"); %>
<% ResultSet ddhRs = lsxBean.getDondathangRs(); %>
<% ResultSet khonhanRs = lsxBean.getKhonhanRs(); %>
<% Utility Util = new Utility(); %>
<% 
  
	NumberFormat formater = new DecimalFormat("##,###,###");
%>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUpERP/index.jsp");
	} else { %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
 
 <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
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
	filter: progid:DXImageTransform.Microsoft.Shadow(color=gray, direction=135
		);
}

#dhtmlpointer {
	position: absolute;
	left: -300px;
	z-index: 101;
	visibility: hidden;
}
}
</style>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});            
	});	
</script>

<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/erp-SpListNhapKho.js"></script>
 
 
 
<script language="javascript" type="text/javascript">

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
	 
	
	
	function Update_SoLuong()
	{
		
			 var spMa = document.getElementsByName("spMa");
			
			var dongia = document.getElementsByName("dongia");
			var spvat = document.getElementsByName("spvat");
			var thanhtien = document.getElementsByName("thanhtien");
			var spchietkhau = document.getElementsByName("spchietkhau");
		 
			 var tongtien = 0;
			 var tongchietkhau = 0;
			for(var i = 0; i < spMa.length; i++)
			{
				
				var soluong = document.getElementsByName(i+"_soluong");
				var so_luong=0;
				for(var j=0;j<soluong.length ;j++){
					if(soluong.item(j).value!=''){
						so_luong+= parseFloat(soluong.item(j).value.replace(/,/g, ""));
					}
				}
				
				
				var don_gia = dongia.item(i).value.replace(/,/g, "");
				 
				var chiet_khau = spchietkhau.item(i).value.replace(/,/g, "");
				
				if(i==0)
					tongchietkhau = parseFloat(tongchietkhau) +  (parseFloat(chiet_khau));
				if(i>0 && chiet_khau != '' && spMa.item(i).value!= spMa.item(i-1).value )
					tongchietkhau = parseFloat(tongchietkhau) +  (parseFloat(chiet_khau));
				if( don_gia != '' && so_luong != '' )
					tongtien = parseFloat(tongtien) +    parseFloat(don_gia) * parseFloat(so_luong);  	  
			}
			
			var phantram=tongchietkhau/tongtien;
			console.log('tphn tram'+ phantram);
					var tongvat = 0;
					
				 for(var i = 0; i < spMa.length; i++)
					{
						var don_gia = dongia.item(i).value.replace(/,/g, "");
						 
						var soluong = document.getElementsByName(i+"_soluong");
						var so_luong=0;
						for(var j=0;j<soluong.length ;j++){
							if(soluong.item(j).value!=''){
								so_luong+= parseFloat(soluong.item(j).value.replace(/,/g, ""));
							}
						}
						
						
						
						var _vat = spvat.item(i).value;
						
						var _tienVAT = parseFloat(so_luong) * parseFloat(don_gia) * parseFloat( _vat ) / 100 ;
						_tienVAT=_tienVAT - ((parseFloat(so_luong) * parseFloat(don_gia) * parseFloat( _vat ) / 100 )*phantram);
						console.log('tien vat trc'+ _tienVAT);
							if(_vat != '')
							tongvat = parseFloat(tongvat) + parseFloat( _tienVAT);
						
						}
					
					var tienchuaVAT = Math.round(tongtien);
					var tienchuaVAT1 = Math.round(tongtien-tongchietkhau);
					document.getElementById("txtBVAT").value = DinhDangTien(tienchuaVAT1);
					var tienvat=Math.round(tongvat);
					document.getElementById("txtVAT").value = DinhDangTien(parseFloat(tongvat));
					var SoTienSauCKKM=tienchuaVAT-tongchietkhau+tienvat;
					
					var tongtienKMHidden = 0 ;
					
					document.getElementById("txtSauVAT").value = DinhDangTien(Math.round(SoTienSauCKKM ));
					document.getElementById("txtTongTien").value = DinhDangTien(Math.round(SoTienSauCKKM -tongtienKMHidden ));
					
					
					setTimeout(Update_SoLuong, 500);
	}
	 
	
	 function saveform()
	 {	
		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['hctmhForm'].action.value = 'save';
	     document.forms['hctmhForm'].submit();
	 }
	 
	 function chotform()
	 {	
		 document.getElementById("btnSave2").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['hctmhForm'].action.value = 'chot';
	     document.forms['hctmhForm'].submit();
	 }
	 
	 function submitform()
	 { 
		 document.forms['hctmhForm'].action.value='submit';
	     document.forms["hctmhForm"].submit();
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
 
</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
	});
</script>

 
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="hctmhForm" method="post" action="../../NhaphangchinhanhUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="id" value='<%= lsxBean.getId() %>'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? t???n kho > Mua h??ng t??? nh?? cung c???p > Nh???n h??ng
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhaphangSvl?userId=" +userId+"") %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <span id="btnSave">
	        <A href="javascript:saveform()" >
	        	<IMG src="../images/Save30.png" title="L??u th??ng tin" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        </span>
        <span id="btnSave2">
	        <A href="javascript:chotform()" >
	        	<IMG src="../images/Chot.png" title="Ho??n t???t nh???p h??ng" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        </span>
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Th??ng b??o</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%"><%= lsxBean.getMsg() %></textarea>
		         <% lsxBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle">Xu???t kho </legend>
        	<div style="float:none; width:100%" align="left">
            <TABLE width="100%" cellpadding="4" cellspacing="0">							
                <TR>
                    <TD width="120px" class="plainlabel" valign="top">Ng??y xu???t kho </TD>
                    <TD class="plainlabel" valign="top" style="width: 250px;"  >
                    	<input type="text" readonly="readonly"  name="ngaychuyen" value="<%= lsxBean.getNgayyeucau() %>"/>
                    </TD>
                
                    <TD width="120px" class="plainlabel" valign="top">Ng??y nh???n h??ng </TD>
                    <TD class="plainlabel" valign="top"  >
                    	<input type="text" class="days" readonly="readonly"  name="ngaynhan" value="<%= lsxBean.getNgaynhap() %>"/>
                    </TD>
                </TR>
                
                <TR>
                    <TD class="plainlabel" style="color: red;" valign="top">S??? ch???ng t??? </TD>
                    <TD class="plainlabel" valign="top" >
                    	<input type="text"  name="sochungtu" value="<%= lsxBean.getSochungtu() %>"/>
                    </TD>
                    <TD class="plainlabel" >Kho nh???n h??ng </TD>
                    <TD class="plainlabel"  >
                    	<select style="width: 200px" name="khonhanID" >
                        	<%
                        		if(khonhanRs != null)
                        		{
                        			try
                        			{
                        				if( lsxBean.getLoaiDh().equals("4") ){%>
                        					<option value="100001" selected="selected" >Khuy???n m??i</option>
                        				<%}
                        			while(khonhanRs.next())
                        			{  
                        				System.out.print("kho nhan id"+lsxBean.getKhonhanId());
                        			if( khonhanRs.getString("pk_seq").equals(lsxBean.getKhonhanId()) ){ %>
                        				<option value="<%= khonhanRs.getString("pk_seq") %>" selected="selected" ><%= khonhanRs.getString("TEN") %></option>
                        			<%}else { %>
                        				<option value="<%= khonhanRs.getString("pk_seq") %>" ><%= khonhanRs.getString("TEN") %></option>
                        		 <% }  } khonhanRs.close();} catch(Exception ex){}
                        		}
                        			
                        	%>
                    	</select>
                    </TD>
                </TR>
                
                <TR>
                	<TD class="plainlabel" >????n ?????t h??ng </TD>
                    <TD class="plainlabel" colspan="3" >
                    	<input type="hidden" name = "ddhIds"  value="<%= lsxBean.getDondathangId() %>" >
                    	<select  class="select2" style="width: 700px" multiple="multiple" disabled="disabled" >
                    		<option value=""> </option>
                        	<%
                        		if(ddhRs != null)
                        		{
                        			try
                        			{
                        			while(ddhRs.next())
                        			{  
                        			if( lsxBean.getDondathangId().contains(ddhRs.getString("pk_seq")) ){ %>
                        				<option value="<%= ddhRs.getString("pk_seq") %>" selected="selected" ><%= ddhRs.getString("TEN") %></option>
                        			<%}else { %>
                        				<option value="<%= ddhRs.getString("pk_seq") %>" ><%= ddhRs.getString("TEN") %></option>
                        		 <% } } ddhRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD>   
                </TR>
              	<TR>
                    <TD class="plainlabel" >Ghi ch?? </TD>
                    <TD class="plainlabel" colspan="3" >
                    	<input type="text" readonly="readonly" name="ghichu" value="<%= lsxBean.getGhichu() %>" style="text-align: right;"  />

                    </TD>
                </TR>
                <TR>
                    <TD class="plainlabel" >T???ng gi?? tr??? </TD>
                    <TD class="plainlabel" colspan="3" >
                    	<input type="text" readonly="readonly"  id="txtBVAT" value="" style="text-align: right;"  />

                    </TD>
                </TR>

                <TR>
                    <TD class="plainlabel" valign="top">Ti???n Vat </TD>
                    <TD class="plainlabel" valign="top" width="240px" >
                    	<input type="text" value="" id="txtVAT" style="text-align: right;" name="ptVat" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" /> 
                    </TD>
                    	
                    <TD class="plainlabel" valign="top" width="130px" >T???ng ti???n sau VAT </TD>
                    <TD class="plainlabel" valign="top">
                    	<input type="text" readonly="readonly"  value="" id="txtSauVAT" style="text-align: right;" />
                    </TD>
                </TR>
                 <TR>
                   
                    	
                    <TD class="plainlabel" valign="top" width="130px" >T???ng ti???n</TD>
                    <TD class="plainlabel" valign="top"  colspan="3">
                    	<input type="text" readonly="readonly"  value="" id="txtTongTien" style="text-align: right;" />
                    </TD>
                </TR>
               
            </TABLE>
			<hr />
			
			<table cellpadding="0px" cellspacing="1px" width="100%">
				<tr class="tbheader">
					<th align="center" width="10%" > M?? s???n ph???m</th>
					<th align="center" width="10%"> T??n s???n ph???m</th>
					<th align="center" width="10%"> ????n v???</th>
				 
					<th align="center" width="10%" >S??? l?????ng xu???t</th>
			 		<th align="center" width="10%">S??? l?????ng nh???p </th>
			 		
					<th align="center" width="10%" >????n gi??</th>
					<th align="center" width="12%" >Scheme</th>
					 
				</tr>
				
				<%
					int count = 0;
					 
						for(int i = 0; i < lsxBean.getListSp().size(); i++)
						{
							SpNhaphang sp=lsxBean.getListSp().get(i);
							
							String style = "";
							String kho = "";
							if(sp.getSpLoai().equals("1"))
							{
								style = " class='mySCHME' ";
								kho = "H??ng KM";
							}
							else
							{
								kho = "H??ng b??n";
							}
						%>
					
						<tr <%= style %> >
							<td >
								<input type="hidden" name="spPK_SEQ" value="<%= sp.getSpPK_SEQ() %>" style="width: 100%"   > 
								<input type="hidden" name="spId" value="<%=  sp.getSpId() %>" style="width: 100%"   > 
								<input type="hidden" name="spLoai" value="<%= sp.getSpLoai() %>" style="width: 100%"   > 
								<input type="hidden" name="spvat" value="<%= sp.getSpvat() %>" style="width: 100%"   > 
								<input type="hidden" name="spchietkhau" value="<%= sp.getSpchietkhau() %>" style="width: 100%"   > 
							 
								<input type="text" name="spMa" value="<%= sp.getSpMa()%>" style="width: 100%"  readonly="readonly"  > 
							</td>
							<td> <input type="text" name="spTen" value="<%=sp.getSpTen() %>" style="width: 100%" readonly="readonly"> </td>
							<td>
								<input type="text" name="donvi" value="<%= sp.getSpDonvi() %>" style="width: 100%; text-align: center;" readonly="readonly">							
							</td>
							  
							<td>
								<input type="text" name="soluongnhap" value="<%=formater.format(sp.getSoluongnhap()) %>" style="width: 100%; text-align: right;" readonly="readonly">							
							</td>
							<td>
								<a href=""
									id="<%=i + "." + sp.getSpId() %>"
									rel="subcontent<%=i%>"> <img alt="S??? l?? - S??? l?????ng nh???n"
										src="../images/vitriluu.png">
									</a>
									 

									<DIV id="subcontent<%=i%>"
										 style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
					                             background-color: white; width: 450px; max-height:300px; overflow:auto; padding: 4px;">
					                    <table width="95%" align="center"  class="detail">
											 
												
											<tr>
												<th width="100px">S??? l??</th>
												 
												<th   width="100px">Ng??y h???t h???n</th>
												 
												<th width="100px">S??? l??????ng</th>
										  		
											</tr>
											
											<% List<Lochitiet> listct= sp.getList(); 
											   for(int j=0;j<listct.size();j++){
											      Lochitiet ct=listct.get(j);
											      
												   %>
												   	<tr>
														<th width="100px"> <input type="text"  style="width: 100%;text-align:center;"  name="<%=i%>_solo" value="<%=ct.getSolo()%>"> </th>
														 
														<th   width="100px"><input type="text" class="days" style="width: 100%;text-align:center;" name="<%=i%>_ngayhethan" value="<%=ct.getNgayhethan()%>"></th>
														 
														<th width="100px"><input style="width: 100%;text-align: right;"  type="text" name="<%=i%>_soluong"  onkeypress="return keypress(event);" value="<%=formater.format(ct.getSoluong())%>"></th>
										  		
													</tr>
											
												   <% 
											   }
											
											%>
											
												
											</table>
											</DIV>
											 
										<script type="text/javascript">
	 						 
		 								  dropdowncontent.init('<%=i + "." + sp.getSpId() %>', "left-bottom", 300, "click");
	 		 
										</script>
												
							</td>
							 
							<td>
								<input type="text" name="dongia" value="<%= formater.format(sp.getSpDongia()) %>" style="width: 100%; text-align: right;" readonly="readonly" >							
							</td>
							<td>
								<input type="text" name="scheme" value="<%=sp.getSpSCheme()%>" style="width: 100%; " readonly="readonly" >							
							</td>
							 
							
						</tr>	
							
					<% count ++; }   %>
					 
					 </table>
				
            </div>
     </fieldset>	
    </div>
</div>
 
<script type="text/javascript">
	Update_SoLuong();
</script>
 
<%
	try
	{
		lsxBean.DBclose(); 
	}
	catch(Exception er){ }

	} %>
</form>
</BODY>
</HTML>