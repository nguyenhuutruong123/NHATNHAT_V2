<%@page import="geso.dms.center.beans.tinhthanh.ITinhthanhList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>

<% 
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	ITinhthanhList tinhList = (ITinhthanhList)session.getAttribute("tinhList");
	ResultSet tinhRs = tinhList.getTinhthanhRs();
	ResultSet vungRs = tinhList.getVungRs();
	session.setAttribute("db", null);
	String url = "";
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("TinhthanhSvl","",userId);
		url = util.getChuyenNguUrl("TinhthanhSvl", "",session);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
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
   
  	<script type="text/javascript" src="../scripts/jquery-1.js"></script>
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
    
	<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
		$(document).ready(function()
		{
			$(".select2").select2();
		});
	</script>
    
    <SCRIPT language="javascript" type="text/javascript">
	 function submitform() {
		 document.forms["ckParkForm"].action.value= 'submit';
		 document.forms["ckParkForm"].submit();
	 }
	 function excel() {
		 document.forms["ckParkForm"].action.value= 'excel';
		 document.forms["ckParkForm"].submit();
	 }
	 function clearform() {   
	    document.forms["ckParkForm"].ten.value = "";
	    document.forms["ckParkForm"].ma.value = "";
	    document.forms["ckParkForm"].vungId.selectedIndex = 0;
	    submitform();
	 }
	 function newform() {
		 document.forms["ckParkForm"].action.value= 'new';
		 document.forms["ckParkForm"].submit();
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
<form name="ckParkForm" method="post" action="../../TinhthanhSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;<%=url %>
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
                <TR>
                        <TD class="plainlabel" width="100px"><%=Utility.GLanguage("Mã",session,jedis) %></TD>
                        <TD class="plainlabel" width="250px" >
                            <input type="text" id="ma" name="ma" value="<%= tinhList.getMa() %>" onchange="submitform()"/>
                        </TD>
                    </TR>
                	 <TR>
                        <TD class="plainlabel" width="100px"><%=Utility.GLanguage("Tên",session,jedis) %></TD>
                        <TD class="plainlabel" width="250px" >
                            <input type="text" id="ten" name="ten" value="<%= tinhList.getTen() %>" onchange="submitform()"/>
                        </TD>
                    </TR>
                	 <TR>
                        <TD class="plainlabel" width="100px"><%=Utility.GLanguage("Vùng",session,jedis) %></TD>
                        <TD class="plainlabel" width="250px" >
                            <select class="select2" name="vungId" onchange="submitform()">
                            	<option value=""></option>
                            	<%
                            	if (vungRs != null) {
                            		while (vungRs.next()) {
                            			if (vungRs.getString("PK_SEQ").equals(tinhList.getVung())) {
                            				%>
                            	<option value="<%= vungRs.getString("PK_SEQ") %>" selected="selected"><%= vungRs.getString("TEN") %></option>
                            				<%
                            			} else {
                            				%>
                            	<option value="<%= vungRs.getString("PK_SEQ") %>"><%= vungRs.getString("TEN") %></option>
                            				<%
                            			}
                            		}
                            		vungRs.close();
                            	}
                            	%>
                            </select>
                        </TD>
                    </TR>
                    <tr>
                        <td colspan="4" class="plainlabel">
                            <a class="button" href="javascript:submitform();">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:excel()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;    
                        </td>
                    </tr>        					
                </TABLE>
       	</fieldset>
       	<fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Tỉnh thành",session,jedis) %> &nbsp;&nbsp;
                <%
		       	if (quyen[Utility.THEM] != 0) {
		       		%>
		       		<a class="button3"  onclick="newform()">
		       			<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> 
		       		</a>
		       		<%
		       	}
		       	%>
		    </legend>
                <table width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
 					<thead>
 						<tr class="tbheader">
 							<th align="center"><%=Utility.GLanguage("Mã hệ thống",session,jedis) %></th>
 							<!-- <th align="center">Mã</th> -->
 							<th align="center"><%=Utility.GLanguage("Tên Tỉnh",session,jedis) %></th>
 							<th align="center"><%=Utility.GLanguage("Tên Vùng",session,jedis) %></th>
 							<th align="center"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></th>
 							<th align="center"><%=Utility.GLanguage("Người tạo",session,jedis) %></th>
 							<th align="center"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></th>
 							<th align="center"><%=Utility.GLanguage("Người sửa",session,jedis) %></th>
 							<th align="center"><%=Utility.GLanguage("Tác vụ",session,jedis) %></th>
 						</tr>
 					</thead>
 					<tbody>
 						<% 
 						if (tinhRs != null) {
							int m = 0;
							
							while (tinhRs.next()) {
								if (m % 2 != 0) {
								%>
						<tr class="tblightrow">
								<%
								} else {
								%>
						<tr class="tbdarkrow">
								<%
								}
							%>
 							<td align="center"><%= tinhRs.getString("MAHETHONG") %></td>
 							<%-- <td align="center"><%= tinhRs.getString("MA") %></td> --%>
 							<td align="center"><%= tinhRs.getString("TINH") %></td>
 							<td align="center"><%= tinhRs.getString("VUNG") %></td>
 							<td align="center"><%= tinhRs.getString("NGAYTAO") %></td>
 							<td align="center"><%= tinhRs.getString("NGUOITAO") %></td>
 							<td align="center"><%= tinhRs.getString("NGAYSUA") %></td>
 							<td align="center"><%= tinhRs.getString("NGUOISUA") %></td>
 							<td align="center">
 								<%
 								if (quyen[Utility.SUA] != 0) {
 									%>
 								<a href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TinhthanhUpdateSvl?userId="+userId+"&update="+tinhRs.getString("MAHETHONG")+"") %>">
 									<img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0>
 								</a>
 									<%
 								}
 								%>
 								
 								
 								
 								<%
 								if (quyen[Utility.XEM] != 0) {
 									%>
 								<a href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TinhthanhUpdateSvl?userId="+userId+"&update="+ tinhRs.getString("MAHETHONG")+"") %>&isDisplay=1">
 									<img src="../images/Display20.png" alt="Hien thi" width="20" height="20" longdesc="Cap nhat" border = 0>
 								</a>
 									<%
 								}
 								%>
 								
 								<%-- 
 								<%if(quyen[Utility.XOA]!=0){ %>
									<A href = "../../TinhthanhSvl?userId=<%=userId%>&delete=<%=tinhRs.getString("MAHETHONG") %>">
										<img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn muốn xoá tỉnh thành này?')) return false;">\
									</A>
								<%} %>	 --%>
 								
 							</td>
 						</tr>
							<%
							m++;}
							tinhRs.close();
 						}
 						%>
 					</tbody>
                </table>
        </fieldset>
    	</div>
    </div>  
</div><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
<script type='text/javascript' src='../scripts/loading.js'></script>
</body>
</HTML>
<%
	tinhList.getDb().shutDown();
	}
%>