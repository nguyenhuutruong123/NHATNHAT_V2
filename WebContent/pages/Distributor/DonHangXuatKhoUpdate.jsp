<%@page import="com.extentech.formats.XLS.Obj"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.donhang.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DateFormat" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.SimpleDateFormat" %>
<%@page import="java.util.Locale" %>
<% Locale.setDefault(new Locale("en", "US")); %>

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
<% IDonhang dhBean = (IDonhang)s.getAttribute("dhBean");
%>
<% List<ISanpham> splist = (List<ISanpham>)dhBean.getSpList(); %>
<% ResultSet ddkd = (ResultSet)dhBean.getDdkdList(); %>
<% ResultSet tbh = (ResultSet)dhBean.getTbhList(); %>
<% ResultSet kh = (ResultSet)dhBean.getKhList();%>
<% ResultSet kho = (ResultSet)dhBean.getKhoList(); %>
<% ResultSet nvgn = (ResultSet)dhBean.getnvgnRs(); %>

<% String ngaykhoaso = (String)dhBean.getNgayKs(); %>
<% String userId = (String) s.getAttribute("userId");
	System.out.print("_______________"+userId);
%>
<% Hashtable<String, Integer> spThieuList = dhBean.getSpThieuList(); %>
<% Hashtable<String, Float> scheme_tongtien = dhBean.getScheme_Tongtien(); %>
<% Hashtable<String, Float> scheme_chietkhau = dhBean.getScheme_Chietkhau(); %>
<% List<ISanpham> scheme_sanpham = (List<ISanpham>)dhBean.getScheme_SpList(); %>
<% ResultSet gsbhs = (ResultSet)dhBean.getgsbhs(); 
	ResultSet ctkmDiemRs = (ResultSet)dhBean.getCtkmDiemRs();
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<%@page import="geso.dms.distributor.util.Utility"%><HTML>
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

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript">
	jQuery(function($){ 
		 $('.addspeech').speechbubble();})
</script>

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
<script type="text/javascript" src="../scripts/ajax-dynamic-list.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
<script language="javascript" type="text/javascript">

function ajaxOption_getlo(id, sanphamid,vitri)
{
	
	var ngaygiaodich = document.getElementById("ngayhoadon").value;
	
	var khoId = document.getElementById("khoTen").value;
	
	var nppId = document.getElementById("nppId").value;
	
	var khId = document.getElementById("khId").value;
	
	
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
	xmlhttp.open("POST","../../DonhangAjaxGetLoSvl?sanphamid="+ sanphamid+"&khoid="+khoId+"&vitri="+vitri+"&ngaygiaodich="+ngaygiaodich+"&nppId="+nppId+"&khId="+khId, true);
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
	var solo = document.getElementsByName(index+".solo");
	var soluongton = document.getElementsByName(index+".soluongton");
	var soluong = document.getElementsByName(index+".soluong");
	var ton=0;
	for(i=0; i < solo.length; i++)
	{
		var slton=soluongton.item(i).value;
		if(slton>soluongphanbo){
			soluong.item(i).value =Math.round(soluongphanbo,0);
			return;
		} else{
			soluongphanbo=soluongphanbo - slton;
			soluong.item(i).value =Math.round(slton,0);
			ton+=slton;
		}
	}
	if(ton==0)
		{
		soluong.item(0).value =Math.round(soluongphanbo,0);
		}
	
	
	
}	




function replaces()
{
	var khTen = document.getElementsByName("khTen"); 
	var smartId = document.getElementsByName("smartId");
	var ckId = document.getElementsByName("ChietKhau");
	var bgstId = document.getElementById("bgstId");
	var khId = document.getElementById("khId");
	
	for(var i=0; i < smartId.length; i++)
	{
		var tem = smartId.item(0).value;
		if(tem == "")
		{
			khTen.item(0).value = "";
			document.getElementById("khId").value = "";
			ckId.item(0).value = "0";
			bgstId.value = "0";
			break;
		}	
		if(parseInt(tem.indexOf("-->")) > 0)
		{
			var tmp = tem.substring(0, parseInt(tem.indexOf("-->[")));
			smartId.item(0).value = tmp.substring(0, parseInt(tem.indexOf("-")));	
			
			tem = tem.substr(parseInt(tem.indexOf("-->[")) + 4);
			
			khTen.item(0).value = tem.substring(0, parseInt(tem.indexOf("] [")));
			
			tem = tem.substr(parseInt(tem.indexOf("] [")) + 3);
			
			ckId.item(0).value = tem.substring(0, parseInt(tem.indexOf("] [")));
			
			tem = tem.substr(parseInt(tem.indexOf("] [")) + 3);
			bgstId.value = tem.substring(0, parseInt(tem.indexOf("] ")));
			
			tem = tem.substr(parseInt(tem.indexOf("] [")) + 3);
			khId.value = tem.substring(0, parseInt(tem.indexOf("]")));
			
			if(khTen.item(0).value != "")
			{				 
				if(document.getElementById("gsbhId").value == "")
				{
					document.forms['dhForm'].action.value = 'submitKh';
			    	document.forms["dhForm"].submit();
				}
				else
				{
					document.forms['dhForm'].action.value = 'submit';
			    	document.forms["dhForm"].submit();
				}
			}
			
			break;
		}	
	}
	
	var IsSampling = document.getElementById("IsSampling");
	
	var masp = document.getElementsByName("masp");
	var tensp = document.getElementsByName("tensp1");
	var donvitinh = document.getElementsByName("donvitinh1");
	var dongia = document.getElementsByName("dongia1");
	var dongiagoc = document.getElementsByName("dongiagoc");

	var chietkhau1 = document.getElementsByName("spchietkhau1");
	var chietkhau = document.getElementsByName("spchietkhau");
	
	var soluong = document.getElementsByName("soluong");
	var tonkho = document.getElementsByName("tonkho1");
	var thanhtien = document.getElementsByName("thanhtien1");
	var ctkm = document.getElementsByName("ctkm1");
	
	var ckId = document.getElementById("ChietKhau");
	var pt_ck = ckId.value;
	if(pt_ck == '')
		pt_ck = 0;
	
	var cknkId = document.getElementById("ChietKhaunk");
	var pt_cknk = cknkId.value;
	if(pt_cknk == '')
		pt_cknk = 0;
	
	var ptVAT = document.getElementsByName("ptVAT1");
	var sptienthue = document.getElementsByName("sptienthue");
	
	var tungay = document.getElementById("tungay").value;
	var sau_mung_01_thang_10 = '1';
	
	if( tungay != '' )
	{
	 	var ngay1 = Date.parse(tungay);
	 	var ngay2 = Date.parse('2014-10-01');
		if(ngay1 <  ngay2)
		{
			sau_mung_01_thang_10 = '0';
		}
	}
	
	
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
				
				//SAU NGAY 01-10-2014 // TU CHIA CHIET KHAU VAO GIA
				<% if(dhBean.getDonhangKhac().equals("1")){ %>
				dongia.item(i).value = "0";
				<%} else {%>
				dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")) );
				<%}%>
				
				
				sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
				<% if(dhBean.getDonhangKhac().equals("1")){ %>
				dongiagoc.item(i).value = "0";
				<%} else {%>
				dongiagoc.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")) );
				<%}%>
				
				
				
				chietkhau.item(i).value = 0;
				chietkhau1.item(i).value = 0;
				
				sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);

				tonkho.item(i).value = DinhDangTien(sp.substring(0, parseInt(sp.indexOf("] ["))));
				//	tonkho.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
				sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
				
				ptVAT.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
				sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
				
				var isnhom = sp.substring(0, parseInt(sp.indexOf("]")));
				ajaxOption_getlo("subcontent"+i,masp.item(i).value ,i);
				console.log(masp.item(i).value);
				
					if(isnhom==0)
					dongia.item(i).value = parseFloat(dongia.item(i).value);
					else
						dongia.item(i).value = parseFloat(dongia.item(i).value) ;
					var sole = '';
					if(dongia.item(i).value.indexOf(".") >= 0)
					{
						sole = dongia.item(i).value.substring(dongia.item(i).value.indexOf('.'), parseInt(dongia.item(i).value.indexOf('.')) + 5 );
					}
					var dg = dongia.item(i).value.split(".");
					dongia.item(i).value = dg[0] + sole;
					//alert('DG0: ' + dg[0] + '  -- DG1: ' + dg[1] );
				
				
			
			}
			
			var so_luong = soluong.item(i).value.replace(/,/g, "");
			if(parseInt(so_luong) > 0)
			{		
				var don_gia = dongia.item(i).value.replace(/,/g, "");
				var tck = 0;
				
				//tinh chiet khau theo san pham
				if( sau_mung_01_thang_10 == '0' )
				{
					tck = (parseFloat(ckId.value) * parseInt(so_luong) * parseFloat(don_gia)) / 100;
					chietkhau.item(i).value = DinhDangTien(roundNumber(tck, 2));
					chietkhau1.item(i).value = 0;
				}
				else
				{
					chietkhau.item(i).value = 0;
					chietkhau1.item(i).value = 0;
				}
				
				var _vat = ptVAT.item(i).value;
				if(_vat == '')
					_vat = '0';
				
				var thueVAT = Math.round( Math.round( parseFloat(so_luong) * parseFloat(don_gia) ) * parseFloat( _vat ) / 100 );
						
				//var thueVAT = ( ( parseFloat(so_luong) * parseFloat(don_gia) ) - parseFloat(tck) ) * parseFloat( _vat ) / 100;
				sptienthue.item(i).value = DinhDangTien(thueVAT); 
				
				//var tt =  ( ( parseInt(so_luong) * parseFloat(don_gia) ) - parseFloat(tck) ) + parseFloat(thueVAT) ;				
				//var tt =  (  Math.round ( parseFloat(don_gia) * parseFloat(so_luong) ) - parseFloat(tck) ) + parseFloat(thueVAT) ;
				var tt =    Math.round ( parseFloat(don_gia) * parseFloat(so_luong) ) ;
				//console.log('thanh tien la  '+tt)
				thanhtien.item(i).value = DinhDangTien(tt);
				
				TinhTien();
			}
			else			
			{
				chietkhau.item(i).value = "";
				sptienthue.item(i).value = "";
				thanhtien.item(i).value = "";
			}
			
			if(checkMasp(masp.item(i).value) == true)
			{
				masp.item(i).parentNode.parentNode.bgColor = "#9FC";
			}
		}
		else
		{
			tensp.item(i).value = "";
			donvitinh.item(i).value = "";
			dongia.item(i).value = "";
			chietkhau.item(i).value = "";
			soluong.item(i).value = "";
			ptVAT.item(i).value = "";
			thanhtien.item(i).value = "";
			tonkho.item(i).value = "";
			//ctkm.item(i).value = "";
			
			TinhTien();
		}
	}	
	
	//CAP NHAT CHIET KHAU BO SUNG
	var ckbsIds = document.getElementsByName("ckbsIds");
	var chietkhau_vat = document.getElementsByName("chietkhau_vat");
	var chietkhau_ck = document.getElementsByName("chietkhau_ck");
	var chietkhau_tienVAT = document.getElementsByName("chietkhau_tienVAT");
	var chietkhau_thanhtien = document.getElementsByName("chietkhau_thanhtien");
	
	if(ckbsIds != null)
	{
		for(i = 0; i < ckbsIds.length; i++)
		{
			if( ckbsIds.item(i).value != '' )
			{
				var _pt = ckbsIds.item(i).value.split('--');
				chietkhau_vat.item(i).value = _pt[1];
				
				if(chietkhau_ck.item(i).value != '' )
				{
					var _ckVAT = parseFloat(chietkhau_ck.item(i).value.replace(/,/g, "")) * parseFloat(_pt[1]) / 100;
					var _ckTHANHTIEN = parseFloat(chietkhau_ck.item(i).value.replace(/,/g, "")) + parseFloat(_ckVAT);
					
					chietkhau_tienVAT.item(i).value = DinhDangTien( (-1) * _ckVAT);
					chietkhau_thanhtien.item(i).value = DinhDangTien( (-1) * _ckTHANHTIEN);
				}
				else
				{
					chietkhau_tienVAT.item(i).value = '';
					chietkhau_thanhtien.item(i).value = '';
				}
			}
			else
			{
				chietkhau_vat.item(i).value = '';
				chietkhau_ck.item.value = '';
			}
		}
	}
	
	
	setTimeout(replaces, 300);
}
	
	
function TinhTien()
{
	var thanhtien = document.getElementsByName("thanhtien1");
	var chietkhau = document.getElementsByName("spchietkhau1");
	var dongia = document.getElementsByName("dongia1");
	var soluong = document.getElementsByName("soluong");
	var ptVAT = document.getElementsByName("ptVAT1");
	var sptienthue = document.getElementsByName("sptienthue");

	var tongtien = 0;
	var tongchietkhau = 0;
	var tongvat = 0;
	var tienckchuavat=0;
	var _ptvat1=0;
	for(var i = 0; i < thanhtien.length; i++)
	{
		var don_gia = dongia.item(i).value.replace(/,/g, "");
		var so_luong = soluong.item(i).value.replace(/,/g, "");
		
			
		var chiet_khau = chietkhau.item(i).value.replace(/,/g, "");
		if(chiet_khau != '')
			tongchietkhau = parseFloat(tongchietkhau) +  parseFloat(chiet_khau);
		
		var _ptvat = ptVAT.item(i).value;
		
		
		var _tienVAT = Math.round( Math.round( parseFloat(so_luong) * parseFloat(don_gia) ) * parseFloat( _ptvat ) / 100 );
		 if(_ptvat != '')
			 {
			
				_ptvat1=_ptvat;
			 }
	 
		if( don_gia != '' && so_luong != '' )
			{
			tongtien = parseFloat(tongtien) +  Math.round ( parseFloat(don_gia) * parseFloat(so_luong) ) + parseFloat(_tienVAT); 
			tienckchuavat = parseFloat(tienckchuavat) +  parseInt( parseFloat(don_gia) * parseFloat(so_luong)  ) ;
			}
		 
	}
	
	
	 var ck = document.getElementById("ChietKhau").value;
	if(ck == "")
		ck = "0";
	var tienchietkhau = (tienckchuavat * parseFloat(ck)) / 100; 
//	console.log(" tien chua vat "+ tienchuaVAT +" chiet khau "+ck + " tien ck la "+ tienchietkhau);
	tongchietkhau = Math.abs( parseFloat( TongchietkhauKM() ) +  Math.abs(  parseFloat( Tongtienkhuyenmai() ) ) );
	
	
	//CHIET KHAU BO SUNG
	var ckbsIds = document.getElementsByName("ckbsIds");
	var chietkhau_ck = document.getElementsByName("chietkhau_ck");
	var chietkhau_tienVAT = document.getElementsByName("chietkhau_tienVAT");
	
	if(ckbsIds != null)
	{
		for(i = 0; i < ckbsIds.length; i++)
		{
			if( ckbsIds.item(i).value != '' && chietkhau_ck.item(i).value != '' )
			{
			//	tongchietkhau = parseFloat(tongchietkhau) +  Math.abs( parseFloat(chietkhau_ck.item(i).value.replace(/,/g, "")) );
			//	tongvat = parseFloat(tongvat) - Math.abs( parseFloat(chietkhau_tienVAT.item(i).value.replace(/,/g, "")) );
			}
		}
	}
	
		
		
	document.getElementById("SoTienChuaVAT").value = DinhDangTien(tienckchuavat);
	//document.getElementById("SoTienCoVAT").value = DinhDangTien(tongtien);

	//document.getElementById("TienChietKhau").value = DinhDangDonGia(tienchietkhau );
	document.getElementById("TienChietKhau").value = DinhDangDonGia(roundNumber(tienchietkhau,0));
	
 	var tienkhuyenmai=Math.abs(  parseFloat(Tongtienkhuyenmai()) )+ Math.abs(parseFloat(TongchietkhauKM()));
	document.getElementById("SoTienCKKM").value = DinhDangTien( tienkhuyenmai );
	
	var tiengiamtru=document.getElementById("tiengiamtru").value.replace(/,/g, "");
	//document.getElementById("tiengiamtru").value = DinhDangTien1( tiengiamtru );
	
	if(tiengiamtru=='')
	{
		tiengiamtru=0;
	}
	
	var tiensauCK = tienckchuavat - Math.round(tienchietkhau) - Math.round(tienkhuyenmai) - Math.round(parseFloat(tiengiamtru));
	document.getElementById("SoTienSauCK").value = DinhDangTien(parseFloat(tiensauCK));
	
	
	
		//var SoTienSauCKKM = tongtiencoVAT - Math.abs( parseFloat(TongchietkhauKM()) + parseFloat(Tongtienkhuyenmai()) ); 
//	console.log(parseFloat(tienckchuavat) +"---"+ parseFloat(tienchietkhau) +"----"+ parseFloat( tienkhuyenmai) +"----"+"_____"+parseFloat(tiengiamtru));

//console.log( parseFloat(tienckchuavat) - Math.round(parseFloat(tienchietkhau)) - Math.round( parseFloat( tienkhuyenmai)) - Math.round(parseFloat(tiengiamtru))  +"----"+_ptvat1);
		//tongvat= ( parseFloat(tienckchuavat) - Math.round(parseFloat(tienchietkhau)) - Math.round( parseFloat( tienkhuyenmai)) - Math.round(parseFloat(tiengiamtru)) ) *(_ptvat1/100.0);
		tongvat = 0;
		document.getElementById("tienvat").value = Math.round(parseFloat(tongvat));

	//var SoTienSauCKKM =  Math.round(parseFloat(tienckchuavat)) - Math.round(parseFloat(tienchietkhau)) - Math.round(parseFloat( tienkhuyenmai)) + Math.round(parseFloat(tongvat)) - Math.round(parseFloat(tiengiamtru))  ; 
	var SoTienSauCKKM =  Math.round(parseFloat(tienckchuavat)) - Math.round(parseFloat(tienchietkhau)) - Math.round(parseFloat( tienkhuyenmai)) - Math.round(parseFloat(tiengiamtru))  ; 

	
	//console.log("tien thu ve la "+parseFloat(tienckchuavat) + " ck la "+ parseFloat(tienchietkhau)+"----" + (( parseFloat( tienkhuyenmai)) * (1+(parseFloat(_ptvat1)/100.0))));
	document.getElementById("SoTienSauCKKM").value = DinhDangTien(SoTienSauCKKM);
	
//	document.getElementById("SoTienVAT").valu	e = DinhDangTien(parseFloat(tongvat));
	
}
	
	function Tongtienkhuyenmai()
	{
		return 0;
	}
	
	function TongchietkhauKM()
	{
		return 0;
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
	
	function keypress2(e)
	{    
		var keypressed = null;
		if (window.event)
			keypressed = window.event.keyCode; //IE
		else
			keypressed = e.which; //NON-IE, Standard
		
			if (keypressed == 13)
			{
				var khTen = document.getElementsByName("khTen"); 
				var smartId = document.getElementsByName("smartId");
				var ckId = document.getElementsByName("ChietKhau");
				var bgstId = document.getElementById("bgstId");
				
				for(i=0; i < smartId.length; i++)
				{
					var tem = smartId.item(0).value;
					if(parseInt(tem.indexOf("-->[")) > 0)
					{
						var tmp = tem.substring(0, parseInt(tem.indexOf("-->[")));
						document.getElementById("khId").value = tmp.substring(parseInt(tem.indexOf("-")+1, tmp.length));
						smartId.item(0).value = tmp.substring(0, parseInt(tem.indexOf("-")));
						tem = tem.substr(parseInt(tem.indexOf("-->[")) + 4);
						khTen.item(0).value = tem.substring(0, parseInt(tem.indexOf("][")));
						tem = tem.substr(parseInt(tem.indexOf("][")) + 2);
						ckId.item(0).value = tem.substring(0, parseInt(tem.indexOf("][")));
						
						tem = tem.substr(parseInt(tem.indexOf("][")) + 2);
						bgstId.value = tem.substring(0, parseInt(tem.indexOf("]")));
					}
				}
				
				document.forms['dhForm'].action.value='submitKh';
				document.forms['dhForm'].enterKH.value='1';
				document.forms["dhForm"].submit();
			}
	}

	 function saveform()
	 {	
		 <% System.out.println("oala "+dhBean.getIsSampling() +" dh hang khac "+dhBean.getDonhangKhac()); %>
		 <% if(!dhBean.getDonhangKhac().equals("1")) { %>
		 
		 var kq = document.getElementById("chuaapkm").value;
		
		 
		 if(kq == "false")
		 {
			 if(!confirm('Bạn chưa áp khuyến mại cho đơn hàng này, bạn có muốn áp khuyến mại?'))
			 {
				 if(checkSanPham() == false)
				 {	
					alert("Error, khong co san pham ban nao duoc chon...");
					return;
				 }
					
				 congDonSPCungMa();
				
				 var masp = document.getElementsByName("masp");
				 var tensp = document.getElementsByName("tensp");
				 var soluong = document.getElementsByName("soluong");
				 var ddkdId = document.getElementById("ddkdTen");
				 var khId = document.getElementById("khTen");
				 var khoId = document.getElementById("khoTen");
				 var gsbhId = document.getElementById("gsbhId");
				 var nvgnId = document.getElementById("nvgnId");
				 var dongia = document.getElementsByName("dongia1");
				 
				 if(gsbhId.value == "")
				 {
					alert("Vui lòng kiểm tra Giám sát bán hàng...");
					return;
				 }
				 
				 if(ddkdId.value == "")
					{
						alert("vui lòng chọn nhân viên bán hàng...");
						return;
					}
					if(khId.value == "")
					{
						alert("Vui lòng chọn khách hàng...");
						return;
					}
					if(khoId.value == "")
					{
						alert("vui lòng chọn kho nhập hàng...");
						return;
					}
				 
					/* if(nvgnId.value == "")
					{
						alert("vui lòng chọn nhân viên giao nhận...");
						return;
					}  */
					
				 for(var k = 0; k < masp.length; k++)
				 {
					if(masp.item(k).value != "")
					{
						if(soluong.item(k).value == "" || soluong.item(k).value  == "0" || tensp.item(k).value == "")			
						{
							alert("Kiểm tra lại tên và số lượng sản phẩm, Phải nhập tên và số lượng cho sản phẩm được chọn");
							return;
						}
						
						if(dongia.item(k).value=="")
						{
							alert("Yêu cầu nhập giá sản phẩm!");
							return;
						}
					}
				 }


				 var ngaykhoaso = document.getElementById("ngaykhoaso").value;
				 var tungay = document.getElementById("tungay").value;
				 if(ngaykhoaso.length >0 && tungay.length >0 ){
				 var ngay1 =Date.parse(ngaykhoaso);
				 var ngay2 = Date.parse(tungay);
				 //	if(ngay1 >=  ngay2)
				//	{
				//		alert('Lỗi...Bạn đã khóa sổ đến ngày ' + ngaykhoaso  + ' rồi...');
				//		return;
				//	}
				 }
				 var SoTienCoVAT = document.getElementById("SoTienCoVAT").value;
				 var muctindung = document.getElementById("muctindung").value;

				 if(parseFloat(SoTienCoVAT) > parseFloat(muctindung) && parseFloat(muctindung) > 0 )
			     {
				  	if(!confirm('Ban da vuot muc tin dung roi, ban co muon tiep tuc mua hang khong?'))
				  	return;
				 }
				 
				 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
			 	 document.forms['dhForm'].action.value='save';
			     document.forms['dhForm'].submit();
			 }
			 else
			 {
				 Apkhuyenmai();
			 }
		 }
		 
		 <% } else { %>
		 	
		 	 congDonSPCungMa();
			 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
		 	 document.forms['dhForm'].action.value='save';
		     document.forms['dhForm'].submit();
		 <% } %>
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
	 
	 function submitform()
	 { 
		 congDonSPCungMa();
		
		document.forms['dhForm'].action.value='submit';
	    document.forms["dhForm"].submit();
	 }
	 
	 function ChotForm()
	 {
		 congDonSPCungMa();
		 var masp = document.getElementsByName("masp");
		 var tensp = document.getElementsByName("tensp");
		 var soluong = document.getElementsByName("soluong");
		 var dongia = document.getElementsByName("dongia1");
		 var tonkho = document.getElementsByName("tonkho1");
		 
		 for(var k = 0; k < masp.length; k++)
		 {
			 if(masp.item(k).value != "")
				 {
					if(soluong.item(k).value == "" || tensp.item(k).value == "")
					{
						alert("Kiểm tra lại tên và số lượng sản phẩm, Phải nhập tên và số lượng cho sản phẩm được chọn");
						return;
					}
					
					if(dongia.item(k).value=="")
					{
						alert("Yêu cầu nhập giá sản phẩm!");
						return;
					}
				 }
		 }
		 document.getElementById("btnChot").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";

		 document.forms['dhForm'].action.value='chotdonhang';
		 document.forms['dhForm'].submit();
	 }
	 
	 function Apkhuyenmai()
	 {
		 
		 var ngaykhoaso = document.getElementById("ngaykhoaso").value;
		 var tungay = document.getElementById("tungay").value;
		// alert(ngaykhoaso +' '+ tungay);
		 if(ngaykhoaso.length >0 && tungay.length >0 ){
		 var ngay1 =Date.parse(ngaykhoaso);
		 var ngay2 = Date.parse(tungay);
		//	if(ngay1 >=  ngay2)
		//	{
		//		alert('Lỗi...Bạn đã khóa sổ đến ngày '+ngaykhoaso  +' rồi...');
		//		return;
		//	}
		 }
		 congDonSPCungMa();
		 var masp = document.getElementsByName("masp");
		 var tensp = document.getElementsByName("tensp");
		 var soluong = document.getElementsByName("soluong");
		 var dongia = document.getElementsByName("dongia1");
		 for(var k = 0; k < masp.length; k++)
		 {
			 if(masp.item(k).value != "")
				 {
				if(soluong.item(k).value == "" || soluong.item(k).value  == "0" || tensp.item(k).value == "")
				{
					alert("Kiểm tra lại tên và số lượng sản phẩm, Phải nhập tên và số lượng cho SP được chọn");
					return;
				}
				
				 }
		 }
		 
		 document.getElementById("btnApKhuyenMai").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
		 
		 document.forms['dhForm'].action.value='apkhuyenmai';
		 document.forms['dhForm'].submit();
	 }

	 function congDonSPCungMa()
	 {
		var masp = document.getElementsByName("masp");
		var soluong = document.getElementsByName("soluong");
		var khoNVBH1 = document.getElementsByName("khoNVBH1");
		var khoNVBH = document.getElementsByName("khoNVBH");
		for(var k = 0; k < khoNVBH1.length; k++ )
		{
			if(khoNVBH1.item(k).checked == true)
				khoNVBH.item(k).value = "1";
			else
				khoNVBH.item(k).value = "0";
		}
		
		var ii;
		for(ii = 0; ii < (masp.length - 1) ; ii++)
		{
			for( j = ii + 1; j < masp.length; j++)
			{
				if(masp.item(ii).value != "" && masp.item(ii).value == masp.item(j).value && khoNVBH.item(ii).value == khoNVBH.item(j).value )
				{
					//alert(masp.item(ii).value + "-" + masp.item(j).value);			
					if(soluong.item(j).value == "")
						soluong.item(j).value = "0";
					
					soluong.item(ii).value = parseInt(soluong.item(ii).value) + parseInt(soluong.item(j).value);
					masp.item(j).value = "";
				}
			}
		}

		 var tensp1 = document.getElementsByName("tensp1");
		 var donvitinh1 = document.getElementsByName("donvitinh1");
		 var dongia1 = document.getElementsByName("dongia1");
		 //var chietkhau1 = document.getElementsByName("spchietkhau1");
		 //var chietkhau1 = document.getElementsByName("spchietkhau1");
	 	 var ptVAT1 = document.getElementsByName("ptVAT1");
	 	 var thanhtien1 = document.getElementsByName("thanhtien1");
	 	 var tonkho1 = document.getElementsByName("tonkho1");
		 
		 var tensp = document.getElementsByName("tensp");
		 var donvitinh = document.getElementsByName("donvitinh");
		 var dongia = document.getElementsByName("dongia");
		 //var chietkhau = document.getElementsByName("spchietkhau");
		 var ptVAT = document.getElementsByName("ptVAT");
	 	 var thanhtien = document.getElementsByName("thanhtien");
	 	 var tonkho = document.getElementsByName("tonkho");
	 	 
	 	for(var pos = 0; pos < masp.length; pos++)
	 	{
	 		if(masp.item(pos).value != "")
	 		{
		 		tensp.item(pos).value = tensp1.item(pos).value;
		 		donvitinh.item(pos).value = donvitinh1.item(pos).value;
		 		dongia.item(pos).value = dongia1.item(pos).value;
		 		//chietkhau.item(pos).value = chietkhau1.item(pos).value;
		 		ptVAT.item(pos).value = ptVAT1.item(pos).value;
		 		thanhtien.item(pos).value = thanhtien1.item(pos).value;
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
	
	function EnterKhachHang()
	{
		<% if(dhBean.getEnterKH().equals("1")) { %>
			var masp = document.getElementsByName("masp");
			masp.item(0).focus();
		<% } %>
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

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0" onload="EnterKhachHang();" >
<form name="dhForm" method="post" action="../../DonhangUpdateSvl">
<input type="hidden" name="userId" value='<%=userId %>'>
<input type="hidden" name="nppId" id="nppId" value='<%= dhBean.getNppId() %>'>
<input type="hidden" name="ngaykhoaso" id = "ngaykhoaso" value='<%=ngaykhoaso %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="chuaapkm" id="chuaapkm" value='<%= dhBean.isChuaApkhuyenmai() %>'>
<input type="hidden" name="muctindung" id ="muctindung" value='<%=dhBean.getMuctindung()%>'>
<INPUT type="hidden" name="trangthai" value=''>   
<INPUT type="hidden" name="bgstId" id="bgstId" value='<%= dhBean.getBgstId() %>'> 
<input type="hidden" id = "khId" name="khId" value='<%= dhBean.getKhId() %>'>
<input type="hidden" name = "enterKH"  value='<%= dhBean.getEnterKH() %>'>
<input type="hidden" name = "donhangkhac"  value='<%= dhBean.getDonhangKhac() %>'>
<input type="hidden" name = "ngayhoadon" id="ngayhoadon"  value='<%= dhBean.getNgayhoadon() %>'>
<%System.out.println("don hang khac"+ dhBean.getDonhangKhac()); %>
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
			 								   <TD align="left"  class="tbnavigation">Quản lý bán hàng > Bán hàng OTC > Bán hàng cho khách hàng > Tạo mới </TD>								   
			 								   <TD align="right" class="tbnavigation">Chào mừng  <%= dhBean.getNppTen() %> &nbsp; </TD>
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
										    
										    <div id="btnSave">
										    	<A href="javascript:saveform()" ><img src="../images/Save30.png" alt="Luu lai"  title="Luu lai" border="1" longdesc="Luu lai" style="border-style:outset"></A>
										    </div>
										   
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
								<TD align="left" colspan="4" class="legendtitle">
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
													<TABLE cellpadding="3" cellspacing="0" width="100%"
														border=0>

														<TR>
															<TD class="plainlabel">Ngày giao dịch</TD>
															<TD class="plainlabel"><input type="text"
																class="days" size="11" onchange="submitform()"
																id="tungay" name="ngaygiaodich"
																value="<%= dhBean.getNgaygiaodich() %>" maxlength="10"
																readonly /></TD>

															<TD class="plainlabel">Giám sát bán hàng</TD>
															<TD class="plainlabel"><SELECT
																name="gsbhId" id="gsbhId"
																onChange="ajaxOption('ddkdTen', this.value)">
																	<option value=""></option>
																	<% if(gsbhs != null){
														  try{ while(gsbhs.next()){ 													 
											    			if(gsbhs.getString("pk_seq").equals(dhBean.getGsbhId())){ %>
																	<option value='<%=gsbhs.getString("pk_seq")%>' selected><%=gsbhs.getString("ten") %></option>
																	<%}else{ %>
																	<option value='<%=gsbhs.getString("pk_seq")%>'><%=gsbhs.getString("ten") %></option>
																	<%}}}catch(java.sql.SQLException e){}} %>
															</SELECT>
															</TD>
															
															<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
																<TD class="plainlabel"><SELECT
																	name="ddkdTen" id="ddkdTen"
																	onChange="ajaxOption('gsbhId', this.value)">
																		<option value=""></option>
																		<% if(ddkd != null){
															  try{ while(ddkd.next()){ 													 
												    			if(ddkd.getString("ddkdId").equals(dhBean.getDdkdId())){ %>
																		<option value='<%=ddkd.getString("ddkdId")%>' selected><%=ddkd.getString("ddkdTen") %></option>
																		<%}else{ %>
																		<option value='<%=ddkd.getString("ddkdId")%>'><%=ddkd.getString("ddkdTen") %></option>
																		<%}}}catch(java.sql.SQLException e){}} %>
																</SELECT>
															</TD>
															
														</TR>

														<TR class="tblightrow">
															<TD class="plainlabel">Kho hàng</TD>
															<TD class="plainlabel"><SELECT name="khoTen"
																id="khoTen" onchange="submitform()">
																	<% if(kho != null){
														  try{while(kho.next()){
											    			if(kho.getString("khoId").equals(dhBean.getKhoId())){ session.setAttribute("khoId", kho.getString("khoId")); %>
																	<option value='<%= kho.getString("khoId") %>' selected
																		onMouseover="ddrivetip('<%= kho.getString("diengiai") %>', 300)"
																		; onMouseout="hideddrivetip()"><%= kho.getString("Ten") + " " %></option>
																	<%}else{ if(kho.getString("Ten").indexOf("PR") < 0){ %>
																	<option value='<%= kho.getString("khoId") %>'
																		onMouseover="ddrivetip('<%= kho.getString("diengiai") %>', 300)"
																		; onMouseout="hideddrivetip()"><%= kho.getString("Ten") + " " %></option>
																	<%}}}}catch(java.sql.SQLException e){} }%>
															</SELECT>
															</TD>
															
															<TD width="120px" class="plainlabel" valign="top">Nhân viên giao nhận</TD>
															<TD class="plainlabel" valign="top" >
																  	<SELECT name="nvgnId" id="nvgnId" >
																  		
																	    <% try{ while(nvgn.next()){ 
																    			if(nvgn.getString("PK_SEQ").equals(dhBean.getnvgnId()   )){ %>
																      				<option value='<%=nvgn.getString("PK_SEQ")%>' selected>
																      				<%=nvgn.getString("Ten") %></option>
																      			<%}else{ %>
																     				<option value='<%=nvgn.getString("PK_SEQ")%>'>
																     				<%=nvgn.getString("Ten") %></option>
																     			<%}}}catch(Exception e){} %>
																	 </SELECT>
															</TD>
															
																<TD class="plainlabel"> Hạng khách hàng </TD>
																<TD class="plainlabel"  > 
													 				<input  style="width:200px;" name="hchid" id="hchid" value="<%=dhBean.getHangkh() %>"  />
																</TD>
															
														</TR>

														<TR>
															<TD class="plainlabel">Mã khách hàng</TD>
															<TD class="plainlabel" width="250px"><input type="text" id="smartId" name="smartId" value="<%= dhBean.getSmartId() %>" onkeypress="keypress2(event);" />
															
															<%-- <TD class="plainlabel">Chủ cửa hiệu</TD>
															<TD class="plainlabel"><input type="text" name="chucuahieu" value="<%= dhBean.getChucuahieu() %>" readonly="readonly" /></TD> --%>
															
															<TD class="plainlabel" >Tên khách hàng - Địa chỉ</TD>
															<TD class="plainlabel" colspan ="3"><input type="text" id="khTen" name="khTen" style="width: 590px" value="<%= dhBean.getKhTen() %>" readonly /></TD>
															
														</TR>
														<TR>
															
															<TD class="plainlabel">Điện thoại</TD>
															<TD class="plainlabel"><input type="text"   name="khDienThoai" value="<%= dhBean.getKhDienThoai() %>" readonly="readonly" /></TD>
															<TD class="plainlabel" >Mã số thuế</TD>
															<TD class="plainlabel" ><input type="text"   name="khMaSoThue" value="<%= dhBean.getKhMaSoThue() %>" readonly /></TD>
															
															<td class="plainlabel" colspan="2" >
															 &nbsp;&nbsp;
																	 Đơn hàng khác
															 <%  if (dhBean.getDonhangKhac().equals("1")){%>
									                                      <input name="donhangKhac" type="checkbox" value ="1" checked onchange="submitform()">
									                                      <%-- <%if(dhBean.getIngia().equals("1")){ %>
											                    	 In đơn giá <input name="ingia" type="checkbox"  value ="1" checked="checked">
											                    	 <%}else { %>
											                    	  In đơn giá <input name="ingia" type="checkbox" value="0"> 
										                    		<%} %>--%>
											                    </TD>
							                                <%} else {%>
							                                       <input name="donhangKhac" type="checkbox" value ="1" onchange="submitform()">
							                                <%} %> 
									                                
									                                
															
			                                              <%--   	&nbsp;&nbsp;
																	 Đơn hàng khác
																	 
																	 <%  if (dhBean.getDonhangKhac().equals("1")){%>
									                                      <input name="donhangKhac" type="checkbox" value ="1" checked onchange="submitform()">
									                                <%} else {%>
									                                       <input name="donhangKhac" type="checkbox" value ="1" onchange="submitform()">
									                                <%} %> 
									                                
									                                &nbsp;&nbsp;
																	 Đề nghị trả CK tháng
																	 
																	 <%  if (dhBean.getDenghitrackThang().equals("1")){%>
									                                      <input name="denghitraCK" type="checkbox" value ="1" checked >
									                                <%} else {%>
									                                       <input name="denghitraCK" type="checkbox" value ="1" >
									                                <%} %> 
									                                
									                                &nbsp;&nbsp;
																	 Đơn quà tặng
																	 
																	 <%  if (dhBean.getIsDonquatang().equals("1")){%>
									                                      <input name="donquatang" type="checkbox" value ="1" checked onchange="submitform()" >
									                                <%} else {%>
									                                       <input name="donquatang" type="checkbox" value ="1" onchange="submitform()" >
									                                <%} %> 
			                                                </td>    --%>
														</TR>

														<TR class="tblightrow">
															<TD style="display: none" class="plainlabel">% Chiết khấu (khách hàng)</TD>
															<TD style="display: none" class="plainlabel"><input name="ChietKhau" style="text-align: right;"
																id="ChietKhau" type="text"
																value="<%= dhBean.getChietkhau() %>"
																onkeypress="return keypress(event);" >
																
																<input name="ChietKhaucu" style="display: none;"
																id="ChietKhau" type="text"
																value="<%= dhBean.getChietkhaucu() %>"
																onkeypress="return keypress(event);" readonly="readonly">
																
																<input name="ChietKhaunk" id="ChietKhaunk" type="hidden" value="<%=dhBean.getChietkhauNNK()%>" onkeypress="return keypress(event);" readonly="readonly" >
																
															</TD>
															
															
															
														</TR>

														
														<TR class="tblightrow">

															<TD class="plainlabel" style="display:none;">Tổng số tiền trước thuế</TD>
															<TD class="plainlabel" style="display:none;"><input name="SoTienChuaVAT"  style="text-align: right;"
																id="SoTienChuaVAT" type="text"
																value="<%=dhBean.getTongtientruocVAT()%>" readonly>
																VND</TD>

															<TD class="plainlabel">Tiền chiết khấu</TD>
															<TD class="plainlabel" ><input name="TienChietKhau"
																id="TienChietKhau" type="text" disabled="disabled"  style="text-align: right;"
																value="<%= dhBean.getTongChietKhau() %>"> VND</TD>
															
															<TD class="plainlabel">Tổng số tiền KM</TD>
															<TD class="plainlabel">
																<input name="SoTienCKKM"  style="text-align: right;" id="SoTienCKKM" type="text" readonly value="<%= dhBean.getTongtienCKKM() %>"> VND</TD>
															<TD class="plainlabel">
															<TD class="plainlabel">		
				                                    											
															
															
															
														</TR>
															
													 
														
														<TR style="display: none" class="tblightrow">
															
															<%if(!dhBean.getDonhangKhac().equals("1")) {%>
															<TD class="plainlabel">Tiền giảm trừ</TD>
															<TD class="plainlabel" colspan="3"><input  style="text-align: right;"
																name="tiengiamtru" id="tiengiamtru" type="text" 
																value="<%=dhBean.getTiengiamtru()==null?"0":dhBean.getTiengiamtru()%>"> VND</TD>
														<%} else { %>
															<TD class="plainlabel">Tiền giảm trừ</TD>
															<TD class="plainlabel" colspan="3"><input  style="text-align: right;"
																name="tiengiamtru" id="tiengiamtru" type="text" 
																value="0"> VND</TD>
												
															<%} %>
															<TD class="plainlabel"></TD>
															<TD class="plainlabel" colspan="3"></TD>
															
														</TR>
														
														<TR class="tblightrow">
															
															<TD class="plainlabel">Tổng tiền sau ck và khuyến mại</TD>
															<TD  class="plainlabel"><input
																name="SoTienSauCK"  style="text-align: right;"  id="SoTienSauCK" type="text" readonly
																value="<%=dhBean.getTongtiensauCK()%>"> VND</TD>
															
															
															<TD class="plainlabel" style="display:none;">Tiền Thuế</TD>
															<TD class="plainlabel" style="display:none;"><input
																name="tienvat" id="tienvat"  style="text-align: right;" type="text" readonly
																value=""> VND</TD>
															
															<TD class="plainlabel" style="color: red;">Tổng
																	tiền thu về</TD>
																<TD class="plainlabel">
																	<input name="SoTienSauCKKM"  style="text-align: right;"	id="SoTienSauCKKM" type="text" readonly value="<%= dhBean.getTongtiensauCKKM() %>"
																	style="color: red;"> VND</TD>
																	<TD class="plainlabel">
																	<TD class="plainlabel">
														</TR>
														
														
														
														
														
													
														
														

														<TR class="tblightrow" style="display: none;">
															<TD class="plainlabel">Tổng số tiền VAT</TD>
															<TD class="plainlabel"><input name="SoTienVAT"
																id="SoTienVAT" type="text" readonly value="">
																VND</TD>
															<TD class="plainlabel">Tổng số tiền (sau VAT)</TD>
															<TD class="plainlabel"><input name="SoTienSauVAT"  text-align="right"
																id="SoTienSauVAT" type="text" readonly value="">
																VND</TD>
														</TR>

														<TR class="tblightrow" style="display: none">
															<TD class="plainlabel">&nbsp;</TD>
															<TD class="plainlabel" colspan="3">
																<% if (dhBean.getIsDonHangLe().equals("1")){ %> <input
																type="checkbox" name="IsDonHangLe" value="1"
																checked="checked"><i> Đơn hàng lẻ</i> <% } else { %>
																<input type="checkbox" name="IsDonHangLe" value="1"><i>
																	Đơn hàng lẻ</i> <% }  %> &nbsp;&nbsp; <% if(!dhBean.getTrangthai().equals("3")) { %>
																<% if (dhBean.getIsSampling().equals("1")){ %> <input
																type="checkbox" name="IsSampling" id="IsSampling"
																value="1" checked="checked" onchange="submitform();"><i>
																	Đơn hàng Sampling</i> <% } else { %> <input type="checkbox"
																name="IsSampling" id="IsSampling" value="1"
																onchange="submitform();"><i> Đơn hàng
																	Sampling</i> <% }  %> <% } else { %> <% if (dhBean.getIsSampling().equals("1")){ %>
																<p style="display: none">
																	<input type="checkbox" name="IsSampling"
																		id="IsSampling" value="1" checked="checked"><i>
																		Đơn hàng Sampling</i>
																</p> <% } else { %>
																<p style="display: none">
																	<input type="checkbox" name="IsSampling"
																		id="IsSampling" value="1"><i> Đơn hàng
																		Sampling</i>
																</p> <% }  %> <% } %>
															</TD>
														</TR>
														<% if (  (dhBean.getDonhangKhac().equals("0") && dhBean.getIsDonquatang().equals("0")) ){ %>
															<TR class="tblightrow" style="display:none;">
																<TD class="plainlabel">CTKM điểm</TD>
																<TD class="plainlabel">
																<SELECT name="ctkmDiemId" id="ctkmDiemId"
																	onChange="submitform();">
																		<option value=""></option>
																		<% 
																		if(ctkmDiemRs!=null)
																		{
																			try{while(ctkmDiemRs.next()){ 
																		    	if(ctkmDiemRs.getString("pk_seq").equals(dhBean.getCtkmDiemId())){ %>
																							<option value='<%=ctkmDiemRs.getString("pk_seq")%>' selected><%=ctkmDiemRs.getString("diengiai") %></option>
																							<%}else{ %>
																							<option value='<%=ctkmDiemRs.getString("pk_seq")%>'><%=ctkmDiemRs.getString("diengiai") %></option>
																							<%}}}catch(java.sql.SQLException e){}
																							
																		}
																		
																		%>
																</SELECT>
																</TD>
																<TD class="plainlabel"> Tổng điểm </TD>
																<TD class="plainlabel"  > 
													 				<input   value="<%= formatter2.format(dhBean.getDiemkm()) %>"  />
																</TD>
																
																<TD class="plainlabel" colspan="2" >
																	<div id="TinhDiem">
																		<a class="button2" href="javascript:submitform()">
																			<img style="top: -4px;" src="../images/New.png" alt="">Tính điểm </a>
																	</div>
																
																
																
																</TD>
																
															</TR>
															<TR class="tblightrow" style="display:none;">
																<TD class="plainlabel"> Tiền KM điểm </TD>
																<TD class="plainlabel"  colspan="5" > 
													 				<input  name ="tienkmdiem" value="<%= formatter2.format(dhBean.getTienKmDiem()) %>" onChange="Dinhdang(this);"  />
																</TD>
															</TR>
														<%} %>
														
														<TR class="tblightrow" >
															
															

															<TD class="plainlabel"> Ghi chú </TD>
																<TD class="plainlabel"  colspan="5"> 
													 				<input type="text"  name="ghichu" value="<%= dhBean.getGhiChu() %>"  style="width: 700" />
																</TD>
				                                    
														</TR>
														<tr>
															<TD class="plainlabel" style="display:none">Tiêu chí đề nghị</TD>
															<TD class="plainlabel" style="display:none"><select name="tieuchidenghi"
																onchange="submitform()">
																	<option value="" selected="selected"></option>
																	<% if(dhBean.getTieuchi().equals("1")) { %>
																	<option value="1" selected="selected">Chỉ lấy
																		thêm nhóm SKX (15%)</option>
																	<% } else { %>
																	<option value="1">Chỉ lấy thêm nhóm SKX (15%)</option>
																	<% } %>
																	<% if(dhBean.getTieuchi().equals("2")) { %>
																	<option value="2" selected="selected">Chỉ lấy
																		thêm nhóm HHBG (40%)</option>
																	<% } else { %>
																	<option value="2">Chỉ lấy thêm nhóm HHBG
																		(40%)</option>
																	<% } %>
																	<% if(dhBean.getTieuchi().equals("3")) { %>
																	<option value="3" selected="selected">Cả 2
																		nhóm phải lấy (15%)</option>
																	<% } else { %>
																	<option value="3">Cả 2 nhóm phải lấy (15%)</option>
																	<% } %>
																	<% if(dhBean.getTieuchi().equals("4")) { %>
																	<option value="4" selected="selected">Chỉ lấy
																		thêm nhóm SKX (25%)</option>
																	<% } else { %>
																	<option value="4">Chỉ lấy thêm nhóm SKX (25%)</option>
																	<% } %>
																	<%-- <% if(dhBean.getTieuchi().equals("5")) { %>
											  				<option value="5" selected="selected" >Chỉ lấy thêm nhóm HHBG (45%)</option>
											  			<% } else { %>
											  				<option value="5" >Chỉ lấy thêm nhóm HHBG (45%)</option>
											  			<% } %> --%>
																	<%-- <% if(dhBean.getTieuchi().equals("6")) { %>
											  				<option value="6" selected="selected" >Cả 2 nhóm phải lấy (25%)</option>
											  			<% } else { %>
											  				<option value="6" >Cả 2 nhóm phải lấy (25%)</option>
											  			<% } %> --%>
															</select> <% if(dhBean.getKhId().trim().length() > 0 && dhBean.getNgaygiaodich().trim().length() > 0 && dhBean.getTieuchi().trim().length() > 0 ) {
											  			
											  			double tongDS = Double.parseDouble(dhBean.getDsTongNhomXanh()) + Double.parseDouble(dhBean.getDsTongNhomHHBG()) + Double.parseDouble(dhBean.getDsTongNhomKHAC());	
											  			double pt_XANH = 0;
											  			if(tongDS > 0)
											  				pt_XANH = Double.parseDouble(dhBean.getDsTongNhomXanh())  * 100 / tongDS;
											  			double pt_HHBG = 0;
											  			if(tongDS > 0)
											  				pt_HHBG = Double.parseDouble(dhBean.getDsTongNhomHHBG())  * 100 / tongDS;
											  			double pt_KHAC = 0;
											  			if(tongDS > 0)
											  				pt_KHAC = Double.parseDouble(dhBean.getDsTongNhomKHAC())  * 100 / tongDS;
											  		%> <a href="" id="doanhsoDENGHI"
																rel="subcontentDOANHSODENGHI"> <img
																	src="../images/vitriluu.png" height="18px"
																	title="Thay đổi ngày bật đèn" style="cursor: pointer;" />
															</a>

																<DIV id="subcontentDOANHSODENGHI"
																	style="position: absolute; visibility: hidden; border: 9px solid #80CB9B; background-color: #FFF; width: 400px; padding: 4px; z-index: 100000009;">
																	<table width="98%" align="center" cellspacing="1">
																		<tr class="tbheader">
																			<td style="width: 60%; font-size: 10pt">Tổng
																				doanh số</td>
																			<td
																				style="width: 20%; font-size: 10pt; text-align: right;"><%= formatter.format(tongDS)  %></td>
																			<td
																				style="width: 20%; font-size: 10pt; text-align: center;">Phần
																				trăm</td>
																		</tr>
																		<tr>
																			<td style="font-size: 10pt">DS nhóm HHBG</td>
																			<td
																				style="font-size: 10pt; text-align: right; width: 20%;"><%= formatter.format( Double.parseDouble(dhBean.getDsTongNhomHHBG()) )  %></td>
																			<td
																				style="font-size: 10pt; text-align: right; width: 20%;"><%= formatter.format( pt_HHBG ) %>
																				%</td>
																		</tr>
																		<tr>
																			<td style="font-size: 10pt">DS Nhóm xanh</td>
																			<td style="font-size: 10pt; text-align: right;"><%= formatter.format( Double.parseDouble(dhBean.getDsTongNhomXanh()) )  %></td>
																			<td style="font-size: 10pt; text-align: right;"><%= formatter.format( pt_XANH )  %>
																				%</td>
																		</tr>
																		<tr>
																			<td style="font-size: 10pt">DS Nhóm khác</td>
																			<td style="font-size: 10pt; text-align: right;"><%= formatter.format( Double.parseDouble(dhBean.getDsTongNhomKHAC()) )  %></td>
																			<td style="font-size: 10pt; text-align: right;"><%= formatter.format( pt_KHAC )  %>
																				%</td>
																		</tr>
																		<tr class="tbheader">
																			<td style="font-size: 10pt" colspan="3">Doanh
																				số đề nghị</td>
																		</tr>
																		<tr>
																			<td style="font-size: 10pt">DS nhóm HHBG phải
																				lấy</td>
																			<td style="font-size: 10pt; text-align: right;"
																				colspan="2"><%= formatter.format( Double.parseDouble(dhBean.getDsTongNhomHHBG_DeNghi()) )  %></td>
																		</tr>
																		<tr>
																			<td style="font-size: 10pt">DS Nhóm xanh phải
																				lấy</td>
																			<td style="font-size: 10pt; text-align: right;"
																				colspan="2"><%= formatter.format( Double.parseDouble(dhBean.getDsTongNhomXanh_DeNghi()) )  %></td>
																		</tr>

																	</table>

																	<div align="right">
																		<a
																			href="javascript:dropdowncontent.hidediv('subcontentDOANHSODENGHI')"
																			style="font-weight: bold;">Đóng lại</a>
																	</div>
																</DIV> <script type="text/javascript">
														dropdowncontent.init('doanhsoDENGHI', "right-top", 300, "click");
													</script> <% } %>
															</TD>
													
													<TD width="120px" class="plainlabel" valign="top"></TD>
															<TD class="plainlabel" valign="top" colspan="5">
																  	
															</TD>
						
															
														</tr>
														
										                 
										                    
										                 
														
											
														  <% if (  (dhBean.getDonhangKhac().equals("0") && dhBean.getIsDonquatang().equals("0")) ){ %> 
														<TR class="tblightrow">
																<TD class="plainlabel" colspan='6'>
																	<div id="btnApKhuyenMai">
																		<a class="button2" href="javascript:Apkhuyenmai()">
																			<img style="top: -4px;" src="../images/New.png" alt="">Lưu & Áp khuyến mại </a>
																	</div>
																
																
																
																</TD>
														</TR>
														<% } %>
													</TABLE>
												</FIELDSET></TD>
										</TR>


							   <TR>
							   		<TD>
										<TABLE width = "100%" border="0" cellpadding="0" cellspacing="1" id="tbSanPham">
										<tbody id="san_pham">
												<TR class="tbheader" >
													<TH width="10%">Mã sản phẩm </TH>
													<TH width="20%">Tên sản phẩm</TH>
													<TH width="8%">ĐVT</TH>
													<TH width="7%">Số lượng</TH>
													<TH width="8%">Tồn hiện tại</TH>
													<TH width="1%" style="display: none;" >Số lượng <br />( Thùng )</TH>
													
													
													<TH width="7%">Đơn giá</TH>
									
													<TH width="7%" style="display: none;">VAT</TH>
													<TH width="7%">Chiết khấu</TH>
													<TH width="7%" style="display: none;">Tiền thuế</TH>
													<TH width="7%">Thành tiền</TH>
													<TH width="10%" >CTKM</TH>
													<TH width="1%" style="display: none;" >Kho </TH>
													<%if(dhBean.getQuanlykho().equals("1")){ %>
														<TH width="5%">Chọn Lô</TH>
													<%} %>
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
								    	<input name="donvitinh1" disabled="disabled" type="text" value="<%= sanpham.getDonvitinh() %>"  style="width:100%;text-align:center; color: black;">
								    	<input name="donvitinh" type="hidden" value="<%= sanpham.getDonvitinh() %>"  style="width:100%;text-align:center">
								    </TD>
								    
									
								    <TD align = "center" style="display: none;" >
								    	<input name="soluong1" id="soluong1_<%= m %>" type="hidden" value="<%= sanpham.getSoluong1() %>"  style="width:100%;text-align:right; color: black;">
								    	<input name="soluong2" id="soluong2_<%= m %>" type="hidden" value="<%= sanpham.getSoluong2() %>"  style="width:100%;text-align:right; color: black;">
								    	<input name="soluongThung" id="soluongThung_<%= m %>" type="text" onkeyup="QuyRaLe(<%= m %>)"  value="<%= sanpham.getSoluongThung() %>"   onkeypress="return keypress(event);" style=" width:100%;text-align:right; color: black;" >
								    </TD>
								    								
						        	<% if (spThieuList.containsKey(sanpham.getMasanpham())){ %>

									    <TD align = "center" ><div class="addspeech" title="San pham nay con toi da <%= spThieuList.get(sanpham.getMasanpham()) %> san pham, vui long chon lai so luong">
								        <input name="soluong" id="soluong_<%= m %>" type="text" value="<%= formatter.format(Math.round(Double.parseDouble(sanpham.getSoluong()))) %>" onkeyup="Dinhdang(this)" style="width:100%; color:red ; cursor:pointer; background-color:#0FF; text-align:right">
								        </div></TD>
								    <%}else{ %>
							        	<TD align = "center" >
								        <input name="soluong" id="soluong_<%= m %>" type="text" value="<%= formatter.format(Math.round(Double.parseDouble(sanpham.getSoluong()))) %>" style="width:100%;text-align:right" onkeyup="Dinhdang(this)">
								        </TD>
								    <%} %>
								    
								    
								  
								    <!-- Nếu tick chọn đơn hàng khác -->
								    <%if(dhBean.getDonhangKhac().equals("1")) {
								     %>
								    <%-- <TD align = "center" >
								    	<input name="donvitinh1" disabled="disabled" type="text" value="<%= sanpham.getDonvitinh() %>"  style="width:100%;text-align:center; color: black;">
								    	<input name="donvitinh" type="hidden" value="<%= sanpham.getDonvitinh() %>"  style="width:100%;text-align:center">
								    </TD> --%>
								    <TD align = "center" >
								    	<input type="text" name="dongiagoc" onkeyup="FormatTien(this);" value="<%=formatter2.format(Double.parseDouble( sanpham.getDongiagoc()))%>" style="width: 100%; text-align: right; color: black;">
										<input type="hidden" name="dongia1" value="<%= sanpham.getDongia() %>" style="width: 100%; text-align: right">
										<input type="hidden" name="dongia"  value="<%= sanpham.getDongia() %>" style="width: 100%; text-align: right">
								    </TD>
								    
								     <TD align = "center" style="display: none;"  >
								    	<input type="text" name="ptVAT1" disabled="disabled" value="<%= sanpham.getKhoNVBH() %>" style="width:100% ;text-align:right; color: black;">
								    	<input type="hidden" name="ptVAT" value="<%= sanpham.getKhoNVBH() %>" style="width:100% ;text-align:right; color: black;">
								    </TD>
								    
								    <TD align = "center" >
								    	<input name="spchietkhau1" type="text" value="<%= sanpham.getChietkhau()%>" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    	<input name="spchietkhau" type="hidden" value="<%= sanpham.getChietkhau() %>" readonly style="width:100%;text-align:right">
								    </TD>
								    <TD align = "center" style="display: none;" >
								    	<input name="sptienthue" type="text" value="<%= sanpham.getChietkhau()%>" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    </TD>
								    <TD align = "center" >
								    	<input name="thanhtien1" type="text" value="<%= formatter.format(Math.round(Double.parseDouble(sanpham.getChietkhau()))) %>" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    	<input name="thanhtien" type="hidden" value="<%= sanpham.getChietkhau() %>"  style="width:100% ;text-align:right">
								    </TD>
								    <TD align = "center" >
								    	<input name="ctkm1" type="text"  value="<%= sanpham.getCTKM() %>" disabled="disabled" style="width:100%; color: black;">
								    	<input name="ctkm" type="hidden"  value="<%= sanpham.getCTKM() %>" readonly style="width:100%">
								    </TD>
								    <TD align = "center" style="display: none;" >
								    	<% if(sanpham.getKhoNVBH().equals("1")) { %>
								    		<input name="khoNVBH1" type="checkbox"  value="1" checked="checked" onclick="return false;" >
								    	<% } else { %>
								    		<input name="khoNVBH1" type="checkbox"  value="1" onclick="return false;"  >
								    	<% } %>
								    	<input name="khoNVBH" type="hidden"  value="<%= sanpham.getKhoNVBH() %>"  >
								    </TD>	
								   
								   
								     <%if(dhBean.getQuanlykho().equals("1")){ %>
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
				                        	
				                        	if( spDetailList!=null &&  spDetailList.size() > 0)
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
						                            	<input  type="text" style="width:100%"  name="<%= m + ".soluong" %>" value="<%= spDetail.getSoluong() %>"  />  
						                            	   
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
								    
								   	<%} %>					
								
								    <%}else {   %>
								    <!-- Hết  -->
								   
								   <TD align = "center" >
								    	<input name="tonkho1" disabled="disabled" type="text" value="<%= formatter.format(Math.round(Double.parseDouble(sanpham.getTonhientai()))) %>"  style="width:100%;text-align:center; color: black;">
								    	<input name="tonkho" type="hidden" value="<%= sanpham.getTonhientai() %>"  style="width:100%;text-align:center">
								    </TD>	
								    <TD align = "center" >
								    <input type="text" name="dongiagoc" onkeyup="FormatTien(this);" value="<%=formatter2.format(Double.parseDouble( sanpham.getDongiagoc()))%>" style="width: 100%; text-align: right; color: black;">
									<input type="hidden" name="dongia1" value="<%= sanpham.getDongia() %>" style="width: 100%; text-align: right">
									<input type="hidden" name="dongia"  value="<%= sanpham.getDongia() %>" style="width: 100%; text-align: right">
								    </TD>
								    
								     <TD align = "center" style="display: none;" >
								    	<input type="text" name="ptVAT1" disabled="disabled" value="<%= sanpham.getKhoNVBH() %>" style="width:100% ;text-align:right; color: black;">
								    	<input type="hidden" name="ptVAT" value="<%= sanpham.getKhoNVBH() %>" style="width:100% ;text-align:right; color: black;">
								    </TD>
								
								    <TD align = "center" >
								    	<input name="spchietkhau1" type="text" value="<%= sanpham.getChietkhau()%>" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    	<input name="spchietkhau" type="hidden" value="<%= sanpham.getChietkhau() %>" readonly style="width:100%;text-align:right">
								    </TD>
								    <TD align = "center" style="display: none;" >
								    	<input name="sptienthue" type="text" value="<%= sanpham.getChietkhau()%>" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    </TD>
								    <TD align = "center" >
								    	<input name="thanhtien1" type="text" value="<%= formatter.format(Math.round(Double.parseDouble(sanpham.getChietkhau()))) %>" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    	<input name="thanhtien" type="hidden" value="<%= sanpham.getChietkhau() %>" readonly  style="width:100% ;text-align:right">
								    </TD>
								    <TD align = "center" >
								    	<input name="ctkm1" type="text"  value="<%= sanpham.getCTKM() %>" disabled="disabled" style="width:100%; color: black;">
								    	<input name="ctkm" type="hidden"  value="<%= sanpham.getCTKM() %>" readonly style="width:100%">
								    </TD>
								    <TD align = "center" style="display: none;" >
								    	<% if(sanpham.getKhoNVBH().equals("1")) { %>
								    		<input name="khoNVBH1" type="checkbox"  value="1" checked="checked" onclick="return false;" >
								    	<% } else { %>
								    		<input name="khoNVBH1" type="checkbox"  value="1" onclick="return false;"  >
								    	<% } %>
								    	<input name="khoNVBH" type="hidden"  value="<%= sanpham.getKhoNVBH() %>"  >
								    </TD>
								    
								    
								     <%if(dhBean.getQuanlykho().equals("1")){ %>
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
				                        	
				                        	if( spDetailList!=null &&  spDetailList.size() > 0)
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
						                            	<input  type="text" style="width:100%"  name="<%= m + ".soluong" %>" value="<%= spDetail.getSoluong() %>"  />  
						                            	   
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
								    
								    
								    <%} %>
								
								<%} %>
								</TR> 
								<% m++; }}%>
																
								<%
									int i = m;
									
									while(i <= (40 + m) ) { //Integer.parseInt(dhBean.getSize())){
								%>
								 <%if(dhBean.getDonhangKhac().equals("1")) { %>
								 
								<TR class= 'tblightrow'>
									<TD align="center" >
										<input name="masp" type="text" value="" onkeyup="ajax_showOptions(this,'abc',event)" style="width:100%" AUTOCOMPLETE="off">
									</TD>
									<TD align="left" >
										<input name="tensp1" type="text" disabled="disabled" value="" style="width:100%; color:black; ">
										<input name="tensp" type="hidden" value="" style="width:100%">
									</TD>
									<TD align = "center" >
								    	<input name="donvitinh1" disabled="disabled" type="text"  style="width:100%;text-align:center; color:black; ">
								    	<input name="donvitinh" type="hidden"  style="width:100%;text-align:center">
								    </TD>
									 <TD align = "center" style="display: none;" >
								    	<input name="soluong1" id="soluong1_<%= i %>" type="hidden" value=""  style="width:100%;text-align:right; color: black;">
								    	<input name="soluong2" id="soluong2_<%= i %>" type="hidden" value=""  style="width:100%;text-align:right; color: black;">
								    	<input name="soluongThung" id="soluongThung_<%= i %>" type="text" value="" onkeyup="QuyRaLe(<%= i %>)" onkeypress="return keypress(event);"   style="width:100%;text-align:right; color: black;">
								    </TD>
								    <TD align = "center" >
							        	<input name="soluong" onchange="Capnhatsolo('<%=i%>')"  id="soluong_<%= i %>" type="text" value="" style="width:100%; text-align:right" onkeyup="Dinhdang(this)">
							        </TD>		
									<TD align = "center" >
								    	<input name="tonkho1" disabled="disabled" type="text"  style="width:100%;text-align:center; color:black; ">
								    	<input name="tonkho" type="hidden"  style="width:100%;text-align:center">
								    </TD>
								    
								    					        
								     
								    <TD align = "center" >
								    	<input type="text" name="dongiagoc"  onkeyup="FormatTien(this);" style="width:100% ;text-align:right; color: black;">
								    	<input type="hidden" name="dongia1"  onkeyup="FormatTien(this);" style="width:100% ;text-align:right">
								    	<input type="hidden" name="dongia"  onkeyup="FormatTien(this);" style="width:100% ;text-align:right">
								    </TD>
								    
							        
							        
								     <TD align = "center" style="display: none;"  >
								    	<input type="text" name="ptVAT1" disabled="disabled" style="width:100% ;text-align:right; color: black;">
								    	<input type="hidden" name="ptVAT" readonly="readonly" style="width:100% ;text-align:right; color: black;">
								    </TD>
								    <TD align = "center" >
								    	<input name="spchietkhau1" type="text" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    	<input name="spchietkhau" type="hidden" readonly style="width:100%;text-align:right">
								    </TD>
								    <TD align = "center" style="display: none;" >
								    	<input name="sptienthue" type="text" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    </TD>
								    <TD align = "center" >
								    	<input name="thanhtien1" type="text" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    	<input name="thanhtien" type="hidden" readonly  style="width:100% ;text-align:right">
								    </TD>
								    <TD align = "center" >
								    	<input name="ctkm1" type="text" disabled="disabled" style="width:100%; color: black;">
								    	<input name="ctkm" type="hidden" readonly style="width:100%">
								    </TD>
								    <TD align = "center" style="display: none;"  >
								    	<input name="khoNVBH1" type="checkbox"  value = "1" onclick="return false;" >
								    	<input name="khoNVBH" type="hidden"  value="0" onclick="return false;"  >
								    </TD>
								    
								    
								     <%if(dhBean.getQuanlykho().equals("1")){ %>
								       <td>
									      <a href="" id="<%= i %>pubup" rel="subcontent<%= i %>">
		           	 							<img alt="Số lô - Vị trí hàng hóa xuất" src="../images/vitriluu.png"></a>
		           	 						<DIV id="subcontent<%= i %>" style=" position:absolute; visibility: hidden; border: 9px solid #80CB9B;
					                             background-color: white; max-height:500px;overflow:auto ; width: 500px; padding: 4px;">
					                    	 <div align="right"><a href="javascript:dropdowncontent.hidediv('subcontent<%= i %>');">Hoàn tất</a></div>
					                		</DIV>
									    </td>
								    <%} %>
								    
								</TR> 
								<%} else{  %>
								<TR class= 'tblightrow'>
									<TD align="center" >
										<input name="masp" type="text" value="" onkeyup="ajax_showOptions(this,'abc',event)" style="width:100%" AUTOCOMPLETE="off">
									</TD>
									<TD align="left" >
										<input name="tensp1" type="text" disabled="disabled" value="" style="width:100%; color:black; ">
										<input name="tensp" type="hidden" value="" style="width:100%">
									</TD>
									<TD align = "center" >
								    	<input name="donvitinh1" disabled="disabled" type="text"  style="width:100%;text-align:center; color:black; ">
								    	<input name="donvitinh" type="hidden"  style="width:100%;text-align:center">
								    </TD>
									
								     <TD align = "center" style="display: none;" >
								    	<input name="soluong1" id="soluong1_<%= i %>" type="hidden" value=""  style="width:100%;text-align:right; color: black;">
								    	<input name="soluong2" id="soluong2_<%= i %>" type="hidden" value=""  style="width:100%;text-align:right; color: black;">
								    	<input name="soluongThung" id="soluongThung_<%= i %>" type="text" onkeyup="QuyRaLe(<%= i %>)" onkeypress="return keypress(event);" value=""  style="width:100%;text-align:right; color: black;">
								    </TD>	
								    <TD align = "center" >
							        	<input name="soluong" onchange="Capnhatsolo('<%=i%>')" id="soluong_<%= i %>" type="text" value="" style="width:100%; text-align:right" onkeyup="Dinhdang(this)">
							        </TD>						        
								     <TD align = "center" >
								    	<input name="tonkho1" disabled="disabled" type="text"  style="width:100%;text-align:center; color:black; ">
								    	<input name="tonkho" type="hidden"  style="width:100%;text-align:center">
								    </TD>
								     
								    <TD align = "center" >
								    	<input type="text" name="dongiagoc" style="width:100% ;text-align:right; color: black;">
								    	<input type="hidden" name="dongia1" readonly  style="width:100% ;text-align:right">
								    	<input type="hidden" name="dongia" readonly  style="width:100% ;text-align:right">
								    </TD>
								    
								     <TD align = "center" style="display: none;"  >
								    	<input type="text" name="ptVAT1" disabled="disabled" style="width:100% ;text-align:right; color: black;">
								    	<input type="hidden" name="ptVAT" readonly="readonly" style="width:100% ;text-align:right; color: black;">
								    </TD>
								    <TD align = "center" >
								    	<input name="spchietkhau1" type="text" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    	<input name="spchietkhau" type="hidden" readonly style="width:100%;text-align:right">
								    </TD>
								    <TD align = "center"  style="display: none;">
								    	<input name="sptienthue" type="text" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    </TD>
								    <TD align = "center"  >
								    	<input name="thanhtien1" type="text" disabled="disabled"  style="width:100%;text-align:right; color: black;">
								    	<input name="thanhtien" type="hidden" readonly  style="width:100% ;text-align:right">
								    </TD>
								    <TD align = "center" >
								    	<input name="ctkm1" type="text" disabled="disabled" style="width:100%; color: black;">
								    	<input name="ctkm" type="hidden" readonly style="width:100%">
								    </TD>
								    <TD align = "center" style="display: none;"  >
								    	<input name="khoNVBH1" type="checkbox"  value = "1" onclick="return false;" >
								    	<input name="khoNVBH" type="hidden"  value="0" onclick="return false;"  >
								    </TD>
								       <%if(dhBean.getQuanlykho().equals("1")){ %>
								    <td>
									      <a href="" id="<%= i %>pubup" rel="subcontent<%= i %>">
		           	 							<img alt="Số lô - Vị trí hàng hóa xuất" src="../images/vitriluu.png"></a>
		           	 						<DIV id="subcontent<%= i %>" style=" position:absolute; visibility: hidden; border: 9px solid #80CB9B;
					                             background-color: white; max-height:500px;overflow:auto ; width: 500px; padding: 4px;">
					                    	 <div align="right"><a href="javascript:dropdowncontent.hidediv('subcontent<%= i %>');">Hoàn tất</a></div>
					                		</DIV>
									    </td>
									    <%} %>
								</TR> 
								<%} %>
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
	replaces();
	
	dropdowncontent.init("searchlink", "right-bottom", 500, "click");
	
	
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
	
	
	jQuery(function()
	{		
		$("#smartId").autocomplete("KhachHangList.jsp");		
	});	
</script>
</form>
</BODY>


	
	
	
	
	
</HTML>
<%
try{
	if(!(ddkd == null ))
		ddkd.close();
	if(!(tbh == null))
		tbh.close();
	if(kh!=null){
		kh.close();
	}
	if(kho!=null){
		kho.close();
	}
	spThieuList=null;
	scheme_tongtien=null;
	scheme_chietkhau=null;
	if(gsbhs!=null){
		gsbhs.close();
	}
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
