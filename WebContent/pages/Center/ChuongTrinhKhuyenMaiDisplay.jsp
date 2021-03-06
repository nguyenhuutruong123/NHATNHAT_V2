<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.ctkhuyenmai.*" %>
<%@ page  import = "geso.dms.center.beans.ctkhuyenmai.imp.*" %>
<%@ page  import = "geso.dms.center.beans.dieukienkhuyenmai.ISanpham" %>
<%@ page  import = "geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% ICtkhuyenmai ctkmBean = (ICtkhuyenmai)session.getAttribute("ctkmBean"); %>
<% List<IDieukienkm> dkkmList = ctkmBean.getDkkmList(); %>
<% List<ITrakm> trakmList = ctkmBean.getTrakmList(); %>
<% ResultSet nhomspRs = ctkmBean.getNhomspRs(); %>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");
Utility util = (Utility) session.getAttribute("util"); %>
<% ResultSet khoIds = (ResultSet)ctkmBean.getkhoIds();%>
<% ResultSet Dsnpp = (ResultSet)ctkmBean.getDsnpp();%>
<% ResultSet DsnppIds = (ResultSet)ctkmBean.getDsnppSelected(); %>
<% Hashtable<Integer, String> nppIds = (Hashtable<Integer, String>)ctkmBean.getnpp(); %>
<% ResultSet Nhomkhnpp = (ResultSet)ctkmBean.getNhomkhnpp();%>
<% ResultSet vungs = (ResultSet)ctkmBean.getVungs();%>
<% ResultSet khuvucs = (ResultSet)ctkmBean.getKhuvuc();%>
<% ResultSet kbhRS = (ResultSet)ctkmBean.getKbhRs(); %>
<% ResultSet loaikhRS = (ResultSet)ctkmBean.getLoaikhRs(); %>
<% ResultSet loaicnRS = (ResultSet)ctkmBean.getLoaicnRs(); %>
<% ResultSet _hangCuaHangs = (ResultSet)ctkmBean.getHangCuaHangs(); %>
<% ResultSet cttbRs = (ResultSet)ctkmBean.getCttbRs(); %>
<% ResultSet ctkmRs = (ResultSet)ctkmBean.getCtkmRs(); %>
<%
String view = ctkmBean.getView();
String url = util.getChuyenNguUrl("CtkhuyenmaiSvl", "&view="+view,session); %>

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
	<script type="text/javascript" src="../scripts/ctkhuyenmaiList.js"></script>
	
   <script>
	  $(document).ready(function() {
			$("#accordion").accordion({autoHeight: false}); //autoHeight content set false
			$( "#accordion" ).accordion( "option", "icons", { 'header': 'ui-icon-plus', 'headerSelected': 'ui-icon-minus' } );
			$( "#accordion" ).accordion({ active: <%= ctkmBean.getActive() %> });
	  });
	  

  </script>
  
    <link media="screen" rel="stylesheet" href="../css/colorbox.css">
    <script src="../scripts/colorBox/jquery.colorbox.js"></script>
    <script>
        $(document).ready(function()
        {
        	$(".dieukienkhuyenmai0").colorbox({width:"46%", inline:true, href:"#dieukienkhuyenmai0"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
           
            $(".dieukienkhuyenmai1").colorbox({width:"46%", inline:true, href:"#dieukienkhuyenmai1"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
            
            $(".dieukienkhuyenmai2").colorbox({width:"46%", inline:true, href:"#dieukienkhuyenmai2"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
            
            $(".dieukienkhuyenmai3").colorbox({width:"46%", inline:true, href:"#dieukienkhuyenmai3"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
            
            $(".dieukienkhuyenmai4").colorbox({width:"46%", inline:true, href:"#dieukienkhuyenmai4"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
            
            $(".trakhuyenmai0").colorbox({width:"46%", inline:true, href:"#trakhuyenmai0"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
            
            $(".trakhuyenmai1").colorbox({width:"46%", inline:true, href:"#trakhuyenmai1"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
            
            $(".trakhuyenmai2").colorbox({width:"46%", inline:true, href:"#trakhuyenmai2"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
            $(".trakhuyenmai3").colorbox({width:"46%", inline:true, href:"#trakhuyenmai3"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
            
            $(".trakhuyenmai4").colorbox({width:"46%", inline:true, href:"#trakhuyenmai4"});
            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function(){ 
                $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
                return false;
            });
        });
    </script>
    
    
    
    
    <script language="javascript" type="text/javascript">
    
    function AjaxDKKM(pos)
	{
    	var dkkmId=document.getElementById("dkkmId"+pos).value;
    	
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
			 var idTableSp =document.getElementById("dieukienkhuyenmai"+pos);
			 if(dkkmId!='')
			{
			 	idTableSp.innerHTML = xmlhttp.responseText;
			}
		  }
		}
		xmlhttp.open("POST","../../AjaxChuongTrinhKhuyenMai?action=AjaxDKKM&dkkmId="+dkkmId+"&pos="+pos+"&ctkmId=0", true);
		xmlhttp.send();
	}
    
    
    function AjaxTraKM(pos)
	{
    	var trakmId=document.getElementById("trakmId"+pos).value;
    	
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
			 var idTableSp =document.getElementById("trakhuyenmai"+pos);
			 if(trakmId!='')
			{
			 	idTableSp.innerHTML = xmlhttp.responseText;
			}
		  }
		}
		xmlhttp.open("POST","../../AjaxChuongTrinhKhuyenMai?action=AjaxTraKM&trakmId="+trakmId+"&pos="+pos+"&ctkmId=0", true);
		xmlhttp.send();
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
		
		
		var loaicnId = document.getElementById("loaicn");
		var loaicnIds="";
		for(var i = 0; i < loaicnId.options.length ; i++)
		{
			if(loaicnId.options[i].selected)
				loaicnIds += loaicnId.options[i].value + ',';
		}
		if(loaicnIds.length>0)
		{
			loaicnIds=loaicnIds.substring(0,loaicnIds.length-1);
		}
					
		//Lay tat ca nppIdSelecd nhung nhapp da tick chon 
		var all_NppId_Checked="";
		var nppId= document.getElementsByName('npp');
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
		
		 $.get("../../AjaxChuongTrinhKhuyenMai?action=ajaxNpp&kenhId="+kenhIds+"&vungId="+vungIds+"&kvId="+khuvucIds+"&loaicnId="+loaicnIds+"&nppSelected="+all_NppId_Checked+"", function(list,status) {
				var table = $('#NppTable');
					table.html(
						'<TABLE id="NppTable">'+
		                    '<TR class="tbheader">'+
	                        '<TH align="center" width="10%">M??</TH>'+
	                        '<TH align="left" width="50%"> T??n </TH>'+
	                        '<TH align="center" width="10%"> Ch???n t???t c??? <input type ="checkbox" name ="all" onclick ="All()"></TH>'+
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
							.append($('<td><input type=text  name="nppId"  value='+data.nppMa +' style="width: 100%;"  readonly="readonly" ></td>' ))
							.append($("<td><input type='text' name='nppTen' value= '"+data.nppTen+"' style='width: 100%;'  readonly='readonly'> </td>" ))
							.append($('<td><input type=checkbox value='+data.nppId+' name="npp" '+checked+'  ></td>' ));
						
					});
			});
		 
}
    
    	function replacesDKKM(pos)
    	{
    		alert(pos);
    		
    		var dkkmId = document.getElementsByName("dkkmId");
			var dkkmDiengiai = document.getElementsByName("dkkmDiengiai");
			var dkkmTongluong = document.getElementsByName("dkkmTongluong");
			var dkkmTongtien = document.getElementsByName("dkkmTongtien");
			var dkkmPheptoan = document.getElementsByName("dkkmPheptoan");
			
			var i;
			for(i = 0; i < dkkmId.length; i++)
			{
				if(i == pos)
				{
					if(dkkmId.item(i).value != "")
					{
						var dkkm = dkkmId.item(i).value;
						var pos = parseInt(dkkm.indexOf(" - "));
						if(pos > 0)
						{
							dkkmId.item(i).value = dkkm.substring(0, pos);
							dkkm = dkkm.substr(parseInt(dkkm.indexOf(" - ")) + 3);
							dkkmDiengiai.item(i).value = dkkm.substring(0, parseInt(dkkm.indexOf(" [")));
							dkkm = dkkm.substr(parseInt(dkkm.indexOf(" [")) + 2);
							dkkmTongluong.item(i).value = dkkm.substring(0, parseInt(dkkm.indexOf("] [")));
							dkkm = dkkm.substr(parseInt(dkkm.indexOf("] [")) + 3);
							dkkmTongtien.item(i).value = dkkm.substring(0, parseInt(dkkm.indexOf("]")));
						}
					}
					else
					{
						dkkmId.item(i).value = "";
						dkkmDiengiai.item(i).value = "";
						dkkmTongluong.item(i).value = "";
						dkkmTongtien.item(i).value = "";
						dkkmPheptoan.item(i).value = "";
					}		
				}	
			}	
    	}
    	
    	
    	function deleleDkMuakmDetail(i)
    	{
    		var dkkmId = document.getElementsByName("dkkmId");
			var dkkmDiengiai = document.getElementsByName("dkkmDiengiai");
			var dkkmTongluong = document.getElementsByName("dkkmTongluong");
			var dkkmTongtien = document.getElementsByName("dkkmTongtien");
			var dkkmPheptoan = document.getElementsByName("dkkmPheptoan");
			if(dkkmId.item(i).value == "")
			{
				var masp = document.getElementsByName('dieukienkhuyenmai' + i + '.masanpham');
				var tensp = document.getElementsByName('dieukienkhuyenmai' + i + '.tensanpham');
				var soluong = document.getElementsByName('dieukienkhuyenmai' + i + '.soluong');
				for(var p=0; p < masp.length; p++)
				{
					masp.item(p).value = "";
					tensp.item(p).value = "";
					soluong.item(p).value = "";
				}
			}
    	}
    	
    	function deleleDkTraKmDetail(j)
    	{
    		var tkmId = document.getElementsByName("trakmId");
			var tkmDiengiai = document.getElementsByName("trakmDiengiai");
			var tkmTongluong = document.getElementsByName("trakmTongluong");
			var tkmTongtien = document.getElementsByName("trakmTongtien");
			var tkmChietkhau = document.getElementsByName("trakmChietkhau");
			var tkmPheptoan = document.getElementsByName("trakmPheptoan");
			if(tkmId.item(j).value == "")
			{
				tkmId.item(j).value = "";
				tkmDiengiai.item(j).value = "";
				tkmTongluong.item(j).value = "";
				tkmTongtien.item(j).value = "";
				tkmChietkhau.item(j).value = "";
				var masp = document.getElementsByName('trakhuyenmai' + j + '.masanpham');
				var tensp = document.getElementsByName('trakhuyenmai' + j + '.tensanpham');
				var soluong = document.getElementsByName('trakhuyenmai' + j + '.soluong');

				for(var p=0; p < masp.length; p++)
				{
					masp.item(p).value = "";
					tensp.item(p).value = "";
					soluong.item(p).value = "";
				}
			}
    	}
    	
		function replaces()
		{
			var dkkmId = document.getElementsByName("dkkmId");
			var dkkmDiengiai = document.getElementsByName("dkkmDiengiai");
			var dkkmTongluong = document.getElementsByName("dkkmTongluong");
			var dkkmTongtien = document.getElementsByName("dkkmTongtien");
			var dkkmPheptoan = document.getElementsByName("dkkmPheptoan");
	
			var i;
			for(i=0; i < dkkmId.length; i++)
			{
				if(dkkmId.item(i).value != "")
				{
					var dkkm = dkkmId.item(i).value;
					var pos = parseInt(dkkm.indexOf(" - "));
					if(pos > 0)
					{
						dkkmId.item(i).value = dkkm.substring(0, pos);
						dkkm = dkkm.substr(parseInt(dkkm.indexOf(" - ")) + 3);
						dkkmDiengiai.item(i).value = dkkm.substring(0, parseInt(dkkm.indexOf(" [")));
						dkkm = dkkm.substr(parseInt(dkkm.indexOf(" [")) + 2);
						dkkmTongluong.item(i).value = dkkm.substring(0, parseInt(dkkm.indexOf("] [")));
						dkkm = dkkm.substr(parseInt(dkkm.indexOf("] [")) + 3);
						dkkmTongtien.item(i).value = dkkm.substring(0, parseInt(dkkm.indexOf("]")));
					}
				}
				else
				{
					dkkmId.item(i).value = "";
					dkkmDiengiai.item(i).value = "";
					dkkmTongluong.item(i).value = "";
					dkkmTongtien.item(i).value = "";
					dkkmPheptoan.item(i).value = "";
					
				}			
			}
			
			var tkmId = document.getElementsByName("trakmId");
			var tkmDiengiai = document.getElementsByName("trakmDiengiai");
			var tkmTongluong = document.getElementsByName("trakmTongluong");
			var tkmTongtien = document.getElementsByName("trakmTongtien");
			var tkmChietkhau = document.getElementsByName("trakmChietkhau");
			var tkmPheptoan = document.getElementsByName("trakmPheptoan");
			
			var j;
			for(j=0; j < tkmId.length; j++)
			{
				if(tkmId.item(j).value != "")
				{
					var trakm = tkmId.item(j).value;
					var pos = parseInt(trakm.indexOf(" - "));
					if(pos > 0)
					{
						tkmId.item(j).value = trakm.substring(0, pos);
						trakm = trakm.substr(parseInt(trakm.indexOf(" - ")) + 3);
						
						tkmDiengiai.item(j).value = trakm.substring(0, parseInt(trakm.indexOf(" [")));
						trakm = trakm.substr(parseInt(trakm.indexOf(" [")) + 2);
						
						tkmTongluong.item(j).value = trakm.substring(0, parseInt(trakm.indexOf("] [")));
						trakm = trakm.substr(parseInt(trakm.indexOf(" [")) + 2);
						
						tkmTongtien.item(j).value = trakm.substring(0, parseInt(trakm.indexOf("] [")));
						trakm = trakm.substr(parseInt(trakm.indexOf(" [")) + 2);
						
						tkmChietkhau.item(j).value = trakm.substring(0, parseInt(trakm.indexOf("]")));
					}
				}
				else
				{
					tkmId.item(j).value = "";
					tkmDiengiai.item(j).value = "";
					tkmTongluong.item(j).value = "";
					tkmTongtien.item(j).value = "";
					tkmChietkhau.item(j).value = "";
					
				}		
			}
			
			//Sanpham
			for(k = 0; k < 5; k++)
			{
				var masp = document.getElementsByName('dieukienkhuyenmai' + k + '.masanpham');
				var tensp = document.getElementsByName('dieukienkhuyenmai' + k + '.tensanpham');
				var soluong = document.getElementsByName('dieukienkhuyenmai' + k + '.soluong');

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
						soluong.item(p).value = "";
					}			
				}
			}
			
			//Tra khuyen mai sanpham
			for(k = 0; k < 5; k++)
			{
				var masp = document.getElementsByName('trakhuyenmai' + k + '.masanpham');
				var tensp = document.getElementsByName('trakhuyenmai' + k + '.tensanpham');
				var soluong = document.getElementsByName('trakhuyenmai' + k + '.soluong');

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
						soluong.item(p).value = "";
					}			
				}
			}
			
			setTimeout(replaces, 100);
		}	
		
		
		//hien thi hoac an 1 the html
		function hideshow(which){
			if (!document.getElementById)
			return
			if (which.style.display=="block")
			which.style.display="none"
			else
			which.style.display="block"
		}
		
    	function selectAll()
    	{
    		
    		 
    		    $('#_hangCuaHangs option').attr('selected', 'selected');
    		
    	}

    	function deSelectAll()
    	{
    		$('#_hangCuaHangs option').removeAttr("selected");
    	}
    	
    	
    	
    	function selectAllKBH()
    	{
    		
    		 
    		    $('#kenhId option').attr('selected', 'selected');
    		
    	}

    	function deSelectAllLKH()
    	{
    		$('#loaikhId option').removeAttr("selected");
    	}
    	
    	function selectAllLKH()
    	{
    		
    		 
    		    $('#loaikhId option').attr('selected', 'selected');
    		
    	}

    	function deSelectAllKBH()
    	{
    		$('#kenhId option').removeAttr("selected");
    	}
    	
		function checkDieukienkm()
		{
			var dkkmIds = document.getElementsByName("dkkmId");
			var dkkmPheptoan = document.getElementsByName("dkkmPheptoan");
			
			//dieu kien dau tien ko can nhap
			for(j = 0; j < dkkmIds.length; j++)
			{
				if(dkkmIds.item(j).value != "")
				{
					if(j > 0 && dkkmPheptoan.item(j).value == "")
						return false;
				}
			}
			return true;
		}
		
		function checkDieukientrung()
		{
			var dkkmIds = document.getElementsByName("dkkmId");
			var dkkmDiengiai = document.getElementsByName("dkkmDiengiai");
			var dkkmTongluong = document.getElementsByName("dkkmTongluong");
			var dkkmTongtien = document.getElementsByName("dkkmTongtien");
			var dkkmPheptoan = document.getElementsByName("dkkmPheptoan");
			
			for(l =0; l < parseInt(dkkmIds.length) - 1; l++)
			{
				for(m = parseInt(l) + 1; m < dkkmIds.length; m++)
				{
					if(dkkmIds.item(l).value == dkkmIds.item(m).value)
					{
						dkkmIds.item(m).value = "";
						dkkmDiengiai.item(m).value = "";
						dkkmTongluong.item(m).value = "";
						dkkmTongtien.item(m).value = "";
						dkkmPheptoan.item(m).value = "";
					}
				}
			}
		}
		
		function checkTrakm()
		{
			var trakmIds = document.getElementsByName("trakmId");
			for(k =0; k < trakmIds.length; k++)
			{
				if(trakmIds.item(k) != "")
					return true; //co chon tra khuyen mai
			}
			return false;
		}
		
		function All()
		 { 
			var npp = document.getElementsByName("npp");
			for(i=0; i<npp.length; i++)
			{
			 	if( document.ctkmForm.all.checked == true )
			 	{
			 	  	npp[i].checked = true;
				}
			 	else
			 	{
					npp[i].checked = false;
				}
			}
		}
		function checkNpp()
		{
			var nhapp = document.getElementsByName("npp");
			for( p = 0; p < nhapp.length; p++)
				if(nhapp.item(p).checked)
					return true;
			return false;
		}
		
		function seach()
		{
			var active = $( "#accordion" ).accordion( "option", "active" );
			document.forms["ctkmForm"].active.value = active;
			document.forms["ctkmForm"].action.value = "seach";
			document.forms["ctkmForm"].load.value = "1";
			document.forms["ctkmForm"].submit();
		}
		
		function tichluy()
		{ 	
			var dkkmId = document.getElementsByName("dkkmId");
			var loai = document.getElementById("loaiCt");
			
			if(loai == "3")
			{
			    for(i = 0;i< dkkmId.length;i++)
			    {
			    	dkkmId[i].value = "";
			    }
			}
		    
			var active = $( "#accordion" ).accordion( "option", "active" );
			document.forms["ctkmForm"].active.value = active;
			
			document.forms["ctkmForm"].action.value = "tichluy";
			document.forms["ctkmForm"].submit();

		}
		
		 function Show()
		 {
		 	var element = document.getElementById('ngansachct');
		 	
		 	var apphanbo = document.getElementById('apphanbo');
		 	if(apphanbo.checked)
		 		element.style.display = "";
		 	else
		 		element.style.display = "none";
		 }
		 
		
		
	</script>
	
	<script type="text/javascript">	
		
	</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ctkmForm" method="post" action="../../CtkhuyenmaiUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="id" value='<%= ctkmBean.getId() %>'>
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="view" value='<%= view %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="tkmTungay" value=''>
<input type="hidden" name="tkmDenngay" value=''>
<input type="hidden" name="tkmDiengiai" value=''>
<input type="hidden" name="dkkmTungay" value=''>
<input type="hidden" name="dkkmDenngay" value=''>
<input type="hidden" name="dkkmDien_giai" value=''>
<input type="hidden" name="active" value='<%= ctkmBean.getActive() %>'>
<input type="hidden" name="load" value='0'>

<div id="main" style="width:100%; padding-left: 1px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	<%=url %> > <%=Utility.GLanguage("Hi???n th???",session,jedis) %>
        </div>
        <div align="right" style="padding:5px;" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp; &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"CtkhuyenmaiSvl?userId="+userId +"&view="+view)%>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
       
    </div>
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
    		<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= ctkmBean.getMessage() %></textarea>
		         <% ctkmBean.setMessage(""); %>
    	</fieldset>
  	</div>
    <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
    <fieldset>
    	<legend class="legendtitle"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></legend>
        <div style="width:100%; float:none" align="left">
                
                <TABLE width="100%" cellpadding="4" cellspacing="0">								
                    <TR>
                        <TD class="plainlabel"><%=Utility.GLanguage("M?? CTKM",session,jedis) %> </TD>
                        <TD class="plainlabel" ><input type="text" name="scheme" id="scheme" size="30" value="<%= ctkmBean.getScheme() %>"></TD>
                    
                        <TD  class="plainlabel" ><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TD>
                        <TD class="plainlabel" colspan="3" >
                        	<input type="text" name="diengiai" id="diengiai" style="width:400px" value="<%= ctkmBean.getDiengiai() %>" >
                        </TD>
                    </TR>               
                    <TR>
                    
                     <TD class="plainlabel" ><%=Utility.GLanguage("Lo???i ch????ng tr??nh",session,jedis) %></TD>
                    <% if(!ctkmBean.getType().equals("4")){ %>
                 	<TD class="plainlabel" >
                    <%} else {%>
                	<TD class="plainlabel" colspan = "1" >
                    <%} %>
                           <select name="loaiCt" id="loaiCt" onchange="tichluy()">
                           <% if(ctkmBean.getType().equals("1")){ %>
                           	<option value="" >&nbsp;</option>
                            <option value="1" selected><%=Utility.GLanguage("B??nh th?????ng",session,jedis) %></option>
                            <option value="2"><%=Utility.GLanguage("On top",session,jedis) %></option>
                            <option value="5"><%=Utility.GLanguage("Game",session,jedis) %></option>
                           <%} else if(ctkmBean.getType().equals("2")){ %>
                           	<option value="" >&nbsp;</option>
                            <option value="1"><%=Utility.GLanguage("B??nh th?????ng",session,jedis) %></option>
                            <option value="2" selected><%=Utility.GLanguage("On top",session,jedis) %></option>
                            <option value="5"><%=Utility.GLanguage("Game",session,jedis) %></option>
                           <%}  else if(ctkmBean.getType().equals("5")) { %>
                           	<option value="" >&nbsp;</option>
                            <option value="1"><%=Utility.GLanguage("B??nh th?????ng",session,jedis) %></option>
                            <option value="2"><%=Utility.GLanguage("On top",session,jedis) %></option>
                            <option value="5" selected><%=Utility.GLanguage("Game",session,jedis) %></option>
                           <% }else{ %>
                           	<option value="" selected>&nbsp;</option>
                            <option value="1"><%=Utility.GLanguage("B??nh th?????ng",session,jedis) %></option>
                            <option value="2"><%=Utility.GLanguage("On top",session,jedis) %></option>
                            <option value="5"><%=Utility.GLanguage("Game",session,jedis) %></option>
                           <%} %>
                       	</select>
                        </TD>
                        
                        <%-- <TD class="plainlabel" width="120px"  >Lo???i ch????ng tr??nh </TD>
                        <TD class="plainlabel" width="250px" >
                            <select name="loaiCt" id="loaiCt" onchange="tichluy()">
                            <% if(ctkmBean.getType().equals("1")){ %>
                                <option value="1" selected>B??nh th?????ng</option>
                                <option value="2">On top</option>
                                <!-- <option value="3">T??ch l??y</option> -->
                                <option value="">&nbsp;</option>
                            <%} else if(ctkmBean.getType().equals("2")){ %>
                            	<option value="2" selected>On top</option>
                            	<option value="1">B??nh th?????ng</option> 
                            	<!--  <option value="3">T??ch l??y</option> -->                             
                                <option value="">&nbsp;</option>
                            <%}  else  if(ctkmBean.getType().equals("3")) { %>
                            	<option value="" >&nbsp;</option>
                            	<option value="1">B??nh th?????ng</option> 
                            	<option value="2">On top</option>   
                            <!-- 	 <option value="3" selected>T??ch l??y</option> -->                                                          
                            <% }else{ %>
                            	<option value="" selected>&nbsp;</option>
                            	<option value="1">B??nh th?????ng</option> 
                            	<option value="2">On top</option>   
                            	<!--  <option value="3">T??ch l??y</option> -->
                            <%} %>
                            </select>
                        </TD> --%>
                        <TD class="plainlabel" width="120px"  ><%=Utility.GLanguage("Kho",session,jedis) %> </TD>
                        <TD class="plainlabel"  >
                            <select name="kho" >
                            <%
                            if(khoIds != null)
                            	khoIds.beforeFirst();
                            
                            while(khoIds.next()) 
                            {
                              if(khoIds.getString("pk_seq").equals(ctkmBean.getkhoId())){ %>
                                <option value="<%=khoIds.getString("pk_seq") %>" selected><%= khoIds.getString("ten") + "-" + khoIds.getString("diengiai") %></option>
                                
                            <%}else { %>
                            	<option value="<%=khoIds.getString("pk_seq") %>"><%= khoIds.getString("ten") + "-" + khoIds.getString("diengiai") %></option>
                            <%} }%>
                            	
                            </select>
                        </TD>
                        
                        <TD class="plainlabel" width="120px"  ><%=Utility.GLanguage("Kho nh???n",session,jedis) %> </TD>
                        <TD class="plainlabel"  >
                            <select name="khonhan_fk" >
                            <%
                            if(khoIds != null)
                            	khoIds.beforeFirst();
                            while(khoIds.next()) 
                            {
                              if(khoIds.getString("pk_seq").equals(ctkmBean.getKhonhan_fk())){ %>
                                <option value="<%=khoIds.getString("pk_seq") %>" selected><%= khoIds.getString("ten") + "-" + khoIds.getString("diengiai") %></option>
                                
                            <%}else { %>
                            	<option value="<%=khoIds.getString("pk_seq") %>"><%= khoIds.getString("ten") + "-" + khoIds.getString("diengiai") %></option>
                            <%} }%>
                            	
                            </select>
                        </TD>
                       <%--  <TD class="plainlabel" width="120px"  >CT Tr??ng b??y </TD>
                        <TD class="plainlabel"  >
                            <select name="cttbId" >
                            <option value="" ></option>
                            <% while(cttbRs.next()) 
                            {
                              if(cttbRs.getString("pk_seq").equals(ctkmBean.getCttbId())){ %>
                                <option value="<%=cttbRs.getString("pk_seq") %>" selected><%= cttbRs.getString("ten") %></option>
                                
                            <%}else { %>
                            	<option value="<%=cttbRs.getString("pk_seq") %>"><%= cttbRs.getString("ten") %></option>
                            <%} } cttbRs.close(); %>
                            	
                            </select>
                        </TD> --%>
                    </TR>
                     <% if(ctkmBean.getType().equals("3")) {%>
					  <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Ng??y ????ng k??",session,jedis) %>: &nbsp;&nbsp;&nbsp;&nbsp;<%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
                        <TD class="plainlabel">
                            <input type="text" size="10" class="days" 
                                   id="tungay" name="tungay" value="<%= ctkmBean.getTungay() %>" maxlength="10" />
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%=Utility.GLanguage("?????n ng??y",session,jedis) %>   &nbsp;&nbsp;  <input type="text" size="10" class="days" 
                                    id="denngay" name="denngay" value="<%= ctkmBean.getDenngay() %>" maxlength="10" />
                         
                        </TD>
                  
                        <TD class="plainlabel" ><%=Utility.GLanguage("Ng??y t??nh doanh s???",session,jedis) %>:&nbsp;&nbsp;&nbsp;&nbsp;<%=Utility.GLanguage("T??? ng??y",session,jedis) %>y</TD>
                        <TD class="plainlabel" colspan="3" >
                            <input type="text" size="10" class="days" 
                                   id="ngayds" name="ngayds" value="<%= ctkmBean.getngayds() %>" maxlength="10" />
                             					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%=Utility.GLanguage("?????n ng??y",session,jedis) %>   &nbsp;&nbsp;  
                             <input type="text" size="10" class="days" 
                                    id="ngayktds" name="ngayktds" value="<%= ctkmBean.getngayktds() %>" maxlength="10" />
                         
                        </TD>
                    </TR>
                   
                   <% } else {%>                
                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" size="10" class="days" 
                                   id="tungay" name="tungay" value="<%= ctkmBean.getTungay() %>" maxlength="10" />
                        </TD>
                   
                        <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel" colspan="3" >
                            <input type="text" size="10" class="days"
                                    id="denngay" name="denngay" value="<%= ctkmBean.getDenngay() %>" maxlength="10" />
                        </TD>
                        
                       <%--  <TD class="plainlabel" style="color: red;" >KM k??m theo </TD>
                        <TD class="plainlabel"  >
                            <select name="ctkmId" >
                            <option value="" ></option>
                            <% while(ctkmRs.next()) 
                            {
                              if(ctkmRs.getString("pk_seq").equals(ctkmBean.getCtkmId())){ %>
                                <option value="<%=ctkmRs.getString("pk_seq") %>" selected><%= ctkmRs.getString("ten") %></option>
                                
                            <%}else { %>
                            	<option value="<%=ctkmRs.getString("pk_seq") %>"><%= ctkmRs.getString("ten") %></option>
                            <%} } ctkmRs.close(); %>
                            	
                            </select>
                        </TD> --%>
                        
                    </TR>
                    <%} %>
  <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %> </TD>
                        <TD class="plainlabel" >
                        	<a href="javascript:;" onclick="selectAllKBH();"><%=Utility.GLanguage("Ch???n t???t c???",session,jedis) %></a> | <a href="javascript:;" onclick="deSelectAllKBH();"><%=Utility.GLanguage("B??? ch???n t???t c???",session,jedis) %></a>
			<br />
                            <select name="kbhId" id="kenhId" multiple="multiple" onchange="AjaxNpp();"  >
					        
		                        <% if(kbhRS != null) {
		                         while(kbhRS.next()) 
		                         {
		                           if(ctkmBean.getKbhIds().indexOf(kbhRS.getString("pk_seq")) >= 0 ){ %>
		                             <option value="<%= kbhRS.getString("pk_seq") %>" selected style="padding: 2px" ><%= kbhRS.getString("ten") %></option>
		                         <%}else { %>
		                         	<option value="<%= kbhRS.getString("pk_seq") %>" style="padding: 2px"><%= kbhRS.getString("ten") %></option>
		                         <%} }}%>        	
		                    </select>
                        </TD>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Lo???i kh??ch h??ng",session,jedis) %> </TD>
                        <TD class="plainlabel" width="230px" colspan="4" >
                        	<a href="javascript:;" onclick="selectAllLKH();"><%=Utility.GLanguage("Ch???n t???t c???",session,jedis) %></a> | <a href="javascript:;" onclick="deSelectAllLKH();"><%=Utility.GLanguage("B??? ch???n t???t c???",session,jedis) %></a>
								<br />
                            <select name="loaikhId" id="loaikhId" multiple="multiple"  >
					        
		                        <% if(loaikhRS != null) {
		                         while(loaikhRS.next()) 
		                         {
		                           if(ctkmBean.getLoaikhIds().indexOf(loaikhRS.getString("loaiId")) >= 0 ){ %>
		                             <option value="<%= loaikhRS.getString("loaiId") %>" selected style="padding: 2px" ><%= loaikhRS.getString("loaiTen") %></option>
		                         <%}else { %>
		                         	<option value="<%= loaikhRS.getString("loaiId") %>" style="padding: 2px"><%= loaikhRS.getString("loaiTen") %></option>
		                         <%} }}%>        	
		                    </select>
		                 </TD>
		                
		                 <%-- <TD class="plainlabel" >Ph???m vi ??p d???ng </TD>   
		                 <TD class="plainlabel" >
		                    <select name="phamvi" id="phamvi" multiple="multiple"  >
		                        <% if(ctkmBean.getPhamvi().contains("0")) { %>
		                        	<option value="0" selected="selected" >Kh??ch h??ng k?? h???p ?????ng</option>
		                        <% } else { %>
		                        	<option value="0" >Kh??ch h??ng k?? h???p ?????ng</option>
		                        <% } %>	
		                        <% if(ctkmBean.getPhamvi().contains("1")) { %>
		                        	<option value="1" selected="selected" >Kh??ch h??ng kh??ng k?? h???p ?????ng</option>
		                        <% } else { %>
		                        	<option value="1" >Kh??ch h??ng kh??ng k?? h???p ?????ng</option>
		                        <% } %>	
		                    </select>
                        </TD> --%>
                    </TR>
                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Nh??m kh??ch h??ng",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <select name="nhomkhnpp">
                            <option value=""> </option>
                            <% while(Nhomkhnpp.next()) 
                            {
                              if(Nhomkhnpp.getString("pk_seq").equals(ctkmBean.getNhomkhnppId())){ %>
                                <option value="<%= Nhomkhnpp.getString("pk_seq") %>" selected><%=Nhomkhnpp.getString("diengiai") %></option>
                                
                            <%}else { %>
                            	<option value="<%= Nhomkhnpp.getString("pk_seq") %>"><%= Nhomkhnpp.getString("diengiai") %></option>
                            <%}}%>
                            	
                            </select>
                        </TD>
                        
                         <TD class="plainlabel" ><%=Utility.GLanguage("Lo???i ng??n s??ch",session,jedis) %> </TD>
                        <TD class="plainlabel" colspan="3" >
                            <% if(ctkmBean.getLoaiNganSach().equals("1")){ %>
                            	<input type="radio" name="loains" value = "1" checked = "checked" id="apphanbo" ><%=Utility.GLanguage("??p ph??n b???",session,jedis) %> &nbsp;&nbsp;
                            	<input type="radio" name="loains" value = "0"  id="khonggh"><%=Utility.GLanguage("Kh??ng gi???i h???n ng??n s??ch",session,jedis) %>
                            <%}else{ %>
                            	<input type="radio" name="loains" value = "1"  id="apphanbo" ><%=Utility.GLanguage("??p ph??n b???",session,jedis) %> &nbsp;&nbsp;
                            	<input type="radio" name="loains" value= "0" checked="checked" id="khonggh" ><%=Utility.GLanguage("Kh??ng gi???i h???n ng??n s??ch",session,jedis) %>
                            <%} %>
                        </TD>
                   
                        <%-- <TD class="plainlabel" >Lo???i ng??n s??ch </TD>
                        <TD class="plainlabel" colspan="3" >
                            <% if(ctkmBean.getLoaiNganSach().equals("1")){ %>
                            <!-- 	<input type="radio" name="loains" value = "1" checked = "checked" id="apphanbo" >??p ph??n b??? &nbsp;&nbsp; -->
                            	<input type="radio" name="loains" value = "0"  id="khonggh" checked="checked" >Kh??ng gi???i h???n ng??n s??ch
                            <%}else{ %>
                            	<!-- <input type="radio" name="loains" value = "1"  id="apphanbo" >??p ph??n b??? &nbsp;&nbsp; -->
                            	<input type="radio" name="loains" value= "0" checked="checked" id="khonggh" >Kh??ng gi???i h???n ng??n s??ch
                            <%} %>
                        </TD> --%>
                    </TR>
                   
                    <TR>
                    
					
						<TD class="plainlabel"><%=Utility.GLanguage("H???ng c???a h??ng",session,jedis) %></TD>
						<TD class="plainlabel" width="230px" >
							
		<a href="javascript:;" onclick="selectAll();"><%=Utility.GLanguage("Ch???n t???t c???",session,jedis) %></a> | <a href="javascript:;" onclick="deSelectAll();"><%=Utility.GLanguage("B??? ch???n t???t c???",session,jedis) %></a>
			<br />
						<select	name="_hangCuaHangs" id="_hangCuaHangs" multiple="multiple" onchange="HangAll();">
												
								<%
								if(_hangCuaHangs != null)
								{
									while(_hangCuaHangs.next())
									{
										if(ctkmBean.getHangCuaHangIds().indexOf(_hangCuaHangs.getString("PK_SEQ"))>=0 )
										{
											%>
											<option value="<%=_hangCuaHangs.getString("PK_SEQ")%>" selected style="padding: 2px"><%=_hangCuaHangs.getString("DIENGIAI")%></option>
											<%						
										}
										else
										{
											%>
											<option value="<%=_hangCuaHangs.getString("PK_SEQ")%>" style="padding: 2px"><%=_hangCuaHangs.getString("DIENGIAI")%></option>
											<%											
										}
									}
									_hangCuaHangs.close();
								}
								%>

						</select>
					
				
						</TD>
                    	
                    
                    
                   <%--      <TD class="plainlabel">Lo???i ??p d???ng</TD>
						<TD class="plainlabel" width="230px" colspan="4">
						<select	name="loaiapdung"  multiple="multiple">
							<% if(ctkmBean.getLoaiapdung().contains("0")) { %>
								<option value="0" selected="selected" >Kh??ng c?? khuy???n m???i</option>		
							<% } else { %>	
								<option value="0" >Kh??ng c?? khuy???n m???i</option>	
							<% } %>							
							
							<% if(ctkmBean.getLoaiapdung().contains("1")) { %>
								<option value="1" selected="selected" >C?? khuy???n m???i</option>		
							<% } else { %>	
								<option value="1" >C?? khuy???n m???i</option>	
							<% } %>		
								
						</select>
						</TD> --%>
                    
                        <TD class="plainlabel" colspan="5" >
                         <% if (ctkmBean.getAp_dung_npp().equals("1")){ %> 
                        		<input type="checkbox" name="ap_dung_npp"  value="1" checked="checked"><i><%=Utility.GLanguage("KM Sell in",session,jedis) %></i>
                        	<% } else { %>
                        		<input type="checkbox" name="ap_dung_npp"   value="1" ><i><%=Utility.GLanguage("KM Sell in",session,jedis) %></i>
                        	 <% }  %> 	
                         </br>
                        	<% if (ctkmBean.getSanphamdautien().equals("1")){ %> 
                        		<input type="checkbox" name="sanphamdautien"  value="1" checked="checked"><i><%=Utility.GLanguage("??p d???ng KH l???n ?????u mua SP",session,jedis) %></i>
                        	<% } else { %>
                        		<input type="checkbox" name="sanphamdautien"   value="0" ><i><%=Utility.GLanguage("??p d???ng KH l???n ?????u mua SP",session,jedis) %></i>
                        	 <% }  %> 	
                        	 </br>
                        	  <% if (ctkmBean.getKhong_tich_luy().equals("1")){ %> 
                        		<input type="checkbox" name="khong_tich_luy"  value="1" checked="checked"><i>Kh??ng t??nh t??ch l??y</i>
                        	<% } else { %>
                        		<input type="checkbox" name="khong_tich_luy"   value="1" ><i>Kh??ng t??nh t??ch l??y</i>
                        	 <% }  %> 	
                        </br>
                        
                        <% if (ctkmBean.getPhanbomucTDV().equals("1")){ %> 
                        		<input type="checkbox" name="phanbotdv"  value="1" checked="checked"><i>Ph??n b??? theo m???c tr??nh d?????c vi??n</i>
                        	<% } else { %>
                        		<input type="checkbox" name="phanbotdv"   value="1" ><i>Ph??n b??? theo m???c tr??nh d?????c vi??n</i>
                        	 <% }  %> 	
                        </br>
                        
                           <%--   <% if (ctkmBean.getApdungchoDHLe().equals("1")){ %> 
                        		<input type="checkbox" name="ApDUNGCHODHLE" value="1" checked="checked"><i> ??p d???ng cho ????n h??ng l???</i>
                        	<% } else { %>
                        		<input type="checkbox" name="ApDUNGCHODHLE" value="1" ><i> ??p d???ng cho ????n h??ng l???</i>
                        	 <% }  %> 
                        	 --%>
                        	<br />
                        
                        	<%-- <% if (ctkmBean.getXuatHDCoVAT().equals("1")){ %> 
                        		<input type="checkbox" name="xuatHDCoVAT"  value="1" checked="checked"><i> Xu???t h??a ????n c?? VAT</i>
                        	<% } else { %>
                        		<input type="checkbox" name="xuatHDCoVAT"   value="1" ><i> Xu???t h??a ????n c?? VAT</i>
                        	 <% }  %> 
                        	
                        	<br /> --%>
                        
                        	 <% if (ctkmBean.getPhanbotheoDH().equals("1")){ %> 
                        		<input type="checkbox" name="PHANBOTHEODH" value="1" checked="checked" onchange="submitform()" ><i> <%=Utility.GLanguage("Ph??n b??? ng??n s??ch theo s??? su???t",session,jedis) %></i>
                        		</br>
                        		<div id="SoXuatToiDa" style="display: block;">
	                        		<i>S??? su???t t???i ??a: </i>
	                        		<input name="SoXuatToiDa_in" id="SoXuatToiDa_in" type="text" onkeypress="return keypress(event);" style="text-align:right; width: 35px;" value="<%= ctkmBean.getSoXuatToiDa()==null?"":ctkmBean.getSoXuatToiDa()%>" >
                        		</div>
                        	<% } else { %>
                        		<input type="checkbox" name="PHANBOTHEODH" value="0" onchange="submitform()" ><i> <%=Utility.GLanguage("Ph??n b??? ng??n s??ch theo s??? su???t",session,jedis) %></i>
                        		</br>
                        		<div id="SoXuatToiDa" style="display: none;">
                        			<i>S??? su???t t???i ??a: </i>
                        			<input name="SoXuatToiDa_in" id="SoXuatToiDa_in" type="text" onkeypress="return keypress(event);" style="text-align:right; width: 35px;" value="<%= ctkmBean.getSoXuatToiDa()==null?"":ctkmBean.getSoXuatToiDa()%>" >
                        		</div>
                        	 <% }  %>
                        	 
                        	 <%-- 	 	<br />
                        	 <%  
                        	 if (ctkmBean.getInchung().equals("1")){ %> 
                        		<input type="checkbox" name="inchung"  value="1" checked="checked"><i> In chung h??a ????n h??ng b??n</i>
                        	<% } else { %>
                        		<input type="checkbox" name="inchung"   value="1" ><i>In chung h??a ????n h??ng b??n</i>
                        	 <% }  %>  --%>
                        	
                        </TD>
                    </TR>
                    
                      <TR>
                    	<TD class="plainlabel"><%=Utility.GLanguage("Lo???i ph??n b???",session,jedis) %></TD>
                    	<TD class="plainlabel"  colspan="5">
                    		<select name="loaipb" id="loaipb">
                    			<option value="">&nbsp;</option>
                    		 	<% if(ctkmBean.getloaiPhanbo().equals("0")){ %>
                    		 	<option value="0" selected><%=Utility.GLanguage("Theo s??? ti???n",session,jedis) %></option>
                            	<option value="1"><%=Utility.GLanguage("Theo s??? su???t",session,jedis) %></option>
                    			<%} else if(ctkmBean.getloaiPhanbo().equals("1")){ %>
                    			<option value="0"><%=Utility.GLanguage("Theo s??? ti???n",session,jedis) %></option>
                            	<option value="1" selected><%=Utility.GLanguage("Theo s??? su???t",session,jedis) %></option>
                            	<%} else { %>
                            	<option value="0"><%=Utility.GLanguage("Theo s??? ti???n",session,jedis) %></option>
                            	<option value="1"><%=Utility.GLanguage("Theo s??? su???t",session,jedis) %></option>
                            	<%} %>
                    		</select>
                    	</TD>
                    </TR>        
                </TABLE>         
         </div>
        
      <div id="accordion" style="width:100%; height:auto; float:none; font-size:1.0em" align="left">
                     
       <h1 style="font-size:1.8em"><a href="#"><%=Utility.GLanguage("Khai b??o ??i???u ki???n khuy???n m???i",session,jedis) %></a></h1>
			<div style="height:auto">
			
                <TABLE class="tabledetail" width="100%" border="0" cellspacing="1px" cellpadding="0px">
                    <TR class="tbheader">
                        <TH align="center" width="10%"> <%=Utility.GLanguage("M??",session,jedis) %></TH>
                        <TH align="left" width="50%"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TH>
                        <TH align="center" width="10%"> <%=Utility.GLanguage("T???ng l?????ng",session,jedis) %></TH>
                        <TH align="center" width="10%"> <%=Utility.GLanguage("T???ng ti???n",session,jedis) %></TH>
                        <TH align="center" width="10%"> <%=Utility.GLanguage("Ph??p to??n",session,jedis) %></TH>
                    </TR>
					<% 
						int i = 0;
                        System.out.println("So DKKM lay duoc: " + dkkmList.size());
						if(dkkmList.size() > 0)
						{ 
						while(i < dkkmList.size())
						{	
							Dieukienkm dkkm = (Dieukienkm)dkkmList.get(i);	
							
							IDieukienDetail dkkmDetai = dkkm.getDieukienDetail();
							List<ISanpham> spList = dkkmDetai.getSpList();
					%>
							
							<TR class='tbdarkrow'>
	                        <TD align="center">
	                        	<input type="text" id='dkkmId<%= i %>' name="dkkmId" value="<%= dkkm.getId() %>"  onkeyup="ajax_showOptions(this,'dieukien',event)" AUTOCOMPLETE="off" style="width: 75%"  onchange="deleleDkMuakmDetail(<%=i%>)">
		                        
		                        <a class="dieukienkhuyenmai<%= i %>" href="#" onclick ="AjaxDKKM(<%=i%> )" >
		                        		<img style="top: -4px;" src="../images/vitriluu.png" title="T???o m???i ??i???u ki???n"></a>
				                <div style='display:none'>
			                        <div id='dieukienkhuyenmai<%= i %>' style='padding:0px 5px; background:#fff;'>
			                        	<h4 align="left"><%=Utility.GLanguage("T???o m???i",session,jedis) %> ??i???u ki???n khuy???n m???i</h4>
										<table cellpadding="4px" cellspacing="2px" width="100%" align="center">
			                            	<tr>
			                                	<td width="40%"  align="left"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></td>
			                                    <td  align="left">
				                                    <input type="text" name="dieukienkhuyenmai<%= i %>.diengiai" id="dieukienkhuyenmai<%= i %>.diengiai" value="<%= dkkmDetai.getDiengiai() %>" />
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("Lo???i ??i???u ki???n",session,jedis) %></td>
			                                    <td  align="left">
			                                    	<select name="dieukienkhuyenmai<%= i %>.loaidieukien" id = "dieukienkhuyenmai<%= i %>.loaidieukien">
			                                    		<% if(dkkmDetai.getLoaidieukien().equals("1")){ %>
				                                    		<option value="2"><%=Utility.GLanguage("B???t k??? trong",session,jedis) %></option>
				                                    		<option value="1" selected="selected"><%=Utility.GLanguage("B???t bu???c nh???p s??? l?????ng",session,jedis) %></option>
			                                    		<%} else { %>
			                                    			<option value="2" selected="selected"><%=Utility.GLanguage("B???t k??? trong",session,jedis) %></option>
				                                    		<option value="1"><%=Utility.GLanguage("B???t bu???c nh???p s??? l?????ng",session,jedis) %></option>
			                                    		<%} %>
			                                    	</select>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("H??nh th???c",session,jedis) %></td>
			                                    <td  align="left">
			                                    	<select name = "dieukienkhuyenmai<%= i %>.hinhthuc" id = "dieukienkhuyenmai<%= i %>.hinhthuc" >
			                                    	<% if(dkkmDetai.getHinhthuc().equals("2")){ %>
			                                    		<option value="1"><%=Utility.GLanguage("Nh???p t???ng l?????ng",session,jedis) %></option>
			                                    		<option value="2" selected="selected"><%=Utility.GLanguage("Nh???p t???ng ti???n",session,jedis) %></option>
			                                    	<%} else { %>
			                                    		<option value="1" selected="selected"><%=Utility.GLanguage("Nh???p t???ng l?????ng",session,jedis) %></option>
			                                    		<option value="2"><%=Utility.GLanguage("Nh???p t???ng ti???n",session,jedis) %></option>
			                                    	<%} %>
			                                    	</select>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("T???ng l?????ng / T???ng ti???n",session,jedis) %></td>
			                                    <td  align="left">
			                                    	<input type="text" name="dieukienkhuyenmai<%= i %>.sotong" id="dieukienkhuyenmai<%= i %>.sotong" 
			                                    			value="<%= dkkmDetai.getSotong() %>" style="text-align: right;"/>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("Nh??m s???n ph???m",session,jedis) %></td>
			                                    <td  align="left">		                                    	
			                                    	<select name="dieukienkhuyenmai<%= i %>.nhomsanpham" id="dieukienkhuyenmai<%= i %>.nhomsanpham" onChange = "ajaxOption(this.id, this.value, <%= i %>)">
			                                    		<option value=""> </option>
			                                    		<% if(nhomspRs != null)
			                                    		{ 
			                                    			nhomspRs.beforeFirst();
			                                    			while(nhomspRs.next()){ if(dkkmDetai.getNhomspId().equals(nhomspRs.getString("nspId"))){ %>
			                                    				<option value="<%= nhomspRs.getString("nspId") %>"><%= nhomspRs.getString("nspTen") %></option>
			                                    		<% } else { %> 
			                                    				<option value="<%= nhomspRs.getString("nspId") %>"><%= nhomspRs.getString("nspTen") %></option>
			                                    		 <%} } } %>
			                                    	</select>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("T??nh theo",session,jedis) %></td>
			                                	<td  align="left" >
			                                	<select name="dieukienkhuyenmai<%= i %>.tinhtheothung" id="dieukienkhuyenmai<%= i %>.tinhtheothung"  style="width: 200px;"   >
										
													<%
														String[] data ={"????n v??? chu???n","????n v??? th??ng","??i???m"};  
													
														for(int x=0 ; x< data.length;x++)
														{
												
															if( x == dkkm.isTheothung() )
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
			                                </tr>
			                                <tr>
			                                	<td colspan="2">
			                                		<table align="left" cellpadding="2px" cellspacing="2px">
				                                		<tr>
				                                			<th width="100px" align="center"><%=Utility.GLanguage("M?? s???n ph???m",session,jedis) %></th>
				                                			<th width="250px" align="left"><%=Utility.GLanguage("T??n s???n ph???m",session,jedis) %></th>
				                                			<th width="60px" align="left"><%=Utility.GLanguage("S??? l?????ng",session,jedis) %></th>
				                                		</tr>
				                                	</table>
				                                	<div id="dieukienkhuyenmai<%= i %>.tbsanpham" style="width: 100%; max-height: 150px; overflow: auto">
				                                	<table align="left" cellpadding="2px" cellspacing="2px">
				                                	<% 
				                                	int count = 0;
				                                	while(count < spList.size())
				                                	{
				                                		ISanpham sp = spList.get(count);
				                                		%>
				                                		<tr>
				                                			<td width="100px" align="center">
				                                				<input type="text" value="<%= sp.getMasanpham() %>" style="width: 100px" name="dieukienkhuyenmai<%= i %>.masanpham" 
				                                						onkeyup="ajax_showOptions(this,'sanpham',event)" AUTOCOMPLETE="off">
				                                			</td>
				                                			<td width="250px" align="left">
				                                				<input type="text" value="<%= sp.getTensanpham() %>" name="dieukienkhuyenmai<%= i %>.tensanpham" style="width: 250px" AUTOCOMPLETE="off" readonly>
				                                			</td>
				                                			<td width="60px" align="center">
				                                				<input type="text" value="<%= sp.getSoluong() %>" name="dieukienkhuyenmai<%= i %>.soluong" style="width: 60px; text-align: right;" AUTOCOMPLETE="off">
				                                			</td>
				                                		</tr>
				                                	<% count++; } %>
				                                	<% for(int pos=count; pos < 50; pos++){ %>
				                                		<tr>
				                                			<td width="100px" align="center">
				                                				<input type="text" value="" style="width: 100px" name="dieukienkhuyenmai<%= i %>.masanpham" 
				                                						onkeyup="ajax_showOptions(this,'sanpham',event)" AUTOCOMPLETE="off"  >
				                                			</td>
				                                			<td width="250px" align="left">
				                                				<input type="text" value="" name="dieukienkhuyenmai<%= i %>.tensanpham" style="width: 250px" >
				                                			</td>
				                                			<td width="60px" align="center">
				                                				<input type="text" value="" name="dieukienkhuyenmai<%= i %>.soluong" style="width: 60px; text-align: right;">
				                                			</td>
				                                		</tr>
				                                	<%} %>
			                                		</table>
			                                		</div>
			                                	</td>
			                                </tr>
			                                <tr>
			                                	<td  align="left" colspan="2">
			        								<a class="button" href="javascript:submitform();">
			        								<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Nh???p l???i",session,jedis) %>  </a>
			        								&nbsp;&nbsp;&nbsp;
			        								<a class="button" href="javascript:submitform2(<%= i %>);">
			        								<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("L??u ??i???u ki???n",session,jedis) %>  </a>
			                                	</td>
			                                </tr>
			                            </table>
									</div>
				                </div>
		                        
	                        </TD>
	                        <TD align="left"><input type="text" name="dkkmDiengiai" id="dkkmDiengiai<%= i %>" size="80" value="<%= dkkm.getDiengiai() %>" readonly></TD>
	                        <TD align="center"><input type="text" name="dkkmTongluong" id="dkkmTongluong<%= i %>" size="6" value="<%= dkkm.getTongluong() %>" style="text-align:right" readonly></TD>							           
	                        <TD align="center"><input type="text" name="dkkmTongtien" id="dkkmTongtien<%= i %>"  size="6" value="<%= dkkm.getTongtien() %>" style="text-align:right" readonly></TD>
	                        <TD align="center">
	                        	<select name="dkkmPheptoan">
	                        	<% if(dkkm.getPheptoan().trim().equals("1")){ %>
	                            	<option value="1" selected="selected"><%=Utility.GLanguage("And",session,jedis) %></option>
	                                <option value="2"><%=Utility.GLanguage("Or",session,jedis) %></option>  
	                                <option value=""></option>   
	                            <%} else { if(dkkm.getPheptoan().trim().equals("2")){ %>
	                            	<option value="1"><%=Utility.GLanguage("And",session,jedis) %></option>
	                                <option value="2" selected="selected"><%=Utility.GLanguage("Or",session,jedis) %></option>  
	                                <option value=""></option>  
	                            <% } else { %>
	                            	<option value="" selected> </option> 
	                            	<option value="1"><%=Utility.GLanguage("And",session,jedis) %></option>
	                                <option value="2"><%=Utility.GLanguage("Or",session,jedis) %></option>  
	                             <%} } %>
	                            </select>
	                            <input type="hidden" name="dkkmThutu" size="6" value="<%= i %>" style="text-align:right">
	                        </TD>
	                    </TR>
		                   
                    	<% i++; } } %>
                    	<% for(int j = i; j < 5; j++){ %>
                    		<TR class='tbdarkrow'>
		                        <TD align="center">
		                        	<input type="text" id='dkkmId<%= j %>' name="dkkmId" size="10" value="" onkeyup="ajax_showOptions(this,'dieukien',event)" 
		                        		 AUTOCOMPLETE="off" style="width: 75%"  onchange="deleleDkMuakmDetail(<%=j%>)">
			                        
			                        <a class="dieukienkhuyenmai<%= j %>" href="#"  onclick ="AjaxDKKM(<%=j%> )"  >
			                        		<img style="top: -4px;" src="../images/vitriluu.png" title="T???o m???i ??i???u ki???n"></a>
					                <div style='display:none'>
				                        <div id='dieukienkhuyenmai<%= j %>' style='padding:0px 5px; background:#fff;'>
				                        	<h4 align="left"><%=Utility.GLanguage("T???o m???i",session,jedis) %> <%=Utility.GLanguage("??i???u ki???n khuy???n m???i",session,jedis) %></h4>
											<table cellpadding="4px" cellspacing="2px" width="100%" align="center">
				                            	<tr>
				                                	<td width="40%"  align="left"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></td>
				                                    <td  align="left">
					                                    <input type="text" name="dieukienkhuyenmai<%= j %>.diengiai" id="dieukienkhuyenmai<%= j %>.diengiai" value="" />
				                                    </td>
				                                </tr>
				                                <tr>
				                                	<td  align="left"><%=Utility.GLanguage("Lo???i ??i???u ki???n",session,jedis) %></td>
				                                    <td  align="left">
				                                    	<select name="dieukienkhuyenmai<%= j %>.loaidieukien" id = "dieukienkhuyenmai<%= j %>.loaidieukien">
				                                    		<option value="2"><%=Utility.GLanguage("B???t k??? trong",session,jedis) %></option>
				                                    		<option value="1"><%=Utility.GLanguage("B???t bu???c nh???p s??? l?????ng",session,jedis) %></option>
				                                    	</select>
				                                    </td>
				                                </tr>
				                                <tr>
				                                	<td  align="left"><%=Utility.GLanguage("H??nh th???c",session,jedis) %></td>
				                                    <td  align="left">
				                                    	<select name = "dieukienkhuyenmai<%= j %>.hinhthuc" id = "dieukienkhuyenmai<%= j %>.hinhthuc" >
				                                    		<option value="1"><%=Utility.GLanguage("Nh???p t???ng l?????ng",session,jedis) %></option>
				                                    		<option value="2"><%=Utility.GLanguage("Nh???p t???ng ti???n",session,jedis) %></option>
				                                    	</select>
				                                    </td>
				                                </tr>
				                                <tr>
				                                	<td  align="left"><%=Utility.GLanguage("T???ng l?????ng / T???ng ti???n",session,jedis) %></td>
				                                    <td  align="left">
				                                    	<input type="text" name="dieukienkhuyenmai<%= j %>.sotong" id="dieukienkhuyenmai<%= j %>.sotong" value="" style="text-align: right;"/>
				                                    </td>
				                                </tr>
				                                <tr>
				                                	<td  align="left"><%=Utility.GLanguage("Nh??m s???n ph???m",session,jedis) %></td>
				                                    <td  align="left">		                                    	
				                                    	<select name="dieukienkhuyenmai<%= j %>.nhomsanpham" id="dieukienkhuyenmai<%= j %>.nhomsanpham" onChange = "ajaxOption(this.id, this.value, <%= j %>)">
				                                    		<option value=""> </option>
				                                    		<% if(nhomspRs != null)
				                                    		{ 
				                                    			nhomspRs.beforeFirst();
				                                    			while(nhomspRs.next()){ %>
				                                    				<option value="<%= nhomspRs.getString("nspId") %>"><%= nhomspRs.getString("nspTen") %></option>
				                                    		<%} } %>
				                                    	</select>
				                                    </td>
				                                </tr>
				                                <tr>
				                                	
				                                	<td  align="left"><%=Utility.GLanguage("T??nh theo",session,jedis) %></td>
			                                	<td  align="left" >
			                                	<select name="dieukienkhuyenmai<%= j %>.tinhtheothung" id="dieukienkhuyenmai<%= j %>.tinhtheothung"  style="width: 200px;"   >
										
													<%
														String[] data ={"????n v??? chu???n","????n v??? th??ng","??i???m"};  
													
														for(int x=0 ; x< data.length;x++)
														{
												
															%>
																<option value="<%= x%>"  ><%=data[x] %></option>
															<%
														}
													%>							
												</select>
									
			                                	</td>
				                                </tr>
				                                <tr>
				                                	<td colspan="2">
				                                		<table align="left" cellpadding="2px" cellspacing="2px">
					                                		<tr>
					                                			<th width="100px" align="center"><%=Utility.GLanguage("M?? s???n ph???m",session,jedis) %></th>
					                                			<th width="250px" align="left"><%=Utility.GLanguage("T??n s???n ph???m",session,jedis) %></th>
					                                			<th width="60px" align="left"><%=Utility.GLanguage("S??? l?????ng",session,jedis) %></th>
					                                		</tr>
					                                	</table>
					                                	<div id="dieukienkhuyenmai<%= j %>.tbsanpham" style="width: 100%; max-height: 150px; overflow: auto">
					                                	<table align="left" cellpadding="2px" cellspacing="2px">
					                                	<% for(int pos=0; pos < 50; pos++){ %>
					                                		<tr>
					                                			<td width="100px" align="center">
					                                				<input type="text" value="" style="width: 100px" name="dieukienkhuyenmai<%= j %>.masanpham" 
					                                						onkeyup="ajax_showOptions(this,'sanpham',event)" AUTOCOMPLETE="off">
					                                			</td>
					                                			<td width="250px" align="left">
					                                				<input type="text" value="" name="dieukienkhuyenmai<%= j %>.tensanpham" style="width: 250px" readonly>
					                                			</td>
					                                			<td width="60px" align="center">
					                                				<input type="text" value="" name="dieukienkhuyenmai<%= j %>.soluong" style="width: 60px; text-align: right;">
					                                			</td>
					                                		</tr>
					                                	<%} %>
				                                		</table>
				                                		</div>
				                                	</td>
				                                </tr>
				                                <tr>
				                                	<td  align="left" colspan="2">
				        								<a class="button" href="javascript:submitform();">
				        								<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Nh???p l???i",session,jedis) %>  </a>
				        								&nbsp;&nbsp;&nbsp;
				        								<a class="button" href="javascript:submitform2(<%= j %>);">
				        								<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("L??u ??i???u ki???n",session,jedis) %>  </a>
				                                	</td>
				                                </tr>
				                            </table>
										</div>
					                </div>
			                        
		                        </TD>
		                        <TD align="left"><input type="text" name="dkkmDiengiai" id="dkkmDiengiai<%= j %>" size="80" value="" readonly></TD>
		                        <TD align="center"><input type="text" name="dkkmTongluong" id="dkkmTongluong<%= j %>" size="6" value="" style="text-align:right" readonly></TD>							           
		                        <TD align="center"><input type="text" name="dkkmTongtien" id="dkkmTongtien<%= j %>"  size="6" value="" style="text-align:right" readonly></TD>
		                        <TD align="center">
		                        	<select name="dkkmPheptoan">
		                            	<option value="" selected></option>
		                            	<option value="1"><%=Utility.GLanguage("And",session,jedis) %></option>
		                                <option value="2"><%=Utility.GLanguage("Or",session,jedis) %></option>     
		                            </select>
		                            <input type="hidden" name="dkkmThutu" size="6" value="<%= j %>" style="text-align:right">
		                        </TD>
		                    </TR>
                    	<%} %>
                    <TR>
                        <TD align="center" colspan="10" class="tbfooter">&nbsp;</TD>
                    </TR>
				</TABLE>
				
			 </div>
			 <h1 style="font-size:1.8em"><a href="#"><%=Utility.GLanguage("tr??? khuy???n m??i",session,jedis) %></a></h1> 
             <div style="height:auto">
              <TABLE class="tabledetail" width="100%" border="0" cellspacing="1px" cellpadding="0px">
              
                  <TR class="tbheader">
                      <TH align="center" width="10%"> <%=Utility.GLanguage("M??",session,jedis) %> </TH>
                      <TH align="left" width="40%" ><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TH>
                      <TH align="center" width="10%" > <%=Utility.GLanguage("T???ng l?????ng",session,jedis) %></TH>
                      <TH align="center" width="10%"> <%=Utility.GLanguage("T???ng ti???n",session,jedis) %></TH>
                      <TH align="center" width="10%"> <%=Utility.GLanguage("Chi???t kh???u",session,jedis) %></TH>
                      <TH align="center" width="10%" > <%=Utility.GLanguage("Ph??p to??n",session,jedis) %></TH>
                  </TR>
                  <% 
						int pos = 0;
						if(trakmList.size() > 0)
						{ 
						while(pos < trakmList.size())
						{	
							Trakm tkm = (Trakm)trakmList.get(pos);	

							ITrakmDetail tkmDetai = tkm.getTraDetail();
							List<ISanpham> spTraList = tkmDetai.getSpList();
					%>
							
							<TR class='tbdarkrow'>
	                        <TD align="center">
	                        	<input type="text" id='trakmId<%= pos %>' name="trakmId" size="10" value="<%= tkm.getId() %>" 
	                        				onkeyup="ajax_showOptions(this,'trakhuyenmai',event)" AUTOCOMPLETE="off" style="width: 75%"  onchange="deleleDkTraKmDetail(<%=pos %>)" >
		                        
		                        <a class="trakhuyenmai<%= pos %>" href="#" onclick ="AjaxTraKM(<%=pos%> )">
		                        		<img style="top: -4px;" src="../images/vitriluu.png" title="T???o m???i tr??? khuy???n m???i"></a>
				                <div style='display:none'>
			                        <div id='trakhuyenmai<%= pos %>' style='padding:0px 5px; background:#fff;'>
			                        	<h4 align="left"><%=Utility.GLanguage("T???o m???i",session,jedis) %> <%=Utility.GLanguage("tr??? khuy???n m???i",session,jedis) %></h4>
										<table cellpadding="4px" cellspacing="2px" width="100%" align="center">
			                            	<tr>
			                                	<td width="40%"  align="left"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></td>
			                                    <td  align="left">
				                                    <input type="text" name="trakhuyenmai<%= pos %>.diengiai" id="trakhuyenmai<%= pos %>.diengiai" value="<%= tkmDetai.getDiengiai() %>" />
			                                    </td>
			                                </tr>
			                                <tr>
			                                
			                                
			                                	<td  align="left"><%=Utility.GLanguage("Lo???i tr???",session,jedis) %></td>
			                                    <td  align="left">
			                                    	<select name="trakhuyenmai<%= pos %>.loaitra" id = "trakhuyenmai<%= pos %>.loaitra">
			                                    		<% if(tkmDetai.getLoaitra().equals("1")){ %>
			                                    			<option value="1" selected="selected"><%=Utility.GLanguage("Tr??? ti???n",session,jedis) %></option>
			                                    			<option value="3"><%=Utility.GLanguage("Tr??? s???n ph???m",session,jedis) %></option>
			                                    			<option value="2"><%=Utility.GLanguage("Tr??? chi???t kh???u",session,jedis) %></option>
			                                    			<option value="4"><%=Utility.GLanguage("Tr??? ti???n theo ??i???m",session,jedis) %></option>
			                                    		<%} else  if (tkmDetai.getLoaitra().equals("2")){  %>
			                                    			<option value="2" selected="selected"><%=Utility.GLanguage("Tr??? chi???t kh???u",session,jedis) %></option>
			                                    			<option value="3"><%=Utility.GLanguage("Tr??? s???n ph???m",session,jedis) %></option>
			                                    			<option value="1"><%=Utility.GLanguage("Tr??? ti???n",session,jedis) %></option>
			                                    			<option value="4"><%=Utility.GLanguage("Tr??? ti???n theo ??i???m",session,jedis) %></option>
			                                    		<%} else  if (tkmDetai.getLoaitra().equals("4")){  %>
			                                    			<option value="2" ><%=Utility.GLanguage("Tr??? chi???t kh???u",session,jedis) %></option>
			                                    			<option value="3"><%=Utility.GLanguage("Tr??? s???n ph???m",session,jedis) %></option>
			                                    			<option value="1"><%=Utility.GLanguage("Tr??? ti???n",session,jedis) %></option>
			                                    			<option value="4" selected><%=Utility.GLanguage("Tr??? ti???n theo ??i???m",session,jedis) %></option>
			                                    		<%}else { %>
			                                    			<option value="3" selected="selected"><%=Utility.GLanguage("Tr??? s???n ph???m",session,jedis) %></option>
			                                    			<option value="1"><%=Utility.GLanguage("Tr??? ti???n",session,jedis) %></option>
			                                    			<option value="2"><%=Utility.GLanguage("Tr??? chi???t kh???u",session,jedis) %></option>
			                                    			<option value="4"><%=Utility.GLanguage("Tr??? ti???n theo ??i???m",session,jedis) %></option>
			                                    		 <%}  %>
			                                    	</select>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("H??nh th???c",session,jedis) %></td>
			                                    <td  align="left">
			                                    	<select name = "trakhuyenmai<%= pos %>.hinhthuc" id = "trakhuyenmai<%= pos %>.hinhthuc" >
			                                    	<% if(tkmDetai.getHinhthuc().equals("1")){ %>
			                                    		<option value="2"><%=Utility.GLanguage("B???t k??? trong",session,jedis) %></option>
			                                    		<option value="1" selected="selected" ><%=Utility.GLanguage("B???t bu???c nh???p s??? l?????ng",session,jedis) %></option>
			                                    	<%} else { %>
			                                    		<option value="2" selected="selected"><%=Utility.GLanguage("B???t k??? trong",session,jedis) %></option>
			                                    		<option value="1"><%=Utility.GLanguage("B???t bu???c nh???p s??? l?????ng",session,jedis) %></option>
			                                    	<%} %>
			                                    	</select>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("T???ng l?????ng / T???ng ti???n",session,jedis) %></td>
			                                    <td  align="left">
			                                    	<input type="text" name="trakhuyenmai<%= pos %>.sotong" id="trakhuyenmai<%= pos %>.sotong" 
			                                    			value="<%= tkmDetai.getSotong() %>" style="text-align: right;"/>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("Nh??m s???n ph???m",session,jedis) %></td>
			                                    <td  align="left">		                                    	
			                                    	<select name="trakhuyenmai<%= pos %>.nhomsanpham" id="trakhuyenmai<%= pos %>.nhomsanpham" onChange = "ajaxOption2(this.id, this.value, <%= pos %>)">
			                                    		<option value=""> </option>
			                                    		<% if(nhomspRs != null)
			                                    		{ 
			                                    			nhomspRs.beforeFirst();
			                                    			while(nhomspRs.next()){ if(tkmDetai.getNhomspId().equals(nhomspRs.getString("nspId"))){ %>
			                                    				<option value="<%= nhomspRs.getString("nspId") %>"><%= nhomspRs.getString("nspTen") %></option>
			                                    		<% } else { %> 
			                                    				<option value="<%= nhomspRs.getString("nspId") %>"><%= nhomspRs.getString("nspTen") %></option>
			                                    		 <%} } } %>
			                                    	</select>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left" colspan="2">
			                                	<% if(tkm.isTheothung() ) { %>
			                                		<input type="checkbox" name="trakhuyenmai<%= pos %>.tinhtheothung" id="trakhuyenmai<%= pos %>.tinhtheothung"  value='1' checked="checked" > <span style="font-style: italic;"><%=Utility.GLanguage("S??? l?????ng t??nh theo th??ng",session,jedis) %></span>
			                                	<%} else { %>
			                                		<input type="checkbox" name="trakhuyenmai<%= pos %>.tinhtheothung" id="trakhuyenmai<%= pos %>.tinhtheothung"  value='1' > <span style="font-style: italic;"><%=Utility.GLanguage("S??? l?????ng t??nh theo th??ng",session,jedis) %></span>
			                                	<%} %>
			                                	</td>
			                                </tr>
			                                <tr>
			                                	<td colspan="2">
			                                		<table align="left" cellpadding="2px" cellspacing="2px">
				                                		<tr>
				                                			<th width="100px" align="center"><%=Utility.GLanguage("M?? s???n ph???m",session,jedis) %></th>
				                                			<th width="250px" align="left"><%=Utility.GLanguage("T??n s???n ph???m",session,jedis) %></th>
				                                			<th width="60px" align="left"><%=Utility.GLanguage("S??? l?????ng",session,jedis) %></th>
				                                		</tr>
				                                	</table>
				                                	<div id="trakhuyenmai<%= pos %>.tbsanpham" style="width: 100%; max-height: 150px; overflow: auto">
				                                	<table align="left" cellpadding="2px" cellspacing="2px">
				                                	<% 
				                                	int count = 0;
				                                	while(count < spTraList.size())
				                                	{
				                                		ISanpham sp = spTraList.get(count);
				                                		%>
				                                		<tr>
				                                			<td width="100px" align="center">
				                                				<input type="text" value="<%= sp.getMasanpham() %>" style="width: 100px" name="trakhuyenmai<%= pos %>.masanpham" 
				                                						onkeyup="ajax_showOptions(this,'sanpham',event)" AUTOCOMPLETE="off">
				                                			</td>
				                                			<td width="250px" align="left">
				                                				<input type="text" value="<%= sp.getTensanpham() %>" name="trakhuyenmai<%= pos %>.tensanpham" style="width: 250px" readonly>
				                                			</td>
				                                			<td width="60px" align="center">
				                                				<input type="text" value="<%= sp.getSoluong() %>" name="trakhuyenmai<%= pos %>.soluong" style="width: 60px; text-align: right;">
				                                			</td>
				                                		</tr>
				                                	<% count++; } %>
				                                	<% for(int sopt=count; sopt < 50; sopt++){ %>
				                                		<tr>
				                                			<td width="100px" align="center">
				                                				<input type="text" value="" style="width: 100px" name="trakhuyenmai<%= pos %>.masanpham" 
				                                						onkeyup="ajax_showOptions(this,'sanpham',event)" AUTOCOMPLETE="off">
				                                			</td>
				                                			<td width="250px" align="left">
				                                				<input type="text" value="" name="trakhuyenmai<%= pos %>.tensanpham" style="width: 250px" >
				                                			</td>
				                                			<td width="60px" align="center">
				                                				<input type="text" value="" name="trakhuyenmai<%= pos %>.soluong" style="width: 60px; text-align: right;">
				                                			</td>
				                                		</tr>
				                                	<%} %>
			                                		</table>
			                                		</div>
			                                	</td>
			                                </tr>
			                                <tr>
			                                	<td  align="left" colspan="2">
			        								<a class="button" href="javascript:submitform();">
			        								<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Nh???p l???i",session,jedis) %>  </a>
			        								&nbsp;&nbsp;&nbsp;
			        								<a class="button" href="javascript:submitform3(<%= pos %>);">
			        								<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("L??u tr??? khuy???n m???i",session,jedis) %>  </a>
			                                	</td>
			                                </tr>
			                            </table>
									</div>
				                </div>
		                        
	                        </TD>
	                        <TD align="left"><input type="text" name="trakmDiengiai" id="trakmDiengiai<%= pos %>" size="80" value="<%= tkm.getDiengiai() %>" readonly></TD>
	                        <TD align="center"><input type="text" name="trakmTongluong" id="trakmTongluong<%= pos %>" size="6" value="<%= tkm.getTongluong() %>" style="text-align:right" readonly></TD>							           
	                        <TD align="center"><input type="text" name="trakmTongtien" id="trakmTongtien<%= pos %>"  size="6" value="<%= tkm.getTongtien() %>" style="text-align:right" readonly></TD>
	                         <TD align="center"><input type="text" name="trakmChietkhau" id="trakmChietkhau<%= pos %>"  size="6" value="<%= tkm.getChietkhau() %>" style="text-align:right" readonly></TD>
	                        <TD align="center">
	                        	<select name="trakmPheptoan">
	                            <% if(tkm.getPheptoan().equals("1")){ %>
	                            	<option value="1" selected="selected"><%=Utility.GLanguage("And",session,jedis) %></option>
	                                <option value="2"><%=Utility.GLanguage("Or",session,jedis) %></option>  
	                                <option value="" ></option>   
	                            <%} else { if(tkm.getPheptoan().equals("2")){ %>
	                            	<option value="1"><%=Utility.GLanguage("And",session,jedis) %></option>
	                                <option value="2" selected="selected"><%=Utility.GLanguage("Or",session,jedis) %></option>  
	                                <option value="" ></option>  
	                            <% } else { %>
	                            	<option value="" selected> </option>
	                            	<option value="1"><%=Utility.GLanguage("And",session,jedis) %></option>
	                                <option value="2"><%=Utility.GLanguage("Or",session,jedis) %></option>  
	                             <%} } %>   
	                            </select>
	                            <input type="hidden" name="trakmThutu" size="6" value="<%= pos %>" style="text-align:right">
	                        </TD>
	                    </TR>
		                   
                    	<% pos++; } } %>
                   	<% for(int j = pos; j < 5; j++){ %>
                   		<TR class='tbdarkrow'>
	                        <TD align="center">
	                        	<input type="text" id='trakmId<%= j %>' name="trakmId" size="10" value="" onkeyup="ajax_showOptions(this,'trakhuyenmai',event)" AUTOCOMPLETE="off" style="width: 75%" onchange="deleleDkTraKmDetail(<%=j %>)" >
		                        
		                        <a class="trakhuyenmai<%= j %>" href="#"  onclick ="AjaxTraKM(<%=j%> )">
		                        		<img style="top: -4px;" src="../images/vitriluu.png" title="T???o m???i tr??? khuy???n m???i"></a>
				                <div style='display:none'>
			                        <div id='trakhuyenmai<%= j %>' style='padding:0px 5px; background:#fff;'>
			                        	<h4 align="left"><%=Utility.GLanguage("T???o m???i",session,jedis) %> tr??? khuy???n m???i</h4>
										<table cellpadding="4px" cellspacing="2px" width="100%" align="center">
			                            	<tr>
			                                	<td width="40%"  align="left"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></td>
			                                    <td  align="left">
				                                    <input type="text" name="trakhuyenmai<%= j %>.diengiai" id="trakhuyenmai<%= j %>.diengiai" value="" />
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("Lo???i tr???",session,jedis) %></td>
			                                    <td  align="left">
			                                    	<select name = "trakhuyenmai<%= j %>.loaitra" id = "trakhuyenmai<%= j %>.loaitra" >
			                                    		<option value="3"><%=Utility.GLanguage("Tr??? s???n ph???m",session,jedis) %></option>
			                                    		<option value="1"><%=Utility.GLanguage("Tr??? ti???n",session,jedis) %></option>
			                                    		<option value="2"><%=Utility.GLanguage("Tr??? chi???t kh???u",session,jedis) %></option>
			                                    		<option value="4"><%=Utility.GLanguage("Tr??? ti???n theo ??i???m",session,jedis) %></option>
			                                    	</select>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("H??nh th???c",session,jedis) %></td>
			                                    <td  align="left">
			                                    	<select name="trakhuyenmai<%= j %>.hinhthuc" id = "trakhuyenmai<%= j %>.hinhthuc">
			                                    		<option value="2"><%=Utility.GLanguage("B???t k??? trong",session,jedis) %></option>
			                                    		<option value="1"><%=Utility.GLanguage("B???t bu???c nh???p s??? l?????ng",session,jedis) %></option>
			                                    	</select>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("T???ng l?????ng / T???ng ti???n",session,jedis) %></td>
			                                    <td  align="left">
			                                    	<input type="text" name="trakhuyenmai<%= j %>.sotong" id="trakhuyenmai<%= j %>.sotong" value="" style="text-align: right;"/>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left"><%=Utility.GLanguage("Nh??m s???n ph???m",session,jedis) %></td>
			                                    <td  align="left">		                                    	
			                                    	<select name="trakhuyenmai<%= j %>.nhomsanpham" id="trakhuyenmai<%= j %>.nhomsanpham" onChange = "ajaxOption2(this.id, this.value, <%= j %>)">
			                                    		<option value=""> </option>
				                                    		<% if(nhomspRs != null)
				                                    		{ 
				                                    			nhomspRs.beforeFirst();
				                                    			while(nhomspRs.next()){ %>
				                                    				<option value="<%= nhomspRs.getString("nspId") %>"><%= nhomspRs.getString("nspTen") %></option>
				                                    		<%} } %>
			                                    	</select>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td  align="left" colspan="2">
			                                		<input type="checkbox" name="trakhuyenmai<%= j %>.tinhtheothung" id="trakhuyenmai<%= j %>.tinhtheothung"  value='1' > <span style="font-style: italic;">S??? l?????ng t??nh theo th??ng</span> 
			                                	</td>
			                                </tr>
			                                <tr>
			                                	<td colspan="2">
			                                		<table align="left" cellpadding="2px" cellspacing="2px">
				                                		<tr>
				                                			<th width="100px" align="center"><%=Utility.GLanguage("M?? s???n ph???m",session,jedis) %></th>
				                                			<th width="250px" align="left"><%=Utility.GLanguage("T??n s???n ph???m",session,jedis) %></th>
				                                			<th width="60px" align="left"><%=Utility.GLanguage("S??? l?????ng",session,jedis) %></th>
				                                		</tr>
				                                	</table>
				                                	<div id="trakhuyenmai<%= j %>.tbsanpham" style="width: 100%; max-height: 150px; overflow: auto">
				                                	<table align="left" cellpadding="2px" cellspacing="2px">
				                                	<% for(int k=0; k < 50; k++){ %>
				                                		<tr>
				                                			<td width="100px" align="center">
				                                				<input type="text" value="" style="width: 100px" name="trakhuyenmai<%= j %>.masanpham" 
				                                						onkeyup="ajax_showOptions(this,'sanpham',event)" AUTOCOMPLETE="off">
				                                			</td>
				                                			<td width="250px" align="left">
				                                				<input type="text" value="" name="trakhuyenmai<%= j %>.tensanpham" style="width: 250px" readonly>
				                                			</td>
				                                			<td width="60px" align="center">
				                                				<input type="text" value="" name="trakhuyenmai<%= j %>.soluong" style="width: 60px; text-align: right;">
				                                			</td>
				                                		</tr>
				                                	<%} %>
			                                		</table>
			                                		</div>
			                                	</td>
			                                </tr>
			                                <tr>
			                                	<td  align="left" colspan="2">
			        								<a class="button" href="javascript:submitform();">
			        								<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Nh???p l???i",session,jedis) %>  </a>
			        								&nbsp;&nbsp;&nbsp;
			        								<a class="button" href="javascript:submitform3(<%= j %>);">
			        								<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("L??u tr??? khuy???n m???i",session,jedis) %>  </a>
			                                	</td>
			                                </tr>
			                            </table>
									</div>
				                </div>
		                        
	                        </TD>
	                        <TD align="left"><input type="text" name="trakmDiengiai" id="trakmDiengiai<%= j %>" size="80" value="" readonly></TD>
	                        <TD align="center"><input type="text" name="trakmTongluong" id="trakmTongluong<%= j %>" size="6" value="" style="text-align:right" readonly></TD>							           
	                        <TD align="center"><input type="text" name="trakmTongtien" id="trakmTongtien<%= j %>"  size="6" value="" style="text-align:right" readonly></TD>
	                        <TD align="center"><input type="text" name="trakmChietkhau" id="trakmChietkhau<%= j %>"  size="6" value="" style="text-align:right" readonly></TD>
	                        <TD align="center">
	                        	<select name="trakmPheptoan">
	                            	<option value="" selected></option>
	                            	<option value="1"><%=Utility.GLanguage("And",session,jedis) %></option>
	                                <option value="2"><%=Utility.GLanguage("Or",session,jedis) %></option>     
	                            </select>
	                            <input type="hidden" name="trakmThutu" size="6" value="<%= j %>" style="text-align:right">
	                        </TD>
	                    </TR>
                   	<%} %>
                  <TR>
                      <TD align="center" colspan="11" class="tbfooter">&nbsp;</TD>
                  </TR>
              </TABLE>   
            </div> 
            <h1 style="font-size:1.8em"><a href="#"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></a></h1>
            <div style="height:auto">
            <TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
                <TR class="plainlabel" valign="bottom">
                <th >
                   
			       <fieldset style="width: 30%; float: left;">
			       <legend><%=Utility.GLanguage("V??ng",session,jedis) %> &nbsp;</legend> 
			       <select name="vung" id="vungId"  multiple="multiple" onchange="AjaxNpp()">
			       <option value=""><%=Utility.GLanguage("Ch???n h???t",session,jedis) %></option>
                        <% if(vungs != null) {
                         while(vungs.next()) 
                         {
                           if(ctkmBean.getVungId().indexOf(vungs.getString("pk_seq")) >= 0 ){ %>
                             <option value="<%= vungs.getString("pk_seq") %>" selected style="padding: 2px" ><%= vungs.getString("ten") %></option>
                         <%}else { %>
                         	<option value="<%=vungs.getString("pk_seq") %>" style="padding: 2px"><%= vungs.getString("ten") %></option>
                         <%} }}%>        	
                    </select>
                    </fieldset>
                    
                    <fieldset style="width: 30%; float: left;"> 
					<legend><%=Utility.GLanguage("Khu v???c",session,jedis) %>&nbsp;</legend>
					<select name="khuvuc" multiple="multiple" id="khuvucId"  onchange="AjaxNpp()">
					<option value=""><%=Utility.GLanguage("Ch???n h???t",session,jedis) %></option>
			            <% if(khuvucs != null) {
			            	while(khuvucs.next())
	                          {
	                            if(ctkmBean.getKhuvucId().indexOf(khuvucs.getString("pk_seq")) >= 0 )
	                            { %>
	                              <option value="<%=khuvucs.getString("pk_seq") %>" selected style="padding: 2px"><%=khuvucs.getString("ten") %></option> 
	                          <%}else { %>
	                          	<option value="<%=khuvucs.getString("pk_seq") %>" style="padding: 2px"><%=khuvucs.getString("ten") %></option>
	                          <%}}}%>
                    </select>
                    </fieldset>
                    
                     <fieldset style="display: none;"> 
					<legend><%=Utility.GLanguage("Lo???i chi nh??nh/?????i t??c",session,jedis) %>&nbsp;</legend>
					<select name="loaicn" multiple="multiple" id="loaicn"  onchange="AjaxNpp()">
					<option value=""><%=Utility.GLanguage("Ch???n h???t",session,jedis) %></option>
			            <% if(loaicnRS != null) {
			            	while(loaicnRS.next())
	                          {
	                            if(ctkmBean.getLoaicnIds().indexOf(loaicnRS.getString("loaiId")) >= 0 )
	                            { %>
	                              <option value="<%=loaicnRS.getString("loaiId") %>" selected style="padding: 2px"><%=loaicnRS.getString("loaiNPP") %></option> 
	                          <%}else { %>
	                          	<option value="<%=loaicnRS.getString("loaiId") %>" style="padding: 2px"><%=loaicnRS.getString("loaiNPP") %></option>
	                          <%}}}%>
                    </select>
                    </fieldset>
			   </th>
				</TR>
                <tr class="plainlabel" style="padding: 5px">
                	<th >
                		<a class="button" href="javascript:seach();">
        					<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Hi???n th??? CN/??T theo ??i???u ki???n",session,jedis) %></a>
                	</th>
                </tr>
                </TABLE>
                <TABLE id="NppTable">
                    <TR class="tbheader">
                        <TH align="center" width="10%"><%=Utility.GLanguage("M??",session,jedis) %></TH>
                        <TH align="left" width="50%"> <%=Utility.GLanguage("T??n",session,jedis) %> </TH>
                        <TH align="center" width="10%"> <%=Utility.GLanguage("Ch???n t???t c???",session,jedis) %><input type ="checkbox" name ="all" onclick ="All()"></TH>
                    </TR>
					<%
					int k=0;
                    if(DsnppIds != null)
                    {
                    	while(DsnppIds.next())
                    	{
                    		if(k % 2 == 0){
                    			%>
                    			<TR class='tbdarkrow'>
	                    	<%}else{ %> 
	                    		 <TR class='tblightrow'>
	                    	<% } %>
	                    	<TD align="center"><%= DsnppIds.getString("ma") %></TD>
		                    <TD align="left"><%=DsnppIds.getString("ten") %></TD>
		                    <% if(nppIds.contains(DsnppIds.getString("pk_seq"))) {%>
		                    	<TD align="center"><input type ="checkbox" name ="npp" value ="<%=DsnppIds.getString("pk_seq")%>" checked="checked"></TD>
		                    <%} else {%>
		                      	<TD align="center"><input type ="checkbox" name ="npp" value ="<%=DsnppIds.getString("pk_seq")%>"></TD>
		                  	<%} k++; } DsnppIds.close(); }
					
					if(Dsnpp != null){
					 k=0;
					try{
					while(Dsnpp.next()) {
					if(k%2==0){
					%>
	                   	<TR class='tbdarkrow'>
	               <%}else{ %>
	                    <TR class='tblightrow'>
	               <%} %>
                        <TD align="center"><%= Dsnpp.getString("ma") %></TD>
	                    <TD align="left"><%=Dsnpp.getString("ten") %></TD>
	                    <% if(nppIds.contains(Dsnpp.getString("pk_seq"))) {%>
	                    	<TD align="center"><input type ="checkbox" name ="npp" value ="<%=Dsnpp.getString("pk_seq")%>" checked="checked"></TD>
	                    <%} else {%>
	                      	<TD align="center"><input type ="checkbox" name ="npp" value ="<%=Dsnpp.getString("pk_seq")%>"></TD>
	                  	<%} %>
                   </TR>
	                <% k++;} Dsnpp.close(); }catch(Exception ex){} }%>
                    <TR>
                        <TD align="center" colspan="10" class="plainlabel">&nbsp;</TD>
                    </TR>
                    </TABLE>
                    
                    </div>
            </div>  
      </fieldset>
  </div>    
</div><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
<script type="text/javascript">
	replaces();
</script>
</BODY>
</HTML>
