<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.cttrungbay.*" %>
<%@ page  import = "geso.dms.center.beans.cttrungbay.imp.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% ICttrungbay cttbBean = (ICttrungbay)session.getAttribute("cttbBean"); %>
<% List<INhomsptrungbay> nsptbList = cttbBean.getNsptbList(); %>
<% 
	ResultSet tratbRs = cttbBean.getTratbRs(); 
	
	String[] diengiaiMuc = (String[])cttbBean.getDiengiaiMuc();
	String[] tumuc = (String[])cttbBean.getTumuc();
	String[] denmuc = (String[])cttbBean.getDenmuc();
	String[] thuongSR = (String[])cttbBean.getThuongSR();
	String[] thuongTDSR = (String[])cttbBean.getThuongTDSR();
	String[] thuongSS = (String[])cttbBean.getThuongSS();
	String[] thuongTDSS = (String[])cttbBean.getThuongTDSS();
	String[] thuongASM = (String[])cttbBean.getThuongASM();
	String[] thuongTDASM = (String[])cttbBean.getThuongTDASM();
%>

<% ResultSet kbhRs = cttbBean.getKbhRs(); %>
<% ResultSet vungRs = cttbBean.getVungRs(); %>
<% ResultSet kvRs = cttbBean.getKhuvucRs(); %>
<% ResultSet nppRs = cttbBean.getNppRs(); 

ResultSet nhomTbRs = cttbBean.getNhomTbRs();

%>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>

<% Utility util = new Utility(); %>
<%String url = util.getChuyenNguUrl("CttrungbaySvl", "",session); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	
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
     <script type="text/javascript" src="..scripts/jquery-1.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
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
			z-index:100200;
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
	</style> 
	
	<script type="text/javascript" src="../scripts/ajax.js"></script>
	<script type="text/javascript" src="../scripts/cttrungbayList.js"></script>
	
	<script>
	  $(document).ready(function() {
			$("#accordion").accordion({autoHeight: false}); //autoHeight content set false
			$( "#accordion" ).accordion( "option", "icons", { 'header': 'ui-icon-plus', 'headerSelected': 'ui-icon-minus' } );
			$( "#accordion" ).accordion({ active: <%= cttbBean.getActive() %> });
	  });
  	</script>
   
    <link media="screen" rel="stylesheet" href="../css/colorbox.css">
    <script src="../scripts/colorBox/jquery.colorbox.js"></script>
  
    <script language="javascript" type="text/javascript">
		function replaces()
		{
			var ngayKtTds = document.getElementsByName("ngayKtTds");
			var ngayKtTb = document.getElementsByName("ngayKtTb");
			var ngayTb = document.getElementsByName("ngayTb");
			var ngay1 =	document.getElementsByName("ngay1");
			var ngay2 =	document.getElementsByName("ngay2");
		    var type = document.getElementsByName("ckType");
			
		    //phai chay bang vong lap vay moi duoc
		    if(type.checked)
	    	{
			    for(var h = 0; h < ngay1.length; h++)
			    {
			    	ngay1.item(h).value = ngayKtTds.item(h).value;
			    	if(type.checked == true)
					 	ngay2.item(0).value = ngayKtTb.item(0).value;
					else
						ngay2.item(0).value  = ngayTb.item(0).value;
			    }
	    	}
			var nsptbId = document.getElementsByName("nsptbId");
			var nsptbDiengiai = document.getElementsByName("nsptbDiengiai");
			var nsptbTongluong = document.getElementsByName("nsptbTongluong");
			var nsptbTongtien = document.getElementsByName("nsptbTongtien");
	
			var i;
			for(i=0; i < nsptbId.length; i++)
			{
				if(nsptbId.item(i).value != "")
				{
					var nsptb = nsptbId.item(i).value;
					var pos = parseInt(nsptb.indexOf(" - "));
					if(pos > 0)
					{
						nsptbId.item(i).value = nsptb.substring(0, pos);
						nsptb = nsptb.substr(parseInt(nsptb.indexOf(" - ")) + 3);
						nsptbDiengiai.item(i).value = nsptb.substring(0, parseInt(nsptb.indexOf(" [")));
						nsptb = nsptb.substr(parseInt(nsptb.indexOf(" [")) + 2);
						nsptbTongluong.item(i).value = nsptb.substring(0, parseInt(nsptb.indexOf("]")));
						nsptb = nsptb.substr(parseInt(nsptb.indexOf("] [")) + 3);
						nsptbTongtien.item(i).value = nsptb.substring(0, parseInt(nsptb.indexOf("]")));
					}
				}
				else
				{
					nsptbId.item(i).value = "";
					nsptbDiengiai.item(i).value = "";
					nsptbTongluong.item(i).value = "";
					nsptbTongtien.item(i).value = "";
				}			
			}
			
			setTimeout(replaces, 20);
		}
		
		function All()
		 { 
			var npp = document.getElementsByName("nppIds");
			for(i=0; i < npp.length; i++)
			{
			 	if(document.cttbForm.all.checked == true)
			 	{
			 	  	npp[i].checked = true;
				}
			 	else
			 	{
					npp[i].checked = false;
				}
			}
		}
		
		function saveform()
		{
			var ngayTds = document.getElementById("ngayTds").value;
			var ngayKtTds = document.getElementById("ngayKtTds").value;
			
			var ngayTb = document.getElementById("ngayTb").value;
			var ngayKtTb = document.getElementById("ngayKtTb").value;
			
			var ngay1 = document.getElementById("ngay1").value;
			var ngay2 = document.getElementById("ngay2").value;
			
			if(document.getElementById("scheme").value == "")
			{
				alert('Vui lòng nhập scheme');
				return;
			}
			
			if( ngayTds == '' || ngayKtTds == '')
			{
				alert('Vui lòng nhập thời gian tính doanh số');
				return;
			}
			
			if( ngayTb == '' || ngayKtTb == '')
			{
				alert('Vui lòng nhập thời gian trưng bày');
				return;
			}
			
			if( ngay1 == '' || ngay2 == '')
			{
				alert('Vui lòng nhập thời gian đăng ký');
				return;
			}
			 
			if(document.getElementById("solantt").value == "")
			{
				alert('Vui lòng nhập số lần thanh toán');
				return;
			}
			if(document.getElementById("ngansach").value == "")
			{
				alert('Vui lòng nhập ngân sách chương trình trưng bày');
				return;
			}
			
		
			if(checkTratb() == false)
			{
				alert('Lỗi...không có hình thức trả trưng bày nào được chọn...');
				return;
			}
			
			var ngayTds = document.getElementById("ngayTds").value;
			var ngayKtTds = document.getElementById("ngayKtTds").value;
			if(ngayTds > ngayKtTds)
			{
				alert('Lỗi...bạn phải chọn ngày kết thúc > ngày bắt đầu của ngày tính doanh số ...');
				return;
			}
			
			var ngayTb =document.getElementById("ngayTb").value;
			var ngayKtTds = document.getElementById("ngayKtTb").value;
			if(ngayTb > ngayKtTds)
			{
				alert('Lỗi...bạn phải chọn ngày kết thúc > ngày bắt đầu của ngày trưng bày ...');
				return;
			}
			
			var ngaydk = document.getElementById("ngay1").value;
			var ngayktdk = document.getElementById("ngay2").value;
			if(ngaydk > ngayktdk)
			{
				alert('Vui lòng nhập ngày bắt đầu đăng ký nhỏ hơn ngày kết thúc đăng ký');
				return;
			}
			
			if(checkNpp() == false)
			{
				alert('Loi...khong co nha phan phoi nao duoc chon...');
				return;
			}
			
			var nsptbIds = document.getElementsByName("nsptbId");
			for(var k = 0; k < nsptbIds.length; k++)
			{
				if(nsptbIds.item(k).value != '') { break; }
				else
				{
					alert("Vui lòng nhập điều kiện trưng bày.");
					return;
				}
			}
			
			document.forms["cttbForm"].action.value = "save";
			document.forms["cttbForm"].submit();
		}
		
		function checkNpp()
		{
			var nhapp = document.getElementsByName("nppIds");
			for( p = 0; p < nhapp.length; p++)
				if(nhapp.item(p).checked)
					return true;
			return false;
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
		
		function submitform()
		{  
			//truyen nhu nay moi duoc....
			document.forms["cttbForm"].ttbTungay.value = document.getElementById("ttb_tungay").value;
			document.forms["cttbForm"].ttbDenngay.value = document.getElementById("ttb_denngay").value;
			document.forms["cttbForm"].ttbDiengiai.value = document.getElementById("ttb_diengiai").value;
			
			document.forms["cttbForm"].nsptbTungay.value = document.getElementById("nsptb_tungay").value;
			document.forms["cttbForm"].nsptbDenngay.value = document.getElementById("nsptb_denngay").value;
			document.forms["cttbForm"].nsptbDien_giai.value = document.getElementById("nsptb_diengiai").value;
			
			/* var active = $( "#accordion" ).accordion( "option", "active" );
			document.forms["cttbForm"].active.value = active;
			 */
		    document.forms["cttbForm"].submit();
		}
		
		function checkNhomsptrungbay()
		{
			var nsptbIds = document.getElementsByName("nsptbId");
			
			for(j = 0; j < nsptbIds.length; j++)
			{
				if(nsptbIds.item(j).value != "")
					return true; //co chon dieu kien
			}
			return false;
		}
		
		function checkNhomsptrung()
		{
			var nsptbIds = document.getElementsByName("nsptbId");
			var nsptbDiengiai = document.getElementsByName("nsptbDiengiai");
			var nsptbTongluong = document.getElementsByName("nsptbTongluong");
			var nsptbTongtien = document.getElementsByName("nsptbTongtien");
			
			for(l =0; l < parseInt(nsptbIds.length) - 1; l++)
			{
				for(m = parseInt(l) + 1; m < nsptbIds.length; m++)
				{
					if(nsptbIds.item(l).value == nsptbIds.item(m).value)
					{
						nsptbIds.item(m).value = "";
						nsptbDiengiai.item(m).value = "";
						nsptbTongluong.item(m).value = "";
						nsptbTongtien.item(m).value = "";
					}
				}
			}
		}
		
		function checkTratb()
		{
			var tratbIds = document.getElementsByName("tratbIds");
			for(k =0; k < tratbIds.length; k++)
			{
				if(tratbIds.item(k).checked)
					return true; //co chon tra khuyen mai
			}
			return false;
		}
		
		function AjaxNpp()
		{
	    	var kenhId = document.getElementById("kenhId");
	    	var kenhIds="";
			for(var i = 0; i < kenhId.options.length ; i++)
			{
				if(kenhId.options[i].selected)
					kenhIds += kenhId.options[i].value + ',';
			}
			if(kenhIds.length>0)
			{
				kenhIds=kenhIds.substring(0,kenhIds.length-1);
			}
			
			var vungId = document.getElementById("vungId");
			var vungIds="";
			for(var i = 0; i < vungId.options.length ; i++)
			{
				if(vungId.options[i].selected)
					vungIds += vungId.options[i].value + ',';
			}
			if(vungIds.length>0)
			{
				vungIds=vungIds.substring(0,vungIds.length-1);
			}
			
			
			var khuvucId = document.getElementById("khuvucId");
			var khuvucIds="";
			for(var i = 0; i < khuvucId.options.length ; i++)
			{
				if(khuvucId.options[i].selected)
					khuvucIds += khuvucId.options[i].value + ',';
			}
			if(khuvucIds.length>0)
			{
				khuvucIds=khuvucIds.substring(0,khuvucIds.length-1);
			}
 
			//Lay tat ca nppIdSelecd nhung nhapp da tick chon 
			var all_NppId_Checked="";
			var nppId= document.getElementsByName('nppIds');
			for(var ii=0;ii<nppId.length;ii++)
			{
				if(nppId.item(ii).checked==true)
				{
					all_NppId_Checked=all_NppId_Checked+nppId.item(ii).value+",";
				} 	
			}
			if(all_NppId_Checked.length>0)
			{
				all_NppId_Checked=all_NppId_Checked.substring(0,all_NppId_Checked.length-1);
			}
			
			 $.get("../../AjaxChuongTrinhKhuyenMai?action=ajaxNpp&kenhId="+kenhIds+"&vungId="+vungIds+"&kvId="+khuvucIds+"&nppSelected="+all_NppId_Checked+"", function(list,status) {
					var table = $('#NppTable');
						table.html(
							'<TABLE id="NppTable">'+
			                    '<TR class="tbheader">'+
		                        '<TH align="center" width="10%">Mã nhà phân phối</TH>'+
		                        '<TH align="center" width="50%">Tên nhà phân phối</TH>'+
		                        '<TH align="center" width="10%"><input type ="checkbox" name ="all" onclick ="All()">Chọn tất cả</TH>'+
		                    '</TR>'
	    					);
						$.each(list, function(index, data) {
							var checked='';
							if(all_NppId_Checked.indexOf(data.nppId)>=0)
								 checked='checked="checked"';
							var vclass= document.createElement("tr");
								vclass.setAttribute("class", "tbdarkrow");
							if(index % 2 !=0)
								vclass.setAttribute("class", "tblightrow");
							
							$(vclass).appendTo(table)
								.append($('<td align="center"><input type="hidden" name="nppId" value='+data.nppMa +' style="width: 100%;" readonly="readonly">'+ data.nppMa +'</td>' ))
								.append($('<td align="left"><input type="hidden" name="nppTen" value= '+data.nppTen+' style="width: 100%;"  readonly="readonly">'+ data.nppTen +'</td>' ))
								.append($('<td align="center"><input type="checkbox" value='+data.nppId+' name="nppIds" '+checked+'  ></td>' ));
						});
				});
	}
	</script>
	
	<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nhomtbId").select2();
});
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="cttbForm" method="post" action="../../CttrungbayUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="id" value='<%= cttbBean.getId() %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="ttbTungay" value=''>
<input type="hidden" name="ttbDenngay" value=''>
<input type="hidden" name="ttbDiengiai" value=''>
<input type="hidden" name="nsptbTungay" value=''>
<input type="hidden" name="nsptbDenngay" value=''>
<input type="hidden" name="nsptbDien_giai" value=''>
<input type="hidden" name="active" value='<%= cttbBean.getActive() %>'>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	<%= url %> > <%=Utility.GLanguage("Cập nhật",session,jedis) %>
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Chào mừng bạn<%=Utility.GLanguage("",session,jedis) %><%= userTen %> &nbsp; &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "javascript:history.back()" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <A href="javascript:saveform()" >
        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
    </div>
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
    		<textarea name="dataerror"  style="width: 100% ; color:#F00; font-weight:bold; font-family:roboto; border:none;" readonly="readonly" rows="1"><%= cttbBean.getMessage() %></textarea>
		         <% cttbBean.setMessage(""); %>
    	</fieldset>
  	</div>
    <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
    <fieldset>
    	<legend class="legendtitle"><%=Utility.GLanguage("Khai báo chương trình trưng bày",session,jedis) %></legend>
        <div style="width:100%; float:none" align="left">
        
        <TABLE width="100%" cellpadding="4" cellspacing="0">	
                    <TR>
                        <TD style="width: 5%;" class="plainlabel"><%=Utility.GLanguage("Mã CTTB",session,jedis) %> </TD>
                        <TD colspan='3' class="plainlabel"><input type="text" name="scheme" id="scheme" size="30" value="<%= cttbBean.getScheme() %>"></TD>
                    </TR> 
                    <TR>
                        <TD class="plainlabel" valign="top"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
                        <TD colspan='3' class="plainlabel" valign="top"><textarea style="font-family:roboto; font-size:10pt; width: 60%; padding: 4px;" name="diengiai" id="diengiai" rows="1"><%= cttbBean.getDiengiai() %></textarea></TD>
                    </TR>               
                    
                    <TR>
                        <TD colspan='4' class="plainlabel"><p style="text-decoration: underline;"><%=Utility.GLanguage("Thời gian tính doanh số",session,jedis) %></p></TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
                        <TD class="plainlabel"><input type="text" size="10" class="days" id="ngayTds" name="ngayTds" value="<%= cttbBean.getNgayTds() %>" maxlength="10"/></TD>
                        <TD style="width: 5%;" class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
                        <TD class="plainlabel"><input type="text" size="10" class="days" id="ngayKtTds" name="ngayKtTds" value="<%= cttbBean.getNgayktTds() %>" maxlength="10"/></TD>
                    </TR>
                    
                    <TR>
                        <TD colspan='4' class="plainlabel"><p style="text-decoration: underline;"><%=Utility.GLanguage("Thời gian trưng bày",session,jedis) %></p></TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
                        <TD style="width: 5%;" class="plainlabel"><input type="text" size="10" class="days" id="ngayTb" name="ngayTb" value="<%= cttbBean.getNgayTb() %>" maxlength="10"/></TD>
                        <TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
                        <TD style="width: 5%;" class="plainlabel"><input type="text" size="10" class="days" id="ngayKtTb" name="ngayKtTb" value="<%= cttbBean.getNgayktTb() %>" maxlength="10"/></TD>
                    </TR>
                    
                     <TR>
                        <TD colspan='4' class="plainlabel"><p style="text-decoration: underline;"><%=Utility.GLanguage("Thời gian đăng ký",session,jedis) %></p></TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
                        <TD class="plainlabel"><input type="text" size="10" class="days" id="ngay1" name="ngay1" value="<%= cttbBean.getNgayBddk() %>" maxlength="10"/></TD>
                        <TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
                        <TD style="width: 5%;" class="plainlabel"><input type="text" size="10" class="days" id="ngay2" name="ngay2" value="<%= cttbBean.getNgayKtdk() %>" maxlength="10"/></TD>
                    </TR>

                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Số lần thanh toán",session,jedis) %> </TD>
                        <TD colspan='3' class="plainlabel"><input type="text" name="solantt" id="solantt" size="30" style="text-align:right" value="<%= cttbBean.getSolantt() %>" onkeypress="return keypress(event);"></TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Ngân sách",session,jedis) %></TD>
                        <TD colspan='3' class="plainlabel"><input type="text" name="ngansach" id="ngansach" size="30" style="text-align:right" value="<%= cttbBean.getNgansach() %>" onkeypress="return keypress(event);"></TD>
                    </TR>	
                     <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Đã sử dụng",session,jedis) %></TD>
                        <TD colspan='3' class="plainlabel"><input type="text" name="dasudung" id="dasudung" size="30" style="text-align:right" value="<%= cttbBean.getDasudung() %>" readonly ></TD>
                    </TR>
                </TABLE>     
                
                <%-- <TABLE width="100%" cellpadding="6" cellspacing="0">			
                    <TR>
                        <TD width="15%" class="plainlabel">Mã CTTB </TD>
                        <TD class="plainlabel"><input type="text" name="scheme" id="scheme" size="30" value="<%= cttbBean.getScheme() %>"></TD>
                    </TR> 
                    <TR>
                        <TD width="15%" class="plainlabel" valign="top"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="top"><textarea name="diengiai" id="diengiai" style="width:500px" rows="1"><%= cttbBean.getDiengiai() %></textarea></TD>
                    </TR>               
                    <TR>
                        <TD class="plainlabel" >Thời gian tính doanh số</TD>
                        <TD class="plainlabel">
                            Từ ngày&nbsp;&nbsp; <input type="text" size="10" class="days" 
                                   id="ngayTds" name="ngayTds" value="<%= cttbBean.getNgayTds() %>" maxlength="10"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;Đến ngày&nbsp;&nbsp;&nbsp; <input type="text" size="10" class="days" 
                                    id="ngayKtTds" name="ngayKtTds" value="<%= cttbBean.getNgayktTds() %>" maxlength="10"/>
                        </TD>
                    </TR>
                    
                    <TR>
                        <TD class="plainlabel" >Thời gian trưng bày</TD>
                        <TD class="plainlabel">
                            Từ ngày&nbsp;&nbsp; <input type="text" size="10" class="days" 
                                   id="ngayTb" name="ngayTb" value="<%= cttbBean.getNgayTb() %>" maxlength="10"/>
                       &nbsp;&nbsp;&nbsp;&nbsp;Đến ngày &nbsp;&nbsp; <input type="text" size="10" class="days" 
                                    id="ngayKtTb" name="ngayKtTb" value="<%= cttbBean.getNgayktTb() %>" maxlength="10"/>
                       
                        </TD>
                    </TR>
                    
                     <TR>
                        <TD class="plainlabel" ></TD>
                        <TD class="plainlabel">
                           Từ ngày &nbsp;&nbsp; <input type="text" size="10" name="ngay1" id ="ngay1" readonly value ="<%= cttbBean.getNgayBddk() %>" class="days">
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Đến ngày&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="10" id ="ngay2" name="ngay2" readonly value="<%= cttbBean.getNgayKtdk() %>" class="days">
                         </TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" >Số lần thanh toán </TD>
                        <TD class="plainlabel"><input type="text" name="solantt" id="solantt" size="30" style="text-align:right" value="<%= cttbBean.getSolantt() %>" onkeypress="return keypress(event);"></TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" >Ngân sách</TD>
                        <TD class="plainlabel"><input type="text" name="ngansach" id="ngansach" size="30" style="text-align:right" value="<%= cttbBean.getNgansach() %>" onkeypress="return keypress(event);"></TD>
                    </TR>	
                     <TR>
                        <TD class="plainlabel" >Đã sử dụng</TD>
                        <TD class="plainlabel"><input type="text" name="dasudung" id="dasudung" size="30" style="text-align:right" value="<%= cttbBean.getDasudung() %>" readonly ></TD>
                    </TR>	
                </TABLE> --%>       
         </div>
         <div id="accordion"  style="width:100%; height:auto; float:none; font-size:1.0em" align="left">
            <h1 style="font-size:1.8em"><a href="#"><%=Utility.GLanguage("Điều kiện trưng bày",session,jedis) %></a></h1>
			<div style="height:auto">
                <TABLE width="100%" class="tabledetail" border="0" cellspacing="1px" cellpadding="1px">
                    <TR class="plainlabel">
                        <TH align="center" width="10%"><%=Utility.GLanguage("Mã",session,jedis) %></TH>
                        <TH align="center" width="50%"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TH>
                        <TH align="center" width="10%"><%=Utility.GLanguage("Tổng lượng",session,jedis) %></TH>
                        <TH align="center" width="10%"><%=Utility.GLanguage("Tổng tiền",session,jedis) %></TH>
                        <TH align="center" width="10%"><%=Utility.GLanguage("Phép toán",session,jedis) %></TH>
                    </TR>
					<% 
						int i = 0;
						if(nsptbList.size() > 0){ 
						while(i < nsptbList.size()){	
						Nhomsptrungbay nsptb = (Nhomsptrungbay)nsptbList.get(i); %>
		                    <TR class='tbdarkrow'>
		                        <TD align="center"><input type="text" name="nsptbId"  value="<%= nsptb.getId() %>" onkeyup="ajax_showOptions(this,'abc',event)" AUTOCOMPLETE="off"></TD>
		                        <TD align="left"><input type="text" name="nsptbDiengiai"  value="<%= nsptb.getDiengiai() %>" readonly></TD>
		                        <TD align="center"><input type="text" name="nsptbTongluong"  value="<%= nsptb.getTongluong() %>" style="text-align:right" readonly></TD>							           
		                        <TD align="center"><input type="text" name="nsptbTongtien"  value="<%= nsptb.getTongtien() %>" style="text-align:right" readonly></TD>
		                        <TD align="center">
		                        	<select name="nsptbPheptoan">
		                        	<% if(nsptb.getPheptoan().equals("2")){ %>
		                                <option value="2" selected>Or</option>
		                                <option value="1">And</option>
		                            <%}else{ %>		                            	
		                                <option value="1" selected>And</option>
		                                <option value="2">Or</option>
		                            <%}%>
		                            </select>
		                        </TD>
		                    </TR>
                    	<% i++; }} %>
                    	<% for(int j = i; j < 5; j++){ %>
                    		<TR class='tbdarkrow'>
		                        <TD align="center"><input type="text" name="nsptbId" value="" onkeyup="ajax_showOptions(this,'abc',event)" AUTOCOMPLETE="off"></TD>
		                        <TD align="left"><input type="text" name="nsptbDiengiai" value="" readonly></TD>
		                        <TD align="center"><input type="text" name="nsptbTongluong"  value="" style="text-align:right" readonly></TD>							           
		                        <TD align="center"><input type="text" name="nsptbTongtien" value="" style="text-align:right" readonly></TD>
		                        <TD align="center">
		                        	<select name="nsptbPheptoan">
		                            	<option value="1" selected="selected">And</option>
		                                <option value="2">Or</option>     
		                            </select>
		                        </TD>
		                    </TR>
                    	<%} %>
                    <TR>
                        <TD align="center" colspan="10" class="plainlabel">&nbsp;</TD>
                    </TR>
				</TABLE>
				<br>
				<%-- <a style="margin-left: 1%;" class="button1" href="#">
                    <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> điều kiện trưng bày</a>
	                <div style='display:none'>
                        <div id='nhomsptrungbay' style='padding:0px 5px; background:#fff;'>
                        	<h4 align="left"><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> nhóm sản phẩm trưng bày</h4>
							<table cellpadding="4px" cellspacing="2px" width="100%" align="center">
                            	<tr>
                                	<td width="18%" valign="top" align="left"><%=Utility.GLanguage("Diễn giải",session,jedis) %></td>
                                    <td valign="top" align="left"><textarea name="nsptb_diengiai" id="nsptb_diengiai" style="width:250px;" rows="2"><%= cttbBean.getNsptb_diengiai() %></textarea></td>
                                </tr>
                                <tr>
                                	<td valign="top" align="left"><%=Utility.GLanguage("Từ ngày",session,jedis) %></td>
                                    <td valign="top" align="left">
                                    	<input type="text" size="10" class="days" 
                                  					id="nsptb_tungay" name="nsptb_tungay" value="<%= cttbBean.getNsptb_tungay() %>" maxlength="10" />
                                    </td>
                                </tr>
                                <tr>
                                	<td valign="top" align="left"><%=Utility.GLanguage("Đến ngày",session,jedis) %></td>
                                    <td valign="top" align="left">
                                    	<input type="text" size="10" class="days" 
                                  					id="nsptb_denngay" name="nsptb_denngay" value="<%= cttbBean.getNsptb_denngay() %>" maxlength="10" />
                                    </td>
                                </tr>
                                <tr>
                                	<td valign="top" align="left"></td>
                                    <td valign="top" align="left">
                                    <a class="button" href="javascript:submitform();">
        								<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %></a>
                                    </td>
                                </tr>
                            </table>
						</div>
	                </div> --%>
     			</div>  
     	
         	 <h1 style="font-size:1.8em"><a href="#"><%=Utility.GLanguage("Trả trưng bày",session,jedis) %></a></h1> 
             <div style="height:auto">
              <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                  <TR class="plainlabel">
                      <TH align="center"><%=Utility.GLanguage("Mã",session,jedis) %></TH>
                      <TH align="left"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TH>
                      <TH align="center"><%=Utility.GLanguage("Tổng lượng",session,jedis) %></TH>
                      <TH align="center"><%=Utility.GLanguage("Tổng tiền",session,jedis) %></TH>
                      <TH align="center"><%=Utility.GLanguage("Loại",session,jedis) %></TH>
                      <TH align="center" width="10%">Phép toán<%=Utility.GLanguage("",session,jedis) %></TH>
                      <TH align="center"><%=Utility.GLanguage("Chọn",session,jedis) %></TH>
                  </TR>
				  <% int pos = 0; 
				  if(tratbRs != null){
						  try{ while(tratbRs.next()){  %>
			      			<TR class='tbdarkrow'>
		                      <TD align="center"><%= tratbRs.getString("tratbId") %></TD>
		                      <TD align="left"><%= tratbRs.getString("diengiai") %></TD>
		                      <TD align="center"><%= tratbRs.getString("tongluong") %></TD>										                                    
		                      <TD align="center"><%= tratbRs.getString("tongtien") %></TD>
		                      <TD align="center"><%= tratbRs.getString("loai") %></TD>
		                      <TD align="center">
		                        	<select name="tratbPheptoan">
		                        	<%
		                        	String key = tratbRs.getString("tratbId");
		                        	if(cttbBean.getTratbId().containsKey(key)) 
		                        	{
		                        		//System.out.println("Phep toan la: " + cttbBean.getTratbId().get(key) + "\n");
		                        		if(cttbBean.getTratbId().get(key) == 2)
		                        		{%>
		                        			<option value="1">And</option>
		                                	<option value="2" selected="selected">Or</option> 
		                        		<%}
		                        		else{%>
		                        			<option value="1" selected="selected">And</option>
		                                	<option value="2">Or</option> 
		                        		<%}
		                        	}
		                        	else{ %>
		                        		<option value="1" selected="selected">And</option>
		                                <option value="2">Or</option>
		                        	<%}%>
		                            </select>
		                      </TD>
		                      <TD align="center">
		                      <% if(cttbBean.getTratbId().get(key) != null){ %>
		                      		<input type="checkbox" name="tratbIds" value="<%= tratbRs.getString("tratbId") + "," + Integer.toString(pos) %>" checked>
		                      <%}else{ %>
		                      		<input type="checkbox" name="tratbIds" value="<%= tratbRs.getString("tratbId") + "," + Integer.toString(pos) %>" >
		                      <%} %>
		                      </TD>
		                  </TR>
			     <%pos++; } tratbRs.close(); }catch(java.sql.SQLException e){} }%>

                  <TR>
                      <TD align="center" colspan="11" class="plainlabel">&nbsp;</TD>
                  </TR>
              	 </TABLE>   
                  <br>
                  	<%-- <a class="button2" href="#" style="font-size:14px; margin-left: 1%;">
                      <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> trả trưng bày</a>
                      <div style='display:none'>
                        <div id='tratrungbay' style='padding:0px 5px; background:#fff;'>
                        	<h4 align="left"><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> trả trưng bày</h4>
							<table cellpadding="4px" cellspacing="2px" width="100%" align="center">
                            	<tr>
                                	<td width="20%" valign="top" align="left"><%=Utility.GLanguage("Diễn giải",session,jedis) %></td>
                                    <td valign="top" align="left"><textarea name="ttb_diengiai" id="ttb_diengiai" style="width:250px" rows="1"><%= cttbBean.getTtb_diengiai() %></textarea></td>
                                </tr>
                                <tr>
                                	<td valign="top" align="left"><%=Utility.GLanguage("Từ ngày",session,jedis) %></td>
                                    <td valign="top" align="left">
                                    	<input type="text" size="10" class="w8em format-d-m-y divider-dash highlight-days-12" 
                                  					id="ttb_tungay" name="ttb_tungay" value="<%= cttbBean.getTtb_tungay() %>" maxlength="10" />
                                    </td>
                                </tr>
                                <tr>
                                	<td valign="top" align="left"><%=Utility.GLanguage("Đến ngày",session,jedis) %></td>
                                    <td valign="top" align="left">
                                    	<input type="text" size="10" class="w8em format-d-m-y divider-dash highlight-days-12" 
                                  					id="ttb_denngay" name="ttb_denngay" value="<%= cttbBean.getTtb_denngay() %>" maxlength="10" />
                                    </td>
                                </tr>
                                <tr>
                                	<td valign="top" align="left"></td>
                                    <td valign="top" align="left">
                                    <a class="button" href="javascript:submitform();">
        								<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %></a>
                                    </td>
                                </tr>
                            </table>
						</div>
	                </div> --%>
             </div>
            
            <h1 style="font-size:1.8em"><a href="#"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></a></h1>
            <div style="height:auto">
            <TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
                <TR class="plainlabel" valign="bottom">
                <th colspan="3">
                   
                   <fieldset style="width: 30%; float: left;"> 
			       <legend><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %> &nbsp;</legend> 
			       <select name="kbhIds" id="kenhId" multiple="multiple" onchange="AjaxNpp()">
                        <% if(kbhRs != null) {
                         while(kbhRs.next()) 
                         {
                           if(cttbBean.getKbhIds().indexOf(kbhRs.getString("pk_seq")) >= 0 ){ %>
                             <option value="<%= kbhRs.getString("pk_seq") %>" selected style="padding: 2px" ><%= kbhRs.getString("ten") %></option>
                         <%}else { %>
                         	<option value="<%=kbhRs.getString("pk_seq") %>" style="padding: 2px"><%= kbhRs.getString("ten") %></option>
                         <%}} kbhRs.close(); }%>        	
                    </select>
                    </fieldset>
                    
			       <fieldset style="width: 30%; float: left;">
			       <legend><%=Utility.GLanguage("Vùng",session,jedis) %> &nbsp;</legend> 
			       <select name="vungIds" id="vungId" multiple="multiple" onchange="AjaxNpp()">
                        <% if(vungRs != null) {
                         while(vungRs.next()) 
                         {
                           if(cttbBean.getVungIds().indexOf(vungRs.getString("pk_seq")) >= 0 ){ %>
                             <option value="<%= vungRs.getString("pk_seq") %>" selected style="padding: 2px" ><%= vungRs.getString("ten") %></option>
                         <%}else { %>
                         	<option value="<%= vungRs.getString("pk_seq") %>" style="padding: 2px"><%= vungRs.getString("ten") %></option>
                         <%}} vungRs.close(); }%>        	
                    </select>
                    </fieldset>
                    
                    <fieldset style="width: 30%; float: left;"> 
					<legend><%=Utility.GLanguage("Khu vực",session,jedis) %> &nbsp;</legend>
					<select name="kvIds" id="khuvucId" multiple="multiple" onchange="AjaxNpp()">
			            <% if(kvRs != null) {
			            	while(kvRs.next())
	                          {
	                            if(cttbBean.getKhuvucIds().indexOf(kvRs.getString("pk_seq")) >= 0 )
	                            { %>
	                              <option value="<%= kvRs.getString("pk_seq") %>" selected style="padding: 2px"><%= kvRs.getString("ten") %></option> 
	                          <%}else { %>
	                          	<option value="<%= kvRs.getString("pk_seq") %>" style="padding: 2px"><%= kvRs.getString("ten") %></option>
	                          <%}} kvRs.close(); }%>
                    </select>
                    </fieldset>
			   </th>
				</TR>
                <tr class="plainlabel" style="padding: 5px">
                	<th colspan="3">
                		<a class="button" href="javascript:submitform();">
        					<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Hiển thị NPP theo điều kiện",session,jedis) %></a>
                	</th>
                </tr>
                 </table>
                <TABLE id="NppTable">
                     <TR class="tbheader">
                        <TH align="center" width="10%"><%=Utility.GLanguage("Mã nhà phân phối",session,jedis) %></TH>
                        <TH align="center" width="50%"> <%=Utility.GLanguage("Tên nhà phân phối",session,jedis) %> </TH>
                        <TH align="center" width="10%"><input type ="checkbox" name ="all" onclick ="All()"><%=Utility.GLanguage("Chọn tất cả",session,jedis) %></TH>
                    </TR>
					<%
					int k = 0;
					while(nppRs.next()) {
					if(k % 2==0){
					%>
	                   	<TR class='tbdarkrow'>
	               <%}else{ %>
	                    <TR class='tblightrow'>
	               <%} %>
                        <TD align="center"><%= nppRs.getString("ma") %></TD>
	                    <TD align="left"><%= nppRs.getString("ten") %></TD>
	                    <% if(cttbBean.getNppIds().indexOf((nppRs.getString("pk_seq"))) >= 0 ) {%>
	                    	<TD align="center"><input type ="checkbox" name ="nppIds" value ="<%= nppRs.getString("pk_seq")%>" checked="checked"></TD>
	                    <%} else {%>
	                      	<TD align="center"><input type ="checkbox" name ="nppIds" value ="<%= nppRs.getString("pk_seq")%>"></TD>
	                  	<%} %>
                   </TR>
	                <% k++;}%>
                    <TR>
                        <TD align="center" colspan="10" class="plainlabel">&nbsp;</TD>
                    </TR>
                    </TABLE>
              </div>     
            
            
            
            
            
            
         </div>
    </fieldset>
  </div>    
</div>
</form>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
<script>


	replaces();


</script>

</BODY>
</HTML>
<%

try{
if(tratbRs!=null){
tratbRs.close();
}
if(kbhRs!=null){
	kbhRs.close();
	}
if(kbhRs!=null){
	kbhRs.close();
	}
if(kvRs!=null){
	kvRs.close();
	}
if(nppRs!=null){
	nppRs.close();
	}
cttbBean.DbClose();

}catch(Exception er){	
}
%>
