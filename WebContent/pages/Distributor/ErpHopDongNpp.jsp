<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.hopdong.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% IErpHopdongNppList obj = (IErpHopdongNppList)session.getAttribute("obj"); %>
<% ResultSet nhapkhoRs =  obj.getDondathangRs(); %>
<% ResultSet nppRs = obj.getKhRs(); %>
<% ResultSet loaihdRs = obj.getLoaiHDRs(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% obj.setNextSplittings();
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/ORGALIFE/index.jsp");
	}else{
		
		String view = obj.getView();
		
		int[] quyen = new  int[6];
		String url = "";
		if(view.equals("convert"))
		{
			quyen = util.Getquyen("ErpHopdongNppSvl","&view=convert",userId);
			url = util.getUrl("ErpHopdongNppSvl","&view=convert");
		}
		else
		{
			quyen = util.Getquyen("ErpHopdongNppSvl","",userId);
			url = util.getUrl("ErpHopdongNppSvl","");
		}
		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
   	
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
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
    <script type="text/javascript">
        $(document).ready(function(){
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
    </script>
    <SCRIPT language="javascript" type="text/javascript">
	 function submitform()
	 {   
	    document.forms["ckParkForm"].submit();
	 }
	 function newform()
	 {   
		document.forms["ckParkForm"].action.value = "Tao moi";
	    document.forms["ckParkForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["ckParkForm"].tungay.value = "";
	    document.forms["ckParkForm"].denngay.value = "";
	    document.forms["ckParkForm"].trangthai.value = "";
	    document.forms["ckParkForm"].khId.value = "";
	    document.forms["ckParkForm"].loaihdId.value = "";
	    document.forms["ckParkForm"].mahd.value = "";
	    document.forms["ckParkForm"].submit();
	 }
	 function thongbao()
	 {
		 var tt = document.forms["ckParkForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["ckParkForm"].msg.value);
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
	
	<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
	<style type="text/css">
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
		th {
		cursor: pointer;
		}	
  	</style>
	
	
</head>
<body>
<form name="ckParkForm" method="post" action="../../ErpHopdongNppSvl">

		<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="nppId" value='<%= obj.getNppId() %>'>
<input type="hidden" name="loaidonhang" value='<%= obj.getLoaidonhang() %>'>
<input type="hidden" name="view" value='<%= obj.getView() %>'>
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	<%=url %>

        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %></legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
                	 <TR>
                        <TD class="plainlabel" width="100px"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
                        <TD class="plainlabel" width="250px" >
                            <input type="text" class="days" name="tungay" id = "tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform();" />
                        </TD>
                    
                        <TD class="plainlabel" width="100px"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" name="denngay" id="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform();" />
                        </TD>
                    </TR>
                    
                     <TR>
                     
                     	<TD class="plainlabel" width="100px">Kh??ch h??ng ETC</TD>
                        <TD class="plainlabel">
                            <select name = "khId"   id = "khId"  class="select2" style="width: 200px" onchange="submitform();" >
	                    		<option value=""> </option>
	                        	<%
	                        		if(nppRs != null)
	                        		{
	                        			try
	                        			{
	                        			while(nppRs.next())
	                        			{  
	                        			if( nppRs.getString("pk_seq").equals(obj.getKhTen())){ %>
	                        				<option value="<%= nppRs.getString("pk_seq") %>" selected="selected" ><%= nppRs.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nppRs.getString("pk_seq") %>" ><%= nppRs.getString("ten") %></option>
	                        		 <% } } nppRs.close();} catch(Exception ex){}
	                        		}
	                        	%>
	                    	</select>
                        </TD>
                     	
                        <TD class="plainlabel" valign="middle"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                           <select name="trangthai"  id="trangthai" class="select2" style="width: 200px"  onchange="submitform();"  >
								<% if (obj.getTrangthai().equals("0")){%>
								  	<option value="0" selected>Ch??a ch???t</option>
								  	<option value="1">???? ch???t</option>
								  	<option value="2" >???? chuy???n th??nh ????n h??ng</option>
								  	<option value="3" >Ho??n t???t</option>
								  	<option value="" ></option>
								<%}else if(obj.getTrangthai().equals("1")) {%>
								 	<option value="0" >Ch??a ch???t</option>
								  	<option value="1" selected>???? ch???t</option>
								  	<option value="2" >???? chuy???n th??nh ????n h??ng</option>
								  	<option value="3" >Ho??n t???t</option>
								  	<option value="" ></option>
								<%}else if(obj.getTrangthai().equals("2")) {%>
								 	<option value="0" >Ch??a ch???t</option>
								  	<option value="1" >???? ch???t</option>
								  	<option value="2" selected >???? chuy???n th??nh ????n h??ng</option>
								  	<option value="3" >Ho??n t???t</option>
								  	<option value="" ></option>
								<%}else if(obj.getTrangthai().equals("3")) {%>
								 	<option value="0" >Ch??a ch???t</option>
								  	<option value="1" >???? ch???t</option>
								  	<option value="2"  >???? chuy???n th??nh ????n h??ng</option>
								  	<option value="3" selected >Ho??n t???t</option>
								  	<option value="" ></option>
								<%} else  {%>
								 	<option value="0" >Ch??a ch???t</option>
								  	<option value="1" >???? ch???t</option>
								  	<option value="2"  >???? chuy???n th??nh ????n h??ng</option>
								  	<option value="3">Ho??n t???t</option>
								  	<option value="" selected></option>
							<%} %>
                           </select>
                        </TD>  
                        
                                              
                    </TR>   
                    <TR>
                    <TD  class="plainlabel">M?? h???p ?????ng</TD>
							<TD class="plainlabel" ><INPUT name="mahd" id="mahd" type="text" size="30" value = '<%= obj.getMaHD() %>' onChange = "submitform();"></TD>
								
					<TD class="plainlabel" >Lo???i h???p ?????ng</TD>
                        <TD class="plainlabel">
                            <select name = "loaihdId" id = "loaihdId" class="select2" style="width: 200px" onchange="submitform();" >
	                    		<option value="">All </option>
	                        	<%
	                        		if(loaihdRs != null)
	                        		{
	                        			try
	                        			{
	                        			while(loaihdRs.next())
	                        			{  
	                        			if( loaihdRs.getString("LoaiDonHang").equals(obj.getLoaiHD())){ %>
	                        				<option value="<%= loaihdRs.getString("LoaiDonHang") %>" selected="selected" ><%= loaihdRs.getString("TenLoaiHD") %></option>
	                        			<%}else { %>
	                        				<option value="<%= loaihdRs.getString("LoaiDonHang") %>" ><%= loaihdRs.getString("TenLoaiHD") %></option>
	                        		 <% } } loaihdRs.close();} catch(Exception ex){}
	                        		}
	                        	%>
	                    	</select>
                        </TD>
                    </TR> 
                    <tr>
                        <td colspan="4" class="plainlabel">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>        					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle">H???p ?????ng </span>&nbsp;&nbsp;
            	<%if(!view.equals("convert") && quyen[Utility.THEM]!=0){ %>
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
                 <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" style="width: 8%" >S??? h???p ?????ng </TH>
	                    <TH align="center" style="width: 8%" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TH>
	                    <TH align="center" style="width: 8%" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TH>
	                    <TH align="center" style="width: 10%" >M?? h???p ?????ng</TH>
	                    <TH align="center" style="width: 10%" >Lo???i h???p ?????ng</TH>
	                    <TH align="center" style="width: 13%" >Kh??ch h??ng ETC</TH>                   
	                    <TH align="center" style="width: 10%" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
	                    <TH align="center" style="width: 10%" ><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
	                    <TH align="center" style="width: 13%" ><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
	                    <TH align="center" style="width: 10%" ><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
	                </TR>
					<%
                 		if(nhapkhoRs != null)
                 		{
                 			int m = 0;
                 			while(nhapkhoRs.next())
                 			{  		
                 				String chuoiFORMAT = "";
                 				String loaihd = nhapkhoRs.getString("loaidonhang");
             					String _loaihopdong = "";
             					if(loaihd.equals("0"))
             						_loaihopdong = "B??nh th?????ng";
             					else if(loaihd.equals("1"))
             						_loaihopdong = "Ph??? l???c";
             					else if(loaihd.equals("2"))
             						_loaihopdong = "Nguy??n t???c";
             					else if(loaihd.equals("3"))
             						_loaihopdong = "H???p ?????ng chung";
             					
                 				if((m % 2 ) == 0) {  

                 					if(nhapkhoRs.getString("NOTE").trim().length() > 0 && !nhapkhoRs.getString("NOTE").contains("Convert t??? ????? ngh???") )
                 						chuoiFORMAT = " style='color: red;' onMouseover=\"ddrivetip('" + nhapkhoRs.getString("NOTE") + "', 250)\"; onMouseout='hideddrivetip()' ";
                 				%>
		                         	<TR class='tbdarkrow' <%= chuoiFORMAT %> >
		                        <%}else{ %>
		                          	<TR class='tblightrow' <%= chuoiFORMAT %> >
		                        <%} %>
		                    <TD align="center"><%= nhapkhoRs.getString("pk_seq") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("TUNGAY") %></TD>
		                    <TD ><%= nhapkhoRs.getString("DENNGAY") %></TD>  
		                    <TD ><%= nhapkhoRs.getString("mahopdong") %></TD>
		                    <TD ><%= _loaihopdong %></TD>
		                    <TD ><%= nhapkhoRs.getString("nppTEN") %></TD>  
		                    	 <TD align="center">
		                    	<%
		                    		String trangthai = "";
		                    		String tt = nhapkhoRs.getString("trangthai");
		                    		if(tt.equals("0")) //NPP TAO
		                    			trangthai = "Ch??a ch???t";
		                    		else
		                    		{
		                    			if(tt.equals("1"))
		                    			{
		                    				trangthai = "???? duy???t";
		                    			}
		                    			else if(tt.equals("2"))
		                    			{
			                    			trangthai = "???? chuy???n th??nh ????n h??ng";
		                    			}
		                    			else if(tt.equals("3"))
		                    			{
		                    				trangthai = "Ho??n t???t";
	                    				}
		                    			else if(tt.equals("4"))
		                    			{
		                    				trangthai = "Kh??ng tr??ng th???u";
	                    				}
		                    		}
		                    	%>
		                    	<%= trangthai %>
		                    </TD>   									                                    
         					<TD align="center"><%= nhapkhoRs.getString("NGAYSUA") %></TD>
							<TD align="center"><%= nhapkhoRs.getString("NGUOISUA") %></TD>
									
		                    <TD align="center"> 
		                    <% if(tt.equals("0")){ %>
		                    
		                    <%if(!view.equals("convert") && quyen[Utility.SUA]!=0){ %>
                                	<A href = "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpHopdongNppUpdateSvl?userId="+userId+"&update="+nhapkhoRs.getString("PK_SEQ")+"") %>"><IMG src="../images/Edit20.png" alt="C???p nh???t" title="C???p nh???t" border=0></A>&nbsp;
                               <% } %>
                                
                                <%if(!view.equals("convert") && quyen[Utility.CHOT]!=0){ %>
    	                            <A href = "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpHopdongNppSvl?userId="+userId+"&chot="+ nhapkhoRs.getString("PK_SEQ") +"&loaidonhang="+ obj.getLoaidonhang()+"") %>"><img src="../images/Chot.png" alt="Ch???t" title="Ch???t" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n ch???t h???p ?????ng n??y?')) return false;"></A>&nbsp;
	                            <% } %>
                                
                                <%if(!view.equals("convert") && quyen[Utility.XOA]!=0){ %>
                                	<A href = "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpHopdongNppSvl?userId="+userId+"&delete="+ nhapkhoRs.getString("PK_SEQ")+" &loaidonhang="+ obj.getLoaidonhang()+"") %>"><img src="../images/Delete20.png" alt="X??a" title="X??a" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n x??a h???p ?????ng n??y?')) return false;"></A>
                              	<% } %>	
                              
                              <%if(!view.equals("convert") && quyen[Utility.XOA]!=0){ %>
                                	<A href = "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpHopdongNppSvl?userId="+userId+"&khongtrungthau="+ nhapkhoRs.getString("PK_SEQ")+"&loaidonhang="+ obj.getLoaidonhang()+"") %>"><img src="../images/warning.gif" alt="Kh??ng tr??ng th???u" title="Kh??ng tr??ng th???u" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n ghi nh???n kh??ng tr??ng th???u h???p ?????ng n??y?')) return false;"></A>
                              	<% } %>			
                              									
		                    <%} else{ %>
		                    
			                    	<%if(quyen[Utility.XEM]!=0){ %>
			                    		<A href = "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpHopdongNppUpdateSvl?userId="+userId+"&display="+ nhapkhoRs.getString("PK_SEQ")+"") %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>
			                    	<% } %>	
			                    	
			                    	<% if( ( tt.equals("1") || tt.equals("2") ) && !loaihd.equals("1") ) { %>
			                    		
			                    		<%-- <% if(!loaihd.equals("3")){ %> --%>
			                    		<%if(view.equals("convert") && quyen[Utility.THEM]!=0){ %>
			                    			<A href = "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpHopdongNppSvl?userId="+userId+"&convert="+ nhapkhoRs.getString("PK_SEQ")+"") %>"><IMG src="../images/Next.png" alt="Chuy???n th??nh ????n h??ng" title="Chuy???n th??nh ????n h??ng" width="20px" border=0 onclick="if(!confirm('B???n c?? mu???n chuy???n th??nh ????n h??ng?')) return false;" ></A>&nbsp;
			                    		<%} %>
			                    		<%-- <% } else { %>
			                    			<A href = "../../ErpHopdongNppUpdateSvl?userId=<%=userId%>&convert=<%= nhapkhoRs.getString("PK_SEQ") %>"><IMG src="../images/Next.png" alt="Chuy???n th??nh ????n h??ng" title="Chuy???n th??nh ????n h??ng" width="20px" border=0  ></A>&nbsp;
			                    		<% } %> --%>
			                    		
			                    	<% } %>
			                    	
		                    <% } %>
		                    
		                    <%if(!view.equals("convert") && quyen[Utility.THEM]!=0){ %>

									<A href = "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpHopdongNppUpdateSvl?userId="+userId+"&copyHopdong="+nhapkhoRs.getString("PK_SEQ")+"") %>"><IMG src="../images/copy20.png" alt="Copy" title="Copy" border=0></A>&nbsp;

														<%} %> 
		                    
		                    </TD>
		                </TR>
                     <% m++; } nhapkhoRs.close(); } %>
					<tr class="tbfooter" > 
						 <TD align="center" valign="middle" colspan="13" class="tbfooter">
						 	 <% obj.setNextSplittings(); %>
												 <script type="text/javascript" src="../scripts/phanTrang.js"></script>
												<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
												<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												System.out.println("Current page:" + obj.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View(document.forms[0].name, <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, -1, 'view')"> &nbsp;
										   		<%} %>
						</TD>
					 </tr>
					 
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
</form>
</body>
</HTML><%}%>