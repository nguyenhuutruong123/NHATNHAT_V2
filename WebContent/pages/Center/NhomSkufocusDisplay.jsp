<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.nhomfocus.*" %>
<%@page import="java.util.Calendar"%>
<%@ page  import = "java.sql.ResultSet" %>
<% INhomfocus ckBean = (INhomfocus)session.getAttribute("ckBean"); %>
<% ResultSet dvkdList = (ResultSet) ckBean.getDvkdList(); %>
<% ResultSet kbhList = (ResultSet) ckBean.getKenhbanhangList(); %>
<% List<ISanpham> spList = ckBean.getSanphamList(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen"); 
ResultSet rsvung=ckBean.getVung();
String vungstr=ckBean.getVungStr();
ResultSet chungloaiRs=ckBean.getRsChungloai();
ResultSet nhanhangRs=ckBean.getRsNhanhang();

%>
<% 
	session.removeAttribute("khoxuat");  
%>
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
<script type="text/javascript" src="../scripts/nhomfocus_sp.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>

<script language="javascript" type="text/javascript">
function Chonkho()
{
	 document.forms['ckForm'].action.value='chonkho';
     document.forms['ckForm'].submit();
}
function replaces()
{
	var masp = document.getElementsByName("masp");
	var tensp = document.getElementsByName("tensp");
	var sodong =  masp.length;
	for(var i = 0; i < sodong; i++)
	{
		for(var k = 0;k <sodong ;k++)
		{
			if(parseInt(k)!=parseInt(i))//khong phai ma hien tai
			{
				if((masp[i].value == masp[k].value) && masp[k].value.length !=0)
					{
						alert("S???n ph???m hi???n t???i ???? c??!");
						masp.item(k).value='';
						//return;
					}
			}
		}
		if(masp.item(i).value.length == 17)
		{
			tensp.item(i).value = "";
			masp.item(i).value = "";
		}
		else if(masp.item(i).value != "")
		{
			var sp = masp.item(i).value;
			var pos = parseInt(sp.indexOf(" - "));
			if(pos > 0)
			{
				masp.item(i).value = sp.substring(0, pos);
				sp = sp.substr(parseInt(sp.indexOf(" - ")) + 3);
				tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf(" [")));
			}
		}
		else
		{
			tensp.item(i).value = "";
			masp.item(i).value = "";
		}
	}	
	setTimeout(replaces, 200);
}
	
	function roundNumber(num, dec) 
	{
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	}
	 function saveform()
	 {	
		 if(checkSanPham()==false)
		 	{
				 alert("Kh??ng c?? s???n ph???m n??o!");
				 return;
		 	}
		 var kenhbanhang = document.getElementById("kenhbanhang");
		 var donvikinhdoanh = document.getElementById("donvikinhdoanh");
		 var thang = document.getElementById("thang");
		 var nam = document.getElementById("nam");
		 if(thang.value == 0)
		 {
			 thang.focus();
			alert("Vui l??ng ch???n th??ng");
			return;
		 }
		 if(nam.value == 0)
		 {
			 nam.focus();
			alert("Vui l??ng ch???n n??m");
			return;
		 }
		 if(kenhbanhang.value == "")
		 {
			
			alert("Vui l??ng ch???n k??nh b??n h??ng");
			kenhbanhang.focus();
			return;
		 }
		 
		 if(donvikinhdoanh.value == "")
		 {
			
			alert("Vui l??ng ch???n ????n v??? kinh doanh");
			donvikinhdoanh.focus();
			
			return;
		 }
		
		
		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['ckForm'].action.value='save';
	     document.forms['ckForm'].submit();
	 }
	
	 function checkSanPham()
	 {
		 var masp = document.getElementsByName("masp");
		 for(var hh = 0; hh < masp.length; hh++)
		 {
			if(masp.item(hh).value != "")
			{
				return true;
			}
		 }
		 return false;
	 }
	 
	 
	 function submitform()
	 { 
		 
		// checkTTSanPham();
		 document.forms['ckForm'].action.value='submit';
	     document.forms['ckForm'].submit();
	 }
	 function addRow(name)
		{
			tableName = document.getElementById(name);
			
			var tr = document.createElement("TR");
			var maspAdd = document.createElement("TD");
			var tenspAdd = document.createElement("TD");
			
			var masp = document.createElement("input");
			masp.setAttribute("type", "textbox");
			masp.setAttribute("AUTOCOMPLETE", "off");
			masp.setAttribute("onkeyup", "ajax_showOptions(this,'chuyenkho',event)");
			masp.setAttribute("style","width:100%;border:1px;	border-style:solid;border-color: #808080;");
			masp.name = 'masp';
			masp.id = 'masp';
			maspAdd.appendChild(masp);
			
			var tensp = document.createElement("input");
			tensp.setAttribute("type", "textbox");
			tensp.setAttribute("readonly", "readonly");
			tensp.setAttribute("style", "width: 100%; text-align: left;");
			tensp.setAttribute("style"," width:100%;border:1px;	border-style:solid;border-color: #808080;");
			
			tensp.name = 'tensp';
			tenspAdd.appendChild(tensp);
			
			tr.appendChild(maspAdd);
			tr.appendChild(tenspAdd);
			
			tableName.appendChild(tr);
		}
		function ThemSanPham()
		{
			 var sl = window.prompt("Nh???p s??? l?????ng s???n ph???m mu???n th??m", '');
			 if(isNaN(sl) == false && sl < 30)
			 {
				 for(var i=0; i < sl ; i++)
					 addRow("san_pham");
			 }
			 else
			 {
				 alert('S??? l?????ng b???n nh???p kh??ng h???p l???. M???i l???n b???n ch??? ???????c th??m t???i ??a 30 s???n ph???m');
				 return;
			 }
		 }
</script>
	
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ckForm" method="post" action="../../NhomFocusUpdateSvl">
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	D??? li???u n???u > Ch??? ti??u > Nh??m th?????ng > Hi???n th???
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../NhomFocusSvl?userId=<%= userId %>" >
        	<img src="../images/Back30.png" alt="Quay v???"  title="Quay v???" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <!-- <span id="btnSave">
	        <A href="javascript:saveform()" >
	        	<IMG src="../images/Save30.png" title="L??u l???i" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        </span> -->
    </div>
  	
  	<div align="left" style="width:100%%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Th??ng b??o</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%%"><%= ckBean.getMsg() %></textarea>
		         <% ckBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle">Th??ng tin nh??m th?????ng</legend>
        	<div style="float:none; width:100%" align="left">
            <TABLE width="100%" cellpadding="4" cellspacing="0">		
            <TR>
              	<TD width="15%" class="plainlabel" ><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> &nbsp;&nbsp;  <FONT class="erroralert"> </FONT></TD>
				<TD class="plainlabel">
				 <input type="text" name ="diengiai" value="<%=ckBean.getDiengiai() %>" style="width:450px"/></TD>
            </TR>
            <TR>
                      <TD width="15%" class="plainlabel" >Th??ng &nbsp;&nbsp;  <FONT class="erroralert"> </FONT></TD>
			<TD class="plainlabel">
				<select name="thang" id="thang" style="width :100px">
				<option value=0> </option>  
				<%
				int k=1;
				int thang=-1;
				if(ckBean.getThang().length()!=0)
					thang=Integer.parseInt(ckBean.getThang());
				for(k=1;k<=12;k++){
				  if(k==thang){
				  
				%>
				<option value=<%=k %> selected="selected" > <%=k %></option> 
				<%
				  }else{
				 %>
				<option value=<%=k %> ><%=k %></option> 
				<%
				  }
				  }
				%>
				</select>
			</TD>
                      </TR>
                         <TR>
	                         <TD width="15%" class="plainlabel" >N??m &nbsp;&nbsp;  <FONT class="erroralert"> </FONT></TD>
							<TD class="plainlabel">
								<select name="nam" id="nam"  style="width :100px">
								<option value=0> </option>  
								<%
								Calendar cal=Calendar.getInstance(); 
								int nam=-1;
								if(ckBean.getNam().length()!=0)
									nam=Integer.parseInt(ckBean.getNam());
								int year_=cal.get(Calendar.YEAR);
								for(int n=2008;n<year_+3;n++){
								  if(n== nam){									  
								%>
								<option value=<%=n %> selected="selected" > <%=n %></option> 
								<%
								  }else{
								 %>
								<option value=<%=n %> ><%=n %></option> 
								<%
								  }
								  }
								%>
								</select>
							</TD>
                        </TR >
                    <TR>
                        <TD class="plainlabel" valign="middle" >????n v??? kinh doanh</TD>
                        <TD class="plainlabel" valign="middle">
                            <select name="donvikinhdoanh" id="donvikinhdoanh" onchange="Chonkho()">
                            	<option value=""></option>
                            	<%
	                        		if(dvkdList  != null)
	                        		{
	                        			while(dvkdList .next())
	                        			{  
	                        			if( dvkdList.getString("pk_seq").equals(ckBean.getDvkd())){ %>
	                        				<option value="<%= dvkdList.getString("pk_seq") %>" selected="selected" ><%= dvkdList.getString("DONVIKINHDOANH") %></option>
	                        			<%}else { %>
	                        				<option value="<%= dvkdList.getString("pk_seq") %>" ><%= dvkdList.getString("DONVIKINHDOANH") %></option>
	                        		 <% } } dvkdList .close();
	                        		}
	                        	%>
                            </select>
                        </TD>                        
                    </TR>
                     <TR>
                        <TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></TD>
                        <TD class="plainlabel"  valign="middle">
                            <select name="kenhbanhang" id="kenhbanhang">
                            	<option value=""></option>
                            	<%
	                        		if(kbhList  != null)
	                        		{
	                        			while(kbhList.next())
	                        			{  
	                        			if( kbhList.getString("pk_seq").equals(ckBean.getKenhbanhang())){ %>
	                        				<option value="<%= kbhList .getString("pk_seq") %>" selected="selected" ><%= kbhList.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= kbhList .getString("pk_seq") %>" ><%= kbhList.getString("ten") %></option>
	                        		 <% } } kbhList .close();
	                        		}
	                        	%>
                            </select>
                        </TD>                        
                    </TR>
                     <TR>
                        <TD class="plainlabel" valign="middle">?????i t?????ng</TD>
                        <TD class="plainlabel" valign="middle">
                           <select name="doituong" >
                           		<option value=""> </option>
								<% if (ckBean.getDoituong().trim().equals("1")){%>
								  <option value="0" >SR</option>
								  	<option value="1" selected>SS</option>
								  	<option value="2">ASM</option>
								  	<option value="3" >RSM</option>
								
								<%}else if(ckBean.getDoituong().trim().equals("2")) {%>
								 	<option value="0" >SR</option>
								 	<option value="2" selected>ASM</option>
								  	<option value="1" >SS</option>
								  	<option value="3" >RSM</option>
								<%}else if(ckBean.getDoituong().trim().equals("3")) {%>
								<option value="0" >SR</option>
							 	<option value="3" selected>RSM</option>
							  	<option value="1" >SS</option>
							  	<option value="2" >ASM</option>
								<%} else if(ckBean.getDoituong().trim().equals("0"))  {%>
								<option value="0" selected>SR</option>
							 	<option value="1">SS</option>
							  	<option value="2" >ASM</option>
							  	<option value="3" >RSM</option>
							  
							<%} else{
							 %> 
							   <option value="0">SR</option>
							 	<option value="1">SS</option>
							  	<option value="2" >ASM</option>
							  	<option value="3" >RSM</option>
							<% }%>
                           </select>
                        </TD>                        
                    </TR> 
                    <TR>
                    	<TD class="plainlabel" valign="middle" >Th?????ng</TD>
                        <TD class="plainlabel"  valign="middle">
                        	<input type="text" name="thuong" value="<%=ckBean.getThuong() %>" />
                        </TD>
                    </TR>
                      <TR>
                    	<TD class="plainlabel" valign="middle" >Nh??m th?????ng B??n Ra</TD>
                        <TD class="plainlabel"  valign="middle">
                        	<% if(ckBean.getIsNhonthuongSalesOut().trim().equals("1")){ %>
                        		<input type="checkbox" checked="checked" name="thuongsalesout" value="1" />
                        	<%}else{ %>
                        		<input type="checkbox"   name="thuongsalesout" value="1" />
                        	<%} %>
                        </TD>
                    </TR>
                    
                     <TR>
                    	<TD class="plainlabel" valign="middle" >Th?????ng ri??ng </TD>
                        <TD class="plainlabel"  valign="middle">
                        	<% if(ckBean.getThuongRieng().trim().equals("1")){ %>
                        		<input type="checkbox" checked="checked" name="thuongrieng" value="1" />
                        	<%}else{ %>
                        		<input type="checkbox"   name="thuongrieng" value="1" />
                        	<%} %>
                        </TD>
                    </TR>
                    
                    <TR>
                    	<TD class="plainlabel" valign="middle" >Nh??n h??ng</TD>
                        <TD class="plainlabel"  valign="middle">
                        	<select name="nhanhangId" onchange="Chonkho()">
                           		<option value=""> </option>
                           		<%if(nhanhangRs!=null){while(nhanhangRs.next()){ 
                           			if(ckBean.getNhanhangId().equals(nhanhangRs.getString("pk_seq"))){
                           		%>
                           			<option value="<%=nhanhangRs.getString("pk_seq")%>" selected="selected"><%=nhanhangRs.getString("ten") %> </option>
                           		<%} else { %>
                           			<option value="<%=nhanhangRs.getString("pk_seq")%>" ><%=nhanhangRs.getString("ten") %> </option>
                           			<% }} }%>
                           	</select>
                        </TD>
                    </TR>
                    
                     <TR>
                    	<TD class="plainlabel" valign="middle" >Ch???ng lo???i</TD>
                        <TD class="plainlabel"  valign="middle">
                        	<select name="chungloaiId" onchange="Chonkho()">
                           		<option value=""> </option>
                           		<%if(chungloaiRs!=null){while(chungloaiRs.next()){ 
                           			if(ckBean.getChungloaiId().equals(chungloaiRs.getString("pk_seq"))){
                           		%>
                           			<option value="<%=chungloaiRs.getString("pk_seq")%>" selected="selected"><%=chungloaiRs.getString("ten") %> </option>
                           		<%} else { %>
                           			<option value="<%=chungloaiRs.getString("pk_seq")%>" ><%=chungloaiRs.getString("ten") %> </option>
                           			<% }} }%>
                           	</select>
                        </TD>
                    </TR>
                    
            </TABLE>
            <hr> 
            </div>
            <div>
              <FIELDSET  valign="top" >
                        <LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Khu v???c",session,jedis) %> qu???n l??</LEGEND>
                        <TABLE  valign="top"  border="0" width="100%" cellpadding="3" cellspacing="1">
                                
                                 <td valign="top" >
                                  <FIELDSET  valign="top" >
                    	    <LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Khu v???c",session,jedis) %></LEGEND>
                    	    <TABLE  class="tabledetail" width="100%" border="0" cellpadding="1" cellspacing="1" >
                    	    	<tr  class="tbheader"> 
                    	    		<th> Khu V???c </th> 
                    	    		<th style="display:none"> Ch??? ti??u </th>
                    	    		<th> Ch???n </th>
                    	    		
                    	    	</tr>
											
											 	
											 <%
											
											 
				                           	if(rsvung!=null){
				                            	while (rsvung.next()){
				                            		%> 
				                            		<tr>
				                            		<td>
				                            			<input type="text" name="<%=rsvung.getString("pk_seq") %>" value="<%=rsvung.getString("ten") %>" >	
				                            		</td>
				                            		<td style="display:none">
				                            			<input type="text" name="<%="CT"+rsvung.getString("pk_seq") %>" value="<%=rsvung.getString("chitieu") %>" >	
				                            		</td>
				                            		<td>
				                            		<% 
				                            	     if(vungstr.contains(rsvung.getString("pk_seq")))  {
				                            			 %>
				                            			    <input type="checkbox" checked="checked" name="checkvung"  onchange="recheck();" value ="<%=rsvung.getString("pk_seq") %>">

							                              <%
				                            		}else{
				                            			 %>
						                            	   <input type="checkbox" name="checkvung"  onchange="recheck();" value ="<%=rsvung.getString("pk_seq") %>">
							                              <%
				                            		}
				                            		%>
				                            		</td>
				                            		</TR>
				                            		<%
				                            	}
				                           	}
											 %>  

											 				
                                    		
								</TABLE>
                        </FIELDSET>
                                 </td>		
                        </TABLE>                        
                        </FIELDSET>		
            </div>
           
           	<div align="left" style="width:100%; float:none; clear:none;">
           <TABLE class="tabledetail" width="100%" border="0" cellpadding="1" cellspacing="1" >
           <tbody id="san_pham">
                <TR class="tbheader">                 
                	<TH align="center" width="16%">M?? s???n ph???m</TH>
                	<TH align="center" width="40%">T??n s???n ph???m</TH>
                </TR>
                <%
                	int sodong = spList.size();
                	if( sodong > 0 )
                	{
                		for(int i = 0; i < sodong; i++)
                		{
                			ISanpham sp = (ISanpham)spList.get(i); %>
                			<TR class='tbdarkrow'>  
		                    	<td align="center" width="16%"><input id="masp" type="text" value="<%= sp.getMasp() %>"  name ="masp" 
		                    		onkeyup="ajax_showOptions(this,'chuyenkho',event)" AUTOCOMPLETE="off"></td>
		                   		<td align="center" width="40%" ><input type="text" style="width: 100%; text-align: left;"  value="<%= sp.getTensp() %>" name= "tensp" readonly></td>       	
			                </TR>
                		<%}
                	}
                %>
                <%
              		for(int j = sodong; j < 30; j++)
              		{
 					%>
              			<TR class='tbdarkrow'>
                    		<td align="center" width="16%"><input type="text" value="" id="masp" name = "masp" 
                    		onkeyup="ajax_showOptions(this,'chuyenkho',event)" AUTOCOMPLETE="off"></td>
                   			<td align="center" width="40%"><input type="text" style="width: 100%; text-align: left;"  value="" name= "tensp" readonly></td>       	
						</TR>
              	  <%}
    
                %>
                </tbody>       
            </TABLE> 
              <br>
				&nbsp;&nbsp;&nbsp;&nbsp; <a class="button2" href="javascript:ThemSanPham()">
					<img style="top: -4px;" src="../images/add.png" alt="">Th??m s???n ph???m</a>&nbsp;&nbsp;&nbsp;&nbsp; 
        </div>      
     </fieldset>	
    </div>
</div>
<script type="text/javascript">
	replaces();
	dropdowncontent.init("searchlink", "right-bottom", 500, "click");
	/* jQuery(function()
	{		
		$("#nccId").autocomplete("ErpNhaCungCapList.jsp");
	});	 */
</script>
<% ckBean.close(); %>
</form>
</BODY>
</HTML>