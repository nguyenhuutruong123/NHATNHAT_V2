<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@page import="java.sql.SQLException" %>
<%@ page import = "geso.dms.center.beans.khaosat.*" %>
<%@ page import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUpERP/index.jsp");
	}else{ %>

<%
 	IKhaosat obj =(IKhaosat)session.getAttribute("csxBean");
	Hashtable<String, String> cauhoi_noidung = (Hashtable<String, String>)obj.getNoidungcauhoi();
	ResultSet nvList = obj.getDdkdRs();
	ResultSet kvList = obj.getKhuvucRs();
	ResultSet vList = obj.getVungRs();
	String url = util.getChuyenNguUrl("KhaosatSvl", "",session);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<script type="text/javascript" src="../scripts/ajax.js"></script>
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">

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
<SCRIPT language="JavaScript" type="text/javascript">
	function submitform()
	{
	    document.forms["khtt"].submit();
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
	
	function save()
	{
	  document.forms["khtt"].action.value = "save";
	  document.forms["khtt"].submit(); 
    }
	
	function ChangeLoaiCauHoi(cau)
	{
		var loaicauhoi = document.getElementById(cau + "_loaicauhoi").value;
		if(loaicauhoi == "1")  //Mot lua chon
		{
			document.getElementById(cau + "_motluachon").style.display = "";
			document.getElementById(cau + "_nhieuluachon").style.display = "none";
		}
		else
		{
			if(loaicauhoi == "2")
			{
				document.getElementById(cau + "_motluachon").style.display = "none";
				document.getElementById(cau + "_nhieuluachon").style.display = "";
			}
			else
			{
				document.getElementById(cau + "_motluachon").style.display = "none";
				document.getElementById(cau + "_nhieuluachon").style.display = "none";
			}
		}
	}
	
	function sellectAll(id1, id2)
	 {
		 var chkAll = document.getElementById(id1);
		 var spIds = document.getElementsByName(id2);
		 
		 if(chkAll.checked)
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = true;
			 }
		 }
		 else
		 {
			 for(i = 0; i < spIds.length; i++)
			 {
				 spIds.item(i).checked = false;
			 }
		 }
	 }
	
	function LocNhanVien()
	{
		document.forms["khtt"].action.value = "submit";
		document.forms["khtt"].submit(); 
	}
	
</SCRIPT>

<script>
//perform JavaScript after the document is scriptable.
$(function() {
 // setup ul.tabs to work as tabs for each div directly under div.panes
 	$("ul.tabs").tabs("div.panes > div");
});
</script>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="khtt" method="post" action="../../KhaosatUpdateSvl" >
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>' >
<input type="hidden" name="id" value='<%= obj.getId() %>' >
<input type="hidden" name="action" value="0">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						  
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %> > <%=Utility.GLanguage("Cập nhật",session,jedis) %></TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %></TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
						 <% String ur = Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhaosatSvl?userId="+userId);  %>			
							<TD width="30" align="left"><A href="../../RouterSvl?task=<%=ur %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
						    <TD width="30" align="left" >
						    <div id="btnSave">
						    <A href="javascript: save()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
						    </div>
						    </TD>
							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Thông báo",session,jedis) %> </LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= obj.getMsg() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Thông tin khảo sát",session,jedis) %></LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
                          <TR>
                          		<TD class="plainlabel" valign="middle" width="120px" ><%=Utility.GLanguage("Tiêu đề",session,jedis) %> </TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="tieude" value="<%= obj.getTieude() %>"  > 
		                        </TD>          
		                  </TR> 
                          <TR>
                          		<TD class="plainlabel" valign="middle" width="120px" ><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="diengiai" value="<%= obj.getDiengiai() %>"  > 
		                        </TD>          
		                  </TR> 
		                   <TR>
                          		<TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Đối tượng khảo sát",session,jedis) %> </TD>   
		                        <TD class="plainlabel" valign="middle" >
		                            <select name="doituong" onChange="submitform();" >
		                            	
		                            	<% if(obj.getDoituong().equals("KH")){ %>
		                            		<option value="KH" selected="selected" ><%=Utility.GLanguage("Khách hàng",session,jedis) %></option>
		                            		<option value="NVBH"  ><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></option>
		                            	<% } else { %> 
		                            		<option value="KH" ><%=Utility.GLanguage("Khách hàng",session,jedis) %></option>
		                            		<option value="NVBH" selected="selected" ><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></option>
		                            	<%  } %>
		                            </select>
		                        </TD>                
		                  </TR> 
		                  
		                  
                           <% if(obj.getDoituong().equals("NVBH")){ %>
	                          <TR>
	                          		<TD class="plainlabel" valign="middle" colspan="2"  >
	                          	
	                          		<% if(obj.getLoaict().trim().equals("1")){ %>
										  	 					<INPUT TYPE="radio" NAME="loaict" value="0" onChange="submitform();"><%=Utility.GLanguage("Câu hỏi Bình thường",session,jedis) %>
										  	 					<INPUT TYPE="radio" NAME="loaict" value="1" checked onChange="submitform();"><%=Utility.GLanguage("Câu hỏi đầu ngày",session,jedis) %>
										  	 					
											  	 			<%}else{%>
										  	 					<INPUT TYPE="radio" NAME="loaict" value="0" checked onChange="submitform();"><%=Utility.GLanguage("Câu hỏi Bình thường",session,jedis) %>
										  	 					<INPUT TYPE="radio" NAME="loaict" value="1" onChange="submitform();"><%=Utility.GLanguage("Câu hỏi đầu ngày",session,jedis) %>	 			
										  	 		  <%}%>
	                          		  </TD>         
			                  </TR> 
		                  <%}else {%>
			                  <TR>
	                          		<TD class="plainlabel" valign="middle" colspan="2"  >
	                          		<INPUT TYPE="radio" NAME="loaict" value="0" checked><%=Utility.GLanguage("Câu hỏi Bình thường",session,jedis) %>
	                          		  </TD>         
			                  </TR> 
		                  <%} %>
		                  
		                  	<TR>
									<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" readonly />
			                    	</td>
			                  </TR>
			                  <TR>
			                    	  <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
								      <td class="plainlabel">
				                            <input type="text"  class="days" size="11"
				                                    id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" readonly />
				                    </td>
			                    	
								</TR>
		                  <TR>
                          		<TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Bộ phận",session,jedis) %> </TD>   
		                        <TD class="plainlabel" valign="middle" >
		                            <select name="bophan" >
		                            	<option value="" ></option>
		                            	<% if(obj.getBophan().equals("R&D")){ %>
		                            		<option value="R&D" selected="selected" >R&D</option>
		                            	<% } else { %> 
		                            		<option value="R&D" >R&D</option>
		                            	<%  } %>
		                            	<% if(obj.getBophan().equals("CRM")){ %>
		                            		<option value="CRM" selected="selected" >CRM</option>
		                            	<% } else { %> 
		                            		<option value="CRM" >CRM</option>
		                            	<%  } %>
		                            	<% if(obj.getBophan().equals("Mar res")){ %>
		                            		<option value="Mar res" selected="selected" >Mar res</option>
		                            	<% } else { %> 
		                            		<option value="Mar res" >Mar res</option>
		                            	<%  } %>
		                            </select>
		                        </TD>                
		                  </TR> 
		                  <TR>
                          		<TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Số câu hỏi",session,jedis) %> </TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="socauhoi" value="<%= obj.getSocauhoi() %>" style="text-align: right;" onchange="submitform();" onkeypress="return keypress(this);" > 
		                        </TD>          
		                  </TR> 
		                  
		                  
		                  
		                  <TR>
		                  	<TD colspan="2" >
		                  		<ul class="tabs">
		                  			<li><a href="#"><%=Utility.GLanguage("Nhân viên áp dụng",session,jedis) %></a></li>
		                  		
		                  		<% if(obj.getSocauhoi().trim().length() > 0) { %>	
		                  			<% for(int i = 0; i < Integer.parseInt(obj.getSocauhoi()); i++) { %>
		                  				<li><a href="#">Câu <%= i + 1 %></a></li>
		                  			<% } %>
		                  		<% } %>
		                  			
		                  		</ul>
		                  		
		                  		<div class="panes">
		                  		
		                  		<div style="font-size: 1.0em; font-weight: 500" >
		                  				<TABLE width="100%" cellspacing="1" cellpadding="4">
		                  					<TR>
		                  						<td ><%=Utility.GLanguage("Vùng",session,jedis) %> </td>
		                  						<td colspan="4" >
		                  							<select name="vungId" onchange="LocNhanVien();">
														 <option value = "" > </option>
							                             <%
							                             if (vList != null)
							                             {
							                            	 while (vList.next())
							                            	 { %>
							                       				<% if(vList.getString("pk_seq").equals(obj.getVungId())){ %>
							                       					<option value ="<%= vList.getString("pk_seq") %>" selected="selected"> <%= vList.getString("ten") %></option>
							                       				<%}else{ %>
							                       					<option value ="<%= vList.getString("pk_seq") %>"> <%= vList.getString("ten") %></option>
							                       				<%} %>
							                       			<% }
							                             }
							                             %>
						                             </select>  
						                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
		                  						 <%=Utility.GLanguage("Khu vực",session,jedis) %>
		                  							 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		                  							<select name="khuvucId" onchange="LocNhanVien();">
														 <option value =""> </option>
							                             <%
							                             if (kvList!=null)
							                             {
							                            	 while (kvList.next())
							                            	 { %>
							                       				<% if(kvList.getString("pk_seq").equals(obj.getKhuvucId())){ %>
							                       					<option value ="<%=kvList.getString("pk_seq") %>" selected="selected"> <%=kvList.getString("ten") %></option>
							                       				<%}else{ %>
							                       					<option value ="<%=kvList.getString("pk_seq") %>"> <%=kvList.getString("ten") %></option>
							                       				<%} %>
							                       			<% }
							                             }
						                             	 %>
						                             </select>      
		                  						</td>
		                  					</TR>
		                  					
		                  					<TR>
		                  						<TH width="10%" align="center" class="plainlabel" ><%=Utility.GLanguage("Mã nhân viên",session,jedis) %></TH>
		                  						<TH width="30%" align="center" class="plainlabel" ><%=Utility.GLanguage("Tên nhân viên",session,jedis) %></TH>
		                  						<TH width="40%" align="center" class="plainlabel" ><%=Utility.GLanguage("Địa chỉ",session,jedis) %></TH>
		                  						<TH width="10%" align="center" class="plainlabel" ><%=Utility.GLanguage("Điện thoại",session,jedis) %></TH>
		                  						<TH width="10%" align="center" class="plainlabel" ><%=Utility.GLanguage("Chọn hết",session,jedis) %> <input type="checkbox" id='chkAll' onchange="sellectAll('chkAll', 'nvIds');"   > </TH>
		                  					</TR>
		                  					
		                  					<% if(nvList != null) 
		                  					{ 
		                  						int m = 0;
		                  						while(nvList.next()) 
		                  						{ 
		                  							%>
		                  							
		                  							<TR class="<%= m % 2 == 0 ? "tblightrow" : "tbdarkrow"  %>" >
		                  								<TD style="font-size: 1.0em" ><%= nvList.getString("MANHANVIEN") %></TD>
		                  								<TD><%= nvList.getString("ten") %></TD>
		                  								<TD><%= nvList.getString("diachi") %></TD>
		                  								<TD><%= nvList.getString("dienthoai") %></TD>
		                  								<TD align="center" >
		                  									<% if( obj.getDdkdId().indexOf(nvList.getString("pk_seq")) >= 0 ) { %>
		                  										<input type="checkbox" name="nvIds" value="<%= nvList.getString("pk_seq") %>" checked="checked"  >
		                  									<% } else { %> 
		                  										<input type="checkbox" name="nvIds" value="<%= nvList.getString("pk_seq") %>"   >
		                  									<% } %>
		                  								</TD>
		                  							</TR>
		                  							
		                  						<% m++; } 
		                  						nvList.close(); 
		                  					} %>
		                  					
		                  				</TABLE>
		                  			</div>
		                  		
		                  		<% if(obj.getSocauhoi().trim().length() > 0) { %>	
		                  			<% for(int i = 0; i < Integer.parseInt(obj.getSocauhoi()); i++) { 
		                  				
		                  					String noidung = cauhoi_noidung.get("cau" + ( i + 1 ) );
		                  					System.out.println("Noi dung lay duoc: " + noidung);
		                  					
		                  					String loaisauhoi = "";
		                  					String cauhoi = "";
		                  					String huongdantraloi = "";
		                  					String dapan = "";
		                  					
		                  					if(noidung != null)
		                  					{
		                  						if(noidung.contains(",,"))
		                  						{
		                  							String[] ndArr = noidung.split(",,");
		                  							loaisauhoi = ndArr[0].trim();
		                  							cauhoi = ndArr[1].trim();
		                  							huongdantraloi = ndArr[2].trim();
		                  							dapan = ndArr[3].trim();
		                  						}
		                  					}
		                  					
		                  					String[] dapanArr = null;
		                  					if(dapan.trim().length() > 0)
		                  						dapanArr = dapan.split("__");
		                  					if(dapanArr != null)
		                  					{
		                  						for(int j = 0; j < dapanArr.length; j++)
		                  						{
		                  							if(dapanArr[j].equals("NA"))
		                  								dapanArr[j] = "";
		                  						}
		                  					}
		                  				
		                  				%>
		                  			
			                  			<div style="font-size: 12px;" >
			                  				<table style="width: 100%" cellspacing="10" >
			                  					<tr>
			                  						<td width="120px" ><span style="font-size: 12px;" ><%=Utility.GLanguage("Loại câu hỏi",session,jedis) %></span></td>
			                  						<td>
			                  							<select id="cau<%= i + 1 %>_loaicauhoi" name="cau<%= i + 1 %>_loaicauhoi"  onchange="ChangeLoaiCauHoi('cau<%= i + 1 %>');" >
			                  								
			                  								<option value="0" selected="selected" ><%=Utility.GLanguage("Văn bản",session,jedis) %></option>
			                  								
			                  								<% if(loaisauhoi.equals("1")) { %> 
			                  									<option value="1" selected="selected" ><%=Utility.GLanguage("Một lựa chọn",session,jedis) %></option>
			                  								<% } else { %> 
			                  									<option value="1"><%=Utility.GLanguage("Một lựa chọn",session,jedis) %></option>
			                  								<% } %>
			                  								
			                  								<% if(loaisauhoi.equals("2")) { %> 
			                  									<option value="2" selected="selected" ><%=Utility.GLanguage("Nhiều lựa chọn",session,jedis) %></option>
			                  								<% } else { %> 
			                  									<option value="2"><%=Utility.GLanguage("Nhiều lựa chọn",session,jedis) %></option>
			                  								<% } %>
			                  								
			                  							</select>
			                  						</td>
			                  					</tr>
			                  					<tr>
			                  						<td ><span style="font-size: 12px;" ><%=Utility.GLanguage("Câu hỏi",session,jedis) %></span></td>
			                  						<td>
			                  							<input type="text" style="width: 600px" name="cau<%= i + 1 %>_cauhoi"  value="<%= cauhoi %>"  >
			                  						</td>
			                  					</tr>
			                  					<tr>
			                  						<td ><span style="font-size: 12px;" ><%=Utility.GLanguage("Hướng dẫn trả lời",session,jedis) %></span></td>
			                  						<td>
			                  							<input type="text" style="width: 600px" name="cau<%= i + 1 %>_huongdantraloi"  value="<%= huongdantraloi %>" >
			                  						</td>
			                  					</tr>
			                  					<tr>
			                  						<td valign="top" ><span style="font-size: 12px;" ><%=Utility.GLanguage("Đáp án",session,jedis) %></span></td>
			                  						<td valign="top" style="font-size: 11px;" >
			                  							
			                  							<table style="width: 100%; <%= loaisauhoi.equals("1") ? "" : "display: none;" %> " id="cau<%= i + 1 %>_motluachon" >
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="radio" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 1",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_motluachon"  value="<%= ( dapanArr == null ?  "" : dapanArr[0].trim() ) %>"  ></td>
			                  								</tr>
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="radio" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 2",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_motluachon" value="<%= ( dapanArr == null ?  "" : dapanArr[1].trim() ) %>" ></td>
			                  								</tr>
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="radio" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 3",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_motluachon" value="<%= ( dapanArr == null ?  "" : dapanArr[2].trim() ) %>"  ></td>
			                  								</tr>
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="radio" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 4",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_motluachon" value="<%= ( dapanArr == null ?  "" : dapanArr[3].trim() ) %>"  ></td>
			                  								</tr>
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="radio" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 5",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_motluachon" value="<%= ( dapanArr == null ?  "" : dapanArr[4].trim() ) %>"  ></td>
			                  								</tr>
			                  							</table>
			                  							
			                  							<table style="width: 100%; <%= loaisauhoi.equals("2") ? "" : "display: none;" %>" id="cau<%= i + 1 %>_nhieuluachon" >
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="checkbox" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 1",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_nhieuluachon"  value="<%= ( dapanArr == null ?  "" : dapanArr[0].trim() ) %>"  ></td>
			                  								</tr>
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="checkbox" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 2",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_nhieuluachon" value="<%= ( dapanArr == null ?  "" : dapanArr[1].trim() ) %>" ></td>
			                  								</tr>
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="checkbox" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 3",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_nhieuluachon" value="<%= ( dapanArr == null ?  "" : dapanArr[2].trim() ) %>"  ></td>
			                  								</tr>
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="checkbox" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 4",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_nhieuluachon" value="<%= ( dapanArr == null ?  "" : dapanArr[3].trim() ) %>"  ></td>
			                  								</tr>
			                  								<tr>
			                  									<td width="100px" ><span style="font-size: 12px;" >
			                  										<input type="checkbox" disabled="disabled" >&nbsp;<%=Utility.GLanguage("Lựa chọn 5",session,jedis) %></span>
			                  									</td>
			                  									<td><input type="text" style="width: 485px" name="cau<%= i + 1 %>_nhieuluachon" value="<%= ( dapanArr == null ?  "" : dapanArr[4].trim() ) %>"  ></td>
			                  								</tr>
			                  							</table>
			                  						
			                  						</td>
			                  					</tr>
			                  				</table>
			                  			</div>
		                  			
		                  			<% } %>
		                  			
		                  		</div>
		                  		
		                  	</TD>
		                  </TR>
		                  
		                  <% } %>
		                  
						</TABLE>
							
						</FIELDSET>						
					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
	</TABLE><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>

</BODY>
</HTML>
<%}%>
