<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.thanhtoanhoadon.*" %>
<%@ page  import = "geso.dms.distributor.beans.thanhtoanhoadon.imp.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<% IErpThanhtoanhoadon tthdBean = (IErpThanhtoanhoadon)session.getAttribute("tthdBean"); %>
<% ResultSet nccList = tthdBean.getNccRs(); %>
<% ResultSet nvList = tthdBean.getNhanvienRs(); %>
<% ResultSet nhomnccList = tthdBean.getNhomNCCRs(); %>
<% ResultSet khList = tthdBean.getKhachhangRs(); %>
<% ResultSet tienteList = tthdBean.getTienteRs(); %>
<% ResultSet htttList = tthdBean.getHtttRs(); %>
<% ResultSet nganhangList = tthdBean.getNganhangRs(); %>
<% ResultSet chinhanhList = tthdBean.getChinhanhRs(); %>
<% List<IHoadon> hoadonList = tthdBean.getHoadonRs(); %>
<% ResultSet sotkRs = tthdBean.getSotkRs(); %>
<% ResultSet sotkRs_tp = tthdBean.getSotkRs_tp();

	int count=tthdBean.getCount();
	ResultSet rstkkt=tthdBean.getTaiKhoanKTRs();
	String[] TaiKhoanId=tthdBean.getTaiKhoanIds();
	
	if(TaiKhoanId == null){
		TaiKhoanId = new String[count];
		for (int i = 0; i < count; i++){
			TaiKhoanId[i] = "";
		}
	}
	
	String[] dcIds = tthdBean.getDcIds();
	
	if(dcIds == null){
		dcIds = new String[count];
		for (int i = 0; i < count; i++){
			dcIds[i] = "";
		}
	}
	
	String[] loais = tthdBean.getLoais();
	
	if(loais == null){
		loais = new String[count];
		for (int i = 0; i < count; i++){
			loais[i] = "";
		}
	}
	
	ResultSet dcRs ;
	
	ResultSet PhongBanList = tthdBean.getPhongBanRs();
	String[] PhongbanId=tthdBean.getPhongBanIds();
	
	if(PhongbanId == null){
		PhongbanId = new String[count];
		for (int i = 0; i < count; i++){
			PhongbanId[i] = "";
		}
	}
	
	
	ResultSet KenhBhList = tthdBean.getKenhBhRs();
	String[] kenhBhId=tthdBean.getKenhIds();
	
	if(kenhBhId == null){
		kenhBhId = new String[count];
		for (int i = 0; i < count; i++){
			kenhBhId[i] = "";
		}
	}
	
	ResultSet TinhThanhList = tthdBean.getTinhThanhRs();
	String[] TinhThanhId=tthdBean.getTinhThanhIds();
	
	if(TinhThanhId == null){
		TinhThanhId = new String[count];
		for (int i = 0; i < count; i++){
			TinhThanhId[i] = "";
		}
	}
	
	ResultSet SanPhamList = tthdBean.getSanPhamRs();
	String[] SanPhamId=tthdBean.getSanphamIds();
	
	if(SanPhamId == null){
		SanPhamId = new String[count];
		for (int i = 0; i < count; i++){
			SanPhamId[i] = "";
		}
	}
	
	ResultSet DiaBanList = tthdBean.getDiaBanRs();
	String[] DiaBanId= tthdBean.getDiaBanIds();
	
	if(DiaBanId == null){
		DiaBanId = new String[count];
		for (int i = 0; i < count; i++){
			DiaBanId[i] = "";
		}
	}
	
	ResultSet MavvList = tthdBean.getMavvRs();
	String[] MavvId= tthdBean.getMavvIds();
	
	if(MavvId == null){
		MavvId = new String[count];
		for (int i = 0; i < count; i++){
			MavvId[i] = "";
		}
	}

	ResultSet BenhvienList = tthdBean.getBenhVienRs();
	String[] BenhvienId= tthdBean.getBenhVienIds();
	
	if(BenhvienId == null){
		BenhvienId = new String[count];
		for (int i = 0; i < count; i++){
			BenhvienId[i] = "";
		}
	}
	
%>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<%	NumberFormat formatter1 = new DecimalFormat("#,###,###.##"); %>
<%	NumberFormat formatter2 = new DecimalFormat("#,###,###"); %>
<% 	String[] Mahd = tthdBean.getMahd();
	if(Mahd == null){
		Mahd = new String[count];
		for (int i = 0; i < count; i++){
			Mahd[i] = "";
		}
	}

	String[] Mauhd = tthdBean.getMauhd();
	if(Mauhd == null){
		Mauhd = new String[count];
		for (int i = 0; i < count; i++){
			Mauhd[i] = "";
		}
	}
	
	String[] Kyhieuhd = tthdBean.getKyhieuhd();
	if(Kyhieuhd == null){
		Kyhieuhd = new String[count];
		for (int i = 0; i < count; i++){
			Kyhieuhd[i] = "";
		}
	}
	
	String[] Sohd = tthdBean.getSohd();
	if(Sohd == null){
		Sohd = new String[count];
		for (int i = 0; i < count; i++){
			Sohd[i] = "";
		}
	}
	
	String[] Ngayhd = tthdBean.getNgayhd();
	if(Ngayhd == null){
		Ngayhd = new String[count];
		for (int i = 0; i < count; i++){
			Ngayhd[i] = "";
		}
	}
	
	String[] TenNCChd = tthdBean.getTenNCChd();
	if(TenNCChd == null){
		TenNCChd = new String[count];
		for (int i = 0; i < count; i++){
			TenNCChd[i] = "";
		}
	}
	
	String[] MSThd = tthdBean.getMSThd();
	if(MSThd == null){
		MSThd = new String[count];
		for (int i = 0; i < count; i++){
			MSThd[i] = "";
		}
	}
	
	String[] Tienhanghd = tthdBean.getTienhanghd();
	if(Tienhanghd == null){
		Tienhanghd = new String[count];
		for (int i = 0; i < count; i++){
			Tienhanghd[i] = "";
		}
	}
	
	String[] Thuesuathd = tthdBean.getThuesuathd();	
	if(Thuesuathd == null){
		Thuesuathd = new String[count];
		for (int i = 0; i < count; i++){
			Thuesuathd[i] = "";
		}
	}
	
	String[] Tienthuehd = tthdBean.getTienthuehd();	
	if(Tienthuehd == null){
		Tienthuehd = new String[count];
		for (int i = 0; i < count; i++){
			Tienthuehd[i] = "";
		}
	}
	
	String[] Diengiaihd = tthdBean.getDiengiaihd();
	if(Diengiaihd == null){
		Diengiaihd = new String[count];
		for (int i = 0; i < count; i++){
			Diengiaihd[i] = "";
		}
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE>Phanam - Project</TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>

<script type="text/javascript">
	$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});            
	});	
</script>


<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
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

#ajax_listOfOptions div {re
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


<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/erp-spList.js"></script>

<script language="javascript" type="text/javascript">

	function replaces()
	{	
			var bpTen = document.getElementById("bpTen");
			var bpId= document.getElementById("bpId");
			
			var  tem = bpTen.value;
			
			if(tem == "")
			{
				bpTen.value = "";
				
			}
			else
			{
				if(parseInt(tem.indexOf(" -- ")) > 0)
				{
					bpId.value = tem.substring(0, parseInt(tem.indexOf(" -- ")));
					
					tem = tem.substr(parseInt(tem.indexOf(" -- ")) + 3);
					bpTen.value = tem.substring(0, tem.length );			
					
					if(bpId.value != '')
					{
						document.forms['tthdForm'].action.value='ChangeTienTe()';
					    document.forms["tthdForm"].submit();
					}
				}
			}   
			
			/* var phongBanId_VAT = document.getElementsByName("phongBanId_VAT");
			var phongBan_VAT = document.getElementsByName("phongBan_VAT");
			
			for(var i = 0; i < 10; i++)
			{
				if(phongBan_VAT.item(i).value != ""  )
				{
					var sp = phongBan_VAT.item(i).value;
					var pos = parseInt(sp.indexOf(" -- "));
					
					if(pos > 0)
					{
						phongBan_VAT.item(i).value = sp.substring(0, pos);
						
						sp = sp.substr(parseInt(sp.indexOf(" -- "))+3);
						
						phongBanId_VAT.item(i).value = sp.substring(0, sp.length);
					}
				}
			}
			
			
			var KenhBhId_VAT = document.getElementsByName("KenhBhId_VAT");
			var KenhBh_VAT = document.getElementsByName("KenhBh_VAT");
			
			for(var i = 0; i < 10; i++)
			{
				if(KenhBh_VAT.item(i).value != ""  )
				{
					var sp = KenhBh_VAT.item(i).value;
					var pos = parseInt(sp.indexOf(" -- "));
					
					if(pos > 0)
					{
						KenhBh_VAT.item(i).value = sp.substring(0, pos);
						
						sp = sp.substr(parseInt(sp.indexOf(" -- "))+3);
						
						KenhBhId_VAT.item(i).value = sp.substring(0, sp.length);
					}
				}
			}
		
			var TaikhoanId_VAT = document.getElementsByName("TaikhoanId_VAT");
			var Taikhoan_VAT = document.getElementsByName("Taikhoan_VAT");
			
			for(var i = 0; i < 10; i++)
			{
				if(Taikhoan_VAT.item(i).value != ""  )
				{
					var sp = Taikhoan_VAT.item(i).value;
					var pos = parseInt(sp.indexOf(" -- "));
					
					if(pos > 0)
					{
						Taikhoan_VAT.item(i).value = sp.substring(0, pos);
						
						sp = sp.substr(parseInt(sp.indexOf(" -- "))+3);
						
						TaikhoanId_VAT.item(i).value = sp.substring(0, sp.length);
					}
				}
			} */
			  		 	
		setTimeout(replaces, 500);
	}

	function ThanhToan(n)
	{
		var trichphi = document.getElementsByName("trichphi");
		var trahet = document.getElementsByName("trahet");
		var avat = document.getElementsByName("avat");
		var thanhtoan = document.getElementsByName("thanhtoan");
		var conlai = document.getElementsByName("conlai");
		 
		var tigia = document.getElementsByName("tigia");
		var tg = tigia.item(0).value.replace(/,/g,"");

		var tienteId = document.getElementsByName("tienteId");
		 	 
		var tongtienNT = 0;
		var tongtienVND = 0;
		var sotienNT;
		var tigiaHD = document.getElementsByName("tigiaHD");
		var doituongthanhtoan = document.getElementsByName("doituongthanhtoan");

  if(doituongthanhtoan.item(0).value != "3"){ // LOAI THANH TOAN HOA DON / TAM UNG
// V??NG L???P CHO T???T C??? H??A ????N
	 	for(i = 0; i < trahet.length; i++)
	 	{

			if(trahet.item(i).checked ) // CH???N TR??? H???T H??A ????N
			{
// CH???N TR??? H???T H??A ????N + THAY ?????I S??? TI???N THANH TO??N				
				if(n == 100){ 
					var tt;
					if(thanhtoan.item(i).value != ''){
						tt = thanhtoan.item(i).value.replace(/,/g,"");
					}else{
						tt = 0;
					}												

// CH???N TR??? H???T H??A ????N + THAY ?????I S??? TI???N THANH TO??N + THANH TOAN HOA DON TIEN VN??					
// CH??? ???????C THANH TO??N T???I ??A B???NG S??? TI???N H??A ????N
					if(tienteId.item(0).value == "100000"){ 
						if(Math.abs(parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt)) > 0){
							conlai.item(i).value = DinhDangTien(roundNumber(parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt), 0));

							tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);
				
							thanhtoan.item(i).value = DinhDangTien(roundNumber(tt, 0));
							trahet.item(i).checked = false;
						
						}else{ 
							thanhtoan.item(i).value = avat.item(i).value;
							conlai.item(i).value = parseFloat(avat.item(i).value) - parseFloat(thanhtoan.item(i).value);
							
							var tt = thanhtoan.item(i).value.replace(/,/g,"");

							tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);
						
							trahet.item(i).checked = true;
						}

// CH???N TR??? H???T H??A ????N + THAY ?????I S??? TI???N THANH TO??N + THANH TOAN HOA DON NGO???I T???						
// CH??? ???????C THANH TO??N T???I ??A B???NG S??? TI???N H??A ????N
					}else{ 
						var sotienNT = document.getElementsByName("sotienNT");
						if(Math.abs(parseFloat(sotienNT.item(i).value.replace(/,/g,"")) - parseFloat(tt)) > 0){
							conlai.item(i).value = DinhDangDonGia((parseFloat(sotienNT.item(i).value.replace(/,/g,"")) - parseFloat(tt)).toFixed(2));

							tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);
							tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tigiaHD.item(i).value.replace(/,/g,""));
									
							thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
							trahet.item(i).checked = false;
						
						}else{
							thanhtoan.item(i).value = sotienNT.item(i).value;
							conlai.item(i).value = DinhDangDonGia((parseFloat(sotienNT.item(i).value) - parseFloat(thanhtoan.item(i).value)).toFixed(2));
							
							var tt = thanhtoan.item(i).value.replace(/,/g,"");
							
							thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
							
							tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);
							tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tigiaHD.item(i).value.replace(/,/g,""));
							
							trahet.item(i).checked = true;
						}						
					}
// CH???N TR??? H???T H??A ????N + KH??NG THAY ?????I S??? TI???N 
				}else{ 
// CH???N TR??? H???T H??A ????N + KH??NG THAY ?????I S??? TI???N
// THANH TO??N CHO H??A ????N VN??
					if(tienteId.item(0).value == "100000"){ 
						thanhtoan.item(i).value = avat.item(i).value;
						conlai.item(i).value = DinhDangTien(roundNumber(parseFloat(avat.item(i).value) - parseFloat(thanhtoan.item(i).value)),0);
					
						var tt = thanhtoan.item(i).value.replace(/,/g,"");

						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);
						
					}else{
						var sotienNT = document.getElementsByName("sotienNT");
						thanhtoan.item(i).value = sotienNT.item(i).value;
						conlai.item(i).value = DinhDangDonGia((parseFloat(sotienNT.item(i).value) - parseFloat(thanhtoan.item(i).value)).toFixed(2));
					
						var tt = thanhtoan.item(i).value.replace(/,/g,"");

						thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
						
						tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);							
						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tigiaHD.item(i).value.replace(/,/g,""));
					}					
				}
				
// CLICK B??? CH???N TR??? H???T H??A ????N
			}else if(i == n){ 
				thanhtoan.item(i).value = "0";				
				
				if(tienteId.item(0).value == "100000"){ // Neu tien te la VND
					conlai.item(i).value = avat.item(i).value;
				}else{
					sotienNT = document.getElementsByName("sotienNT");
					conlai.item(i).value = sotienNT.item(i).value;
				}

// KH??NG CH???N TR??? H???T H??A ????N  				
			}else{ 
				
				var tt;
				if(thanhtoan.item(i).value != ''){
					tt = thanhtoan.item(i).value.replace(/,/g,"");
					 
				}else{
					tt = 0;
				}					
				
// KH??NG CH???N TR??? H???T H??A ????N + THANH TO??N H??A ????N VN??				
				if(tienteId.item(0).value == "100000"){ 
					//alert("avat:"+parseFloat(avat.item(i).value.replace(/,/g,"")) + " ----- tt: "+tt );
					if((parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt)) >= 0){
						
						conlai.item(i).value = DinhDangTien(roundNumber(parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt), 0));
				
						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);

						thanhtoan.item(i).value = DinhDangTien(roundNumber(tt, 0));
						
						
					}else{
						thanhtoan.item(i).value = avat.item(i).value;
						conlai.item(i).value = parseFloat(avat.item(i).value) - parseFloat(thanhtoan.item(i).value);
						
						var tt = thanhtoan.item(i).value.replace(/,/g,"");

						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);
					
						trahet.item(i).checked = true;
						
					}

// KH??NG CH???N TR??? H???T H??A ????N + THANH TO??N H??A ????N NGO???I T???						
				}else{ 
					var sotienNT = document.getElementsByName("sotienNT");

					if(Math.abs(parseFloat(sotienNT.item(i).value.replace(/,/g,"")) - parseFloat(tt)) >= 0){
						conlai.item(i).value = DinhDangDonGia((parseFloat(sotienNT.item(i).value.replace(/,/g,"")) - parseFloat(tt)).toFixed(2));
				
						tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);
						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tigiaHD.item(i).value.replace(/,/g,""));
						
						thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
					}else{
						thanhtoan.item(i).value = sotienNT.item(i).value;
						conlai.item(i).value = DinhDangDonGia((parseFloat(sotienNT.item(i).value) - parseFloat(thanhtoan.item(i).value)).toFixed(2));
						
						var tt = thanhtoan.item(i).value.replace(/,/g,"");
						
						thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
						
						tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);
						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tigiaHD.item(i).value.replace(/,/g,""));
						
						trahet.item(i).checked = true;
					}
					
				}				
			}
	 	}
	 	
// THANH TO??N CHO H??A ????N VN??	 	
	 	if(tienteId.item(0).value == "100000"){ 

	 		document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(tongtienVND, 0));

 			var pnganhangVND = document.getElementsByName("pnganhangVND");

 			var pnhVND;
			if(pnganhangVND.item(0).value != ''){
				pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");
				 
			}else{
				pnhVND = 0;
			}					

 			var thuevatVND = document.getElementsByName("vatVND");
 			
 			var vatVND;
			if(thuevatVND.item(0).value != ''){
				vatVND = thuevatVND.item(0).value.replace(/,/g,"");
				 
			}else{
				vatVND = 0;
			}

 			var tongthanhtoan = parseFloat(tongtienVND) + parseFloat(pnhVND) + parseFloat(vatVND);

 			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongthanhtoan, 0));
 			
 			document.getElementById("vatVND").value = DinhDangTien(document.getElementById("vatVND").value);
 			document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(pnhVND, 0));

 			document.getElementById("tienhang_VAT").value = document.getElementById("pnganhangVND").value;
 			document.getElementById("tienthue_VAT").value = document.getElementById("vatVND").value;
 			
 			document.getElementById("thuesuat_VAT").value = DinhDangDonGia((parseFloat(vatVND)*100/parseFloat(pnhVND)).toFixed(2));

// THANH TO??N CHO H??A ????N NGO???I T???	 	
	 	}else{ 
	 		
			var tongttVND = 0;
	 		var tongttNT = 0;
	 		
	 		document.getElementById("sotienHDNT").value = DinhDangDonGia((tongtienNT).toFixed(2));
	 		document.getElementById("sotienHDVND").value = DinhDangTien(tongtienVND, 0);

// THANH TO??N CHO H??A ????N NGO???I T??? + TRICH PH?? NGO???I T???			
			if(trichphi.item(0).checked == false){ 
				var pnganhangNT = document.getElementsByName("pnganhangNT");
	 			var pnhNT;
				if(pnganhangNT.item(0).value != ''){
					pnhNT = pnganhangNT.item(0).value.replace(/,/g,"");
					 
				}else{
					pnhNT = 0;
				}					

				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(pnhNT)*parseFloat(tg), 0));

				var thuevatNT = document.getElementsByName("vatNT");
		 		var vatNT;
				if(thuevatNT.item(0).value != ''){
					vatNT = thuevatNT.item(0).value.replace(/,/g,"");
					 
				}else{
					vatNT = 0;
				}					
				
				document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(vatNT)*parseFloat(tg), 0));
				
	 			document.getElementById("pnganhangNT").value = DinhDangDonGia((parseFloat(pnhNT)).toFixed(2));

	 			document.getElementById("vatNT").value = DinhDangDonGia((parseFloat(document.getElementById("vatNT").value)).toFixed(2));
	 			
	 			tongttVND = parseFloat(tongtienVND) + parseFloat(pnhNT)*parseFloat(tg) + parseFloat(vatNT)*parseFloat(tg);
	 			
	 			tongttNT = parseFloat(tongtienNT) + parseFloat(pnhNT) + parseFloat(vatNT);

// THANH TO??N CHO H??A ????N NGO???I T??? + TRICH PH?? VN??			
	 			
			}else{ 
				var pnganhangVND = document.getElementsByName("pnganhangVND");
	 			var pnhVND;
				if(pnganhangVND.item(0).value != ''){
					pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");
					 
				}else{
					pnhVND = 0;
				}					

				var thuevatVND = document.getElementsByName("vatVND");
		 		var vatVND;
				if(thuevatVND.item(0).value != ''){
					vatVND = thuevatVND.item(0).value.replace(/,/g,"");
					 
				}else{
					vatVND = 0;
				}					
				
				document.getElementById("pnganhangNT").value = "0";
				document.getElementById("vatNT").value = "0";

				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(document.getElementById("pnganhangVND").value.replace(/,/g,"")), 0));
	 			document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(document.getElementById("vatVND").value.replace(/,/g,"")), 0));
				
				tongttNT = parseFloat(tongtienNT) ;
				tongttVND = parseFloat(tongtienVND) + parseFloat(pnhVND) + parseFloat(vatVND);
			}
			
			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongttVND, 0));
			document.getElementById("tongtienNT").value = DinhDangDonGia((tongttNT).toFixed(2));
			
			var chenhlechVND = parseFloat(tongttNT)*parseFloat(tg) - parseFloat(tongttVND);
			document.getElementById("chenhlechVND").value = DinhDangTien(roundNumber(chenhlechVND, 0));
			
 			document.getElementById("tienhang_VAT").value = document.getElementById("pnganhangVND").value; 			
 			
 			document.getElementById("tienthue_VAT").value = document.getElementById("vatVND").value;
 			
 			document.getElementById("thuesuat_VAT").value = DinhDangDonGia((parseFloat(document.getElementById("vatVND").value.replace(/,/g,""))*100/document.getElementById("pnganhangVND").value.replace(/,/g,"")).toFixed(2));
 			
 			document.getElementById("tigia").value = DinhDangTien(document.getElementById("tigia").value);
	 	}
  } else { // LOAI THANH TOAN : KH??C
	// THANH TOAN VND
	  if(tienteId.item(0).value == "100000"){
		  var sotienthanhtoan = document.getElementsByName("sotienHDVND");
          var sotienthanhtoanVND;
			if(sotienthanhtoan.item(0).value != ''){
				sotienthanhtoanVND = sotienthanhtoan.item(0).value.replace(/,/g,"");
				 
			}else{
				sotienthanhtoanVND = 0;
			}
		  
		  document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(sotienthanhtoanVND, 0));

		  var pnganhangVND = document.getElementsByName("pnganhangVND");

		  var pnhVND;
			if(pnganhangVND.item(0).value != ''){
				pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");
				 
			}else{
				pnhVND = 0;
			}					

			var thuevatVND = document.getElementsByName("vatVND");
			
			var vatVND;
			if(thuevatVND.item(0).value != ''){
				vatVND = thuevatVND.item(0).value.replace(/,/g,"");
				 
			}else{
				vatVND = 0;
			}
			
			var tongthanhtoan = parseFloat(sotienthanhtoanVND) + parseFloat(pnhVND) + parseFloat(vatVND);

			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongthanhtoan, 0));
			
			//document.getElementById("vatVND").value = DinhDangTien(document.getElementById("vatVND").value);
			//document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(pnhVND, 0));

			//document.getElementById("tienhang_VAT").value = document.getElementById("pnganhangVND").value;
			//document.getElementById("tienthue_VAT").value = document.getElementById("vatVND").value;
			
			//document.getElementById("thuesuat_VAT").value = DinhDangDonGia((parseFloat(vatVND)*100/parseFloat(pnhVND)).toFixed(2)); 
	      
	  }else { // THANH TOAN BANG NGOAI TE
		  
		  var sotienthanhtoanNT = document.getElementsByName("sotienHDNT");
		  var sotienttNT= 0;
	      if(sotienthanhtoanNT.item(0).value != ''){
	    	  sotienttNT = sotienthanhtoanNT.item(0).value.replace(/,/g,"");
	      }else {
	    	  sotienttNT = 0;
	      }
	      var sotienttVND = parseFloat(sotienttNT)*parseFloat(tg);
	      document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(parseFloat(sotienttNT)*parseFloat(tg), 0)); 
	      document.getElementById("sotienHDNT").value = DinhDangTien(sotienttNT); 
	      
	   // THANH TO??N CHO LOAI "KHAC" BANG NGO???I T??? + TRICH PH?? NGO???I T???			
			if(trichphi.item(0).checked == false){
				var pnganhangNT = document.getElementsByName("pnganhangNT");
	 			var pnhNT;
				if(pnganhangNT.item(0).value != ''){
					pnhNT = pnganhangNT.item(0).value.replace(/,/g,"");
					 
				}else{
					pnhNT = 0;
				}					

				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(pnhNT)*parseFloat(tg), 0));

				var thuevatNT = document.getElementsByName("vatNT");
		 		var vatNT;
				if(thuevatNT.item(0).value != ''){
					vatNT = thuevatNT.item(0).value.replace(/,/g,"");
					 
				}else{
					vatNT = 0;
				}					
				
				document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(vatNT)*parseFloat(tg), 0));
				
	 			document.getElementById("pnganhangNT").value = DinhDangDonGia((parseFloat(pnhNT)).toFixed(2));

	 			document.getElementById("vatNT").value = DinhDangDonGia((parseFloat(document.getElementById("vatNT").value)).toFixed(2));
	 			
	 			tongttVND = parseFloat(sotienttVND) + parseFloat(pnhNT)*parseFloat(tg) + parseFloat(vatNT)*parseFloat(tg);
	 			
	 			tongttNT = parseFloat(sotienttNT) + parseFloat(pnhNT) + parseFloat(vatNT);
	 			
			}else{  // THANH TO??N CHO LOAI "KHAC" BANG NGO???I T??? + TRICH PH?? VND	
				var pnganhangVND = document.getElementsByName("pnganhangVND");
	 			var pnhVND;
				if(pnganhangVND.item(0).value != ''){
					pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");
					 
				}else{
					pnhVND = 0;
				}					

				var thuevatVND = document.getElementsByName("vatVND");
		 		var vatVND;
				if(thuevatVND.item(0).value != ''){
					vatVND = thuevatVND.item(0).value.replace(/,/g,"");
					 
				}else{
					vatVND = 0;
				}					
				
				document.getElementById("pnganhangNT").value = "0";
				document.getElementById("vatNT").value = "0";

				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(document.getElementById("pnganhangVND").value.replace(/,/g,"")), 0));
	 			document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(document.getElementById("vatVND").value.replace(/,/g,"")), 0));
				
				tongttNT = parseFloat(sotienttNT) ;
				tongttVND = parseFloat(sotienttVND) + parseFloat(pnhVND) + parseFloat(vatVND);
				
			}
	   			  
			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongttVND, 0));
			document.getElementById("tongtienNT").value = DinhDangDonGia((tongttNT).toFixed(2));		
			
			document.getElementById("tienhang_VAT").value = document.getElementById("pnganhangVND").value; 			
				
			document.getElementById("tienthue_VAT").value = document.getElementById("vatVND").value;
				
			document.getElementById("thuesuat_VAT").value = DinhDangDonGia((parseFloat(document.getElementById("vatVND").value.replace(/,/g,""))*100/document.getElementById("pnganhangVND").value.replace(/,/g,"")).toFixed(2));
				
			document.getElementById("tigia").value = DinhDangTien(document.getElementById("tigia").value); 		  
	  }
    
  }
  ThaydoiSotienNT();
	}
	
	function ThanhToan_2(n)
	{
		var trichphi = document.getElementsByName("trichphi");
		var trahet = document.getElementsByName("trahet");
		var avat = document.getElementsByName("avat");
		var thanhtoan = document.getElementsByName("thanhtoan");
		var conlai = document.getElementsByName("conlai");
		 
		var tigia = document.getElementsByName("tigia");
		var tg = tigia.item(0).value.replace(/,/g,"");

		var tienteId = document.getElementsByName("tienteId");
		
		var tongtienNT = 0;
		var tongtienVND = 0;
		var sotienNT;
		
//		var tigiaHD = document.getElementsByName("tigiaHD");
		var doituongthanhtoan = document.getElementsByName("doituongthanhtoan");

  if(doituongthanhtoan.item(0).value != "3"){ // LOAI THANH TOAN HOA DON / TAM UNG
// V??NG L???P CHO T???T C??? H??A ????N
	 	for(i = 0; i < trahet.length; i++)
	 	{

// CH???N TR??? H???T H??A ????N
			if(trahet.item(i).checked ) 
			{
// CH???N TR??? H???T H??A ????N + THAY ?????I S??? TI???N THANH TO??N				
				if(n == 100){ 
					var tt;
					if(thanhtoan.item(i).value != ''){
						tt = thanhtoan.item(i).value.replace(/,/g,"");
					}else{
						tt = 0;
					}												

// CH???N TR??? H???T H??A ????N + THAY ?????I S??? TI???N THANH TO??N + THANH TOAN HOA DON TIEN VN??					
// CH??? ???????C THANH TO??N T???I ??A B???NG S??? TI???N H??A ????N
					if(tienteId.item(0).value == "100000"){ 
						if((parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt)) > 0){
							conlai.item(i).value = DinhDangTien(roundNumber(parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt), 0));

							tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);
				
							thanhtoan.item(i).value = DinhDangTien(roundNumber(tt, 0));
							trahet.item(i).checked = false;
						
						}else{ 
							thanhtoan.item(i).value = avat.item(i).value;
							conlai.item(i).value = parseFloat(avat.item(i).value) - parseFloat(thanhtoan.item(i).value);
							
							var tt = thanhtoan.item(i).value.replace(/,/g,"");

							tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);
						
							trahet.item(i).checked = true;
						}

// CH???N TR??? H???T H??A ????N + THAY ?????I S??? TI???N THANH TO??N + THANH TOAN HOA DON NGO???I T???						
// CH??? ???????C THANH TO??N T???I ??A B???NG S??? TI???N H??A ????N
					}else{ 
						var sotienNT = document.getElementsByName("sotienNT");
						if((parseFloat(sotienNT.item(i).value.replace(/,/g,"")) - parseFloat(tt)) > 0){
							conlai.item(i).value =DinhDangDonGia((parseFloat(sotienNT.item(i).value.replace(/,/g,"")) - parseFloat(tt)).toFixed(2));

							tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);
							tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tg);
									
							thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
							trahet.item(i).checked = false;
						
						}else{
							thanhtoan.item(i).value = sotienNT.item(i).value;
							conlai.item(i).value = parseFloat(sotienNT.item(i).value) - parseFloat(thanhtoan.item(i).value);
							
							var tt = thanhtoan.item(i).value.replace(/,/g,"");
							
							thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
							
							tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);
							tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tg);
							
							trahet.item(i).checked = true;
						}						
					}
// CH???N TR??? H???T H??A ????N + KH??NG THAY ?????I S??? TI???N 
				}else{ 
// CH???N TR??? H???T H??A ????N + KH??NG THAY ?????I S??? TI???N
// THANH TO??N CHO H??A ????N VN??
					if(tienteId.item(0).value == "100000"){ 
						thanhtoan.item(i).value = avat.item(i).value;
						conlai.item(i).value = DinhDangDonGia((parseFloat(avat.item(i).value) - parseFloat(thanhtoan.item(i).value)).toFixed(2));
					
						var tt = thanhtoan.item(i).value.replace(/,/g,"");

						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);
						
						thanhtoan.item(i).value = DinhDangTien(roundNumber(tt, 0));
						avat.item(i).value = DinhDangTien(roundNumber(tt, 0));
						
					}else{
						var sotienNT = document.getElementsByName("sotienNT");
						thanhtoan.item(i).value = sotienNT.item(i).value;
						conlai.item(i).value = DinhDangDonGia((parseFloat(sotienNT.item(i).value) - parseFloat(thanhtoan.item(i).value)).toFixed(2));
					
						var tt = thanhtoan.item(i).value.replace(/,/g,"");

						tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);
						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tg);

						thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
						sotienNT.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
					}					
				}
				
// CLICK B??? CH???N TR??? H???T H??A ????N
			}else if(i == n){ 
				thanhtoan.item(i).value = "0";				
				
				if(tienteId.item(0).value == "100000"){ // Neu tien te la VND
					conlai.item(i).value = avat.item(i).value;
				}else{
					sotienNT = document.getElementsByName("sotienNT");
					conlai.item(i).value = sotienNT.item(i).value;
				}

// KH??NG CH???N TR??? H???T H??A ????N  				
			}else{ 
				var tt;
				if(thanhtoan.item(i).value != ''){
					tt = thanhtoan.item(i).value.replace(/,/g,"");
					 
				}else{
					tt = 0;
				}					
				
// KH??NG CH???N TR??? H???T H??A ????N + THANH TO??N H??A ????N VN??				
				if(tienteId.item(0).value == "100000"){ 
					if((parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt)) >= 0){
						conlai.item(i).value = DinhDangTien(roundNumber(parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt), 0));
				
						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);

						thanhtoan.item(i).value = DinhDangTien(roundNumber(tt, 0));
						
					}else{
						thanhtoan.item(i).value = avat.item(i).value;
						conlai.item(i).value = parseFloat(avat.item(i).value) - parseFloat(thanhtoan.item(i).value);
						
						var tt = thanhtoan.item(i).value.replace(/,/g,"");

						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt);
											
						trahet.item(i).checked = true;
					}
					
					avat.item(i).value = DinhDangTien(avat.item(i).value.replace(/,/g,""));
					
// KH??NG CH???N TR??? H???T H??A ????N + THANH TO??N H??A ????N NGO???I T???						
				}else{ 
					var sotienNT = document.getElementsByName("sotienNT");

					if((parseFloat(sotienNT.item(i).value.replace(/,/g,"")) - parseFloat(tt)) >= 0){
						conlai.item(i).value = DinhDangDonGia((parseFloat(sotienNT.item(i).value.replace(/,/g,"")) - parseFloat(tt)).toFixed(2));
				
						tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);
						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tg);
						
						thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
						
					}else{
						thanhtoan.item(i).value = sotienNT.item(i).value;
						conlai.item(i).value = parseFloat(sotienNT.item(i).value) - parseFloat(thanhtoan.item(i).value);
						
						var tt = thanhtoan.item(i).value.replace(/,/g,"");
						
						thanhtoan.item(i).value = DinhDangDonGia(parseFloat(tt).toFixed(2));
						
						tongtienNT = parseFloat(tongtienNT) + parseFloat(tt);
						tongtienVND = parseFloat(tongtienVND) + parseFloat(tt)*parseFloat(tg);
						
						trahet.item(i).checked = true;
					}
					
					sotienNT.item(i).value = DinhDangDonGia(parseFloat(sotienNT.item(i).value.replace(/,/g,"")).toFixed(2));
				}				
			}
	 	}
	 	
// THANH TO??N CHO H??A ????N VN??	 	
	 	if(tienteId.item(0).value == "100000"){ 

	 		document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(tongtienVND, 0));

 			var pnganhangVND = document.getElementsByName("pnganhangVND");

 			var pnhVND;
			if(pnganhangVND.item(0).value != ''){
				pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");
				 
			}else{
				pnhVND = 0;
			}					

 			var thuevatVND = document.getElementsByName("vatVND");
 			
 			var vatVND;
			if(thuevatVND.item(0).value != ''){
				vatVND = thuevatVND.item(0).value.replace(/,/g,"");
				 
			}else{
				vatVND = 0;
			}

 			var tongthanhtoan = parseFloat(tongtienVND) + parseFloat(pnhVND) + parseFloat(vatVND);

 			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongthanhtoan, 0));
 			
 			document.getElementById("vatVND").value = DinhDangTien(document.getElementById("vatVND").value);
 			document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(pnhVND, 0));

 			document.getElementById("tienhang_VAT").value = document.getElementById("pnganhangVND").value;
 			document.getElementById("tienthue_VAT").value = document.getElementById("vatVND").value;
 			
 			document.getElementById("thuesuat_VAT").value = DinhDangDonGia((parseFloat(vatVND)*100/parseFloat(pnhVND)).toFixed(2));

// THANH TO??N CHO H??A ????N NGO???I T???	 	
	 	}else{ 
	 		
			var tongttVND = 0;
	 		var tongttNT = 0;
	 		
	 		document.getElementById("sotienHDNT").value = DinhDangDonGia((tongtienNT).toFixed(2));
	 		document.getElementById("sotienHDVND").value = DinhDangTien(tongtienVND, 0);

	 		
// THANH TO??N CHO H??A ????N NGO???I T??? + TRICH PH?? NGO???I T???			
			if(trichphi.item(0).checked == false){ 
				var pnganhangNT = document.getElementsByName("pnganhangNT");
	 			var pnhNT;
				if(pnganhangNT.item(0).value != ''){
					pnhNT = pnganhangNT.item(0).value.replace(/,/g,"");
					 
				}else{
					pnhNT = 0;
				}					

				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(pnhNT)*parseFloat(tg), 0));

				var thuevatNT = document.getElementsByName("vatNT");
		 		var vatNT;
				if(thuevatNT.item(0).value != ''){
					vatNT = thuevatNT.item(0).value.replace(/,/g,"");
					 
				}else{
					vatNT = 0;
				}					
				
				document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(vatNT)*parseFloat(tg), 0));
				
	 			document.getElementById("pnganhangNT").value = DinhDangDonGia((parseFloat(pnhNT)).toFixed(2));

	 			document.getElementById("vatNT").value = DinhDangDonGia((parseFloat(document.getElementById("vatNT").value)).toFixed(2));
	 			
	 			tongttVND = parseFloat(tongtienVND) + parseFloat(pnhNT)*parseFloat(tg) + parseFloat(vatNT)*parseFloat(tg);
	 			
	 			tongttNT = parseFloat(tongtienNT) + parseFloat(pnhNT) + parseFloat(vatNT);

// THANH TO??N CHO H??A ????N NGO???I T??? + TRICH PH?? VN??			
	 			
			}else{ 
				var pnganhangVND = document.getElementsByName("pnganhangVND");
	 			var pnhVND;
				if(pnganhangVND.item(0).value != ''){
					pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");
					 
				}else{
					pnhVND = 0;
				}					

				var thuevatVND = document.getElementsByName("vatVND");
		 		var vatVND;
				if(thuevatVND.item(0).value != ''){
					vatVND = thuevatVND.item(0).value.replace(/,/g,"");
					 
				}else{
					vatVND = 0;
				}					
				
				document.getElementById("pnganhangNT").value = "0";
				document.getElementById("vatNT").value = "0";

				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(document.getElementById("pnganhangVND").value.replace(/,/g,"")), 0));
	 			document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(document.getElementById("vatVND").value.replace(/,/g,"")), 0));
				
				tongttNT = parseFloat(tongtienNT) ;
				tongttVND = parseFloat(tongtienVND) + parseFloat(pnhVND) + parseFloat(vatVND);
			}
			
			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongttVND, 0));
			document.getElementById("tongtienNT").value = DinhDangDonGia((tongttNT).toFixed(2));
			
			var chenhlechVND = parseFloat(tongttNT)*parseFloat(tg) - parseFloat(tongttVND);
			document.getElementById("chenhlechVND").value = DinhDangTien(roundNumber(chenhlechVND, 0));
			
 			document.getElementById("tienhang_VAT").value = document.getElementById("pnganhangVND").value; 			
 			
 			document.getElementById("tienthue_VAT").value = document.getElementById("vatVND").value;
 			
 			document.getElementById("thuesuat_VAT").value = DinhDangDonGia((parseFloat(document.getElementById("vatVND").value.replace(/,/g,""))*100/document.getElementById("pnganhangVND").value.replace(/,/g,"")).toFixed(2));
 			
 			document.getElementById("tigia").value = DinhDangTien(document.getElementById("tigia").value);
	 	}
  } else { // LOAI THANH TOAN : KH??C
	// THANH TOAN VND
	  if(tienteId.item(0).value == "100000"){
		  var sotienthanhtoan = document.getElementsByName("sotienHDVND");
          var sotienthanhtoanVND;
			if(sotienthanhtoan.item(0).value != ''){
				sotienthanhtoanVND = sotienthanhtoan.item(0).value.replace(/,/g,"");
				 
			}else{
				sotienthanhtoanVND = 0;
			}
		  
		  document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(sotienthanhtoanVND, 0));

		  var pnganhangVND = document.getElementsByName("pnganhangVND");

		  var pnhVND;
			if(pnganhangVND.item(0).value != ''){
				pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");
				 
			}else{
				pnhVND = 0;
			}					

			var thuevatVND = document.getElementsByName("vatVND");
			
			var vatVND;
			if(thuevatVND.item(0).value != ''){
				vatVND = thuevatVND.item(0).value.replace(/,/g,"");
				 
			}else{
				vatVND = 0;
			}
			
			var tongthanhtoan = parseFloat(sotienthanhtoanVND) + parseFloat(pnhVND) + parseFloat(vatVND);

			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongthanhtoan, 0));
			
			document.getElementById("vatVND").value = DinhDangTien(document.getElementById("vatVND").value);
			document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(pnhVND, 0));

			document.getElementById("tienhang_VAT").value = document.getElementById("pnganhangVND").value;
			document.getElementById("tienthue_VAT").value = document.getElementById("vatVND").value;
			
			document.getElementById("thuesuat_VAT").value = DinhDangDonGia((parseFloat(vatVND)*100/parseFloat(pnhVND)).toFixed(2)); 
	      
	  }else { // THANH TOAN BANG NGOAI TE
		  
		  var sotienthanhtoanNT = document.getElementsByName("sotienHDNT");
		  var sotienttNT= 0;
	      if(sotienthanhtoanNT.item(0).value != ''){
	    	  sotienttNT = sotienthanhtoanNT.item(0).value.replace(/,/g,"");
	      }else {
	    	  sotienttNT = 0;
	      }
	      var sotienttVND = parseFloat(sotienttNT)*parseFloat(tg);
	      document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(parseFloat(sotienttNT)*parseFloat(tg), 0)); 
	      document.getElementById("sotienHDNT").value = DinhDangTien(sotienttNT); 
	      
	   // THANH TO??N CHO LOAI "KHAC" BANG NGO???I T??? + TRICH PH?? NGO???I T???			
			if(trichphi.item(0).checked == false){
				var pnganhangNT = document.getElementsByName("pnganhangNT");
	 			var pnhNT;
				if(pnganhangNT.item(0).value != ''){
					pnhNT = pnganhangNT.item(0).value.replace(/,/g,"");
					 
				}else{
					pnhNT = 0;
				}					

				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(pnhNT)*parseFloat(tg), 0));

				var thuevatNT = document.getElementsByName("vatNT");
		 		var vatNT;
				if(thuevatNT.item(0).value != ''){
					vatNT = thuevatNT.item(0).value.replace(/,/g,"");
					 
				}else{
					vatNT = 0;
				}					
				
				document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(vatNT)*parseFloat(tg), 0));
				
	 			document.getElementById("pnganhangNT").value = DinhDangDonGia((parseFloat(pnhNT)).toFixed(2));

	 			document.getElementById("vatNT").value = DinhDangDonGia((parseFloat(document.getElementById("vatNT").value)).toFixed(2));
	 			
	 			tongttVND = parseFloat(sotienttVND) + parseFloat(pnhNT)*parseFloat(tg) + parseFloat(vatNT)*parseFloat(tg);
	 			
	 			tongttNT = parseFloat(sotienttNT) + parseFloat(pnhNT) + parseFloat(vatNT);
	 			
			}else{  // THANH TO??N CHO LOAI "KHAC" BANG NGO???I T??? + TRICH PH?? VND	
				var pnganhangVND = document.getElementsByName("pnganhangVND");
	 			var pnhVND;
				if(pnganhangVND.item(0).value != ''){
					pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");
					 
				}else{
					pnhVND = 0;
				}					

				var thuevatVND = document.getElementsByName("vatVND");
		 		var vatVND;
				if(thuevatVND.item(0).value != ''){
					vatVND = thuevatVND.item(0).value.replace(/,/g,"");
					 
				}else{
					vatVND = 0;
				}					
				
				document.getElementById("pnganhangNT").value = "0";
				document.getElementById("vatNT").value = "0";

				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(document.getElementById("pnganhangVND").value.replace(/,/g,"")), 0));
	 			document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(document.getElementById("vatVND").value.replace(/,/g,"")), 0));
				
				tongttNT = parseFloat(sotienttNT) ;
				tongttVND = parseFloat(sotienttVND) + parseFloat(pnhVND) + parseFloat(vatVND);
				
			}
	   			  
			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongttVND, 0));
			document.getElementById("tongtienNT").value = DinhDangDonGia((tongttNT).toFixed(2));		
			
			document.getElementById("tienhang_VAT").value = document.getElementById("pnganhangVND").value; 			
				
			document.getElementById("tienthue_VAT").value = document.getElementById("vatVND").value;
				
			document.getElementById("thuesuat_VAT").value = DinhDangDonGia((parseFloat(document.getElementById("vatVND").value.replace(/,/g,""))*100/document.getElementById("pnganhangVND").value.replace(/,/g,"")).toFixed(2));
				
			document.getElementById("tigia").value = DinhDangTien(document.getElementById("tigia").value); 		  
	  }
    
  }
	}

	function ThaydoiSotienNT(){
		var trichphi = document.getElementsByName("trichphi");
		var tigia = document.getElementsByName("tigia");
		var tg = tigia.item(0).value.replace(/,/g,"");
		
		
		if(trichphi.item(0).checked == false){ // TRICH PHI BANG NGOAI TE 
			
			var tongtienNT = document.getElementsByName("tongtienNT");
			var tongNT = tongtienNT.item(0).value.replace(/,/g,"");

			document.getElementById("tongtienVND").value = DinhDangTien(parseFloat(tongNT)*parseFloat(tg));

			var sotienHDVND = document.getElementsByName("sotienHDVND");
			var stHDVND = sotienHDVND.item(0).value.replace(/,/g,"");

			var pnganhangVND = document.getElementsByName("pnganhangVND");
			var pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");

			var vatVND = document.getElementsByName("vatVND");
			var vat = vatVND.item(0).value.replace(/,/g,"");

			var chenhlech = parseFloat(tongNT)*parseFloat(tg) - (parseFloat(stHDVND) + parseFloat(pnhVND) + parseFloat(vat));
			document.getElementById("chenhlechVND").value = DinhDangTien(chenhlech);
			document.getElementById("tongtienNT").value = DinhDangDonGia((tongNT).toFixed(2));
		}else{ // TRICH PHI BANG VND

			var tongtienNT = document.getElementsByName("tongtienNT");
			var tongNT = tongtienNT.item(0).value.replace(/,/g,"");

			var tongVND = parseFloat(tongNT)*parseFloat(tg);

			document.getElementById("tongtienVND").value = DinhDangTien(tongVND);

			var sotienHDVND = document.getElementsByName("sotienHDVND");
			var stHDVND = sotienHDVND.item(0).value.replace(/,/g,"");

			var pnganhangVND = document.getElementsByName("pnganhangVND");
			var pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");

			var vatVND = document.getElementsByName("vatVND");
			var vat = vatVND.item(0).value.replace(/,/g,"");

			var chenhlech = parseFloat(tongVND) - (parseFloat(stHDVND) + parseFloat(pnhVND) + parseFloat(vat));
			document.getElementById("chenhlechVND").value = DinhDangTien(chenhlech);
			
		}
	}
	
	function ThaydoiSotienTT(){
		var tongtienVND = document.getElementsByName("tongtienVND");
		var tongVND = tongtienVND.item(0).value.replace(/,/g,"");
		
		var sotienHDVND = document.getElementsByName("sotienHDVND");
		var stHDVND = sotienHDVND.item(0).value.replace(/,/g,"");

		var pnganhangVND = document.getElementsByName("pnganhangVND");
		var pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");

		var vatVND = document.getElementsByName("vatVND");
		var vat = vatVND.item(0).value.replace(/,/g,"");

		document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongVND, 0));
		
		var chenhlech = parseFloat(tongVND) - (parseFloat(stHDVND) + parseFloat(pnhVND) + parseFloat(vat));

		document.getElementById("chenhlechVND").value = DinhDangTien(roundNumber(chenhlech, 0));
	}

	function changeNcc()
	{
		var nccId=document.getElementById("nccId");
		//alert("da toi day");
		//alert(nccId.value);
		//alert(nccId.value.indexOf(' -- '));
		if(nccId.value.indexOf(' -- ') > 0 )
		{
			//alert("da toi day roi ne");
			//submitform();
			 document.forms['tthdForm'].action.value='submit';
		     document.forms["tthdForm"].submit();
		}
		
		
	}
	function TongTienThanhToanVND()
	{
		var sotienthanhtoanVND = document.getElementsByName("sotienthanhtoanVND");
		var stttVND = sotienthanhtoanVND.item(0).value.replace(/,/g,"");
		
		var pnganhangVND = document.getElementsByName("pnganhangVND");
		var pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");

		var vatVND = document.getElementsByName("vatVND");
		var vat = vatVND.item(0).value.replace(/,/g,"");
		
		var tongVND = parseFloat(stttVND) + parseFloat(pnhVND) + parseFloat(vat);
		
		document.getElementById("sotienthanhtoanVND").value = DinhDangTien(stttVND);
		document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongVND, 0));
	}
	
	function DinhDangTienTT()
	{
		var giatrinhap = document.getElementById("sotienthanhtoan");
		giatrinhap.value = DinhDangTien(giatrinhap.value);
	}
	
	function PhanBoTien()
	{	
		 var kyhieuhd = document.getElementsByName("kyhieuhd");
		 var trahet = document.getElementsByName("trahet");
		 var avat = document.getElementsByName("avat");
		 var thanhtoan = document.getElementsByName("thanhtoan");
		 var conlai = document.getElementsByName("conlai");
		 
		 for(j = 0; j < kyhieuhd.length; j++)
		 {
			 thanhtoan.item(j).value = "";
			 conlai.item(j).value = "";
			 trahet.item(j).checked = false;
		 }
		 
		 var sotienphanbo = document.getElementById("sotienthanhtoan").value.replace(/,/g,"");
		 //alert(sotienphanbo);

		 var tongtien = 0;
		 for(i = 0; i < kyhieuhd.length; i++)
		 {
			tienAvat =  avat.item(i).value.replace(/,/g, "");
			tongtien = parseFloat(tongtien) + parseFloat(tienAvat);
			
			if(tongtien < parseFloat(sotienphanbo))
			{
				thanhtoan.item(i).value = DinhDangTien(roundNumber(tienAvat, 0));
				conlai.item(i).value = 0;
				trahet.item(i).checked = true;
			}
			else
			{
				tongtien = parseFloat(tongtien) - parseFloat(tienAvat);
				var tienconlai = parseFloat(sotienphanbo) - parseFloat(tongtien);
				
				thanhtoan.item(i).value = DinhDangTien(roundNumber(tienconlai, 0));
				conlai.item(i).value = DinhDangTien(roundNumber(parseFloat(tienAvat) - parseFloat(tienconlai), 0));
				if(parseFloat(tienAvat) - parseFloat(tienconlai) <= 0)
					trahet.item(i).checked = true;
				else
					trahet.item(i).checked = false;
				
				break;
			}
		 } 
	}
	
	function DinhDangDonGia(num) 
	{
		num = num.toString().replace(/\$|\,/g,'');
			
		//num = (Math.round( num * 1000 ) / 1000).toString();
			
		var sole = '';
		if(num.indexOf(".") >= 0)
		{
			sole = num.substring(num.indexOf('.'));
		}
			
		if(isNaN(num))
			num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100);
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
	
	function keypress(e) //H??m d??ng d? ngan ngu?i d??ng nh?p c??c k?? t? kh??c k?? t? s? v??o TextBox
	{    
		var keypressed = null;
		if (window.event)
			keypressed = window.event.keyCode; //IE
		else
			keypressed = e.which; //NON-IE, Standard
		
		if (keypressed < 48 || keypressed > 57)
		{ 
			if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46|| keypressed == 45)
			{//Ph??m Delete v?? Ph??m Back
				return;
			}
			return false;
		}
	}
	
	 function saveform()
	 {	
		 var ngayghinhan = document.getElementById("ngayghinhan");
 		 if(ngayghinhan.value == "")
		 {
			alert("Vui long chon ngay ghi nhan");
			return;
		 }
		 
		 var sotienHDVND = document.getElementById("sotienHDVND");
		 if(sotienHDVND.value == "" || sotienHDVND.value == "0")
		 {
			alert("Vui long nhap so tien thanh toan");
			return;
		 } 
		 

		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['tthdForm'].action.value='save';
	     document.forms['tthdForm'].submit();
	 }
	 
	 function ChangeTienTe(){
		 document.forms['tthdForm'].action.value= 'changeTT';
	     document.forms["tthdForm"].submit();
		 
	 }

	 function loaddoituong(){
		 document.forms['tthdForm'].action.value= 'loaddoituong';
	     document.forms["tthdForm"].submit();
		 
	 }
	 
	 function submitform()
	 { 
		 document.forms['tthdForm'].action.value='submit';
	     document.forms["tthdForm"].submit();
	 }
	 
	 function submitform_HTTT(){
		 document.forms['tthdForm'].action.value='changeHTTT';
	     document.forms["tthdForm"].submit();
	 
	 }
	 
	 function nextform()
	 { 
		 document.forms['tthdForm'].action.value='next';
	     document.forms["tthdForm"].submit();
	 }
	 function changePO()
	 { 
		 document.forms['tthdForm'].action.value='changePO';
	     document.forms["tthdForm"].submit();
	 }
	 function changeLoaiThanhToan()
	 { 
		 document.forms['tthdForm'].action.value='loaithanhtoan';
	     document.forms["tthdForm"].submit();
	 }
	 
	 function changeNhomNCC()
	 {
		document.forms['tthdForm'].action.value='nhomncccn';
		document.forms["tthdForm"].submit();
		
	 } 
	 
	function replacesdinhkhoan()
	{
		var dinhkhoanOld =  document.getElementById("dinhkhoannoId").value;
		dinhkhoanOld = parseInt(dinhkhoanOld);
		var temp = document.getElementById("dinhkhoanno").value;
		var dinhkhoanNew = temp.substring(0, parseInt(temp.indexOf(" -- ")));
					
		if(dinhkhoanNew.length >0)
		{
			if(dinhkhoanOld!=dinhkhoanNew)
			{									
				document.forms["tthdForm"].dinhkhoannoId.value = dinhkhoanNew;			
				document.forms["tthdForm"].submit();
			}
		}		
		setTimeout(replacesdinhkhoan, 300);
	}
	
	function mauhoadon(){
		var mauHD = document.getElementById("mauHD_VAT").value;
		var maHD;
		
		if(mauHD.length >= 6) 
			maHD =  mauHD.substring(0, 6) ;
		else
			maHD = mauHD;
		
		document.getElementById("maHD_VAT").value = maHD;
	} 
	
	function tinhthue(){
		var tienhang = document.getElementById("tienhang_VAT").value;
		var thuesuat = document.getElementById("thuesuat_VAT").value;		
		var tienteId = document.getElementsByName("tienteId");
		
		tienhang = tienhang.replace(/,/g, "");
		thuesuat = thuesuat.replace(/,/g, "");

		var tigia = document.getElementById("tigia").value;
		tigia = tigia.replace(/,/g, "");

		document.getElementById("tienhang_VAT").value = DinhDangTien(roundNumber(parseFloat(tienhang), 0));		
		document.getElementById("tienthue_VAT").value = DinhDangTien(roundNumber(parseFloat(tienhang)*parseFloat(thuesuat)/100, 0));


		if(tienteId.item(0).value != "100000"){ //tien te la ngoai te
			var trichphi = document.getElementsByName("trichphi");
			if(trichphi.item(0).checked == true){ //Ngoai te, nhung trich phi bang VND
				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(tienhang), 0));
				document.getElementById("vatVND").value = document.getElementById("tienthue_VAT").value;	
				
				document.getElementById("vatNT").value = DinhDangDonGia((parseFloat(tienhang)*parseFloat(thuesuat)/(100*parseFloat(tigia))).toFixed(2));
				document.getElementById("pnganhangNT").value = DinhDangDonGia(parseFloat(tienhang)/parseFloat(tigia));
				
				
			}else{ //Ngoai te, va trich phi bang ngoai te 
				document.getElementById("pnganhangNT").value = DinhDangTien(roundNumber(parseFloat(tienhang), 0));
				document.getElementById("vatNT").value = document.getElementById("tienthue_VAT").value;	
				
				document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(tienhang)*parseFloat(thuesuat)*parseFloat(tigia)/100, 0));
				document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(tienhang)*parseFloat(tigia), 0));

			}
		}else{ //tien te la tien Viet

			document.getElementById("pnganhangVND").value = document.getElementById("tienhang_VAT").value;
			document.getElementById("vatVND").value = document.getElementById("tienthue_VAT").value;	
 			
 			var sotienHDVND = document.getElementsByName("sotienHDVND");
 			
 			var sotienVND;
			if(sotienHDVND.item(0).value != ''){
				sotienVND = sotienHDVND.item(0).value.replace(/,/g,"");
				 
			}else{
				sotienVND = 0;
			}					

			var tongtienVND = parseFloat(sotienVND) + parseFloat(tienhang) + parseFloat(tienhang)*parseFloat(thuesuat)/100;

 			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongtienVND, 0));
 			
		}		

		//ThanhToan(100);
	}
	
	
	function tinhthue_loaikhac(){
		var tienhang = document.getElementById("tienhang_VAT").value;
		var thuesuat = document.getElementById("thuesuat_VAT").value;		
		var tienteId = document.getElementsByName("tienteId");
		
		var pnganhangVND = document.getElementsByName("pnganhangVND");
			var pnhVND;
		if(pnganhangVND.item(0).value != ''){
			pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");			 
		}else{
			pnhVND = 0;
		}
		
		
		tienhang = tienhang.replace(/,/g, "");
		thuesuat = thuesuat.replace(/,/g, "");

		var tigia = document.getElementById("tigia").value;
		tigia = tigia.replace(/,/g, "");

		var tienthue_VAT = roundNumber(parseFloat(tienhang)*parseFloat(thuesuat)/100, 0);
		document.getElementById("tienhang_VAT").value = DinhDangTien(roundNumber(parseFloat(tienhang), 0));		
		document.getElementById("tienthue_VAT").value = DinhDangTien(tienthue_VAT);


		if(tienteId.item(0).value != "100000"){ //tien te la ngoai te
			var trichphi = document.getElementsByName("trichphi");
			if(trichphi.item(0).checked == true){ //Ngoai te, nhung trich phi bang VND
				document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(parseFloat(tienhang), 0));
				//document.getElementById("vatVND").value = document.getElementById("tienthue_VAT").value;	
				
				//document.getElementById("vatNT").value = DinhDangDonGia((parseFloat(tienhang)*parseFloat(thuesuat)/(100*parseFloat(tigia))).toFixed(2));
				//document.getElementById("pnganhangNT").value = DinhDangDonGia(parseFloat(tienhang)/parseFloat(tigia));
				
				
			}else{ //Ngoai te, va trich phi bang ngoai te 
				document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(parseFloat(tienhang), 0));
				//document.getElementById("vatNT").value = document.getElementById("tienthue_VAT").value;	
				
				//document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(tienhang)*parseFloat(thuesuat)*parseFloat(tigia)/100, 0));
				//document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(tienhang)*parseFloat(tigia), 0));

			}
		}else{ //tien te la tien Viet

			document.getElementById("sotienHDVND").value = document.getElementById("tienhang_VAT").value;
			document.getElementById("vatVND").value = document.getElementById("tienthue_VAT").value;	
 			
 			var sotienHDVND = document.getElementsByName("sotienHDVND");
 			
 			var sotienVND;
			if(sotienHDVND.item(0).value != ''){
				sotienVND = sotienHDVND.item(0).value.replace(/,/g,"");
				 
			}else{
				sotienVND = 0;
			}					

			//var tongtienVND = parseFloat(sotienVND) + parseFloat(tienhang) + parseFloat(tienhang)*parseFloat(thuesuat)/100;
			var tongtienVND = parseFloat(tienhang) + parseFloat(tienthue_VAT) + parseFloat(pnhVND);

 			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongtienVND, 0));
 			
		}		

		//ThanhToan(100);
	}
	
	
	function tinhthue_loaikhac_new(){
		
		
		var tienteId = document.getElementsByName("tienteId");		
		
		var pnganhangVND = document.getElementsByName("pnganhangVND");
			var pnhVND;
		if(pnganhangVND.item(0).value != ''){
			pnhVND = pnganhangVND.item(0).value.replace(/,/g,"");			 
		}else{
			pnhVND = 0;
		}
		
		var sum_tienhang = 0;		
		var sum_tienthue_VAT = 0;
		var tienthueVATct =  0;
		var tienhangct = 0;
		var thuesuatct = 0;
		
		var count = document.getElementById("count").value;
		
		 for(var i = 0; i < count; i++)
		 {
			 var tienhang = document.getElementById("tienhang_VAT_"+i).value.replace(/,/g,"");
			 var thuesuat = document.getElementById("thuesuat_VAT_"+i).value.replace(/,/g,"");	
			 var tienthue = document.getElementById("tienthue_VAT_"+i).value.replace(/,/g,"");
			 				
			 if(tienhang != ''){
				 tienhangct = parseFloat(tienhang);
				}else{
					tienhangct = 0;
				}
			 
			 if(thuesuat != ''){
				 thuesuatct = parseFloat(thuesuat);	
				}else{
					thuesuatct = 0;
				}			 
				 			 
			 tienthueVATct = roundNumber(tienhangct*thuesuatct/100, 0);
			 

			
			 
			 sum_tienhang += tienhangct;
			 sum_tienthue_VAT += tienthueVATct;
				 
			 if(tienhangct != 0)				 
				 document.getElementById("tienhang_VAT_"+i).value = DinhDangTien(roundNumber(tienhangct, 0));
			 else
				 document.getElementById("tienhang_VAT_"+i).value = '';
			 
			 if(thuesuatct != 0)	
				 document.getElementById("tienthue_VAT_"+i).value = DinhDangTien(tienthueVATct);
			 else
				 {
				 	if(tienhangct != 0)
				 		document.getElementById("tienthue_VAT_"+i).value = '0';
				 	else
				 		document.getElementById("tienthue_VAT_"+i).value = '';
				 }
			 			 			 
		 }
		
	

		if(tienteId.item(0).value != "100000"){ //tien te la ngoai te
			var trichphi = document.getElementsByName("trichphi");
			if(trichphi.item(0).checked == true){ //Ngoai te, nhung trich phi bang VND
				document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(sum_tienhang, 0));
								
			}else{ //Ngoai te, va trich phi bang ngoai te 
				document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(sum_tienhang, 0));
				//document.getElementById("vatNT").value = document.getElementById("tienthue_VAT").value;	
				
				//document.getElementById("vatVND").value = DinhDangTien(roundNumber(parseFloat(tienhang)*parseFloat(thuesuat)*parseFloat(tigia)/100, 0));
				//document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(parseFloat(tienhang)*parseFloat(tigia), 0));

			}
		}else{ //tien te la tien Viet

			document.getElementById("sotienHDVND").value = DinhDangTien(roundNumber(sum_tienhang, 0));
			document.getElementById("vatVND").value = DinhDangTien(roundNumber(sum_tienthue_VAT, 0));
 			
 			var sotienHDVND = document.getElementsByName("sotienHDVND");
 			
 			var sotienVND;
			if(sotienHDVND.item(0).value != ''){
				sotienVND = sotienHDVND.item(0).value.replace(/,/g,"");
				 
			}else{
				sotienVND = 0;
			}					

			//var tongtienVND = parseFloat(sotienVND) + parseFloat(tienhang) + parseFloat(tienhang)*parseFloat(thuesuat)/100;
			var tongtienVND = sum_tienhang + sum_tienthue_VAT + parseFloat(pnhVND);

 			document.getElementById("tongtienVND").value = DinhDangTien(roundNumber(tongtienVND, 0));
 			
		}		

		//ThanhToan(100);
	}
	
	function tinhthuesuat(){
		var tienhang = document.getElementById("tienhang_VAT").value;
		var tienthue = document.getElementById("tienthue_VAT").value;

		tienhang = tienhang.replace(/,/g, "");
		tienhue = tienthue.replace(/,/g, "");
		
		var tigia = document.getElementById("tigia").value;
		tigia = tigia.replace(/,/g, "");

		if(tienteId.item(0).value != "100000"){ //tien te la ngoai te
		
			var trichphi = document.getElementsByName("trichphi");
			if(trichphi.item(0).checked == true){ //Ngoai te, nhung trich phi bang VND

			}else{ //Ngoai te, va trich phi bang ngoai te
				
			}
		}else{ //tien te la tien Viet
			document.getElementById("tienhang_VAT").value = DinhDangTien(roundNumber(document.getElementById("tienhang_VAT").value, 0));
			document.getElementById("tienthue_VAT").value = DinhDangTien(roundNumber(document.getElementById("tienthue_VAT").value, 0));
			document.getElementById("thuesuat_VAT").value = DinhDangDonGia((parseFloat(tienthue)*100/parseFloat(tienhang)).toFixed(2));
			
			document.getElementById("vatVND").value = document.getElementById("tienthue_VAT").value;
			document.getElementById("vatNT").value = DinhDangDonGia((parseFloat(tienhue)/parseFloat(tigia)).toFixed(2));
			
			document.getElementById("pnganhangVND").value = DinhDangTien(roundNumber(tienhang, 0));
			document.getElementById("pnganhangNT").value = DinhDangDonGia(parseFloat(tienhang)/parseFloat(tigia));
			
		}
		
		
		ThanhToan(100);
	}
	
	
	function ChangeNganHang(){
		var nganhang = document.getElementById("nghangTen").value;
//		NG??N H??NG TMCP NGO???I TH????NG - TH??? ?????C - TP.HCM, M?? s??? thu???: Chua c?? [ 100039:100051 ]
		
		if( nganhang.indexOf(" - ") > 0){
			var nghangTen = nganhang.substring(0, nganhang.indexOf(" - "));
			document.getElementById("nghangTen").value = nghangTen;
		
			var mst = nganhang.substring(nganhang.indexOf(": ") + 2, nganhang.indexOf("["));
			document.getElementById("mst_VAT").value = mst;
			
			var nhId_VAT = nganhang.substring(nganhang.indexOf("[ ") + 2, nganhang.indexOf(":", nganhang.indexOf("[ "))); 
			document.getElementById("nhId_VAT").value = nhId_VAT;
			
			var cnId_VAT = nganhang.substring(nganhang.indexOf(":", nganhang.indexOf("[ "))+ 1, nganhang.length-2); // Tim ":" bat dau tu "[ "
			
			document.getElementById("cnId_VAT").value = cnId_VAT;
			
		}
		setTimeout(ChangeNganHang, 300);
	}
	
</script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2(); });
     
</script>
	
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="tthdForm" method="post" action="../../ErpTTHoadonUpdateSvl">
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" id="count" value='<%= count %>'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? c??ng n??? > C??ng n??? ph???i tr??? > Phi???u chi > T???o m???i
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../ErpThanhtoanhoadonSvl?userId=<%= userId %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <span id="btnSave">
	        <A href="javascript:saveform()" >
	        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        </span>
          <%-- <A href="../../ErpTTHoaDonPdfSvl?userId=<%= tthdBean.getUserId() %>&id=<%= tthdBean.getId() %>&httt=<%= tthdBean.getHtttId() %>" >
	        <IMG src="../images/Printer30.png" title="In phieu" alt="In phieu" border ="1px" style="border-style:outset"></A> --%>
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Th??ng b??o</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%%"><%= tthdBean.getMsg() %></textarea>
		         <% tthdBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle"> Phi???u chi  </legend>
        	<div style="float:none; width:100%" align="left">
            <TABLE width="100%" cellpadding="4" cellspacing="0" border = '0'>		
            	
            	<TR>
                    
                    <TD width="20%" class="plainlabel" valign="top">Lo???i thanh to??n </TD>
                    <TD class="plainlabel" valign="top"  colspan = 4>
                    	 <select name="doituongthanhtoan" id="doituongthanhtoan" onChange="submitform();" style = "width:200px">					              	
				              	<%
				              		if(tthdBean.getDoiTuongChiPhiKhac().equals("1")){ %>
			              				<option value="1" selected="selected" >Nh?? cung c???p</option>
			              				<option value="0"  >Nh??n vi??n</option>
			              				<option value="2"  >Kh??ch h??ng</option>
			              				<option value="3"  >Kh??c</option>
			              				<!-- <option value="4"  >B??? ph???n</option> -->
			              				<!-- <option value="5"  >Chi tr??? h???p ?????ng t??i tr???</option> -->
				              		<%}else if(tthdBean.getDoiTuongChiPhiKhac().equals("0")){ %>
			              				<option value="1"  >Nh?? cung c???p</option>
			              				<option value="0" selected="selected" >Nh??n vi??n</option>
			              				<option value="2"  >Kh??ch h??ng</option>
			              				<option value="3"  >Kh??c</option>
			              				<!-- <option value="4"  >B??? ph???n</option> -->
			              				<!-- <option value="5"  >Chi tr??? h???p ?????ng t??i tr???</option> -->
				              		 <% }else if(tthdBean.getDoiTuongChiPhiKhac().equals("2")){ %>
			              				<option value="1"  >Nh?? cung c???p</option>
			              				<option value="0" >Nh??n vi??n</option>
			              				<option value="2" selected="selected" >Kh??ch h??ng</option>
			              				<option value="3"  >Kh??c</option>
			              				<!-- <option value="4"  >B??? ph???n</option> -->
			              				<!-- <option value="5"  >Chi tr??? h???p ?????ng t??i tr???</option> -->
			              		    <% } else if(tthdBean.getDoiTuongChiPhiKhac().equals("3")){ %>
			              				<option value="1"  >Nh?? cung c???p</option>
			              				<option value="0" >Nh??n vi??n</option>
			              				<option value="2"  >Kh??ch h??ng</option>
			              				<option value="3"  selected="selected">Kh??c</option>
			              				<!-- <option value="4"  >B??? ph???n</option> -->
			              				<!-- <option value="5"  >Chi tr??? h???p ?????ng t??i tr???</option> -->
			              		    <% } %>					       											              		
					      </select>
                    </TD>
                    
                    <%--  <TD class="plainlabel"  style="width:150px" align = "right">
                    <%if(Double.parseDouble(tthdBean.getCheckThanhtoantuTV()) == 1){ %>
                    	<input type="checkbox" id="thanhtoantuTV" name ="thanhtoantuTV" value= "1" checked="checked" onchange="submitform();">
                    <%}else{ %>
                    	<input type="checkbox" id="thanhtoantuTV" name ="thanhtoantuTV" value= "1" onchange="submitform();"() >
                    <%} %>
                    </TD>
                    <TD class="plainlabel" colspan = 2>Thanh to??n t??? ti???n vay</TD> --%>
                    
                    <%//N???U C?? CHECKED THANHTOANTUTIENVAY TH?? CH??? ???????C S???A ?? PH?? && THU???, C??C ?? C??N L???I KH??NG ???????C S???A %>
                </TR> 				
                <TR>
                 <TD width="20%" class="plainlabel" valign="top">Ng??y ch???ng t???</TD>
                                 
                    <TD  class="plainlabel" valign="top" colspan = 4>
                    	<input type="text"  class="days" id="ngayghinhan" name="ngayghinhan" value="<%= tthdBean.getNgayghinhan() %>" 
                    		maxlength="10"  onChange = "ChangeTienTe();" />
                    </TD>
                    
                    
                </TR> 
	         <%if(tthdBean.getDoiTuongChiPhiKhac().equals("1")){ %>
	                <TR>
	                    <TD class="plainlabel">Nh?? cung c???p / Nh??m NCC
	                    	<% if(tthdBean.getNhomNCCCN().equals("1")) { %>
									<input type="checkbox" id="nhomncccn" name="nhomncccn" value="1" checked = "checked" onchange="ChangeTienTe();" >
									<% } else {  %>
										<input type="checkbox" id="nhomncccn" name="nhomncccn" value="1" onchange="ChangeTienTe();" >
									<% } %>
	                    </TD>
	                    <% if(tthdBean.getNhomNCCCN().equals("1")) { %>
	                    <TD class="plainlabel" colspan="3">
		                  <select name="nhomnccId" id="nhomnccId" style="width: 300px" onChange="ChangeTienTe();">
                        	<option value=""> </option>
                        	<%
                        		if(nhomnccList != null)
                        		{
                        			try
                        			{
                        			while(nhomnccList.next())
                        			{  
                        			if( nhomnccList.getString("pk_seq").equals(tthdBean.getNhomNCCCNId())){ %>
                        				<option value="<%= nhomnccList.getString("pk_seq") %>" selected="selected" ><%= nhomnccList.getString("ten")%></option>
                        			<%}else { %>
                        				<option value="<%= nhomnccList.getString("pk_seq") %>" ><%= nhomnccList.getString("ten") %></option>
                        		 <% } } nhomnccList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
		               </TD>
	                    <%}else{ %>
	                    <TD class="plainlabel" colspan="3">
		                  <select name="nccId" id="nccId" style="width: 300px" onChange="ChangeTienTe();">
                        	<option value=""> </option>
                        	<%
                        		if(nccList != null)
                        		{
                        			try
                        			{
                        			while(nccList.next())
                        			{  
                        			if( nccList.getString("pk_seq").equals(tthdBean.getNccId())){ %>
                        				<option value="<%= nccList.getString("pk_seq") %>" selected="selected" ><%= nccList.getString("ten")%></option>
                        			<%}else { %>
                        				<option value="<%= nccList.getString("pk_seq") %>" ><%= nccList.getString("ten") %></option>
                        		 <% } } nccList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
		               </TD> 			                        										
						<%} %>		
	                </TR>
	         <%}else if(tthdBean.getDoiTuongChiPhiKhac().equals("0")){ // LOAITT: KH??CH H??NG TR??? TR?????C >> LOAD NV %>
						<TR>	
						  <TD width="20%" class="plainlabel" valign="top">T??n nh??n vi??n </TD>				        
					      <TD class="plainlabel" colspan="4">
					       <select name="NhanvienId" id="NhanvienId" style="width: 300px" onChange="ChangeTienTe();">
                        	<option value=""> </option>
                        	<%
                        		if(nvList != null)
                        		{
                        			try
                        			{
                        			while(nvList.next())
                        			{  
                        			if( nvList.getString("pk_seq").equals(tthdBean.getNhanVienId())){ %>
                        				<option value="<%= nvList.getString("pk_seq") %>" selected="selected" ><%= nvList.getString("ten")%></option>
                        			<%}else { %>
                        				<option value="<%= nvList.getString("pk_seq") %>" ><%= nvList.getString("ten") %></option>
                        		 <% } } nvList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
		               </TD> 	
						</TR>
			<%} else if(tthdBean.getDoiTuongChiPhiKhac().equals("2") || tthdBean.getDoiTuongChiPhiKhac().equals("5") ){ // LOAITT: KH??CH H??NG TR??? TR?????C >> LOAD KH %>
						<TR>
						 	 <TD width="20%" class="plainlabel" valign="top">T??n kh??ch h??ng </TD>
							<TD class="plainlabel" colspan="3">
		                 	 <select name="khid" id="khid" style="width: 300px" onChange="ChangeTienTe();">
                        	<option value=""> </option>
                        	<%
                        		if(khList != null)
                        		{
                        			try
                        			{
                        			while(khList.next())
                        			{  
                        			if( khList.getString("pk_seq").equals(tthdBean.getKhachhangId())){ %>
                        				<option value="<%= khList.getString("pk_seq") %>" selected="selected" ><%= khList.getString("ten")%></option>
                        			<%}else { %>
                        				<option value="<%= khList.getString("pk_seq") %>" ><%= khList.getString("ten") %></option>
                        		 <% } } khList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
		               </TD>					        
						</TR>						      
			<%} else if (tthdBean.getDoiTuongChiPhiKhac().equals("4")) { %>
					     <TR>
					          <TD width="20%" class="plainlabel" valign="top">B??? ph???n</TD>
					          <TD colspan="4" class="plainlabel" valign="top">
					          <input  type="text" name="bpTen" id="bpTen" value="<%= tthdBean.getBophanTen()%>"  style="width:400px" >
					          <input type="hidden" id="bpId" name="bpId" value = "<%=tthdBean.getBophanId() %>" style="width: 400px">	
					          </TD>
					     </TR> 		
    					      
			<% }  %> 
										
                <TR>
                   <TD class="plainlabel">H??nh th???c thanh to??n</TD>               
                    <TD colspan="6" class="plainlabel">
                        <select name="htttId" id="htttId" style="width:200px" onChange = "submitform_HTTT();">
                        	<option value=""> </option>
                        	<%
                        		if(htttList != null)
                        		{
                        			try
                        			{
                        			while(htttList.next())
                        			{  
                        			if( htttList.getString("pk_seq").equals(tthdBean.getHtttId())){ %>
                        				<option value="<%= htttList.getString("pk_seq") %>" selected="selected" ><%= htttList.getString("ma")%></option>
                        			<%}else { %>
                        				<option value="<%= htttList.getString("pk_seq") %>" ><%= htttList.getString("ma") %></option>
                        		 <% } } htttList.close();} catch(SQLException ex){
                        			 
                        		 }
                        		}
                        	%>
                        </select>
                     </TD> 
                   
                </TR>
           <%
             if(tthdBean.getHtttId().equals("100001"))
             { %>               						
                <TR>
                  <% if( Double.parseDouble(tthdBean.getCheckThanhtoantuTV()) == 0) {%>
                     <TD class="plainlabel">S??? t??i kho???n</TD>
                  <%}else{ %>
               		 <TD class="plainlabel">S??? t??i kho???n (Tr??ch ph?? ng??n h??ng)</TD>
                  <%} %>
                     <TD class="plainlabel" colspan = 4>                     
                        <select name="sotaikhoan" id="sotaikhoan" style="width: 300px" onChange="submitform();">
                        	<OPTION VALUE = "">&nbsp;</OPTION>
                        	<%
                        		if(sotkRs != null)
                        		{
                        			try
                        			{
                        			while(sotkRs.next())
                        			{  
                        			if( sotkRs.getString("SOTAIKHOAN").equals(tthdBean.getSotaikhoan())){ %>
                        				<option value="<%= sotkRs.getString("SOTAIKHOAN") %>" selected="selected" ><%= sotkRs.getString("TAIKHOAN")%></option>
                        			<%}else { %>
                        				<option value="<%= sotkRs.getString("SOTAIKHOAN") %>" ><%= sotkRs.getString("TAIKHOAN") %></option>
                        		 <% } } sotkRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD>                
                  
                </TR>
            <% }%>
               
            	<TR>
                    <TD class="plainlabel">Ti???n t???</TD>
                    <TD class="plainlabel" style = "width:100px">
                        <select name="tienteId" id="tienteId" onChange = "ChangeTienTe();" style = "width:200px;">
                        	<%
                        		if(tienteList != null)
                        		{
                        			try
                        			{
                        			while(tienteList.next())
                        			{  
                        			if( tienteList.getString("pk_seq").equals(tthdBean.getTienteId())){ %>
                        				<option value="<%= tienteList.getString("pk_seq") %>" selected="selected" ><%= tienteList.getString("ten")%></option>
                        			<%}else { %>
                        				<option value="<%= tienteList.getString("pk_seq") %>" ><%= tienteList.getString("ten") %></option>
                        		 <% } } tienteList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD>
                

                      <TD class="plainlabel" style="width:150px" align = "right">T??? gi??</TD>
                      <TD class="plainlabel" colspan = 3>
           		<% if(tthdBean.getTienteId().equals("100000")){ %>
                      	<input type="text" name="tigia" Id="tigia" value="<%= formatter2.format(Double.parseDouble(tthdBean.getTigia().replaceAll(",","")))  %>" style = "width:100px;text-align: right;"  readonly>
           		<%}else{ %>                   
           				<input type="text" name="tigia" Id="tigia" value="<%= formatter2.format(Double.parseDouble(tthdBean.getTigia().replaceAll(",","")))  %>" style = "width:100px;text-align: right;"  onchange="ThanhToan(100);" onKeyPress = "return keypress(event);">
           		<%} %>
                      </TD> 
            	
            	</TR>
			<%// THANH TOAN HOA DON DUNG NGOAI TE %>
            <% if(!tthdBean.getTienteId().equals("100000")){ %>
            	<TR>
                   <%if(tthdBean.getDoiTuongChiPhiKhac().equals("3")){  %>
                    <TD class="plainlabel">S??? ti???n thanh to??n (Ngo???i t???) </TD>
                    <TD class="plainlabel">                   
                       <input type="text" style="text-align: right;" name="sotienHDNT" id = "sotienHDNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getSotienHDNT().replaceAll(",",""))) %>" onchange="ThanhToan(100)" onKeyPress = "return keypress(event);" >              
                     </TD>
                   <%}else{ %>
                    <TD class="plainlabel">S??? ti???n h??a ????n (Ngo???i t???) </TD>
                    <TD class="plainlabel">
                         <input type="text" style="text-align: right;" name="sotienHDNT" id = "sotienHDNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getSotienHDNT().replaceAll(",",""))) %>" readonly  > 
                     </TD>
                   <%} %>

                   <%if(tthdBean.getDoiTuongChiPhiKhac().equals("3")){ %>
                    <TD class="plainlabel">S??? ti???n thanh to??n(VND) </TD>
                   <%}else { %>
                    <TD class="plainlabel">S??? ti???n h??a ????n (VN??) </TD>
					<%}%>
                    <TD class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="sotienHDVND" id = "sotienHDVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getSotienHD().replaceAll(",",""))) %>" readonly  > 
                     </TD>
                      
                </TR>

                <TR>
                  	<% if(tthdBean.getTrichphi().equals("0")) { %>

                    <TD class="plainlabel" colspan = 5>Tr??ch ph?? b???ng VN??
							<input type="checkbox" id="trichphi" name="trichphi" value="1"  onChange= "ChangeTienTe();" >
	                </TD>
	               <%}else{ %> 
                    <TD class="plainlabel">Tr??ch ph?? b???ng VN??
							<input type="checkbox" id="trichphi" name="trichphi" value="1" checked = "checked" onChange= "ChangeTienTe();" >
	                </TD>
					<TD class="plainlabel">T??i kho???n tr??ch ph?? (VND) </TD>				
					<TD class="plainlabel" colspan = 3>
                     
                        <select name="sotaikhoan_tp" id="sotaikhoan_tp" style="width: 500px" onChange = "submitform();">
                        		<OPTION VALUE = "">&nbsp</OPTION>
                        	<%
                        		if(sotkRs_tp != null)
                        		{
                        			try
                        			{
                        			while(sotkRs_tp.next())
                        			{  
                        			if( sotkRs_tp.getString("SOTAIKHOAN").equals(tthdBean.getSotaikhoan_tp())){ %>
                        				<option value="<%= sotkRs_tp.getString("SOTAIKHOAN") %>" selected="selected" ><%= sotkRs_tp.getString("TAIKHOAN")%></option>
                        			<%}else { %>
                        				<option value="<%= sotkRs_tp.getString("SOTAIKHOAN") %>" ><%= sotkRs_tp.getString("TAIKHOAN") %></option>
                        		 <% } } sotkRs_tp.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>

                    </TD>
					
				   <%} %>	
                </TR>                    
               
<%// THANH TOAN HOA DON DUNG NGOAI TE - TRICH PHI BANG NGOAI TE
               if(tthdBean.getTrichphi().equals("0")) { %>

				<TR>
                    <TD class="plainlabel" style = "width:150px">Ph?? ng??n h??ng (Ngo???i t???)</TD>
                    <TD  class="plainlabel">                                    
                    	<input type="text" style="text-align: right;" name="pnganhangNT" id = "pnganhangNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getPhinganhangNT().replaceAll(",",""))) %>" onchange="ThanhToan(100)" onKeyPress = "return keypress(event);"  >                  
                     </TD> 
				
                    <TD class="plainlabel" style = "width:150px">Ph?? ng??n h??ng (VN??)</TD>
                    <TD  class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="pnganhangVND" id = "pnganhangVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getPhinganhang().replaceAll(",",""))) %>" readonly  > 
                     </TD> 

				</TR>
            	<TR>
                    <TD class="plainlabel">Thu??? VAT (Ngo???i t???) </TD>
                    <TD  class="plainlabel">                  
                         <input type="text" style="text-align: right;" name="vatNT" id = "vatNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getThueVATNT().replaceAll(",",""))) %>"  onchange="ThanhToan(100)" onKeyPress = "return keypress(event);" > 
                     </TD> 

                    <TD class="plainlabel">Thu??? VAT (VND) </TD>
                    <TD  class="plainlabel" style="width:200px">
                         <input type="text" style="text-align: right;" name="vatVND" id = "vatVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getThueVAT().replaceAll(",",""))) %>" readonly   > 
                    </TD> 

					<TD align = "left" class="plainlabel" >
	                   <A href="" id="tinhVATNT" rel="subcontentVATNT"><img alt="T??nh VAT" title="T??nh VAT" src="../images/vitriluu.png"></a>
	                   <DIV id="subcontentVATNT" style="position:absolute; visibility: hidden; border: 2px solid #80CB9B; background-color: white; width: 900px; padding: 4px; max-height: 300px; overflow: auto; ">
							<TABLE width="100%" align="center">
        						<TR class="tbheader">
						        	<TH width="80px" align = "center">M?? h??a ????n</TH>
						        	<TH width="80px" align = "center">M???u h??a ????n</TH>
						        	<TH width="80px" align = "center">K?? hi???u</TH>
						         	<TH width="100px" align = "center">S??? h??a ????n</TH>
						         	<TH width="100px" align = "center">Ng??y h??a ????n </TH>
									<TH width="100px" align = "center">T??n NCC </TH>
									<TH width="100px" align = "center">MST </TH>
									<TH width="100px" align = "center">Ti???n h??ng </TH>
									<TH width="100px" align = "center">Thu??? su???t(%) </TH>
									<TH width="100px" align = "center">Ti???n thu??? </TH>
								</TR>
					      		<TR>
									<TD>
										<input type="text" name = "maHD_VAT" id = "maHD_VAT" value = "<%= tthdBean.getMahoadon() %>" style="width: 100%" readonly="readonly" >
									</TD>
									<TD>
										<input type="text" name = "mauHD_VAT" id = "mauHD_VAT" value = "<%= tthdBean.getMauhoadon() %>" onChange = "javascript:mauhoadon();" style="width: 100%" >
									</TD>
									<TD>
										<input type="text" name = "kyhieu_VAT" id = "kyhieu_VAT" value = "<%= tthdBean.getKyhieu() %>" style="width: 100%" >
									</TD>
											      		
									<TD>
										<input type="text" name = "sohd_VAT" id = "sohd_VAT" value = "<%= tthdBean.getSohoadon() %>" style="width: 100%" >
									</TD>

									<TD>
										<input type="text" name = "ngayhd_VAT"  value = "<%= tthdBean.getNgayhoadon() %>" style="width: 100%" class="days" readonly="readonly">
									</TD>
								
									<TD>
										<input type="text" name = "nghangTen" id = "nghangTen" value = "<%= tthdBean.getTenNCC_VAT() %>" style="width: 100%" onChange ="ChangeNganHang();" >
										<input type="hidden" name = "nhId_VAT" id = "nhId_VAT" value = "<%= tthdBean.getNhId_VAT() %>" style="width: 100%"  >
										<input type="hidden" name = "cnId_VAT" id = "cnId_VAT" value = "<%= tthdBean.getCnId_VAT() %>" style="width: 100%"  >
										
									</TD>

									<TD>
										<input type="text" name= "mst_VAT" id= "mst_VAT" value = "<%= tthdBean.getMST() %>" style="width: 100%"  >
									</TD>

									<TD>
										<input type="text" name= "tienhang_VAT" id= "tienhang_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getTienhang().replaceAll(",",""))) %>" style="width: 100%;text-align: right" readonly>
									</TD>

									<TD>
										<input type="text" name= "thuesuat_VAT" id= "thuesuat_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getThuesuat().replaceAll(",",""))) %>" style="width: 100%;text-align: right" readonly>
									</TD>

									<TD>
										<input type="text" name= "tienthue_VAT" id= "tienthue_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getTienthue().replaceAll(",",""))) %>" style="width: 100%;text-align: right" readonly>
									</TD>
								</TR>
							</TABLE>
						    <DIV align="right">
					             <LABEL style="color: red" ></LABEL>
					               		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	              	<a href="javascript:dropdowncontent.hidediv('subcontentVATNT')">Ho??n t???t</a>
							</DIV>
						     				
    					</DIV>

					</TD>

                </TR>
            	<TR>
                    <TD class="plainlabel" style = "width:150px">T???ng ti???n thanh to??n (Ngo???i t???)</TD>
                    <TD class="plainlabel">
                         <input type="text" style="text-align: right;" name="tongtienNT" id = "tongtienNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getSotienttNT().replaceAll(",",""))) %> "  onKeyPress = "return keypress(event);"  onChange = "ThaydoiSotienNT();"  > 
                     </TD> 

                    <TD class="plainlabel" style = "width:150px">T???ng ti???n thanh to??n (VN??)</TD>
                    <TD class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="tongtienVND" id = "tongtienVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getSotientt().replaceAll(",",""))) %>"   onKeyPress = "return keypress(event);" readonly> 
                     </TD> 
                </TR>
		
            	<TR>
                    <TD class="plainlabel">N???i dung thanh to??n </TD>
                    <TD  class="plainlabel" style = "width:350px" >
                        <input type="text" name="noidungthanhtoan" value="<%= tthdBean.getNoidungtt() %>"> 
                     </TD> 
                  
                    <%if(tthdBean.getDoiTuongChiPhiKhac().equals("1")) {%>
                    <TD align = "right" class="plainlabel" style = "width:200px">Ch??nh l???ch (VN??)</TD>
                    <TD class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="chenhlechVND" id = "chenhlechVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getChenhlech().replaceAll(",",""))) %>" readonly > 
                     </TD> 
                     <%} else{%>	
						<TD class="plainlabel" colspan = 3>
							<input type="hidden" style="text-align: right;" name="chenhlechVND" id = "chenhlechVND" value="0" readonly >
						</TD>
					<%} %>

                </TR>

<%// THANH TOAN HOP DONG DUNG NGOAI TE - TRICH PHI VN??
			}else{ %>        
				<TR>
                    <TD class="plainlabel" style = "width:150px">Ph?? ng??n h??ng (Ngo???i t???)</TD>
                    <TD  class="plainlabel">
                    
                    	<input type="text" style="text-align: right;" name="pnganhangNT" id = "pnganhangNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getPhinganhangNT().replaceAll(",",""))) %>"  readonly >
                    
                     </TD> 
				
                    <TD class="plainlabel" style = "width:150px">Ph?? ng??n h??ng (VN??)</TD>
                    <TD  class="plainlabel" colspan = 3>                   
                         <input type="text" style="text-align: right;" name="pnganhangVND" id = "pnganhangVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getPhinganhang().replaceAll(",",""))) %>" onchange="ThanhToan(100)" onKeyPress = "return keypress(event);"  > 
                     </TD> 

				</TR>
            	<TR>
                    <TD class="plainlabel">Thu??? VAT (Ngo???i t???) </TD>
                    <TD  class="plainlabel">
                         <input type="text" style="text-align: right;" name="vatNT" id = "vatNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getThueVATNT().replaceAll(",",""))) %>"  readonly> 
                     </TD> 

                    <TD class="plainlabel">Thu??? VAT (VND) </TD>
                    <TD  class="plainlabel" style="width:200px">                   
                         <input type="text" style="text-align: right;" name="vatVND" id = "vatVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getThueVAT().replaceAll(",",""))) %>" onchange="ThanhToan(100)" onKeyPress = "return keypress(event);"     > 
                    </TD> 

					<TD align = "left" class="plainlabel" >
	                   <A href="" id="tinhVATNT" rel="subcontentVATNT"><img alt="T??nh VAT" title="T??nh VAT" src="../images/vitriluu.png"></a>
	                   <DIV id="subcontentVATNT" style="position:absolute; visibility: hidden; border: 2px solid #80CB9B; background-color: white; width: 900px; padding: 4px; max-height: 300px; overflow: auto; ">
							<TABLE width="100%" align="center">
        						<TR class="tbheader">
						        	<TH width="80px" align = "center">M?? h??a ????n</TH>
						        	<TH width="80px" align = "center">M???u h??a ????n</TH>
						        	<TH width="80px" align = "center">K?? hi???u</TH>
						         	<TH width="80px" align = "center">S??? h??a ????n</TH>
						         	<TH width="80px" align = "center">Ng??y h??a ????n </TH>
									<TH width="100px" align = "center">T??n NCC </TH>
									<TH width="100px" align = "center">MST </TH>
									<TH width="100px" align = "center">Ti???n h??ng </TH>
									<TH width="100px" align = "center">Thu??? su???t(%) </TH>
									<TH width="100px" align = "center">Ti???n thu??? </TH>
								</TR>
					      		<TR>
									<TD>
										<input type="text" name = "maHD_VAT" id = "maHD_VAT" value = "<%= tthdBean.getMahoadon() %>" style="width: 100%" readonly="readonly" >
									</TD>
									<TD>
										<input type="text" name = "mauHD_VAT" id = "mauHD_VAT" value = "<%= tthdBean.getMauhoadon() %>" onChange = "javascript:mauhoadon();" style="width: 100%" >
									</TD>
									<TD>
										<input type="text" name = "kyhieu_VAT" id = "kyhieu_VAT" value = "<%= tthdBean.getKyhieu() %>" style="width: 100%" >
									</TD>
											      		
									<TD>
										<input type="text" name = "sohd_VAT" id = "sohd_VAT" value = "<%= tthdBean.getSohoadon() %>" style="width: 100%" >
									</TD>

									<TD>
										<input type="text" name = "ngayhd_VAT"  value = "<%= tthdBean.getNgayhoadon() %>" style="width: 100%" class="days" readonly="readonly">
									</TD>								
								
									<TD>
										<input type="hidden" name = "nhId_VAT" id = "nhId_VAT" value = "<%= tthdBean.getNhId_VAT() %>" style="width: 100%"  >
										<input type="hidden" name = "cnId_VAT" id = "cnId_VAT" value = "<%= tthdBean.getCnId_VAT() %>" style="width: 100%"  >
										<input type="text" name = "nghangTen" id = "nghangTen" value = "<%= tthdBean.getTenNCC_VAT() %>" style="width: 100%" onChange ="ChangeNganHang();" >
									</TD>

									<TD>
										<input type="text" name= "mst_VAT" id= "mst_VAT" value = "<%= tthdBean.getMST() %>" style="width: 100%"  >
									</TD>

									<TD>
										<input type="text" name= "tienhang_VAT" id= "tienhang_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getTienhang().replaceAll(",",""))) %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthue();" onKeyPress = "return keypress(event);" >
									</TD>

									<TD>
										<input type="text" name= "thuesuat_VAT" id= "thuesuat_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getThuesuat().replaceAll(",",""))) %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthue();" onKeyPress = "return keypress(event);" >
									</TD>
									
									<TD>
										<%-- <input type="text" name= "tienthue_VAT" id= "tienthue_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getTienthue().replaceAll(",",""))) %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthuesuat();" onKeyPress = "return keypress(event);"> --%>
										<input type="text" name= "tienthue_VAT" id= "tienthue_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getTienthue().replaceAll(",",""))) %>" style="width: 100%;text-align: right" onKeyPress = "return keypress(event);">
										
									</TD>
								</TR>
							</TABLE>
						    <DIV align="right">
					             <LABEL style="color: red" ></LABEL>
					               		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	              	<a href="javascript:dropdowncontent.hidediv('subcontentVATNT')">Ho??n t???t</a>
							</DIV>
						     				
    					</DIV>

					</TD>

                </TR>
            	<TR>
                    <TD class="plainlabel" style = "width:150px">T???ng ti???n thanh to??n (Ngo???i t???)</TD>
                    <TD class="plainlabel">
                         <input type="text" style="text-align: right;" name="tongtienNT" id = "tongtienNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getSotienttNT().replaceAll(",",""))) %>"  onKeyPress = "return keypress(event);" onChange = "ThaydoiSotienNT();" > 
                     </TD> 

                    <TD class="plainlabel" style = "width:150px">T???ng ti???n thanh to??n (VN??)</TD>
                    <TD class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="tongtienVND" id = "tongtienVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getSotientt().replaceAll(",",""))) %>" onKeyPress = "return keypress(event);" > 
                     </TD> 
                </TR>
		
            	<TR>
                    <TD class="plainlabel">N???i dung thanh to??n </TD>
                    <TD  class="plainlabel" style = "width:350px" >
                        <input type="text" name="noidungthanhtoan" value="<%= tthdBean.getNoidungtt() %>"> 
                     </TD> 

					 <%if(tthdBean.getDoiTuongChiPhiKhac().equals("1")) {%>
                    <TD align = "right" class="plainlabel" style = "width:200px">Ch??nh l???ch (VN??)</TD>
                    <TD class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="chenhlechVND" id = "chenhlechVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getChenhlech().replaceAll(",",""))) %>" readonly > 
                     </TD> 
  					<%} else{%>	
						<TD class="plainlabel" colspan = 3></TD>
					<%} %>
                </TR>
	
	
			<%} %>
<%// THANH TOAN HOA DON VN??			%>
	<%}else{ %>
            	<TR>
                    <TD class="plainlabel" style = "display:none">S??? ti???n h??a ????n (Ngo???i t???) </TD>
                    <TD class="plainlabel" style = "display:none">
                         <input type="text" style="text-align: right;display:none" name="sotienHDNT" id = "sotienHDNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getSotienHDNT().replaceAll(",",""))) %>" readonly  > 
                     </TD>

           		 <%if(tthdBean.getDoiTuongChiPhiKhac().equals("3")){ %>
                    <TD class="plainlabel">S??? ti???n thanh to??n (VND) </TD>
                    <TD class="plainlabel" colspan = 3>                                       
                         <input type="text" style="text-align: right;" name="sotienHDVND" id = "sotienHDVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getSotienHD().replaceAll(",",""))) %>"  readonly onKeyPress = "return keypress(event);">                    
                     </TD>
            	 <%}
            	 else{%>                                        
                    <TD class="plainlabel">S??? ti???n h??a ????n (VN??) </TD>
                     <TD class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="sotienHDVND" id = "sotienHDVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getSotienHD().replaceAll(",",""))) %>" readonly  > 
                     </TD>
             	<%}%>

                     
                </TR>
                <TR style="display:none;">
					<TD class="plainlabel" style="display:none;" >T??i kho???n tr??ch ph?? (VND) </TD>				
					<TD class="plainlabel" colspan = 5 style="display:none;">
                     
                        <select name="sotaikhoan_tp" id="sotaikhoan_tp" style="width: 500px" >
                        	
                        	<%
                        		if(sotkRs_tp != null)
                        		{
                        			try
                        			{
                        			while(sotkRs_tp.next())
                        			{  
                        			if( sotkRs_tp.getString("SOTAIKHOAN").equals(tthdBean.getSotaikhoan_tp())){ %>
                        				<option value="<%= sotkRs_tp.getString("SOTAIKHOAN") %>" selected="selected" ><%= sotkRs_tp.getString("TAIKHOAN")%></option>
                        			<%}else { %>
                        				<option value="<%= sotkRs_tp.getString("SOTAIKHOAN") %>" ><%= sotkRs_tp.getString("TAIKHOAN") %></option>
                        		 <% } } sotkRs_tp.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>

                    </TD>
					
                </TR>                
				<TR>
                    <TD class="plainlabel" style = "width:150px;display:none">Ph?? ng??n h??ng (Ngo???i t???)</TD>
                    <TD  class="plainlabel" style = "display:none">
                         <input type="text" style="text-align: right;display:none" name="pnganhangNT" id = "pnganhangNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getPhinganhangNT().replaceAll(",",""))) %>" readonly  > 
                     </TD> 
				
					<TD class="plainlabel" style = "width:150px">Ph?? ng??n h??ng (VN??)</TD>
                    <TD  class="plainlabel" colspan = "3">                 
                         <input type="text" style="text-align: right;" name="pnganhangVND" id = "pnganhangVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getPhinganhang().replaceAll(",",""))) %>" onchange="ThanhToan(100)" onKeyPress = "return keypress(event);"  > 
                     </TD>
				
				</TR>
            	<TR>
                    <TD class="plainlabel" style = "display:none">Thu??? VAT (Ngo???i t???) </TD>
                    <TD  class="plainlabel" style = "display:none">
                         <input type="text" style="text-align: right;display:none" name="vatNT" id = "vatNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getThueVATNT().replaceAll(",",""))) %>" readonly  > 
                     </TD> 
					
				<% //Ri??ng ?????i t?????ng chi ph??: kh??c th?? cho 10 d??ng %>
				<%if(tthdBean.getDoiTuongChiPhiKhac().equals("3")){ %>
                    <TD class="plainlabel" >Thu??? VAT (VND) </TD>
					<TD align = "left" class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;width:181px" name="vatVND" id = "vatVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getThueVAT().replaceAll(",",""))) %>"  onKeyPress = "return keypress(event);" readonly="readonly"  >
                     </TD> 
				<%} else  { %>
					 <TD class="plainlabel" >Thu??? VAT (VND) </TD>
					<TD align = "left" class="plainlabel" colspan = 3>
	                   <A href="" id="tinhVAT" rel="subcontentVAT"><img alt="T??nh VAT" title="T??nh VAT" src="../images/vitriluu.png"></a>
	                   <DIV id="subcontentVAT" style="position:absolute; visibility: hidden; border: 2px solid #80CB9B; background-color: white; width: 900px; padding: 4px; max-height: 300px; overflow: auto; ">
							<TABLE width="100%" align="center">
        						<TR class="tbheader">
						        	<TH width="80px" align = "center">M?? h??a ????n</TH>
						        	<TH width="80px" align = "center">M???u h??a ????n</TH>
						        	<TH width="80px" align = "center">K?? hi???u</TH>
						         	<TH width="100px" align = "center">S??? h??a ????n</TH>
						         	<TH width="100px" align = "center">Ng??y h??a ????n </TH>
									<TH width="100px" align = "center">T??n NCC </TH>
									<TH width="100px" align = "center">MST </TH>	
									<TH width="100px" align = "center">Ti???n h??ng </TH>
									<TH width="100px" align = "center">Thu??? su???t(%) </TH>
									<TH width="100px" align = "center">Ti???n thu??? </TH>
								</TR>
					      		<TR>
									<TD>
										<input type="text" name = "maHD_VAT" id = "maHD_VAT" value = "<%= tthdBean.getMahoadon() %>" style="width: 100%" readonly="readonly" >
									</TD>
									<TD>
										<input type="text" name = "mauHD_VAT" id = "mauHD_VAT" value = "<%= tthdBean.getMauhoadon() %>" onChange = "javascript:mauhoadon();" style="width: 100%" >
									</TD>
									<TD>
										<input type="text" name = "kyhieu_VAT" id = "kyhieu_VAT" value = "<%= tthdBean.getKyhieu() %>" style="width: 100%" >
									</TD>
											      		
									<TD>
										<input type="text" name = "sohd_VAT" id = "sohd_VAT" value = "<%= tthdBean.getSohoadon() %>" style="width: 100%" >
									</TD>

									<TD>
										<input type="text" name = "ngayhd_VAT"  value = "<%= tthdBean.getNgayhoadon() %>" style="width: 100%" class="days" readonly="readonly">
									</TD>
								
									<TD>
										<input type="hidden" name = "nhId_VAT" id = "nhId_VAT" value = "<%= tthdBean.getNhId_VAT() %>" style="width: 100%"  >
										<input type="hidden" name = "cnId_VAT" id = "cnId_VAT" value = "<%= tthdBean.getCnId_VAT() %>" style="width: 100%"  >
										
										<input type="text" name = "nghangTen" id = "nghangTen" value = "<%= tthdBean.getTenNCC_VAT() %>" style="width: 100%" onChange ="ChangeNganHang();" >
									</TD>

									<TD>
										<input type="text" name= "mst_VAT" id= "mst_VAT" value = "<%= tthdBean.getMST() %>" style="width: 100%"  readonly="readonly">
									</TD>

									<TD>
										<input type="text" name= "tienhang_VAT" id= "tienhang_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getTienhang().replaceAll(",",""))) %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthue();" onKeyPress = "return keypress(event);">
									</TD>

									<TD>
										<input type="text" name= "thuesuat_VAT" id= "thuesuat_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getThuesuat().replaceAll(",",""))) %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthue();" onKeyPress = "return keypress(event);">
									</TD>

									<TD>
										<input type="text" name= "tienthue_VAT" id= "tienthue_VAT" value = "<%= formatter2.format(Double.parseDouble(tthdBean.getTienthue().replaceAll(",",""))) %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthuesuat();" onKeyPress = "return keypress(event);">
									</TD>
								</TR>
							</TABLE>
						    <DIV align="right">
					             <LABEL style="color: red" ></LABEL>
					               		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	              	<a href="javascript:dropdowncontent.hidediv('subcontentVAT')">Ho??n t???t</a>
							</DIV>
						     				
    					</DIV>                  
                         <input type="text" style="text-align: right;width:181px" name="vatVND" id = "vatVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getThueVAT().replaceAll(",",""))) %>" onchange="ThanhToan(100)" onKeyPress = "return keypress(event);"  >
                     </TD> 
				<%} %>
                </TR>
            	<TR>
                    <TD class="plainlabel" style = "width:150px;display:none">T???ng ti???n thanh to??n (Ngo???i t???)</TD>
                    <TD class="plainlabel" style = "display:none">
                         <input type="text" style="text-align: right;display:none" name="tongtienNT" id = "tongtienNT" value="<%= formatter1.format(Double.parseDouble(tthdBean.getSotienttNT().replaceAll(",",""))) %>" onKeyPress = "return keypress(event);" >
                     </TD> 
					
					<% if(tthdBean.getDoiTuongChiPhiKhac().equals("3")){  %>
                    <TD class="plainlabel" style = "width:150px">T???ng ti???n thanh to??n (VN??)</TD>
                    <TD class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="tongtienVND" id = "tongtienVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getSotientt().replaceAll(",",""))) %>" onKeyPress = "return keypress(event);" readonly="readonly" > 
                     </TD> 
                     <%} else { %>
                      <TD class="plainlabel" style = "width:150px">T???ng ti???n thanh to??n (VN??)</TD>
                    <TD class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="tongtienVND" id = "tongtienVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getSotientt().replaceAll(",",""))) %>" onKeyPress = "return keypress(event);" onChange = "ThaydoiSotienTT();" > 
                     </TD> 
                     <%} %>
                </TR>			

            	<TR>

                    <TD class="plainlabel">N???i dung thanh to??n </TD>
                    <TD  class="plainlabel" >
                        <input type="text" name="noidungthanhtoan" value="<%= tthdBean.getNoidungtt() %>" > 
                     </TD> 

					 <%if(tthdBean.getLoaiThanhToan().equals("1")|| tthdBean.getLoaiThanhToan().equals("2") || tthdBean.getLoaiThanhToan().equals("4")|| tthdBean.getLoaiThanhToan().equals("5")) {%>
                    <TD align = "right" class="plainlabel" style = "width:200px">Ch??nh l???ch (VN??)</TD>
                    <TD class="plainlabel" colspan = 3>
                         <input type="text" style="text-align: right;" name="chenhlechVND" id = "chenhlechVND" value="<%= formatter2.format(Double.parseDouble(tthdBean.getChenhlech().replaceAll(",",""))) %>" readonly > 
                     </TD> 
  					<%} else{%>	
						<TD class="plainlabel" colspan = 3></TD>
					<%} %>
                </TR>
			
			<%} %>
				
				<TR>
            		<TD class="plainlabel">Ch???ng t??? k??m theo</TD>
                    <TD class="plainlabel" colspan = "5">                   
                        <input type="text" name="ctkemtheo" value="<%= tthdBean.getChungtukemtheo() %>" style = "width:300px"> 
                    </TD> 
            	</TR>

            </TABLE>

            
            </div>                   
           
           	<% if(!tthdBean.getDoiTuongChiPhiKhac().equals("3") && !tthdBean.getDoiTuongChiPhiKhac().equals("4") ){  // ?????I T?????NG NCC/NV/KH/BP %>
           	<hr> 
           	<div align="left" style="width:100%; float:none; clear:none;" class="plainlabel">
            <TABLE class="tabledetail" width="100%" border="0" cellpadding="1" cellspacing="1" >
	        <% if(tthdBean.getTienteId().equals("100000")){ // Don vi tien la VND %>
                <TR class="tbheader"> 
                	<!-- <TH align="center" width="10%">Lo???i h??a ????n</TH> -->
                <!-- 	<TH align="center" width="10%">S??? h???p ?????ng n???i</TH>
                	<TH align="center" width="10%">S??? h???p ?????ng ngo???i</TH> -->
                	<!-- <TH align="center" width="8%">K?? hi???u H??</TH> -->
                	<TH align="center" width="8%">S??? H??</TH>
                	<TH align="center" width="8%">Ng??y H??</TH>
                <%if(tthdBean.getDoiTuongChiPhiKhac().equals("1")) { %>	
                	<TH align="center" width="8%">Ng??y ?????n han TT</TH>
                <%} %>
               		<TH align="center" width="10%">S??? ti???n g???c H??</TH>
                	<TH align="center" width="10%">S??? d?? H?? (???? c?? VAT)</TH>
               	 	<TH align="center" width="10%">Thanh to??n (VN??)</TH>
               	 	<TH align="center" width="10%">C??n l???i</TH>
               	 	<TH align="center" width="5%">Tr??? h???t</TH>
                </TR>

            <%}else{ // NGO???I T??? %>
                <TR class="tbheader"> 
               	   <!--  <TH align="center" width="10%">Lo???i h??a ????n</TH> -->
              <!--  	   <TH align="center" width="10%">S??? h???p ?????ng n???i</TH>
               	   <TH align="center" width="10%">S??? h???p ?????ng ngo???i</TH> -->
                	<!-- <TH align="center" width="8%">K?? hi???u H??</TH> -->
                	<TH align="center" width="8%">S??? H??</TH>
                	<TH align="center" width="8%">Ng??y H??</TH>
                <%if(tthdBean.getDoiTuongChiPhiKhac().equals("1")) { %>	
                	<TH align="center" width="8%">Ng??y ?????n han TT</TH>
                <%} %>
                	<TH align="center" width="10%">S??? ti???n g???c H??</TH>	
                	<TH align="center" width="10%">S??? d?? H?? (VN??)</TH>
                	<TH align="center" width="10%">S??? d?? H?? (Ngo???i t???)</TH>
               	 	<TH align="center" width="10%">Thanh to??n (Ngo???i t???)</TH>
               	 	<TH align="center" width="10%">C??n l???i</TH>
               	 	<TH align="center" width="10%">T??? gi??</TH>
               	 	<TH align="center" width="5%">Tr??? h???t</TH>
                </TR>
			<%} %>
                <%
                	for(int i = 0; i < hoadonList.size(); i++)
                	{
                		IHoadon hoadon = hoadonList.get(i);
	               		%>
	               		<tr>
		                	<Td align="center" >
		                	    <input type="hidden" style="width: 100%;" value="<%= hoadon.getId() %>" name= "idHd" readonly="readonly" >
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getTienteId() %>" name= "ttId" readonly="readonly" >
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getLoaihd1() %>" name= "loaihdId" readonly="readonly" >
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getTenloaihd1() %>" name= "tenloaihd" readonly="readonly" >
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getSohopdong() %>" name= "sohopdong" readonly="readonly" >
        	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getKyhieu() %>" name= "kyhieuhd" readonly="readonly" >
        	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getSohopdongNGOAI() %>" name= "sohopdongngoai" readonly="readonly" >
		                		<input type="text" style="width: 100%;" value="<%= hoadon.getSo() %>" name= "sohd" readonly="readonly" >
		                	</Td>
		                	<td align="center">
           	 					<input type="text" style="width: 100%; text-align: center;" value="<%= hoadon.getNgay() %>" name= "ngayhd" readonly="readonly" >
           	 				</td>
           	 										
						  <%if(tthdBean.getDoiTuongChiPhiKhac().equals("1")) { %>	                
						    <td align="center">
           	 					<input type="text" style="width: 100%; text-align: center;" value="<%= hoadon.getNgaydenhanTT() %>" name= "ngayhd" readonly="readonly" >
           	 				</td>
           	 			<%} %>						
						
							<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= formatter2.format(Double.parseDouble(hoadon.getSoTienGoc2().replaceAll(",",""))) %>" name= "sotiengoc" id="sotiengoc<%= i %>" readonly="readonly" >
           	 				</td>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= formatter2.format(Double.parseDouble(hoadon.getTongtiencoVAT().replaceAll(",",""))) %>" name= "avat" id="avat<%= i %>" readonly="readonly" >
           	 				</td>
           	 				
           	 			<% if(!tthdBean.getTienteId().equals("100000")){ // Ngo???i t???%>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= formatter1.format(Double.parseDouble(hoadon.getSotienNT().replaceAll(",",""))) %>" name= "sotienNT" id="sotienNT<%= i %>" readonly="readonly" >
           	 				</td>
           	 			
           	 			<%} %>
          	 				
           	 				<td align="center">			
           	 					<%if(hoadon.getLoaihd1().equals("6")){ %>
           	 						<input type="text" style="width: 100%; text-align: right;" readonly="readonly"  value="<%= formatter1.format(Double.parseDouble(hoadon.getThanhtoan().replaceAll(",",""))) %>" name= "thanhtoan" id="thanhtoan<%= i %>"  onchange="ThanhToan(100)" onKeyPress = "return keypress(event);">
           	 					<%} else { %>
           	 						<input type="text" style="width: 100%; text-align: right;" value="<%= formatter1.format(Double.parseDouble(hoadon.getThanhtoan().replaceAll(",",""))) %>" name= "thanhtoan" id="thanhtoan<%= i %>"  onchange="ThanhToan(100)" onKeyPress = "return keypress(event);">
           	 					<%} %>
           	 				</td>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= formatter1.format(Double.parseDouble(hoadon.getConlai().replaceAll(",",""))) %>" name= "conlai" id="conlai<%= i %>" readonly="readonly" >
           	 				</td>
						
						<% if(!tthdBean.getTienteId().equals("100000")){ // Ngo???i t???%>           	 				
           	 				<td align="center">
           	 					<input type="text" style="width: 100%;text-align: right;" value="<%= formatter1.format(Double.parseDouble(hoadon.getTigia().replaceAll(",",""))) %>" name= "tigiaHD" id="tigiaHD<%= i %>" readonly="readonly" >
           	 				</td>
           	 			<%} %>
           	 			
           	 				<td align="center">
           	 				<% 	if(hoadon.getConlai().equals("0")){ %>
           	 					<input type="checkbox" value="<%= hoadon.getId() %>" name = "trahet" id="trahet<%= i %>" checked="checked" onchange="ThanhToan(<%= i %>)" >
           	 				<%} else { %>
           	 					<input type="checkbox"  value="<%= hoadon.getId() %>" name = "trahet" id="trahet<%= i %>" onchange="ThanhToan(<%= i %>)" >
           	 				<%} %>
           	 				</td>
           	 			</tr>
           	 	<%} %>
           	 	
            </TABLE> 
        	</div> 
        	<%} else if(tthdBean.getDoiTuongChiPhiKhac().equals("4") ){  // ?????I T?????NG BP %>
           	<hr> 
           	<div align="left" style="width:100%; float:none; clear:none;" class="plainlabel">
            <TABLE class="tabledetail" width="100%" border="0" cellpadding="1" cellspacing="1" >
	        <% if(tthdBean.getTienteId().equals("100000")){ // Don vi tien la VND %>
                <TR class="tbheader"> 
                	<TH align="center" width="8%">?????i t?????ng</TH>
                	<TH align="center" width="8%">M?? ?????i t?????ng</TH>
                	<TH align="center" width="8%">K?? hi???u H??</TH>
                	<TH align="center" width="8%">S??? H??</TH>
                	<TH align="center" width="8%">Ng??y H??</TH>
                	<TH align="center" width="10%">S??? ti???n g???c H??</TH>
                	<TH align="center" width="10%">S??? d?? H?? (???? c?? VAT)</TH>
               	 	<TH align="center" width="10%">Thanh to??n (VN??)</TH>
               	 	<TH align="center" width="10%">C??n l???i</TH>
               	 	<TH align="center" width="5%">Tr??? h???t</TH>
                </TR>

            <%}else{ // NGO???I T??? %>
                <TR class="tbheader"> 
               	    <TH align="center" width="8%">?????i t?????ng</TH>
               	    <TH align="center" width="8%">M?? ?????i t?????ng</TH>
                	<TH align="center" width="8%">K?? hi???u H??</TH>
                	<TH align="center" width="8%">S??? H??</TH>
                	<TH align="center" width="8%">Ng??y H??</TH>
                	<TH align="center" width="10%">S??? ti???n g???c H??</TH>	
                	<TH align="center" width="10%">S??? d?? H?? (VN??)</TH>
                	<TH align="center" width="10%">S??? d?? H?? (Ngo???i t???)</TH>
               	 	<TH align="center" width="10%">Thanh to??n (Ngo???i t???)</TH>
               	 	<TH align="center" width="10%">C??n l???i</TH>
               	 	<TH align="center" width="10%">T??? gi??</TH>
               	 	<TH align="center" width="5%">Tr??? h???t</TH>
                </TR>
			<%} %>
                <%
                	for(int i = 0; i < hoadonList.size(); i++)
                	{
                		IHoadon hoadon = hoadonList.get(i);
	               		%>
	               		<tr>
           	 				<td align="center">
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getId() %>" name= "idHd" readonly="readonly" >
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getTienteId() %>" name= "ttId" readonly="readonly" >
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getLoaihd1() %>" name= "loaihdId" readonly="readonly" >
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getDoituong() %>" name= "doituong" readonly="readonly" >
           	 					<%if(hoadon.getDoituong().equals("0")){ %>
           	 					<input type="text" style="width: 100%;" value="Nh??n vi??n" name= "doituongten" readonly="readonly" >
           	 					<%} else {%>
           	 					<input type="text" style="width: 100%;" value="Nh?? cung c???p" name= "doituongten" readonly="readonly" >
           	 					<%} %>
           	 				</td>
							
							<td align="center">
							    <input type="hidden" style="width: 100%;" value="<%= hoadon.getDoituongId() %>" name= "doituongId" readonly="readonly" >
        	 					<input type="text" style="width: 100%;" value="<%= hoadon.getMadoituong() %>" name= "madoituong" readonly="readonly" >
           	 				</td>
							<td align="center">
        	 					<input type="text" style="width: 100%;" value="<%= hoadon.getKyhieu() %>" name= "kyhieuhd" readonly="readonly" >
           	 				</td>
							
		                	<Td align="center" >
		                		<input type="text" style="width: 100%;" value="<%= hoadon.getSo() %>" name= "sohd" readonly="readonly" >
		                	</Td>
		                	<td align="center">
           	 					<input type="text" style="width: 100%; text-align: center;" value="<%= hoadon.getNgay() %>" name= "ngayhd" readonly="readonly" >
           	 				</td>
           	 														
							<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= formatter2.format(Double.parseDouble(hoadon.getSoTienGoc2().replaceAll(",",""))) %>" name= "sotiengoc" id="sotiengoc<%= i %>" readonly="readonly" >
           	 				</td>
           	 				
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= formatter2.format(Double.parseDouble(hoadon.getTongtiencoVAT().replaceAll(",",""))) %>" name= "avat" id="avat<%= i %>" readonly="readonly" >
           	 				</td>
           	 				
           	 			<% if(!tthdBean.getTienteId().equals("100000")){ // Ngo???i t???%>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= formatter1.format(Double.parseDouble(hoadon.getSotienNT().replaceAll(",",""))) %>" name= "sotienNT" id="sotienNT<%= i %>" readonly="readonly" >
           	 				</td>
           	 			
           	 			<%} %>
          	 				
           	 				<td align="center">						
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= formatter1.format(Double.parseDouble(hoadon.getThanhtoan().replaceAll(",",""))) %>" name= "thanhtoan" id="thanhtoan<%= i %>"  onchange="ThanhToan(100)" onKeyPress = "return keypress(event);">
           	 				</td>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= formatter1.format(Double.parseDouble(hoadon.getConlai().replaceAll(",",""))) %>" name= "conlai" id="conlai<%= i %>" readonly="readonly" >
           	 				</td>
						
						<% if(!tthdBean.getTienteId().equals("100000")){ // Ngo???i t???%>           	 				
           	 				<td align="center">
           	 					<input type="text" style="width: 100%;text-align: right;" value="<%= formatter1.format(Double.parseDouble(hoadon.getTigia().replaceAll(",",""))) %>" name= "tigiaHD" id="tigiaHD<%= i %>" readonly="readonly" >
           	 				</td>
           	 			<%} %>
           	 			
           	 				<td align="center">
           	 				<% 	if(hoadon.getConlai().equals("0")){ %>
           	 					<input type="checkbox" value="<%= hoadon.getId() %>" name = "trahet" id="trahet<%= i %>" checked="checked" onchange="ThanhToan(<%= i %>)" >
           	 				<%} else { %>
           	 					<input type="checkbox"  value="<%= hoadon.getId() %>" name = "trahet" id="trahet<%= i %>" onchange="ThanhToan(<%= i %>)" >
           	 				<%} %>
           	 				</td>
           	 			</tr>
           	 	<%} %>
           	 	
            </TABLE> 
        	</div> 
        	<%} else if(tthdBean.getDoiTuongChiPhiKhac().equals("3")){  %>                  
			<TABLE width="2000px" align="center" border="0" cellpadding="1" cellspacing="1" >
				
			<tr class="tbheader">
				<th align="center" width = "150px">T??i kho???n</th>
				<th align="center" width = "150px">?????i t?????ng </th>
				<th align="center" width = "150px">Ph??ng ban </th>
				<th align="center" width = "80px">K??nh BH </th>
				<th align="center" width = "150px">M?? v??? vi???c</th>
				<th align="center" width = "150px">?????a b??n</th>
				<th align="center" width = "100px">T???nh th??nh</th>
				<th align="center" width = "100px">B???nh vi???n</th>
				<th align="center" width = "150px"><%=Utility.GLanguage("S???n ph???m",session,jedis) %> </th>
				<TH align="center" width = "100px">K?? hi???u</TH>	
				<TH align="center" width = "100px">S??? h??a ????n</TH>
	         	<TH width = "150px" align = "center">Ng??y h??a ????n </TH>
				<TH width = "150px" align = "center">T??n NCC </TH>
				<TH width = "150px" align = "center">MST </TH>	
				<TH width = "150px" align = "center"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TH>
				<TH width = "150px" align = "center">Ti???n h??ng </TH>
				<TH width = "150px" align = "center">Thu??? su???t(%) </TH>
				<TH width = "150px" align = "center">Ti???n thu??? </TH>
			</tr>
		
		<% 	
		int stt = count;						   	
		for(int i=0; i < count;i++){ 
			stt++;
			if(TaiKhoanId[i].length() >0) { %>
			<TR>
				<!-- T??I KHO???N  -->
            		<TD  align="left" >  
                		<select name="TaiKhoanId" id="TaiKhoanId_<%=i %>" onChange = "submitform_HTTT();" style="width: 150px;">
							<option value=""></option>
                		<%			                       		
						
							rstkkt.beforeFirst();
                    		while (rstkkt.next())
                    		{  
                    			if(TaiKhoanId[i].indexOf(rstkkt.getString("pk_seq"))>=0 ){%>		
                    				             				
									<option  selected="selected" value="<%=rstkkt.getString("pk_seq")%>_<%=rstkkt.getString("COTTCHIPHI")%>"> <%=rstkkt.getString("ma") %> -- <%=rstkkt.getString("ten") %> </option>
									
								<%}else{ %>
									<option value="<%=rstkkt.getString("pk_seq")%>_<%=rstkkt.getString("COTTCHIPHI")%>"> <%=rstkkt.getString("ma") %> -- <%=rstkkt.getString("ten") %> </option>
								<%} %>
                		  <%  } %> 
						 </select>									 
					</TD>
					
				<!-- ?????I T?????NG  -->						 
            	<TD align="center"  >                       		
            		<select  name="dcIds_<%=i %>" id="dcIds_<%=i %>" style="width: 150px;">
			   			<option   value="">&nbsp;</option>
						<%		
						  String selected = "";
						  dcRs = tthdBean.getDoituongRs(TaiKhoanId[i]);
						  if(dcRs != null){
								while(dcRs.next()){ 
									if(dcRs.getString("PK_SEQ").equals(dcIds[i]) && dcRs.getString("LOAI").equals(loais[i])){
										selected = "selected";
									}else{
										selected = "";	
									}
								%>											
								<option   <%=selected %> value="<%=dcRs.getString("PK_SEQ")%>_<%=dcRs.getString("LOAI")%>"> <%=dcRs.getString("MA") %> --<%= dcRs.getString("TEN") %> </option>															   			
					   
		     				<%		}
								dcRs.close();
		     				} 	%>
					</select>						
				</TD>	
				
				<!--PH??NG BAN  -->
				<TD  align="left" >  
        			<select name="PhongbanId" id="PhongbanId_<%=i %>" style="width: 150px;">
						<option value=""></option>
            			<%	

						PhongBanList.beforeFirst();
                		while (PhongBanList.next())
                		{  
							if(!PhongBanList.getString("PK_SEQ").trim().equals(PhongbanId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=PhongBanList.getString("pk_seq")%>"> <%=PhongBanList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				 
			  </TD>
			  
			  <!-- K??NH B??N H??NG  -->
				<TD  align="left" >  
        			<select name="KenhId" id="KenhId_<%=i %>" style="width: 80px;">
						<option value=""></option>
            			<%	

						KenhBhList.beforeFirst();
                		while (KenhBhList.next())
                		{  
							if(!KenhBhList.getString("PK_SEQ").trim().equals(kenhBhId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=KenhBhList.getString("pk_seq")%>"> <%=KenhBhList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				</TD>
				
				<!-- M?? V??? VI???C  -->
				<TD  align="left" >  
        			<select name="mavvId" id="mavvId_<%=i %>" style="width: 80px;">
						<option value=""></option>
            			<%	

						MavvList.beforeFirst();
                		while (MavvList.next())
                		{  
							if(!MavvList.getString("PK_SEQ").trim().equals(MavvId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=MavvList.getString("pk_seq")%>"> <%=MavvList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				</TD>
				
				<!-- ?????A B??N  -->
				<TD  align="left" >  
        			<select name="diabanId" id="diabanId_<%=i %>" style="width: 80px;">
						<option value=""></option>
            			<%	

						DiaBanList.beforeFirst();
                		while (DiaBanList.next())
                		{  
							if(!DiaBanList.getString("PK_SEQ").trim().equals(DiaBanId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=DiaBanList.getString("pk_seq")%>"> <%=DiaBanList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				</TD>
				
				 <!-- T???NH TH??NH  -->
				<TD  align="left" >  
        			<select  name="TinhthanhId" id="TinhthanhId_<%=i %>" style="width: 100px;">
						<option value=""></option>
            			<%	

						TinhThanhList.beforeFirst();
                		while (TinhThanhList.next())
                		{  
							if(!TinhThanhList.getString("PK_SEQ").trim().equals(TinhThanhId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=TinhThanhList.getString("pk_seq")%>"> <%=TinhThanhList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				</TD>
				
				 <!-- B???NH VI???N  -->
				<TD  align="left" >  
        			<select  name="BenhvienId" id="BenhvienId_<%=i %>" style="width: 100px;">
						<option value=""></option>
            			<%	

						BenhvienList.beforeFirst();
                		while (BenhvienList.next())
                		{  
							if(!BenhvienList.getString("PK_SEQ").trim().equals(BenhvienId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=BenhvienList.getString("pk_seq")%>"> <%=BenhvienList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				</TD>
				
				 <!-- S???N PH???M  -->
				<TD  align="left" >  
        			<select name="SanphamId" id="SanphamId_<%=i %>" style="width: 150px;">
						<option value=""></option>
            			<%	

						SanPhamList.beforeFirst();
                		while (SanPhamList.next())
                		{  
							if(!SanPhamList.getString("PK_SEQ").trim().equals(SanPhamId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=SanPhamList.getString("pk_seq")%>"> <%=SanPhamList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				</TD>
				
				 <TD>
					<input type="text" name = "kyhieu_VAT_<%=i %>"  value = "<%= Kyhieuhd[i] %>" style="width: 100%" >
				</TD>      
				<TD>
					<input type="text" name = "sohd_VAT_<%=i %>"  value = "<%= Sohd[i] %>" style="width: 100%" >
				</TD>

				<TD>
					<input type="text" name = "ngayhd_VAT_<%=i %>"  value = "<%= Ngayhd[i] %>" style="width: 100%" class="days" readonly="readonly">
				</TD>
			
				<TD>											
					<input type="text" name = "tenNCC_VAT_<%=i %>"  value = "<%= TenNCChd[i] %>" style="width: 100%" >
				</TD>

				<TD>
					<input type="text" name= "mst_VAT_<%=i %>" value = "<%= MSThd[i] %>" style="width: 100%" >
				</TD>
					
				<TD>
						<input type="text" name= "diengiai_VAT_<%=i %>" value = "<%= Diengiaihd[i] %>" style="width: 100%" >
				</TD>
					
				<TD>
					<%if(Tienhanghd[i]!=null && Tienhanghd[i].trim().length()>0) {%>
						<input type="text" name= "tienhang_VAT_<%=i %>" id= "tienhang_VAT_<%=i %>" value = "<%= formatter2.format(Double.parseDouble(Tienhanghd[i].replaceAll(",",""))) %>" style="width: 100%;text-align: right" onkeyup = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} else { %>
						<input type="text" name= "tienhang_VAT_<%=i %>" id= "tienhang_VAT_<%=i %>" value = "<%= Tienhanghd[i] %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} %>
				</TD>

				<TD>
					<%if(Thuesuathd[i]!=null && Thuesuathd[i].trim().length()>0) {%>								
						<input type="text" name= "thuesuat_VAT_<%=i %>"  id = "thuesuat_VAT_<%=i %>"  value = "<%= formatter2.format(Double.parseDouble(Thuesuathd[i].replaceAll(",",""))) %>" style="width: 100%;text-align: right" onkeyup = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} else { %>
						<input type="text" name= "thuesuat_VAT_<%=i %>"  id = "thuesuat_VAT_<%=i %>" value = "<%= Thuesuathd[i] %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} %>
				</TD>

				<TD>
					<%if(Tienthuehd[i]!=null && Tienthuehd[i].trim().length()>0) {%>	
						<input type="text" name= "tienthue_VAT_<%=i %>"  id= "tienthue_VAT_<%=i %>" value = "<%= formatter2.format(Double.parseDouble(Tienthuehd[i].replaceAll(",",""))) %>" style="width: 100%;text-align: right" onKeyPress = "return keypress(event);">
					<%} else { %>
						<input type="text" name= "tienthue_VAT_<%=i %>" id= "tienthue_VAT_<%=i %>"  value = "<%= Tienthuehd[i] %>" style="width: 100%;text-align: right" onKeyPress = "return keypress(event);">
					<%} %>
				</TD>  		                    
			</TR>
     <% }
		else
		{ %>
			
			<TR>
			<%
        	 
        	%>
        		<TD  align="left" >
				<select style="width: 150px;"   name="TaiKhoanId" id="TaiKhoanId_<%=i %>" onChange = "submitform_HTTT();" >
					<option value=""></option>
        		<%	                        		
				
				rstkkt.beforeFirst();
        		while (rstkkt.next())
        		{  

        		%>
					<option style="text-align: left" value="<%=rstkkt.getString("pk_seq")%>_<%=rstkkt.getString("COTTCHIPHI")%>"> <%=rstkkt.getString("ma") %> -- <%=rstkkt.getString("ten") %> </option>
					
        	<%  } %> 
				 </select>								 
				 </TD>
				 
				  <TD align = "center">
					<select style="width: 150px;" name="dcIds_<%=i %>" id="dcIds_<%=i %>" >
						<option   value="">&nbsp;</option>
					</select> 
        		 </TD>
        		 
        		  <!--PH??NG BAN  -->
				<TD  align="left" >  
        			<select style="width: 150px;" name="PhongbanId" id="PhongbanId_<%=i %>"  >
					<option value=""></option>
        			<%	

					PhongBanList.beforeFirst();
        			String selected = "";
            		while (PhongBanList.next())
            		{  
						if(!PhongBanList.getString("PK_SEQ").trim().equals(PhongbanId[i].trim())){
							selected = "";
						}else{
							selected = "selected";	
						}
						
            			%>
							<option <%=selected %> value="<%=PhongBanList.getString("pk_seq")%>"> <%=PhongBanList.getString("ten") %> </option>											
						<% %>
        		  <%  } %> 
				 </select>
				 
				</TD>
				
				 <!-- K??NH B??N H??NG  -->
				<TD  align="left" >  
        			<select style="width: 80px;" name="KenhId" id="KenhId_<%=i %>"  >
					<option value=""></option>
        			<%	

					KenhBhList.beforeFirst();
            		while (KenhBhList.next())
            		{  
						if(!KenhBhList.getString("PK_SEQ").trim().equals(kenhBhId[i].trim())){
							selected = "";
						}else{
							selected = "selected";	
						}
						
            			%>
							<option <%=selected %> value="<%=KenhBhList.getString("pk_seq")%>"> <%=KenhBhList.getString("ten") %> </option>											
						<% %>
        		  <%  } %> 
				 </select>
				 
			   </TD>
			   
			   <!-- M?? V??? VI???C  -->
				<TD  align="left" >  
        			<select name="mavvId" id="mavvId_<%=i %>" style="width: 80px;">
						<option value=""></option>
            			<%	

						MavvList.beforeFirst();
                		while (MavvList.next())
                		{  
							if(!MavvList.getString("PK_SEQ").trim().equals(MavvId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=MavvList.getString("pk_seq")%>"> <%=MavvList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				</TD>
				
				<!-- ?????A B??N  -->
				<TD  align="left" >  
        			<select name="diabanId" id="diabanId_<%=i %>" style="width: 80px;">
						<option value=""></option>
            			<%	

						DiaBanList.beforeFirst();
                		while (DiaBanList.next())
                		{  
							if(!DiaBanList.getString("PK_SEQ").trim().equals(DiaBanId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=DiaBanList.getString("pk_seq")%>"> <%=DiaBanList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				</TD>
				
			    <!-- T???NH TH??NH  -->
				<TD  align="left" >  
        			<select style="width: 100px;" name="TinhthanhId" id="TinhthanhId_<%=i %>"  >
					<option value=""></option>
        			<%	

					TinhThanhList.beforeFirst();
            		while (TinhThanhList.next())
            		{  
						if(!TinhThanhList.getString("PK_SEQ").trim().equals(TinhThanhId[i].trim())){
							selected = "";
						}else{
							selected = "selected";	
						}
						
            			%>
							<option <%=selected %> value="<%=TinhThanhList.getString("pk_seq")%>"> <%=TinhThanhList.getString("ten") %> </option>											
						<% %>
        		  <%  } %> 
				 </select>
			  </TD>
				
				 <!-- B???NH VI???N  -->
				<TD  align="left" >  
        			<select  name="BenhvienId" id="BenhvienId_<%=i %>" style="width: 100px;">
						<option value=""></option>
            			<%	

						BenhvienList.beforeFirst();
                		while (BenhvienList.next())
                		{  
							if(!BenhvienList.getString("PK_SEQ").trim().equals(BenhvienId[i].trim())){
								selected = "";
							}else{
								selected = "selected";	
							}
							
                			%>
								<option <%=selected %> value="<%=BenhvienList.getString("pk_seq")%>"> <%=BenhvienList.getString("ten") %> </option>											
							<% %>
            		  <%  } %> 
				 </select>
				</TD>
				
			   
			  
			   <!-- S???N PH???M  -->
				<TD  align="left" >  
        			<select style="width: 150px;" name="SanphamId" id="SanphamId_<%=i %>"  >
					<option value=""></option>
        			<%	

        			SanPhamList.beforeFirst();
            		while (SanPhamList.next())
            		{  
						if(!SanPhamList.getString("PK_SEQ").trim().equals(SanPhamId[i].trim())){
							selected = "";
						}else{
							selected = "selected";	
						}
						
            			%>
							<option <%=selected %> value="<%=SanPhamList.getString("pk_seq")%>"> <%=SanPhamList.getString("ten") %> </option>											
						<% %>
        		  <%  } %> 
				 </select>
			  </TD>
			  
			  
			   <TD>
					<input type="text" name = "kyhieu_VAT_<%=i %>"  value = "<%= Kyhieuhd[i] %>" style="width: 100%" >
				</TD>      
				<TD>
					<input type="text" name = "sohd_VAT_<%=i %>"  value = "<%= Sohd[i] %>" style="width: 100%">
				</TD>

				<TD>
					<input type="text" name = "ngayhd_VAT_<%=i %>"  value = "<%= Ngayhd[i] %>" style="width: 100%" class="days" readonly="readonly">
				</TD>
			
				<TD>											
					<input type="text" name = "tenNCC_VAT_<%=i %>"  value = "<%= TenNCChd[i] %>" style="width: 100%" >
				</TD>

				<TD>
					<input type="text" name= "mst_VAT_<%=i %>" value = "<%= MSThd[i] %>" style="width: 100%" >
				</TD>
				
				<TD>
					<input type="text" name= "diengiai_VAT_<%=i %>" value = "<%= Diengiaihd[i] %>" style="width: 100%" >
				</TD>

				<TD>
					<%if(Tienhanghd[i]!=null && Tienhanghd[i].trim().length()>0) {%>
						<input type="text" name= "tienhang_VAT_<%=i %>" id= "tienhang_VAT_<%=i %>" value = "<%= formatter2.format(Double.parseDouble(Tienhanghd[i].replaceAll(",",""))) %>" style="width: 100%;text-align: right" onkeyup = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} else { %>
						<input type="text" name= "tienhang_VAT_<%=i %>" id= "tienhang_VAT_<%=i %>"  value = "<%= Tienhanghd[i] %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} %>
				</TD>

				<TD>
					<%if(Thuesuathd[i]!=null && Thuesuathd[i].trim().length()>0) {%>								
						<input type="text" name= "thuesuat_VAT_<%=i %>" id= "thuesuat_VAT_<%=i %>"  value = "<%= formatter2.format(Double.parseDouble(Thuesuathd[i].replaceAll(",",""))) %>" style="width: 100%;text-align: right" onkeyup = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} else { %>
						<input type="text" name= "thuesuat_VAT_<%=i %>" id= "thuesuat_VAT_<%=i %>" value = "<%= Thuesuathd[i] %>" style="width: 100%;text-align: right" onChange = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} %>
				</TD>

				<TD>
					<%if(Tienthuehd[i]!=null && Tienthuehd[i].trim().length()>0) {%>	
						<input type="text" name= "tienthue_VAT_<%=i %>" id= "tienthue_VAT_<%=i %>"  value = "<%= formatter2.format(Double.parseDouble(Tienthuehd[i].replaceAll(",",""))) %>" style="width: 100%;text-align: right" onKeyPress = "return keypress(event);">
					<%} else { %>
						<input type="text" name= "tienthue_VAT_<%=i %>"  id= "tienthue_VAT_<%=i %>" value = "<%= Tienthuehd[i] %>" style="width: 100%;text-align: right" onKeyPress = "return keypress(event);">
					<%} %>
				</TD>  		                    	      		
				                        		                         		
		</TR>
		
	<% 	
	stt++; }
			
	} %>
 <% 
 int bien = stt+1;
 for(int i = stt; i< bien; i++){ %>

		<TR>
		<%
			stt++;
          
        %>
                        	
        		<TD  align="left" style="width: 150px;"  >
				<select style="width: 150px;"  name="TaiKhoanId" id="TaiKhoanId_<%=i %>" onChange = "submitform_HTTT();" >
					<option value=""></option>
        		<%	
				
				rstkkt.beforeFirst();
        		while (rstkkt.next())
        		{  

        		%>
					<option value="<%=rstkkt.getString("pk_seq")%>_<%=rstkkt.getString("COTTCHIPHI")%>"> <%=rstkkt.getString("ma") %> -- <%=rstkkt.getString("ten") %> </option>
					
        	<%  } %> 
				 </select>								 
				 </TD>
				 
				  <TD align = "center">
					<select  style="width: 150px;" name="dcIds_<%=i %>" id="dcIds_<%=i %>" >
						<option   value="">&nbsp;</option>
					</select> 
        		 </TD>
        		 
        		  <!--PH??NG BAN  -->
				<TD  align="left" >  
        			<select  style="width: 150px;" name="PhongbanId" id="PhongbanId_<%=i %>"  >
					<option value=""></option>
        			<%	

					PhongBanList.beforeFirst();
            		while (PhongBanList.next())
            		{  		
            			%>
							<option value="<%=PhongBanList.getString("pk_seq")%>"> <%=PhongBanList.getString("ten") %> </option>											
						
        		  <%  } %> 
				 </select>
				 
				</TD>
				
				
				  <!-- K??NH B??N H??NG  -->
				<TD  align="left" >  
        			<select style="width: 80px;"  name="KenhId" id="KenhId_<%=i %>" >
					<option value=""></option>
        			<%	

					KenhBhList.beforeFirst();
            		while (KenhBhList.next())
            		{  			
            			%>
							<option value="<%=KenhBhList.getString("pk_seq")%>"> <%=KenhBhList.getString("ten") %> </option>											
						
        		  <%  } %> 
				 </select>
				 
				</TD>
				
				<!-- M?? V??? VI???C  -->
				<TD  align="left" >  
        			<select name="mavvId" id="mavvId_<%=i %>" style="width: 80px;">
						<option value=""></option>
            			<%	

						MavvList.beforeFirst();
                		while (MavvList.next())
                		{  			
                			%>
								<option value="<%=MavvList.getString("pk_seq")%>"> <%=MavvList.getString("ten") %> </option>											
							
            		  <%  } %> 
				 </select>
				</TD>
				
				<!-- ?????A B??N  -->
				<TD  align="left" >  
        			<select name="diabanId" id="diabanId_<%=i %>" style="width: 80px;">
						<option value=""></option>
            			<%	

						DiaBanList.beforeFirst();
                		while (DiaBanList.next())
                		{  			
                			%>
								<option value="<%=DiaBanList.getString("pk_seq")%>"> <%=DiaBanList.getString("ten") %> </option>											
							
            		  <%  } %> 
				 </select>
				</TD>
				
			    <!-- T???NH TH??NH  -->
				<TD  align="left" >  
        			<select style="width: 100px;" name="TinhthanhId" id="TinhthanhId_<%=i %>"  >
					<option value=""></option>
        			<%	
						TinhThanhList.beforeFirst();
                		while (TinhThanhList.next())
                		{  			
                			%>
								<option value="<%=TinhThanhList.getString("pk_seq")%>"> <%=TinhThanhList.getString("ten") %> </option>											
							
            		 <%  } %> 
				 </select>
			  </TD>
				
				 <!-- B???NH VI???N  -->
				<TD  align="left" >  
        			<select  name="BenhvienId" id="BenhvienId_<%=i %>" style="width: 100px;">
						<option value=""></option>
            			<%	
						BenhvienList.beforeFirst();
                		while (BenhvienList.next())
                		{  			
                			%>
								<option value="<%=BenhvienList.getString("pk_seq")%>"> <%=BenhvienList.getString("ten") %> </option>											
							
            		 <%  } %> 
				 </select>
				</TD>
				
				 <!-- S???N PH???M  -->
				<TD  align="left" >  
        			<select style="width: 150px;"  name="SanphamId" id="SanphamId_<%=i %>" >
					<option value=""></option>
        			<%	

					SanPhamList.beforeFirst();
            		while (SanPhamList.next())
            		{  
            			%>
							<option value="<%=SanPhamList.getString("pk_seq")%>"> <%=SanPhamList.getString("ten") %> </option>											
						
        		  <%  } %> 
				 </select>								 
				</TD>
				
				
				<TD>
					<input type="text" name = "kyhieu_VAT_<%=i %>"  value = '' style="width: 100%;">
				</TD>      
				<TD>
					<input type="text" name = "sohd_VAT_<%=i %>"  value = ''  style="width: 100%;">
				</TD>

				<TD>
					<input type="text" name = "ngayhd_VAT_<%=i %>"  value = ''  style="width: 100%;" class="days" readonly="readonly">
				</TD>
			
				<TD>											
					<input type="text" name = "tenNCC_VAT_<%=i %>"  value = ''  style="width: 100%;" >
				</TD>

				<TD>
					<input type="text" name= "mst_VAT_<%=i %>" value = ''  style="width: 100%;" >
				</TD>
				
				<TD>
					<input type="text" name= "diengiai_VAT_<%=i %>" value = ''  style="width: 100%;" >
				</TD>

				<TD>
					<%-- <%if(Tienhanghd[i]!=null && Tienhanghd[i].trim().length()>0) {%>
						<input type="text" name= "tienhang_VAT_<%=i %>"  id= "tienhang_VAT_<%=i %>" value = "<%= formatter2.format(Double.parseDouble(Tienhanghd[i].replaceAll(",",""))) %>" style="width: 100%;text-align: right" onkeyup = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} else { %> --%>
						<input type="text" name= "tienhang_VAT_<%=i %>"  id= "tienhang_VAT_<%=i %>"  value = ''  style="width: 100%;text-align: right" onChange = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%-- <%} %> --%>
				</TD>

				<TD>
					<%-- <%if(Thuesuathd[i]!=null && Thuesuathd[i].trim().length()>0) {%>								
						<input type="text" name= "thuesuat_VAT_<%=i %>" id= "thuesuat_VAT_<%=i %>"  value = "<%= formatter2.format(Double.parseDouble(Thuesuathd[i].replaceAll(",",""))) %>" style="width: 100%;text-align: right" onkeyup = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%} else { %> --%>
						<input type="text" name= "thuesuat_VAT_<%=i %>"  id= "thuesuat_VAT_<%=i %>"  value = ''  style="width: 100%;text-align: right" onChange = "javascript:tinhthue_loaikhac_new();" onKeyPress = "return keypress(event);">
					<%-- <%} %> --%>
				</TD>

				<TD>
					<%-- <%if(Tienthuehd[i]!=null && Tienthuehd[i].trim().length()>0) {%>	
						<input type="text" name= "tienthue_VAT_<%=i %>" id= "tienthue_VAT_<%=i %>" value = "<%= formatter2.format(Double.parseDouble(Tienthuehd[i].replaceAll(",",""))) %>" style="width: 100%;text-align: right" onKeyPress = "return keypress(event);">
					<%} else { %> --%>
						<input type="text" name= "tienthue_VAT_<%=i %>" id= "tienthue_VAT_<%=i %>" value = ''  style="width: 100%;text-align: right" onKeyPress = "return keypress(event);">
					<%-- <%} %> --%>
				</TD>  		                         		
	</TR>
	<%
		stt++;                        	 
     %>

<%} %>
</TABLE> 
<%} %>
              
        	
     </fieldset>	
    </div>
</div>
<script type="text/javascript">
	//replaces();
</script>

</form>
<script type="text/javascript">
		jQuery(function()
		{		
			$("#NhanVienId").autocomplete("ErpThanhToanTamUngList.jsp");
			$("#nccId").autocomplete("ErpThanhToanTamUngList.jsp");
			$("#dinhkhoanno").autocomplete("ErpTaiKhoanKeToanList.jsp");
			$("#doituongdinhkhoan").autocomplete("ErpDoiTuongTaiKhoanKeToanList.jsp");
			$("#khId").autocomplete("ErpKhachHangList.jsp");
			
		});	
		
		
		//ChangeNganHang();
		
</script>

<script type="text/javascript">
	jQuery(function()
		{		
			$("#bpTen").autocomplete("Erp_Bophan_TTHD.jsp");
		});	
		
	dropdowncontent.init("tinhVATNT", "left-top", 200, "click");
	dropdowncontent.init("tinhVAT", "right-top", 200, "click");
</script>

</BODY>
</HTML>

<%
	if(nccList != null) nccList.close(); 
	if(nhomnccList != null) nhomnccList.close(); 
	if(khList != null) khList.close(); 
	if(nvList != null) nvList.close(); 
	if(htttList != null) htttList.close(); 
	if(tienteList != null) tienteList.close(); 
	if(nganhangList != null) nganhangList.close();
	if(nganhangList != null) nganhangList.close(); 
	if(chinhanhList != null) chinhanhList.close();
	if(tienteList != null) tienteList.close();
	if(sotkRs != null) sotkRs.close();
	if(sotkRs_tp != null) sotkRs_tp.close();
	if(hoadonList != null) hoadonList.clear();
	
	tthdBean.DBclose();
	%>