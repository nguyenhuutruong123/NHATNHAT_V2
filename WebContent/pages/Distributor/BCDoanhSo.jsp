<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.hoadontaichinh.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>

<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<% IBCHoadonbanra obj = (IBCHoadonbanra)session.getAttribute("obj"); %>
<% ResultSet hdRs =  obj.getHoadonRs(); %>
<% ResultSet khRs = obj.getKhRs(); 
ResultSet ETCRs = obj.getETCRs();
ResultSet OTCRs = obj.getOTCRs();
ResultSet KMRs = obj.getKMRs();
ResultSet HDKHACRs = obj.getHDKhacRs();
ResultSet khuvucRs = obj.getKhuvucRs();
ResultSet nppRs = obj.getnppRs();
ResultSet totalRs = obj.getTotalRs();
ResultSet ddkd = obj.getTdvRs();
ResultSet khoRs = obj.getKhoRs();
ResultSet ETCHORS=obj.getEtcHoRs();
%>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% 
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	String view = (String) session.getAttribute("view");
	
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[5];
		quyen = util.Getquyen("BCDoanhsoSvl", "22", userId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <link type="text/css" rel="stylesheet" href="../css/mybutton.css">
    
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    
    <LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
	<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">
    
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>

	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js"
		type="text/javascript"></script>
		
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>


	<script type="text/javascript">
		$(document).ready(function() {		
				$( ".days" ).datepicker({			    
						changeMonth: true,
						changeYear: true				
				});            
	        }); 		
			
	
	</script>
	<script type="text/javascript">
	 
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
   
    <SCRIPT language="javascript" type="text/javascript">
    	
	     function clearform()
		 {   
	    	document.forms["ckParkForm"].tungay.value="";
	    	document.forms["ckParkForm"].denngay.value="";
	    	document.forms["ckParkForm"].vung.value="";
	    	document.forms["ckParkForm"].khuvuc.value="";
	    	document.forms["ckParkForm"].npp.value="";
		    document.forms["ckParkForm"].submit();
		 }
	    
		 function submitform()
		 {   
		    document.forms["ckParkForm"].submit();
		 }
		 
		 function xuatExcel()
		 {
		 	document.forms['ckParkForm'].action.value= 'excel';
		 	document.forms['ckParkForm'].submit();
		 }
		 
		 function search()
		 {
			
		 	document.forms['ckParkForm'].action.value= 'search';
		 	document.forms['ckParkForm'].submit();
		 }
	
	</SCRIPT>
 	
	<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
		$(document).ready(function()
		{
			$(".select2").select2();
		});
	</script> 
</head>
<body>
<form name="ckParkForm" method="post" action="../../BCDoanhsoSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<input type="hidden" name="view" value="<%= obj.getView() %>" >

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
    	<% if(!obj.getView().equals("TT")) { %>
        	&nbsp;Qu???n l?? b??n h??ng > B??o c??o > Doanh s??? theo s???n ph???m
        <% } else { %>
        	&nbsp;B??o c??o qu???n tr??? > Doanh s??? > Doanh s??? theo s???n ph???m
        <% } %>
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Th??ng b??o</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%"><%= obj.getMsg() %></textarea>
		         <% obj.setMsg(""); %>
    	</fieldset>
  	</div>
    
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %></legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
                	 <TR>
                        <TD class="plainlabel" width="120px"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
                        <TD class="plainlabel" width="250px" >
                            <input type="text"  class="days" name="tungay" value="<%= obj.getTungay() %>" maxlength="10"  />
                        </TD>
                    
                        <TD class="plainlabel" width="100px" > ?????n ng??y</TD>
                        <TD class="plainlabel" colspan="3">
                            <input type="text" class="days" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10"   />
                        </TD>
                    </TR>
                    <% if(obj.getView().equals("TT")){ %>
                      <TR>
                        <TD class="plainlabel" >Mi???n</TD>
                        <TD class="plainlabel" >
                            <select name="vung" >
                    		<% if(obj.getVung().equals("100001")) { %>
									  	 <option value=""></option>
									  	 <option value="100001" selected="selected"> Mi???n B???c </option>
									  	 <option value="100002"> Mi???n Nam</option>
									  	 <option value="100003"> Mi???n Trung </option>
								<% } else if(obj.getVung().equals("100002")) { %>
									  	 <option value=""></option>
									  	 <option value="100001"> Mi???n B???c </option>
									  	 <option value="100002"  selected="selected"> Mi???n Nam</option>
									  	 <option value="100003"> Mi???n Trung </option>
								<% } else if(obj.getVung().equals("100003")) { %>
									  	 <option value=""></option>
									  	 <option value="100001" > Mi???n B???c </option>
									  	 <option value="100002"> Mi???n Nam</option>
									  	 <option value="100003" selected="selected"> Mi???n Trung </option>
								<% } else { %> 
									  	 <option value="" ></option>
									  	 <option value="100001" > Mi???n B???c </option>
									  	 <option value="100002"> Mi???n Nam</option>
									  	 <option value="100003"> Mi???n Trung </option>
								<%} %>	  	
								
                            </select>
                        </TD>
                    
                        <TD class="plainlabel" width="100px"> Khu v???c </TD>
                        <TD class="plainlabel" colspan="3" >
                       		 <select name="khuvuc" >
                      			<option></option>
                          		<% try{
                          			while(khuvucRs.next()){
                          				if(khuvucRs.getString("PK_SEQ").equals(obj.getKhuvuc())){
                          		%>	
                          			<option value="<%= khuvucRs.getString("PK_SEQ")%>" selected="selected"><%= khuvucRs.getString("TEN")%></option>
                          		<%} else { %>	
                          			<option value="<%= khuvucRs.getString("PK_SEQ")%>"><%= khuvucRs.getString("TEN")%></option>
                          		<%}}} catch(Exception e){}%>
                            </select>
                        </TD>
                    </TR> 
                    
                    <TR style="display: none;" >
                        <TD class="plainlabel" >S??? h??a ????n t???</TD>
                        <TD class="plainlabel" >
                            <input type="text" name="sohoadontu" value="<%= obj.getSohoadontu() %>" onkeypress="return keypress(event);" />
                        </TD>
                    
                        <TD class="plainlabel" >S??? h??a ????n ?????n</TD>
                        <TD class="plainlabel" colspan="3" >
                            <input type="text" name="sohoadonden" value="<%= obj.getSohoadonden() %>" onkeypress="return keypress(event);" />
                        </TD>
                    </TR> 
                    
                    <TR>
                    	<TD class="plainlabel" > Chi nh??nh/ ?????i t??c</TD>
                        <TD class="plainlabel"  colspan="6">
                             <select name="npp" onchange="search();" style="width:200px"  class="select2">
                             	<option></option>
                            	<% try{
                          			while(nppRs.next()){
                          				if(nppRs.getString("PK_SEQ").equals(obj.getNppId())){
                          		%>	
                          			<option value="<%= nppRs.getString("PK_SEQ")%>" selected="selected"><%= nppRs.getString("TEN")%></option>
                          		<%} else { %>	
                          			<option value="<%= nppRs.getString("PK_SEQ")%>"><%= nppRs.getString("TEN")%></option>
                          		<%}}} catch(Exception e){}%>
                            </select>
                        </TD>
                      </tr>

                      <%} %>
                      
                       <TR>
							<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
							<TD class="plainlabel"   >
								<select name="ddkdId" id="ddkdId" style="width:250px"   class="notuseselect2" >
									<option value="" >All</option>
									<%if (ddkd != null)
											while (ddkd.next()) {
												if (ddkd.getString("pk_seq").equals(obj.getTdvId()  )) {%>
									   <option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
									   <%} else {%>
									   <option value="<%=ddkd.getString("pk_seq")%>"><%=ddkd.getString("ten")%></option>
									<%}}%>
								</select>
							</TD>
							
							
							<TD class="plainlabel">Kho xu???t</TD>
							<TD class="plainlabel"  colspan="3" >
								<select name="khoId" id="khoId" style="width:250px"   class="notuseselect2" >
									<option value="" >All</option>
									<%if (khoRs != null)
											while (khoRs.next()) {
												if (khoRs.getString("pk_seq").equals(obj.getKhoId()  )) {%>
									   <option value="<%=khoRs.getString("pk_seq")%>" selected><%=khoRs.getString("ten")%></option>
									   <%} else {%>
									   <option value="<%=khoRs.getString("pk_seq")%>"><%=khoRs.getString("ten")%></option>
									<%}}%>
								</select>
							</TD>
							
						</TR>
                      
                      
                      <tr>  
                        <TD class="plainlabel" >Lo???i h??a ????n</TD>
                        <TD class="plainlabel" colspan="5" >
                            <select name="loaihoadon" >
                            	<option value="" ></option>
                            	<% if(obj.getLoaidonhang().equals("0")) { %>
                            		<option value="0" selected="selected" >H??ng b??n</option>
                            	<% } else { %>
                            		<option value="0" >H??ng b??n</option>
                            	<% } %>
                            	<% if(obj.getLoaidonhang().equals("1")) { %>
                            		<option value="1" selected="selected" >H??ng khuy???n m???i</option>
                            	<% } else { %>
                            		<option value="1" >H??ng khuy???n m???i</option>
                            	<% } %>
                            	<% if(obj.getLoaidonhang().equals("2")) { %>
                            		<option value="2" selected="selected" >H??a ????n kh??c </option>
                            	<% } else { %>
                            		<option value="2" >H??a ????n kh??c</option>
                            	<% } %>
                            </select>
                        </TD>
                    
                    </TR> 
                    
					<%
					double DoanhSo=0;
					double Thue=0;
					double DoanhThu=0;
					while(totalRs!=null && totalRs.next())
					{
						DoanhSo=totalRs.getDouble("BVAT");
						Thue=totalRs.getDouble("VAT");
						DoanhThu=totalRs.getDouble("AVAT");
					}
					
					%>
								<TR><TD class="plainlabel" colspan="6"></TD>
								<TR>
									<TD class="plainlabel" >Doanh s???</TD>
									<td class="plainlabel"><input type="text" name="ds" size="6" value="<%= formatter.format(DoanhSo   ) %>"></td>
									<TD class="plainlabel" >Thu???</TD>
									<td class="plainlabel"><input type="text" name="ck" size="6" value="<%= formatter.format( Thue ) %>"></td>
									<TD class="plainlabel" >Doanh thu</TD>
									<td class="plainlabel"><input type="text" name="dt" size="6" value="<%= formatter.format(  DoanhThu) %>"></td>
							
								</TR>
								
								<TR>
									<TD class="plainlabel">Doanh s??? t??nh theo </TD>
									<TD class="plainlabel"  colspan="5">
							<%
								if(obj.getMucCN_DT().equals("1")){ %>
								<input type="checkbox" name="cndt" value="1" checked="checked"  /> CN/DT &nbsp; &nbsp;
							<% }else { %>
								    <input type="checkbox" name="cndt" value="1"  /> CN/DT &nbsp; &nbsp;
							 <%} %>
										
									<%
								if(obj.getMuc_KhachHang().equals("1")){ %>
								<input type="checkbox" name="kh" value="1" checked="checked"  /> Kh??ch H??ng &nbsp; &nbsp;
							<% }else { %>
								    <input type="checkbox" name="kh" value="1"  /> Kh??ch H??ng &nbsp; &nbsp;
							 <%} %>
										
									</TD>
									
								</TR>
								
								

							<TR>									
							<TD class="plainlabel">M???c l???y</TD>
							<TD class="plainlabel"  colspan="6">
								<%
									if(obj.getMuclay().equals("1")){
										%>
										<input type="radio" name="type" value="0"   onchange="search();" /> T???ng qu??t &nbsp; &nbsp;
										<input type="radio" name="type" value="1"   onchange="search();"  checked="checked"/> Chi ti???t 
										<%
									}
									else
									{
										%>
											<input type="radio" name="type" value="0"  onchange="search();"  checked="checked"/> T???ng qu??t &nbsp; &nbsp;
											<input type="radio" name="type" value="1"   onchange="search();"  />Chi ti???t 
										<%
									}
								%>
							</TD>
							
						</TR>
						
							<TR>
									<TD class="plainlabel"></TD>
									<TD class="plainlabel"  colspan="5">
							<%
							
							System.out.print("chi nhanh checked "+obj.getCn());
							if(obj.getCn().equals("1")) {%>
								<input type="checkbox" name="cn1" value="1" checked="checked"  /> L???y s??? li???u CN1 b??n t???i khu v???c &nbsp; &nbsp;		
							<% }else {%>
								<input type="checkbox" name="cn1" value="1"  /> L???y s??? li???u CN1 b??n t???i khu v???c &nbsp; &nbsp;		
									<%} %>
									
									</TD>
									
								</TR>					

                    <TR>
						<TD class="plainlabel" colspan="6">
							<a class="button2" href="javascript:search();"> 
								<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("T??m ki???m",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="button2" href="javascript:xuatExcel();"> 
								<img style="top: -4px;" src="../images/button.png" alt="">Xu???t b??o c??o </a>&nbsp;&nbsp;&nbsp;&nbsp;
					</TR> 
					
				
					
			
                </TABLE>     
                
                <% String current=""; %>
                <table width="100%">
                <tr>
                	<td>
                		<ul class="tabs">
                						<li class='current' ><a
											href="#tabOTC">OTC</a></li>
											
										<li>
											<a href="#tabKM"> Khuy???n m??i </a>
										</li>
									
										<li ><a
											href="#tabETCHO"> ETCHO</a></li>
										<li ><a
											href="#tabETC"> ETCCN</a></li>
										
										<li ><a
											href="#tabHDKHAC"> H??a ????n kh??c</a></li>
										

									</ul>
									
									<div class="panes">
									
										<div id="tabOTC" class="tab_content">
											<TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
												<TR class="plainlabel" valign="bottom">
													<th align="center"> STT</th>
													<th align="center"> NH??N VI??N B??N H??NG</th>
													<th align="center"> M?? v???t t??</th>
													<th align="center"> T??n v???t t??</th>
													<th align="center"> ??VT</th>
													<th align="center"> S??? l?????ng</th>
													<th align="center"> ????n gi?? </th>
													<th align="center"> Doanh thu</th>
													<th align="center"> Thu??? GTGT</th>
													<th align="center"> T???ng ti???n</th>
												
												</TR>
												
												<%
												if(OTCRs!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (OTCRs.next()){
															if (m % 2 != 0) {%>						
															<TR class= <%=lightrow%> >
														<%} else {%>
															<TR class= <%= darkrow%> >
														<%}%>
															<Td align="center"><%= m %></Td>
															<Td align="center"><%= OTCRs.getString("ddkdTEN")==null?"":OTCRs.getString("ddkdTEN") %></Td>
															<Td align="center"><%= OTCRs.getString("MA") %></Td>
															<Td align="left"><%= OTCRs.getString("TEN") %></Td>
															<Td align="center"><%= OTCRs.getString("DONVI") %></Td>
															<Td align="right"><%= OTCRs.getString("soluong") %></Td>
															<Td align="right"><%= formatter.format(OTCRs.getDouble("dongia")) %></Td>
															<Td align="right"><%= formatter.format(OTCRs.getDouble("doanhthu")) %></Td>
															<Td align="right"><%= formatter.format(OTCRs.getDouble("thuexuat")) %></Td>
															<Td align="right"><%= formatter.format(OTCRs.getDouble("AVAT")) %></Td>
															 
														
												<% m++;}}catch(Exception e){e.printStackTrace();}} %>
												</TR>
											</TABLE>
										</div>
									
										<div id="tabKM" class="tab_content">
											<TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
												<TR class="plainlabel" valign="bottom">
													<th align="center"> STT</th>
													<th align="left"> NH??N VI??N B??N H??NG</th>
													<th align="center"> M?? v???t t??</th>
													<th align="center"> T??n v???t t??</th>
													<th align="center"> ??VT</th>
													<th align="center"> S??? l?????ng</th>
													<th align="center"> ????n gi?? </th>
													<th align="center"> Doanh thu</th>
													<th align="center"> Thu??? GTGT</th>
													<th align="center"> T???ng ti???n</th>
												
												</TR>
												
													
												<%
												if(KMRs!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (KMRs.next()){
															if (m % 2 != 0) {%>						
															<TR class= <%=lightrow%> >
														<%} else {%>
															<TR class= <%= darkrow%> >
														<%}%>
															<Td align="center"><%= m %></Td>
															<Td align="center"><%= KMRs.getString("ddkdTEN")==null?"":KMRs.getString("ddkdTEN") %></Td>
															<Td align="center"><%= KMRs.getString("MA") %></Td>
															<Td align="left"><%= KMRs.getString("TEN") %></Td>
															<Td align="center"><%= KMRs.getString("DONVI") %></Td>
															<Td align="right"><%= KMRs.getString("soluong") %></Td>
															<Td align="right"><%= formatter.format(KMRs.getDouble("dongia")) %></Td>
															<Td align="right"><%= formatter.format(KMRs.getDouble("doanhthu")) %></Td>
															<Td align="right"><%= formatter.format(KMRs.getDouble("thuexuat")) %></Td>
															<Td align="right"><%= formatter.format(KMRs.getDouble("AVAT")) %></Td>
															
														
												<% m++;}}catch(Exception e){e.printStackTrace();}} %>
												</TR>
											</TABLE>
										</div>
										
										
										<div id="tabETCHO" class="tab_content">
											<TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
												<TR class="plainlabel" valign="bottom">
													<th align="center"> STT</th>
													<th align="center"> NH??N VI??N B??N H??NG</th>
													<th align="center"> M?? v???t t??</th>
													<th align="center"> T??n v???t t??</th>
													<th align="center"> ??VT</th>
													<th align="center"> S??? l?????ng</th>
													<th align="center"> ????n gi?? </th>
													<th align="center"> Doanh thu</th>
													<th align="center"> Thu??? GTGT</th>
													<th align="center"> T???ng ti???n</th>
												
												</TR>
												<%
												if(ETCHORS!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (ETCHORS.next()){
															
															double doanhthu = ETCHORS.getDouble("doanhthu");
															double thuexuat = ETCHORS.getDouble("thuexuat");
															
															double tongtien = doanhthu * (1 + thuexuat / 100);
															
															if (m % 2 != 0) {%>						
															<TR class= <%=lightrow%> >
														<%} else {%>
															<TR class= <%= darkrow%> >
														<%}%>
															<Td align="center"><%= m %></Td>
															
															<Td align="center"><%= ETCHORS.getString("ddkdTEN")==null?"":ETCHORS.getString("ddkdTEN") %></Td>
															<Td align="center"><%= ETCHORS.getString("MA") %></Td>
															<Td align="left"><%= ETCHORS.getString("TEN") %></Td>
															<Td align="center"><%= ETCHORS.getString("DONVI") %></Td>
															<Td align="right"><%= ETCHORS.getString("soluong") %></Td>
															<Td align="right"><%= formatter.format(ETCHORS.getDouble("dongia")) %></Td>
															<Td align="right"><%= formatter.format(ETCHORS.getDouble("doanhthu")) %></Td>
															<Td align="right"><%= formatter.format(ETCHORS.getDouble("thuexuat")) %></Td>
															<Td align="right"><%= formatter.format( ETCHORS.getDouble("AVAT") ) %></Td>
															
														
												<% m++;}}catch(Exception e){ e.printStackTrace(); }} %>
												</TR>
											</TABLE>
										
										</div>
										
										<div id="tabETC" class="tab_content">
											<TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
												<TR class="plainlabel" valign="bottom">
													<th align="center"> STT</th>
													<th align="center"> NH??N VI??N B??N H??NG</th>
													<th align="center"> M?? v???t t??</th>
													<th align="center"> T??n v???t t??</th>
													<th align="center"> ??VT</th>
													<th align="center"> S??? l?????ng</th>
													<th align="center"> ????n gi?? </th>
													<th align="center"> Doanh thu</th>
													<th align="center"> Thu??? GTGT</th>
													<th align="center"> T???ng ti???n</th>
												
												</TR>
												<%
												if(ETCRs!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (ETCRs.next()){
															
															double doanhthu = ETCRs.getDouble("doanhthu");
															double thuexuat = ETCRs.getDouble("thuexuat");
															
															double tongtien = doanhthu * (1 + thuexuat / 100);
															
															if (m % 2 != 0) {%>						
															<TR class= <%=lightrow%> >
														<%} else {%>
															<TR class= <%= darkrow%> >
														<%}%>
															<Td align="center"><%= m %></Td>
															
															<Td align="center"><%= ETCRs.getString("ddkdTEN")==null?"":ETCRs.getString("ddkdTEN") %></Td>
															<Td align="center"><%= ETCRs.getString("MA") %></Td>
															<Td align="left"><%= ETCRs.getString("TEN") %></Td>
															<Td align="center"><%= ETCRs.getString("DONVI") %></Td>
															<Td align="right"><%= ETCRs.getString("soluong") %></Td>
															<Td align="right"><%= formatter.format(ETCRs.getDouble("dongia")) %></Td>
															<Td align="right"><%= formatter.format(ETCRs.getDouble("doanhthu")) %></Td>
															<Td align="right"><%= formatter.format(ETCRs.getDouble("thuexuat")) %></Td>
															<Td align="right"><%= formatter.format( ETCRs.getDouble("AVAT") ) %></Td>
															
														
												<% m++;}}catch(Exception e){ e.printStackTrace(); }} %>
												</TR>
											</TABLE>
										
										</div>
										
										<div id="tabHDKHAC" class="tab_content">
											<TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
												<TR class="plainlabel" valign="bottom">
													<th align="center"> STT</th>
													<th align="center"> NH??N VI??N B??N H??NG</th>
													<th align="center"> M?? v???t t??</th>
													<th align="center"> T??n v???t t??</th>
													<th align="center"> ??VT</th>
													<th align="center"> S??? l?????ng</th>
													<th align="center"> ????n gi?? </th>
													<th align="center"> Doanh thu</th>
													<th align="center"> Thu??? GTGT</th>
													<th align="center"> T???ng ti???n</th>
												
												</TR>
												
												<%
												if(HDKHACRs!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (HDKHACRs.next()){
															if (m % 2 != 0) {%>						
															<TR class= <%=lightrow%> >
														<%} else {%>
															<TR class= <%= darkrow%> >
														<%}%>
															<Td align="center"><%= m %></Td>
															<Td align="center"><%= HDKHACRs.getString("ddkdTEN")==null?"":HDKHACRs.getString("ddkdTEN") %></Td>
															<Td align="center"><%= HDKHACRs.getString("MA") %></Td>
															<Td align="left"><%= HDKHACRs.getString("TEN") %></Td>
															<Td align="center"><%= HDKHACRs.getString("DONVI") %></Td>
															<Td align="right"><%= HDKHACRs.getString("soluong") %></Td>
															<Td align="right"><%= formatter.format(HDKHACRs.getDouble("dongia")) %></Td>
															<Td align="right"><%= formatter.format(HDKHACRs.getDouble("doanhthu")) %></Td>
															<Td align="right"><%= formatter.format(HDKHACRs.getDouble("thuexuat")) %></Td>
															<Td align="right"><%= formatter.format(HDKHACRs.getDouble("AVAT")) %></Td>
															 
														
												<% m++;}}catch(Exception e){e.printStackTrace();}} %>
												</TR>
											</TABLE>
										</div>
									</div>
                	</td>
                	
                </tr>
                </table>

                	            
        </fieldset>                      
    	</div>
        
    </div>  
</div>
</form>
</body>
</HTML>
<%
 
if(hdRs!=null)  hdRs.close();
if(khRs!=null)  khRs.close();
if(ETCRs!=null)  ETCRs.close();
if(ETCHORS!=null)  ETCHORS.close();
if(OTCRs!=null)  OTCRs.close();
if(KMRs!=null)  KMRs.close();
if(khuvucRs!=null)  khuvucRs.close();
if(nppRs!=null)  nppRs.close();
if(totalRs!=null)  totalRs.close();
if(ddkd!=null)  ddkd.close();

obj.DBclose();
obj=null;

	}%>
	