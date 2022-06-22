<%@ page  import = "geso.dms.center.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.phieuthanhtoan.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{ %>
<% IErpDonmuahangList_Giay obj = (IErpDonmuahangList_Giay)session.getAttribute("obj");
	
%>
<% ResultSet dvthList = (ResultSet)obj.getDvthList(); %>
<% ResultSet dmhList = (ResultSet)obj.getDmhList(); %>
<% ResultSet nguoitaoRs = (ResultSet)obj.getNguoitaoRs(); %>
<% ResultSet lspList = (ResultSet)obj.getLoaisanpham(); 

	
	int[] quyen = new  int[5];
	quyen = util.Getquyen("ErpPhieuThanhToanSvl","",userId);
	
 	NumberFormat formatter = new DecimalFormat("#,###,###"); 
 	NumberFormat formater = new DecimalFormat("##,###,###");  %>


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
   
    <script type="text/javascript" src="../scripts/jquery.js"></script>
	<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
	   <script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
   <script type="text/javascript" src="../scripts/ajax.js"></script>
   <script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script> 
	
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
	<style type="text/css">
		#dhtmltooltip
		{
			position: absolute;
			left: -300px;
			width: 300px;
			border: 1px solid black;
			padding: 5px;
			background-color: lightyellow;
			visibility: hidden;
			z-index: 100;
			font-size: 1.2em;
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
		
  	</style>
  	
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
		$(document).ready(function(){
            $(".button2").hover(function(){
                $(".button2 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button3").hover(function(){
                $(".button3 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
    </script>
    <SCRIPT language="javascript" type="text/javascript">
	 function confirmLogout()
	 {
	    if(confirm("Bạn có muốn đăng xuất?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	 }
	 function submitform()
	 {   
	    document.forms["erpDmhForm"].submit();
	 }
	 function newform()
	 {   
		document.forms["erpDmhForm"].action.value = "Tao moi";
	    document.forms["erpDmhForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["erpDmhForm"].dvth.value = "";
	    //document.forms["erpDmhForm"].tongtien.value = "";
	    document.forms["erpDmhForm"].tungay.value = "";
	    document.forms["erpDmhForm"].mactsp.value = "";
	    document.forms["erpDmhForm"].trangthai.value = "";
	    document.forms["erpDmhForm"].ncc.value = "";
	    document.forms["erpDmhForm"].sodonmuahang.value = "";
	    document.forms["erpDmhForm"].denngay.value = "";
	    document.forms["erpDmhForm"].nguoitao.value = "";
 
	    document.forms["erpDmhForm"].submit();
	 }
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 

	 function processing(id,chuoi)
	 {
 	    document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
 	 	document.getElementById(id).href=chuoi;
 	 }
	 
	</SCRIPT>
	
	<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
	     $(document).ready(function() { $("select").select2();  });
	     
	</script>

</head>
<body>
<form name="erpDmhForm" method="post" action="../../ErpPhieuThanhToanSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >

<input type="hidden" name="msg" value='<%= obj.getmsg() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:70%; padding:5px; float:left" class="tbnavigation">
        	Quản lý công nợ > Công nợ phải trả >  Đề nghị thanh toán
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
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
                        <TD class="plainlabel" width="25%">
                            <input type="text" class="days" id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform()" style="border-radius:4px; height: 20px;" />
                        </TD>
                        <TD class="plainlabel" width="22%" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform()" style="border-radius:4px; height: 20px;" />
                        </TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" valign="middle" >Đơn vị thực hiện </TD>
                        <TD class="plainlabel" valign="middle">
                            <select name="dvth" onchange="submitform()" style="width: 200px">
                            	<option value=""></option>
                            	<%
	                        		if(dvthList != null)
	                        		{
	                        			while(dvthList.next())
	                        			{  
	                        			if( dvthList.getString("pk_seq").equals(obj.getDvthId())){ %>
	                        				<option value="<%= dvthList.getString("pk_seq") %>" selected="selected" ><%= dvthList.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= dvthList.getString("pk_seq") %>" ><%= dvthList.getString("ten") %></option>
	                        		 <% } } dvthList.close();
	                        		}
	                        	%>
                            </select>
                        </TD>                        
                        <TD class="plainlabel" valign="middle">Nhà cung cấp/ Nhân viên/ Khách hàng</TD>
                        <TD class="plainlabel" valign="middle">
                            <input type="text" name="ncc" value="<%= obj.getNCC() %>" onchange="submitform()" style="border-radius:4px; height: 20px;">
                        </TD>                        
                    </TR>
                   
                    <TR>
                    	<TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle" >
                            <select name="trangthai" onchange="submitform()" style="width: 200px">
                            	<option value=""></option>
                            	
                            	<% if(obj.getTrangthai().equals("0")) { %>
                            		<option value="0" selected="selected" >Chưa chốt</option>
                            	<% } else { %> 
                            		<option value="0" >Chưa chốt</option>
                            	<%  } %>
                            	<% if(obj.getTrangthai().equals("-1")) { %>
                            		<option value="-1" selected="selected" >Chưa duyệt</option>
                            	<% } else { %> 
                            		<option value="-1" >Chưa duyệt</option>
                            	<%  } %>
                            	<% if(obj.getTrangthai().equals("-2")) { %>
                            		<option value="-2" selected="selected" >Đã duyệt(Trưởng Bộ Phận)</option>
                            	<% } else { %> 
                            		<option value="-2" >Đã duyệt(Trưởng Bộ Phận)</option>
                            	<%  } %>
                            	<% if(obj.getTrangthai().equals("-3")) { %>
                            		<option value="-3" selected="selected" >Đã duyệt(Kế toán trưởng)</option>
                            	<% } else { %> 
                            		<option value="-3" >Đã duyệt(Kế toán trưởng)</option>
                            	<%  } %>
                            	<% if(obj.getTrangthai().equals("1")) { %>
                            		<option value="1" selected="selected" >Đã duyệt(Tổng Giám Đốc)</option>
                            	<% } else { %> 
                            		<option value="1" >Đã duyệt(Tổng Giám Đốc)</option>
                            	<%  } %>
                            	 <% if(obj.getTrangthai().equals("2")) { %>
                            		<option value="2" selected="selected" >Hoàn tất</option>
                            	<% } else { %>
                            		<option value="2" >Hoàn tất</option>
                            	 <% }  %>
                            	  <% if(obj.getTrangthai().equals("3")) { %>
                            		<option value="3" selected="selected" >Đã xóa</option>
                            	<% } else { %>
                            		<option value="3" >Đã xóa</option>
                            	 <% }  %>
                            	 <% if(obj.getTrangthai().equals("4")) { %>
                            		<option value="4" selected="selected" >Đã hủy</option>
                            	<% } else { %>
                            		<option value="4" >Đã hủy</option>
                            	 <% }  %>
                            	  <% if(obj.getTrangthai().equals("5")) { %>
                            		<option value="5" selected="selected" >Đã thanh toán</option>
                            	<% } else { %>
                            		<option value="5" >Đã thanh toán</option>
                            	 <% }  %>
                            	
                            </select>
                        </TD>      
                        <%-- <TD class="plainlabel" valign="middle">Tổng tiền </TD>
                        <TD class="plainlabel" valign="middle" >
                            <input type="text" name="tongtien" value="<%= obj.getTongtiensauVat() %>" onchange="submitform()">
                        </TD>  --%> 
                         <TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Người tạo",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle" >
                            <select name="nguoitao" onchange="submitform()" style="width: 200px">
                            	<option value=""></option>
                            	<%
	                        		if(nguoitaoRs != null)
	                        		{
	                        			while(nguoitaoRs.next())
	                        			{  
	                        			if( nguoitaoRs.getString("pk_seq").equals(obj.getNguoitaoIds())){ %>
	                        				<option value="<%= nguoitaoRs.getString("pk_seq") %>" selected="selected" ><%= nguoitaoRs.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nguoitaoRs.getString("pk_seq") %>" ><%= nguoitaoRs.getString("ten") %></option>
	                        		 <% } } nguoitaoRs.close();
	                        		}
	                        	%>
                            </select>
                        </TD>                       
                    </TR>   
                    <TR>                        
		                <TD class="plainlabel" valign="middle">Số đề nghị thanh toán </TD>
                        <TD class="plainlabel" valign="middle">
                            <input type="text" name="sodonmuahang" value="<%= obj.getSodonmuahang() %>" onchange="submitform()" style="border-radius:4px; height: 20px;">
                        </TD>          
						<TD class="plainlabel" >Mã Sản Phẩm</TD>
						<TD class="plainlabel">
							<input type="text" id="mactsp" name="mactsp" value="<%= obj.getMaCtSp() %>" onchange="submitform()" style="border-radius:4px; height: 20px;" />
						</TD>                                           
                    </TR>   

                    <tr>
                        <td colspan="4" class="plainlabel">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                </TABLE>  
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle"> Đơn mua hàng </span>&nbsp;&nbsp;
            	<%if(quyen[0]!=0){ %>
            	
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>
                           <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" width="7%">Mã ĐNTT</TH>
	                    <TH align="center" width="7%">Ngày</TH>
	                    <TH align="center" width="7%">ĐVTH</TH>
	                    <TH align="center" width="15%">Nhà cung cấp/ Nhân viên/ Khách hàng</TH>
	                    <TH align="center" width="7%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
	                    <TH align="center" width="10%">Tổng tiền</TH>
	                    <TH align="center" width="5%">Tổng lượng</TH>
	                    <TH align="center" width="7%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
	                    <TH align="center" width="8%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
	                    <TH align="center" width="7%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </TH>
	                    <TH align="center" width="8%"><%=Utility.GLanguage("Người sửa",session,jedis) %> </TH>
	                    <TH align="center" width="10%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
	                </TR>
	                
	                <%  int m = 0;
	                	if(dmhList!=null) {
	                		while (dmhList.next())
	                		{
	                			String style="";
	                			String tt = dmhList.getString("Trangthai");
	                			if(dmhList.getString("noibo").equals("1"))
	                			{
	                				style="color: #006400;font-weight: bold; ";
	                			}
	                			 if((m % 2 ) == 0 & tt.equals("3")) { %>
	                			 	<TR class='tbdarkrow'  style="color: red;" <%= dmhList.getString("NOTE").trim().length() > 0 ? " style='color: red;' onMouseover=\"ddrivetip('" + dmhList.getString("NOTE") + "', 300)\"; onMouseout='hideddrivetip();' " : ""  %>  >
		                		 <%}else if((m % 2 ) != 0 & tt.equals("3")) {  %>
		                		 		<TR class='tblightrow' style="color: red;" <%= dmhList.getString("NOTE").trim().length() > 0 ? " style='color: red;' onMouseover=\"ddrivetip('" + dmhList.getString("NOTE") + "', 300)\"; onMouseout='hideddrivetip();' " : ""  %>  >	                		 
		                		 <%}else if((m % 2 ) == 0) { %>
		                				<TR class='tbdarkrow' <%= dmhList.getString("NOTE").trim().length() > 0 ? " style='color: red;' onMouseover=\"ddrivetip('" + dmhList.getString("NOTE") + "', 300)\"; onMouseout='hideddrivetip();' " : ""  %>  >
		                		 <%}else{ %>
		                		 		<TR class='tblightrow' <%= dmhList.getString("NOTE").trim().length() > 0 ? " style='color: red;' onMouseover=\"ddrivetip('" + dmhList.getString("NOTE") + "', 300)\"; onMouseout='hideddrivetip();' " : ""  %>  >
		                		<%  } %>
	                			 <TD align="center"><%= dmhList.getString("SOCHUNGTU") %></TD>
				                 <TD align="center"><%= dmhList.getString("Ngaymua") %></TD>
				                 <TD align="center"><%= dmhList.getString("Ten") %></TD>	
				                 <TD align="left"><%= dmhList.getString("nccTen") %></TD>	
	                			
	                		<%
				               if(tt.equals("0") || tt.equals("1") || tt.equals("2") || tt.equals("3")  ){%>
				                    <TD align="center">
				                    
				              <%}else{ %>
				                    <TD align="center" style="color: red;">
				              <%} %>
				              
				              <%
		                    		String duyet = dmhList.getString("Duyet");				                    	
		                    		String str = "";
		                    		
		                    		String dachot = dmhList.getString("Dachot");
		                    		String captrenduyet = dmhList.getString("CAPTREN");
		                    		String ISQLTT = dmhList.getString("ISQLTT");
		                    		String ISCS = dmhList.getString("isCS");
		                    		String ISDUYETCHI = dmhList.getString("ISDUYETCHI"); 
		                    		String ISKTTH = dmhList.getString("ISKTTH"); 
		                    		String ISKTT = dmhList.getString("ISKTT"); 
		                    		
		                    		String isthanhtoan = dmhList.getString("ISTHANHTOAN"); 
		                    		
		                    		String trangthai = "";
		                    		
				              		if(tt.equals("0"))
					                {
					                   trangthai = "Chưa chốt";
					                    			
									   if(dachot.equals("1") && captrenduyet.equals("0"))
										{
					                    	trangthai = "Chưa duyệt";
										}											   
													
									 	if(ISQLTT.equals("1"))
										{
											trangthai = "Đã duyệt (Quản lý trực tiếp)";
										}
									 	
									 	if(ISCS.equals("1"))
										{
											trangthai = "Đã duyệt (Quản lý CS)";
										}
									 	
									 	if(ISDUYETCHI.equals("1"))
										{
											trangthai = "Đã duyệt (Duyệt ĐNTT/ĐNTU)";
										}
									 	
 									 	if(ISKTTH.equals("1"))
 										{
 											trangthai = "Đã duyệt (Kế toán tổng hợp)";
 										} 
									 	
									 	if(ISKTT.equals("1"))
										{
											trangthai = "Đã duyệt (Kế toán trưởng)";
										}
												 	
									 	if(isthanhtoan.equals("1"))
									 	{
									 		trangthai = "Đã thanh toán";
									 	}
					                    			
		                    		}
		                    		else
		                    		{
		                    			if(tt.equals("1"))
		                    			{	 							                    			
				                    		trangthai = "Đã duyệt";
		                    			}
		                    			else 
		                    			{
		                    				if(tt.equals("2"))
		                    					trangthai = "Hoàn tất";
		                    				else
		                    				{
		                    					if(tt.equals("3"))
		                    						trangthai = "Đã xóa";
		                    					else
		                    						trangthai = "Đã hủy";
		                    				}					                    			
		                    		    }
					                    			 
		                    			 if(isthanhtoan.equals("1"))
									 	 {
									 		trangthai = "Đã thanh toán";
									 	 }
				                      }
				              		
		                    		if(str.trim().length() > 0)
		                    			trangthai = trangthai + " ( " + str + " ) ";
				                    		
				                %>
				                <%= trangthai %>    
				                
				                <TD align="right"><%= formater.format(Double.parseDouble(dmhList.getString("tongtienAvat"))) + " " + dmhList.getString("tiente") + "" %></TD>
			                    <TD align="center"><%= dmhList.getString("tongluong") %></TD>
			                    <TD align="center"><%= dmhList.getString("ngaytao") %></TD>
			                    <TD align="left"><%= dmhList.getString("nguoitao") %></TD>
			                    <TD align="center"><%= dmhList.getString("ngaysua") %></TD>	
			                    <TD align="left"><%= dmhList.getString("nguoisua") %></TD>	
			                    <TD align="center">
			                    <% if(tt.equals("0") && dmhList.getString("CAPTREN").equals("0")){ %>
				                
				              	<%		if(quyen[2] != 0 ){ %>
				                
		                               <A href = "../../ErpPhieuThanhToanUpdateSvl?userId=<%=userId%>&update=<%= dmhList.getString("dmhId") %>&duyet=<%= duyet %>">
		                               		<IMG src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" border=0></A>&nbsp;		  
		                               		                             
		                               <%} %>		                                
		                               
			                    	<% if( dachot.equals("0") ){ %>
		                               <%if(quyen[4]!=0){ %>
			                                 <A id='chotphieu<%= dmhList.getString("dmhId") %>'  href="">
			                                 <img src="../images/Chot.png" alt="Chốt" width="20" height="20" title="Chốt" 
										      border="0" onclick="if(!confirm('Bạn có muốn chốt phiếu này?')) {return false ;}else{ processing('<%="chotphieu"+dmhList.getString("dmhId")%>' , '../../ErpPhieuThanhToanSvl?userId=<%=userId%>&chot=<%= dmhList.getString("dmhId")%>');}"  >
										    </A>
									    <%} %>
									    
		                            <%} %>		                            
		                            
                                    <%if(quyen[1]!=0){ %>
                                		<A href = "../../ErpPhieuThanhToanSvl?userId=<%=userId%>&delete=<%= dmhList.getString("dmhId") %>"><img src="../images/Delete20.png" width="20" height="20" border=0 
                                		alt="Xóa Quản lý mua hàng" title="Xóa Quản lý mua hàng" onclick="if(!confirm('Bạn có muốn xóa Đề nghị thanh toán này?')) return false;"></A>&nbsp;
                                	<%} %>
                                		                							
		                         <% } else{ if(tt.equals("1")){ %>
		                         		<%if(quyen[3]!=0){ %>
		                            	<A href = "../../ErpPhieuThanhToanUpdateSvl?userId=<%=userId%>&display=<%= dmhList.getString("dmhId") %>&duyet=<%= duyet %>"><IMG src="../images/Display20.png" alt="Hiển thị" title="Hiển thị" border=0></A>&nbsp;
		                            	<%} %> 
		                            	
		                         <% }else { %>
		                         		<%if(quyen[3]!=0){ %>
		                            	<A href = "../../ErpPhieuThanhToanUpdateSvl?userId=<%=userId%>&display=<%= dmhList.getString("dmhId") %>&duyet=<%= duyet %>">
		                            			<IMG src="../images/Display20.png" alt="Hiển thị" title="Hiển thị" border=0></A>&nbsp;
		                				<%} %>
		                            	<%} 								
		                          }%>
				                    </TD>
				                </TR>
				                    		
	                		<%  m++; }
	                		} %>
					
						
						
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
<script type="text/javascript">
	
	 <%for(int k=0; k < m; k++) {%>
	   
		dropdowncontent.init("ktlist<%=k%>", "left-bottom", 300, "click");
	   
	  <%}%>	  
</script>
</form>
</body>
</HTML>

<%
	if(dmhList != null ){ dmhList.close(); dmhList = null; }
	if(nguoitaoRs != null ){ nguoitaoRs.close(); nguoitaoRs = null; }
	if(dvthList != null ){ dvthList.close(); dvthList = null; }
	if(lspList != null ){ lspList.close(); lspList = null; }

	if(obj!= null){ obj.DBclose(); obj = null; }
	session.setAttribute("obj",null);
   
	}
%>