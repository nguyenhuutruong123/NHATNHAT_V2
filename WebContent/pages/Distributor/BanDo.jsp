<%@page import="com.cete.dynamicpdf.text.bd"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.bando.IBando" %>
<%@ page  import = "geso.dms.distributor.beans.bando.imp.Bando" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<%	
NumberFormat formatter = new DecimalFormat("#,###,###");
String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IBando bdBean = (IBando)session.getAttribute("obj"); %>
<% ResultSet ddkd = bdBean.getDdkdRs(); %>
<% ResultSet tbh = bdBean.getTbhRs(); %>
<% ResultSet khSelected = bdBean.getKhDaViengThamRs(); %>
<% 
	ResultSet khList = bdBean.getKhChuaViengThamRs();
	String[] latconditon = bdBean.getLatcondition();
	String[] longconditon = bdBean.getLongconditon();
	
	NumberFormat formater = new DecimalFormat("#,###,###");
	String pathAnhDaiDien = getServletContext().getInitParameter("pathhinhJSP")  + "AnhDaiDien/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	
	<script src="//maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyDUs-Mowbr3M3jRoVxy30B9uBAlEFgbKMQ" type="text/javascript"></script>
    
    <script type="text/javascript">
    var root = window.location.protocol + '//' + window.location.hostname;
    /* alert(root); */
    
    var khSi = '<%=bdBean.getKhachHangSi().trim().replaceAll("'","") %>';
    
    var map = null;
    var geocoder = null;
    var center = null;
    
	var khs = new Array();
	var khsSelected = new Array();
	var khLatLons = [];
	var khMarkers = [];
	var vtLatLons = [];
	var vtMarkers = [];
	
	var RANDOM_NUMER = 10; 
	const infowindow = new google.maps.InfoWindow(); // Only one InfoWindow
	function initialize2() 
    {
		
		 <% if(bdBean.getTrungtam().length() > 0 ){ System.out.println("1.Center la: " + bdBean.getTrungtam());  %>
     	center = new google.maps.LatLng(<%= bdBean.getTrungtam() %>)
	     <% } else { %>        	
	     	//center = new GLatLng(10.798804,106.679732);
	     	center =new google.maps.LatLng(10.79912,106.680259);
	     <%} %>
		
		var map = new google.maps.Map(
		        document.getElementById('map_canvas'), {
		          center: center,
		          zoom: 13,
		          mapTypeId: google.maps.MapTypeId.ROADMAP
		      });
		
		   // Create our "tiny" marker icon
        var blueIcon = "../images/maps_human.png";
        
		// Set up our GMarkerOptions object
		markerOptions = { icon:blueIcon };
		
		var ko_doanh_so = "../images/ko_doanh_so.png";
		      
		// Set up our GMarkerOptions object
		marker_ko_ds = { icon:ko_doanh_so };
		

		
		var khSiIcon = "../images/thang_truoc_co.png";
		
		var khSiIcon2 = "../images/khSiCDS.png";
		
		
		
		var blueIcon2 = "../images/blank.png";
	     

		markerOptions2 = { icon:blueIcon2 };
	    
	    var yellowIcon ="../images/vtYellow.png";
		markerOptions3 = { icon: yellowIcon };
		
		
		//L???y data kh??ch h??ng
		<%
		
		if(khList != null)
		{
			int count = 0;
			int img_stt = 1;
			
			while(khList.next())
			{
				
		%>
		
		var __khSi = '<%= khList.getString("khachhangsi") == null ? "0" : khList.getString("khachhangsi").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>';
		
	    khs.push({
	    	ten: '<%= khList.getString("khTen") == null ? " " : khList.getString("khTen").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>', 
	    	sdt: '<%= khList.getString("dienthoai") == null ? " " : khList.getString("dienthoai").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>', 
	    	lat: parseFloat('<%= khList.getString("lat") == null  ? "0" : khList.getString("lat").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>'), 
	    	lon: parseFloat('<%= khList.getString("long") == null  ? "0" : khList.getString("long").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>'),
	    	dsThangTruoc: '<%= khList.getString("doanhsoThangtruoc") == null ? "0" : formater.format(khList.getDouble("doanhsoThangtruoc")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	dsTrongThang: '<%= khList.getString("doanhsoTrongthang") == null ? "0" : formater.format(khList.getDouble("doanhsoTrongthang")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	si: khSi == "1" && __khSi == '1' ? '1' : '0',
	    	diachi: '<%= khList.getString("diachi") == null ? " " : khList.getString("diachi").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>',
	    	ngayMHDauTien: '<%= khList.getString("ngayMHDauTien") == null ? " " : khList.getString("ngayMHDauTien").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>',
	    	ngayMHCuoicung: '<%= khList.getString("ngayMHCuoicung") == null ? " " : khList.getString("ngayMHCuoicung").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>',
	    	dhGANNHAT: '<%= khList.getString("dhGANNHAT") == null ? " " : formater.format(khList.getDouble("dhGANNHAT")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	vtGANNHAT: '<%= khList.getString("vtGANNHAT") == null ? " " : khList.getString("vtGANNHAT").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>',
	    	tbDONHANG: '<%= khList.getString("tbDONHANG") == null ? " " : formater.format(khList.getDouble("tbDONHANG")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	dophuSP: '<%= khList.getString("dophuSP") == null ? " " : formater.format(khList.getDouble("dophuSP")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	tonkhoSP: '<%= khList.getString("tonkhoSP") == null ? " " : formater.format(khList.getDouble("tonkhoSP")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	img_url: '<%= pathAnhDaiDien + khList.getString("anhcuahang")%>'
	    });
		          
			<% count++;  img_stt++;
				if(img_stt > 11)
					img_stt = 1;
			}
		}
		%>
		
		//L???y data vi???ng th??m
		
		<%
		if(khSelected != null)
		{
			khSelected.beforeFirst();
			int pos = 0;
			int img_stt = 1;
			
			while(khSelected.next())
			{
		%>
		khsSelected.push({
	    	ten: '<%= khSelected.getString("khTen") == null ? " " : khSelected.getString("khTen").trim().replaceAll("'", "").replaceAll("\n","").replaceAll("\t","") %>',
	    	ddkd: '<%= khSelected.getString("ddkdTen") == null ? " " : khSelected.getString("ddkdTen").trim().replaceAll("'", "").replaceAll("\n","").replaceAll("\t","") %>',
	    	sdt: '<%= khSelected.getString("dienthoai") == null ? " " : khSelected.getString("dienthoai").trim().replaceAll("'", "").replaceAll("\n","").replaceAll("\t","") %>',
	    	thoidiem: '<%= khSelected.getString("thoidiem") == null ? " " : khSelected.getString("thoidiem").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>', 
	    	lat: parseFloat('<%= khSelected.getString("lat") == null  ? "0" : khSelected.getString("lat").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>'), 
	    	lon: parseFloat('<%= khSelected.getString("long") == null  ? "0" : khSelected.getString("long").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>'),
	    	dsThangTruoc: '<%= khSelected.getString("doanhsoThangtruoc") == null ? "0" : formater.format(khSelected.getDouble("doanhsoThangtruoc")) %>',
	    	dsTrongThang: '<%= khSelected.getString("doanhsoTrongthang") == null ? "0" : formater.format(khSelected.getDouble("doanhsoTrongthang")) %>',
	    	diachi: '<%= khSelected.getString("diachi") == null ? " " : khSelected.getString("diachi").trim().replaceAll("'","") %>',
		    ngayMHDauTien: '<%= khSelected.getString("ngayMHDauTien") == null ? " " : khSelected.getString("ngayMHDauTien").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>',
		    ngayMHCuoicung: '<%= khSelected.getString("ngayMHCuoicung") == null ? " " : khSelected.getString("ngayMHCuoicung").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>',
		    dhGANNHAT: '<%= khSelected.getString("dhGANNHAT") == null ? " " : formater.format(khSelected.getDouble("dhGANNHAT")) %>',
		    vtGANNHAT: '<%= khSelected.getString("vtGANNHAT") == null ? " " : khSelected.getString("vtGANNHAT").trim().replaceAll("'","") %>',
		    tbDONHANG: '<%= khSelected.getString("tbDONHANG") == null ? " " : formater.format(khSelected.getDouble("tbDONHANG")) %>',
		    dophuSP: '<%= khSelected.getString("dophuSP") == null ? " " : formater.format(khSelected.getDouble("dophuSP")) %>',
		    tonkhoSP: '<%= khSelected.getString("tonkhoSP") == null ? " " : formater.format(khSelected.getDouble("tonkhoSP")) %>',
		    img_url: '<%=  pathAnhDaiDien + khSelected.getString("anhcuahang").replaceAll("\n","").replaceAll("\t","")%>',
		    ds: '<%= khSelected.getString("ds") == null ? "0" : formater.format(khSelected.getDouble("ds")) %>'
	    });
		
		<% 		pos++; img_stt++;
				if(img_stt > 11)
					img_stt = 1;
			}
		} 
		%>
		
	
		
		//X??? l??
		var z;
		
		var khLats = [];
		var khLons = [];
		
		//Kh??ch h??ng
		for(z = 0; z < khs.length; z++) {
			var kh = khs[z];
			
			//Ch???nh c??c t???a ????? tr??ng
			var latIndex = khLats.indexOf(kh.lat);
			var lonIndex = khLons.indexOf(kh.lon);
			if( latIndex >= 0 && lonIndex >= 0 && latIndex === lonIndex) {
				var ranLat = (Math.random()*RANDOM_NUMER-RANDOM_NUMER/2)/100000;
				var ranLon = (Math.random()*RANDOM_NUMER-RANDOM_NUMER/2)/100000;
				 //console.log("["+z+"] lat = " + ranLat + "lon = " + ranLon);
				kh.lat += ranLat;
				kh.lon += ranLon;
			}
			khLats.push(kh.lat);
			khLons.push(kh.lon);
			
			var latlon = new google.maps.LatLng(kh.lat, kh.lon);
		
			var marker;
			if(parseFloat(kh.dsTrongThang) > 0) {
				marker = new google.maps.Marker(
						{  map: map,
							position:latlon,
							options : kh.si == '1' ? {icon: khSiIcon2} : markerOptions
						}		
				);
			} else {
				
				marker = new google.maps.Marker(
						{  map: map,
							position:latlon,
							options : kh.si == '1' ? {icon: khSiIcon} : marker_ko_ds
						}	
						
				);
			}
			const mmm = marker;
			const khx =kh;
			 marker.addListener("click", () => {
				 var info = 	"<table style='width: 650px' cellpadding='2' cellspacing='1' > " +
					"	<tr> " +
					"		<td style='width: 140px;font-weight:400;' > " +
					"			<b>H??? t??n: </b> " +
					"		</td> " +
					"		<td style='width: 150px;font-weight:400;' > " +
					"			 " + khx.ten + (khx.si == '1' ? " (KH S???) " : "") +
					"		</td> " +
					"		<td style='width: 110px;font-weight:400;' > " +
					"			<b>?????a ch???: </b> " +
					"		</td> " +
					"		<td style='font-weight:400;'> " +
					"		 " + khx.diachi +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>??i???n tho???i: </b>  " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.sdt +
					"		</td> " +
					"		<td style='font-weight:400;'> " +
					"			<b>T???n kho: </b> " +
					"		</td> " +
					"		<td > " +
					"			" + khx.tonkhoSP + " (KG) " +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>Doanh s??? TB 3 th??ng: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.dsThangTruoc +
					"		</td> " +
					"		<td style='font-weight:400;'> " +
					"			<b>DS t??? ?????u th??ng: </b>  " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.dsTrongThang +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>Ng??y MH ?????u ti??n: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.ngayMHDauTien +
					"		</td> " +
					"		<td style='font-weight:400;' rowspan='6' valign='top' > " +
					"			<b>H??nh ???nh: </b>	" +		
					"		</td> " +
					"		<td rowspan='6' valign='top' > " +
					"			<img src = '" + khx.img_url + "' style='max-height: 150px; max-width: 250px' />	 " +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>Ng??y MH cu???i c??ng: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.ngayMHCuoicung +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>TB ????n h??ng 3 th??ng: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.tbDONHANG +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>????n h??ng g???n nh???t: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.dhGANNHAT +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>L???n VT g???n nh???t: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.vtGANNHAT +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;' > " +
					"			<b>????? ph??? s???n ph???m: </b> " + 
					"		</td> " +
					"		<td > " +
					"			 " + khx.dophuSP +
					"		</td> " +
					"	</tr> " +
				"</table> ";
				
				 infowindow.close(); // Close previously opened infowindow
			        infowindow.setContent(info);
			        infowindow.open(map, mmm);
			});
			
		}
		
		khLats = [];
		khLons = [];
		
		//Vi???ng th??m
		for(z = 0; z < khsSelected.length; z++) {
			var kh = khsSelected[z];
			
			//Ch???nh c??c t???a ????? tr??ng
			var latIndex = khLats.indexOf(kh.lat);
			var lonIndex = khLons.indexOf(kh.lon);
			if( latIndex >= 0 && lonIndex >= 0 && latIndex === lonIndex) {
				var ranLat = (Math.random()*RANDOM_NUMER-RANDOM_NUMER/2)/100000;
				var ranLon = (Math.random()*RANDOM_NUMER-RANDOM_NUMER/2)/100000;
				//console.log("["+z+"] lat = " + ranLat + "lon = " + ranLon);
				kh.lat += ranLat;
				kh.lon += ranLon;
			}
			khLats.push(kh.lat);
			khLons.push(kh.lon);
			
			var latlon = new google.maps.LatLng(kh.lat, kh.lon);      
	        var marker;// = new GMarker(latlon, markerOptions2);

			var marker;
			var _title = kh.ddkd + " " + (z+1);
			if( parseFloat(kh.ds) > 0) {
				
				marker = new google.maps.Marker(
						{  map: map,
							position:latlon,
							options : {icon: { url :blueIcon2 , scaledSize: new google.maps.Size(20, 40) } },
							title: _title
						}		
				);
				
			} else {				
				marker = new google.maps.Marker(
						{  map: map,
							position:latlon,
							options : {icon:yellowIcon},
							title: _title
						}	);
			}
			const mmm = marker;
			const khx =kh;
			 marker.addListener("click", () => {
					
				 var info = 	"<table style='width: 650px' cellpadding='2' cellspacing='1' > " +
					"	<tr> " +
					"		<td style='width: 140px;font-weight:400;' > " +
					"			<b>H??? t??n: </b> " +
					"		</td> " +
					"		<td style='width: 150px;font-weight:400;' > " +
					"			 " + khx.ten +
					"		</td> " +
					"		<td style='width: 110px;font-weight:400;' > " +
					"			<b>?????a ch???: </b> " +
					"		</td> " +
					"		<td style='font-weight:400;'> " +
					"		 " + khx.diachi +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>??i???n tho???i: </b>  " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.sdt +
					"		</td> " +
					"		<td style='font-weight:400;'> " +
					"			<b>T???n kho: </b> " +
					"		</td> " +
					"		<td > " +
					"			" + khx.tonkhoSP + " (KG) " +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>Doanh s??? TB 3 th??ng: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.dsThangTruoc +
					"		</td> " +
					"		<td style='font-weight:400;'> " +
					"			<b>DS t??? ?????u th??ng: </b>  " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.dsTrongThang +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>Ng??y MH ?????u ti??n: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.ngayMHDauTien +
					"		</td> " +
					"		<td style='font-weight:400;' rowspan='6' valign='top' > " +
					"			<b>H??nh ???nh: </b>	" +		
					"		</td> " +
					"		<td rowspan='6' valign='top' > " +
					"			<img src = '" + khx.img_url + "' style='max-height: 150px; max-width: 250px' />	 " +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>Ng??y MH cu???i c??ng: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.ngayMHCuoicung +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>TB ????n h??ng 3 th??ng: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.tbDONHANG +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>????n h??ng g???n nh???t: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.dhGANNHAT +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>L???n VT g???n nh???t: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.vtGANNHAT +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;'> " +
					"			<b>Th???i ??i???m VT: </b> " +
					"		</td> " +
					"		<td > " +
					"			 " + khx.thoidiem +
					"		</td> " +
					"	</tr> " +
					"	<tr> " +
					"		<td style='font-weight:400;' > " +
					"			<b>????? ph??? s???n ph???m: </b> " + 
					"		</td> " +
					"		<td > " +
					"			 " + khx.dophuSP +
					"		</td> " +
					"	</tr> " +
				"</table> ";
				
				 infowindow.close(); // Close previously opened infowindow
			        infowindow.setContent(info);
			        infowindow.open(map, mmm);
			});
			 
	        
			
	      /*   //V??? t??n ??DKD
			var canvas = document.createElement("canvas");
	        canvas.width = 130;
	        canvas.height = 20;
			var ctx = canvas.getContext("2d");
			ctx.font = "bold 10px Arial";
			ctx.textAlign = "center";
			ctx.strokeStyle = "white";
			ctx.strokeText(_title, 65, 12);
			ctx.fillText(_title, 65, 12);
			var url = canvas.toDataURL();
			var textIcon = new GIcon(G_DEFAULT_ICON);
			textIcon.image = url;
			textIcon.iconSize = new GSize(130,20);
			textIcon.iconAnchor = new GPoint(60, 60);
			textIcon.shadowSize = new GSize(0,0);
			//console.log(url);
			
			var marker2  = new google.maps.Marker(
						{  map: map,
							position:latlon,
							options : icon:textIcon
						}		
			 */
			
		}
		
		
	      <% if( latconditon != null ) 
	      {%>
	      
	      	
	    	
	    	<%
	    	 String data = "";
	    	  for(int i = 0; i < latconditon.length ; i++ ) 
	    	  {
	    		  
	    		  data += "{ lat: "+latconditon[i]+", lng: "+longconditon[i]+" },";
	    		  
	    		 
	    		  %>
	    		  
	    		
	    		  
	    		 
	    		  
	    	  <% } %>
	     
	    	  const  flightPlanCoordinates = [<%= data%>];
	    	  
	    	  const flightPath = new google.maps.Polyline({
	    		    path: flightPlanCoordinates,
	    		    geodesic: true,
	    		    strokeColor: "#FF0000",
	    		    strokeOpacity: 1.0,
	    		    strokeWeight: 2,
	    		  });
	    	  flightPath.setMap(map);
	    	  
	     <% } %> 
		
    }
	
  <%--   function initialize() 
    {
      if (GBrowserIsCompatible())
      {
        map = new GMap2(document.getElementById("map_canvas")); 
        geocoder = new GClientGeocoder();
        
        <% if(bdBean.getTrungtam().length() > 0 ){ System.out.println("1.Center la: " + bdBean.getTrungtam());  %>
        	center = new GLatLng(<%= bdBean.getTrungtam() %>)
        <% } else { %>        	
        	//center = new GLatLng(10.798804,106.679732);
        	center = new GLatLng(10.79912,106.680259);
        <%} %>
        
        //map.addControl(new GNavLabelControl());
        
        //map.setCenter(new GLatLng(10.822031,106.630293), 14);
        map.setCenter(center, 14);
        map.setUIToDefault();
        
        // Create our "tiny" marker icon
        var blueIcon = new GIcon(G_DEFAULT_ICON);
        blueIcon.image = "../images/maps_human.png";
        
        //blueIcon.image = "../upload/Penguins.jpg";
		blueIcon.iconSize = new GSize(39, 39);
        
		// Set up our GMarkerOptions object
		markerOptions = { icon:blueIcon };
		
		var ko_doanh_so = new GIcon(G_DEFAULT_ICON);
		ko_doanh_so.image = "../images/ko_doanh_so.png";
		
		//ko_doanh_so.image = "../upload/Hydrangeas.jpg";
		ko_doanh_so.iconSize = new GSize(39, 39);
        
		// Set up our GMarkerOptions object
		marker_ko_ds = { icon:ko_doanh_so };
		

		
		var khSiIcon = new GIcon(G_DEFAULT_ICON);
		khSiIcon.image = "../images/thang_truoc_co.png";
		
		var khSiIcon2 = new GIcon(G_DEFAULT_ICON);
		khSiIcon2.image = "../images/khSiCDS.png";
		
		
		
		var blueIcon2 = new GIcon(G_DEFAULT_ICON);
		blueIcon2.image = "../images/blank.png";
	     
	     //blueIcon2.image = "../upload/Hydrangeas.jpg";
			
		// Set up our GMarkerOptions object
		markerOptions2 = { icon:blueIcon2 };
	    
	    var yellowIcon = new GIcon(G_DEFAULT_ICON);
	    yellowIcon.image = "../images/vtYellow.png";
		markerOptions3 = { icon: yellowIcon };
		

		//L???y data kh??ch h??ng
		<%
		if(khList != null)
		{
			int count = 0;
			int img_stt = 1;
			
			while(khList.next())
			{
		%>
		
		var __khSi = '<%= khList.getString("khachhangsi") == null ? "0" : khList.getString("khachhangsi").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>';
		
	    khs.push({
	    	ten: '<%= khList.getString("khTen") == null ? " " : khList.getString("khTen").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>', 
	    	sdt: '<%= khList.getString("dienthoai") == null ? " " : khList.getString("dienthoai").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>', 
	    	lat: parseFloat('<%= khList.getString("lat") == null  ? "0" : khList.getString("lat").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>'), 
	    	lon: parseFloat('<%= khList.getString("long") == null  ? "0" : khList.getString("long").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>'),
	    	dsThangTruoc: '<%= khList.getString("doanhsoThangtruoc") == null ? "0" : formater.format(khList.getDouble("doanhsoThangtruoc")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	dsTrongThang: '<%= khList.getString("doanhsoTrongthang") == null ? "0" : formater.format(khList.getDouble("doanhsoTrongthang")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	si: khSi == "1" && __khSi == '1' ? '1' : '0',
	    	diachi: '<%= khList.getString("diachi") == null ? " " : khList.getString("diachi").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>',
	    	ngayMHDauTien: '<%= khList.getString("ngayMHDauTien") == null ? " " : khList.getString("ngayMHDauTien").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>',
	    	ngayMHCuoicung: '<%= khList.getString("ngayMHCuoicung") == null ? " " : khList.getString("ngayMHCuoicung").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>',
	    	dhGANNHAT: '<%= khList.getString("dhGANNHAT") == null ? " " : formater.format(khList.getDouble("dhGANNHAT")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	vtGANNHAT: '<%= khList.getString("vtGANNHAT") == null ? " " : khList.getString("vtGANNHAT").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","")  %>',
	    	tbDONHANG: '<%= khList.getString("tbDONHANG") == null ? " " : formater.format(khList.getDouble("tbDONHANG")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	dophuSP: '<%= khList.getString("dophuSP") == null ? " " : formater.format(khList.getDouble("dophuSP")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	tonkhoSP: '<%= khList.getString("tonkhoSP") == null ? " " : formater.format(khList.getDouble("tonkhoSP")).replaceAll("\n","").replaceAll("\t","")  %>',
	    	img_url: '<%= pathAnhDaiDien + khList.getString("anhcuahang")%>'
	    });
		          
			<% count++;  img_stt++;
				if(img_stt > 11)
					img_stt = 1;
			}
		}
		%>
		
		//L???y data vi???ng th??m
		
		<%
		if(khSelected != null)
		{
			khSelected.beforeFirst();
			int pos = 0;
			int img_stt = 1;
			
			while(khSelected.next())
			{
		%>
		khsSelected.push({
	    	ten: '<%= khSelected.getString("khTen") == null ? " " : khSelected.getString("khTen").trim().replaceAll("'", "").replaceAll("\n","").replaceAll("\t","") %>',
	    	ddkd: '<%= khSelected.getString("ddkdTen") == null ? " " : khSelected.getString("ddkdTen").trim().replaceAll("'", "").replaceAll("\n","").replaceAll("\t","") %>',
	    	sdt: '<%= khSelected.getString("dienthoai") == null ? " " : khSelected.getString("dienthoai").trim().replaceAll("'", "").replaceAll("\n","").replaceAll("\t","") %>',
	    	thoidiem: '<%= khSelected.getString("thoidiem") == null ? " " : khSelected.getString("thoidiem").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>', 
	    	lat: parseFloat('<%= khSelected.getString("lat") == null  ? "0" : khSelected.getString("lat").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>'), 
	    	lon: parseFloat('<%= khSelected.getString("long") == null  ? "0" : khSelected.getString("long").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>'),
	    	dsThangTruoc: '<%= khSelected.getString("doanhsoThangtruoc") == null ? "0" : formater.format(khSelected.getDouble("doanhsoThangtruoc")) %>',
	    	dsTrongThang: '<%= khSelected.getString("doanhsoTrongthang") == null ? "0" : formater.format(khSelected.getDouble("doanhsoTrongthang")) %>',
	    	diachi: '<%= khSelected.getString("diachi") == null ? " " : khSelected.getString("diachi").trim().replaceAll("'","") %>',
		    ngayMHDauTien: '<%= khSelected.getString("ngayMHDauTien") == null ? " " : khSelected.getString("ngayMHDauTien").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>',
		    ngayMHCuoicung: '<%= khSelected.getString("ngayMHCuoicung") == null ? " " : khSelected.getString("ngayMHCuoicung").trim().replaceAll("'","").replaceAll("\n","").replaceAll("\t","") %>',
		    dhGANNHAT: '<%= khSelected.getString("dhGANNHAT") == null ? " " : formater.format(khSelected.getDouble("dhGANNHAT")) %>',
		    vtGANNHAT: '<%= khSelected.getString("vtGANNHAT") == null ? " " : khSelected.getString("vtGANNHAT").trim().replaceAll("'","") %>',
		    tbDONHANG: '<%= khSelected.getString("tbDONHANG") == null ? " " : formater.format(khSelected.getDouble("tbDONHANG")) %>',
		    dophuSP: '<%= khSelected.getString("dophuSP") == null ? " " : formater.format(khSelected.getDouble("dophuSP")) %>',
		    tonkhoSP: '<%= khSelected.getString("tonkhoSP") == null ? " " : formater.format(khSelected.getDouble("tonkhoSP")) %>',
		    img_url: '<%=  pathAnhDaiDien + khSelected.getString("anhcuahang").replaceAll("\n","").replaceAll("\t","")%>',
		    ds: '<%= khSelected.getString("ds") == null ? "0" : formater.format(khSelected.getDouble("ds")) %>'
	    });
		
		<% 		pos++; img_stt++;
				if(img_stt > 11)
					img_stt = 1;
			}
		} 
		%>
		
		
		//X??? l??
		var z;
		
		var khLats = [];
		var khLons = [];
		
		//Kh??ch h??ng
		for(z = 0; z < khs.length; z++) {
			var kh = khs[z];
			
			//Ch???nh c??c t???a ????? tr??ng
			var latIndex = khLats.indexOf(kh.lat);
			var lonIndex = khLons.indexOf(kh.lon);
			if( latIndex >= 0 && lonIndex >= 0 && latIndex === lonIndex) {
				var ranLat = (Math.random()*RANDOM_NUMER-RANDOM_NUMER/2)/100000;
				var ranLon = (Math.random()*RANDOM_NUMER-RANDOM_NUMER/2)/100000;
				 //console.log("["+z+"] lat = " + ranLat + "lon = " + ranLon);
				kh.lat += ranLat;
				kh.lon += ranLon;
			}
			khLats.push(kh.lat);
			khLons.push(kh.lon);
			
			var latlon = new GLatLng(kh.lat, kh.lon);
			var marker;
			if(parseFloat(kh.dsTrongThang) > 0) {
				marker = new GMarker(latlon, kh.si == '1' ? {icon: khSiIcon2} : markerOptions);
			} else {
				marker = new GMarker(latlon, kh.si == '1' ? {icon: khSiIcon} : marker_ko_ds);
			}
			
			GEvent.addListener(marker, "click", (
				function(kh, latlon) { 
					return function () 
					{
						//var info = "<b>Kh??ch h??ng: </b>"+kh.ten + (kh.si == '1' ? " (KH S???) " : "") + "<br /><b>S??? ??i???n tho???i: </b>"+kh.sdt+"<br /><b>Doanh s??? TB 3 th??ng: </b>"+kh.dsThangTruoc+"<br /><b>Doanh s??? th??ng n??y: </b>" + kh.dsTrongThang;
						
						var info = 	"<table style='width: 650px' cellpadding='2' cellspacing='1' > " +
							"	<tr> " +
							"		<td style='width: 140px;font-weight:400;' > " +
							"			<b>H??? t??n: </b> " +
							"		</td> " +
							"		<td style='width: 150px;font-weight:400;' > " +
							"			 " + kh.ten + (kh.si == '1' ? " (KH S???) " : "") +
							"		</td> " +
							"		<td style='width: 110px;font-weight:400;' > " +
							"			<b>?????a ch???: </b> " +
							"		</td> " +
							"		<td style='font-weight:400;'> " +
							"		 " + kh.diachi +
							"		</td> " +
							"	</tr> " +
							"	<tr> " +
							"		<td style='font-weight:400;'> " +
							"			<b>??i???n tho???i: </b>  " +
							"		</td> " +
							"		<td > " +
							"			 " + kh.sdt +
							"		</td> " +
							"		<td style='font-weight:400;'> " +
							"			<b>T???n kho: </b> " +
							"		</td> " +
							"		<td > " +
							"			" + kh.tonkhoSP + " (KG) " +
							"		</td> " +
							"	</tr> " +
							"	<tr> " +
							"		<td style='font-weight:400;'> " +
							"			<b>Doanh s??? TB 3 th??ng: </b> " +
							"		</td> " +
							"		<td > " +
							"			 " + kh.dsThangTruoc +
							"		</td> " +
							"		<td style='font-weight:400;'> " +
							"			<b>DS t??? ?????u th??ng: </b>  " +
							"		</td> " +
							"		<td > " +
							"			 " + kh.dsTrongThang +
							"		</td> " +
							"	</tr> " +
							"	<tr> " +
							"		<td style='font-weight:400;'> " +
							"			<b>Ng??y MH ?????u ti??n: </b> " +
							"		</td> " +
							"		<td > " +
							"			 " + kh.ngayMHDauTien +
							"		</td> " +
							"		<td style='font-weight:400;' rowspan='6' valign='top' > " +
							"			<b>H??nh ???nh: </b>	" +		
							"		</td> " +
							"		<td rowspan='6' valign='top' > " +
							"			<img src = '" + kh.img_url + "' style='max-height: 150px; max-width: 250px' />	 " +
							"		</td> " +
							"	</tr> " +
							"	<tr> " +
							"		<td style='font-weight:400;'> " +
							"			<b>Ng??y MH cu???i c??ng: </b> " +
							"		</td> " +
							"		<td > " +
							"			 " + kh.ngayMHCuoicung +
							"		</td> " +
							"	</tr> " +
							"	<tr> " +
							"		<td style='font-weight:400;'> " +
							"			<b>TB ????n h??ng 3 th??ng: </b> " +
							"		</td> " +
							"		<td > " +
							"			 " + kh.tbDONHANG +
							"		</td> " +
							"	</tr> " +
							"	<tr> " +
							"		<td style='font-weight:400;'> " +
							"			<b>????n h??ng g???n nh???t: </b> " +
							"		</td> " +
							"		<td > " +
							"			 " + kh.dhGANNHAT +
							"		</td> " +
							"	</tr> " +
							"	<tr> " +
							"		<td style='font-weight:400;'> " +
							"			<b>L???n VT g???n nh???t: </b> " +
							"		</td> " +
							"		<td > " +
							"			 " + kh.vtGANNHAT +
							"		</td> " +
							"	</tr> " +
							"	<tr> " +
							"		<td style='font-weight:400;' > " +
							"			<b>????? ph??? s???n ph???m: </b> " + 
							"		</td> " +
							"		<td > " +
							"			 " + kh.dophuSP +
							"		</td> " +
							"	</tr> " +
						"</table> ";
						
						map.openInfoWindowHtml(latlon, info);
					};
				}
			)(kh, latlon));
			map.addOverlay(marker);
			
		}
		
		khLats = [];
		khLons = [];
		
		//Vi???ng th??m
		for(z = 0; z < khsSelected.length; z++) {
			var kh = khsSelected[z];
			
			//Ch???nh c??c t???a ????? tr??ng
			var latIndex = khLats.indexOf(kh.lat);
			var lonIndex = khLons.indexOf(kh.lon);
			if( latIndex >= 0 && lonIndex >= 0 && latIndex === lonIndex) {
				var ranLat = (Math.random()*RANDOM_NUMER-RANDOM_NUMER/2)/100000;
				var ranLon = (Math.random()*RANDOM_NUMER-RANDOM_NUMER/2)/100000;
				//console.log("["+z+"] lat = " + ranLat + "lon = " + ranLon);
				kh.lat += ranLat;
				kh.lon += ranLon;
			}
			khLats.push(kh.lat);
			khLons.push(kh.lon);
			
			var latlon = new GLatLng(kh.lat, kh.lon);      
	        var marker;// = new GMarker(latlon, markerOptions2);

			var marker;
			var _title = kh.ddkd + " " + (z+1);
			if( parseFloat(kh.ds) > 0) {
				marker = new GMarker(latlon, { icon:blueIcon2, title: _title });
			} else {
				marker = new GMarker(latlon, { icon: yellowIcon, title: _title });
			}
	        
	        GEvent.addListener(marker, "click", (
				function(kh, latlon) { 
					return function () 
					{
						//var noidung = "<b>Th???i ??i???m: </b>"+kh.thoidiem+"<br /><b>S??? ??i???n tho???i: </b>"+kh.sdt+"<br /><b>T??n nh??n vi??n: </b>"+kh.ddkd+"<br /><b>Kh??ch h??ng: </b>"+kh.ten;
						
						var noidung = 	"<table style='width: 650px' cellpadding='2' cellspacing='1' > " +
									"	<tr> " +
									"		<td style='width: 140px;font-weight:400;' > " +
									"			<b>H??? t??n: </b> " +
									"		</td> " +
									"		<td style='width: 150px;font-weight:400;' > " +
									"			 " + kh.ten +
									"		</td> " +
									"		<td style='width: 110px;font-weight:400;' > " +
									"			<b>?????a ch???: </b> " +
									"		</td> " +
									"		<td style='font-weight:400;'> " +
									"		 " + kh.diachi +
									"		</td> " +
									"	</tr> " +
									"	<tr> " +
									"		<td style='font-weight:400;'> " +
									"			<b>??i???n tho???i: </b>  " +
									"		</td> " +
									"		<td > " +
									"			 " + kh.sdt +
									"		</td> " +
									"		<td style='font-weight:400;'> " +
									"			<b>T???n kho: </b> " +
									"		</td> " +
									"		<td > " +
									"			" + kh.tonkhoSP + " (KG) " +
									"		</td> " +
									"	</tr> " +
									"	<tr> " +
									"		<td style='font-weight:400;'> " +
									"			<b>Doanh s??? TB 3 th??ng: </b> " +
									"		</td> " +
									"		<td > " +
									"			 " + kh.dsThangTruoc +
									"		</td> " +
									"		<td style='font-weight:400;'> " +
									"			<b>DS t??? ?????u th??ng: </b>  " +
									"		</td> " +
									"		<td > " +
									"			 " + kh.dsTrongThang +
									"		</td> " +
									"	</tr> " +
									"	<tr> " +
									"		<td style='font-weight:400;'> " +
									"			<b>Ng??y MH ?????u ti??n: </b> " +
									"		</td> " +
									"		<td > " +
									"			 " + kh.ngayMHDauTien +
									"		</td> " +
									"		<td style='font-weight:400;' rowspan='6' valign='top' > " +
									"			<b>H??nh ???nh: </b>	" +		
									"		</td> " +
									"		<td rowspan='6' valign='top' > " +
									"			<img src = '" + kh.img_url + "' style='max-height: 150px; max-width: 250px' />	 " +
									"		</td> " +
									"	</tr> " +
									"	<tr> " +
									"		<td style='font-weight:400;'> " +
									"			<b>Ng??y MH cu???i c??ng: </b> " +
									"		</td> " +
									"		<td > " +
									"			 " + kh.ngayMHCuoicung +
									"		</td> " +
									"	</tr> " +
									"	<tr> " +
									"		<td style='font-weight:400;'> " +
									"			<b>TB ????n h??ng 3 th??ng: </b> " +
									"		</td> " +
									"		<td > " +
									"			 " + kh.tbDONHANG +
									"		</td> " +
									"	</tr> " +
									"	<tr> " +
									"		<td style='font-weight:400;'> " +
									"			<b>????n h??ng g???n nh???t: </b> " +
									"		</td> " +
									"		<td > " +
									"			 " + kh.dhGANNHAT +
									"		</td> " +
									"	</tr> " +
									"	<tr> " +
									"		<td style='font-weight:400;'> " +
									"			<b>L???n VT g???n nh???t: </b> " +
									"		</td> " +
									"		<td > " +
									"			 " + kh.vtGANNHAT +
									"		</td> " +
									"	</tr> " +
									"	<tr> " +
									"		<td style='font-weight:400;'> " +
									"			<b>Th???i ??i???m VT: </b> " +
									"		</td> " +
									"		<td > " +
									"			 " + kh.thoidiem +
									"		</td> " +
									"	</tr> " +
									"	<tr> " +
									"		<td style='font-weight:400;' > " +
									"			<b>????? ph??? s???n ph???m: </b> " + 
									"		</td> " +
									"		<td > " +
									"			 " + kh.dophuSP +
									"		</td> " +
									"	</tr> " +
								"</table> ";
										
						map.openInfoWindowHtml(latlon, noidung);
					};
				}
			)(kh, latlon));
	        map.addOverlay(marker);
			
	        //V??? t??n ??DKD
			var canvas = document.createElement("canvas");
	        canvas.width = 130;
	        canvas.height = 20;
			var ctx = canvas.getContext("2d");
			ctx.font = "bold 10px Arial";
			ctx.textAlign = "center";
			ctx.strokeStyle = "white";
			ctx.strokeText(_title, 65, 12);
			ctx.fillText(_title, 65, 12);
			var url = canvas.toDataURL();
			var textIcon = new GIcon(G_DEFAULT_ICON);
			textIcon.image = url;
			textIcon.iconSize = new GSize(130,20);
			textIcon.iconAnchor = new GPoint(60, 60);
			textIcon.shadowSize = new GSize(0,0);
			//console.log(url);
			
			marker = new GMarker(latlon, {icon: textIcon});

			map.addOverlay(marker);
			
		}
		
      }
      	
      
      	
	      <% if( latconditon != null ) 
	      {
	    	  for(int i = 0; i < latconditon.length - 1; i++ ) 
	    	  {
	    		  String lat_long = latconditon[i] + ", " + longconditon[i];
	    		  String lat_long2 = latconditon[i + 1] + ", " + longconditon[i + 1];
	    		  %>
	    		  
	    		  var polyline = new GPolyline([
	    	                                    new GLatLng(<%= lat_long %>),
	    	                                    new GLatLng(<%= lat_long2 %>)
	    	                                  ], "#FF0000", 5);
	    	                                  map.addOverlay(polyline);
	    		  
	    	  <% } %>
	      /* var polyline = new GPolyline([
	                                    new GLatLng(10.8169844, 106.6872128),
	                                    new GLatLng(10.8165455, 106.6876032)
	                                  ], "#66FF33", 5);
	                                  map.addOverlay(polyline); */
	     <% } %> 
      
    } --%>
    
    function showAddress(address) 
    {
    	 geocoder.getLatLng(
    	    address,
    	    function(point) 
    	    {
    	      if (!point) 
    	      {
    	        alert("Kh??ng th??? t??n th???y ?????a ch???:" + address);
    	      } 
    	      else 
    	      {
	    	        map.setCenter(point, 14);
	    	        var marker = new GMarker(point);
	    	        map.addOverlay(marker);
	
	    	        // As this is user-generated content, we display it as
	    	        // text rather than HTML to reduce XSS vulnerabilities.
	    	        marker.openInfoWindow(document.createTextNode("?????a ch???: " + address));
	    	        document.getElementById("trungtam").value = point.lat() + "," + point.lng();
    	      }
    	    }
    	  );
    }
    
    </script>
    
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">

   	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
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
  	
  	<script type="text/javascript">
	  	function submitform()
		{  
		    document.forms["bdForm"].submit();
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

<style type="text/css">
	input
	{
		padding: 2px 0px;
	}
</style>
</head>
<body onload="initialize()" onunload="GUnload()">
<form name="bdForm" method="post" action="../../BandoSvl">

	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="nppId" value='<%= bdBean.getNppId() %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="trungtam" id="trungtam" value=''>

<div id="main" style="width:99.5%; padding-left:2px">

	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:40%; padding:5px; float:left" class="tbnavigation">
        	B??o c??o qu???n tr??? > B??o c??o kh??c > L??? tr??nh b??n h??ng
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Ch??o m???ng  <%= bdBean.getNppTen() %> &nbsp;&nbsp;
        </div>
    </div>
    
    <div style="width:100%; float:none" align="left">
    <fieldset>
    	<legend class="legendtitle">Th??ng tin l??? tr??nh b??n h??ng</legend>
        <TABLE width="100%" cellpadding="4" cellspacing="0" style="background-color: #C5E8CD;">								   
            <TR>
                <TD class="plainlabel" width="130px" >Ng??y </TD>
                <TD class="plainlabel" width="230px">
                    <input type="text" size="11" class="days" 
                          id="ngay" name="ngay" value="<%= bdBean.getDate() %>" maxlength="10" readonly   />
                </TD>
            
                <TD class="plainlabel" width="130px" ><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %> </TD>
                <TD class="plainlabel">
                    <select name="ddkd" id="ddkd" onChange = "submitform();">
                            <option value="" selected></option>
                            <% if(ddkd != null){
								  try{ while(ddkd.next()){ 
					    			if(ddkd.getString("ddkdId").equals(bdBean.getDdkdId())){ %>
					      				<option value='<%= ddkd.getString("ddkdId")%>' selected><%= ddkd.getString("ddkdTen") %></option>
					      			<%}else{ %>
					     				<option value='<%= ddkd.getString("ddkdId")%>'><%= ddkd.getString("ddkdTen") %></option>
					     	<%}} ddkd.close(); }catch(java.sql.SQLException e){} }%>
                        </select>    
                </TD>
            </TR>
            <TR>
                <TD class="plainlabel" >Tuy???n b??n h??ng </TD>
                <TD class="plainlabel">
                    <select name="tbh" id="tbh" >
                          <option value="" selected></option>
                          <% if(tbh != null){
						  try{ while(tbh.next()){ 
			    			if(tbh.getString("tbhId").equals(bdBean.getTbhId())){ %>
			      				<option value='<%= tbh.getString("tbhId")%>' selected><%= tbh.getString("tbhTen") %></option>
			      			<%}else{ %>
			     				<option value='<%= tbh.getString("tbhId")%>'><%= tbh.getString("tbhTen") %></option>
			     	<%}} tbh.close(); }catch(java.sql.SQLException e){} }%>
                      </select>    
               </TD>
            
                <TD class="plainlabel" >??o???n ???????ng </TD>
                <TD class="plainlabel">
                    <input type="text" name="address" value="<%= bdBean.getAddress() %>" onchange="showAddress(this.value)" >  
               </TD>
            </TR>
            <tr>
            	<TD class="plainlabel" >Ghi ch?? </TD>
            	<TD class="plainlabel" colspan="3" >
            		
            		<img src="../images/ko_doanh_so.png" width="30" height="30" alt=""> KH &nbsp;&nbsp;&nbsp;&nbsp;
                    <img src="../images/maps_human.png" width="30" height="30" alt=""> KH c?? doanh s??? &nbsp;&nbsp;&nbsp;&nbsp;
                    
                  <!--  <img src="../images/thang_truoc_co.png" width="30" height="30" alt=""> KH s??? &nbsp;&nbsp;&nbsp;&nbsp; -->  
                     <img src="../images/khSiCDS.png" width="30" height="30" alt=""> KH s??? c?? doanh s??? &nbsp;&nbsp;&nbsp;&nbsp; 
                    
                    <img src="../images/vtYellow.png" alt="" width="16"> Vi???ng th??m &nbsp;&nbsp;&nbsp;&nbsp;
					<img src="http://gmaps-samples.googlecode.com/svn/trunk/markers/blue/blank.png" alt="" width="16"> Vi???ng th??m c?? doanh s??? &nbsp;&nbsp;&nbsp;&nbsp;
            		
            	</TD>
            </tr>
            <tr> 
            		<td class="plainlabel"> 
            		<a class="button2" href="javascript:submitform();">
            		<img style="margin-bottom: -10px;" src="../images/button.png" alt=""> Hi???n th???  </a>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
            </tr>
        </TABLE>
     	<hr>
    
	<div id="map_canvas" style="width: 100%; height: 650px; position:static; margin-left:auto; margin-right:auto">
    </div>
    
    </fieldset></div>
 </div>


 
 </form>
</body>
</HTML>

<% bdBean.DBclose(); %>
<%}%>