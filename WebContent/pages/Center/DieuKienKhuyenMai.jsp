<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.dieukienkhuyenmai.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<% 
NumberFormat formatter = new DecimalFormat("#,###,###");
NumberFormat formatter2 = new DecimalFormat("#,###,###.####");

IDkkhuyenmaiList obj = (IDkkhuyenmaiList)session.getAttribute("obj"); %>

<% ResultSet dkkmList = (ResultSet)obj.getDkkmList(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
	 obj.setNextSplittings(); 
	int[] quyen = new  int[6];
	quyen = util.Getquyen("DieukienkhuyenmaiSvl","",userId);
%>
<%String url = util.getChuyenNguUrl("DieukienkhuyenmaiSvl", "",session); %>
<% Utility Util = new Utility(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
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
	    document.forms["dkkmForm"].submit();
	 }
	 function newform()
	 {   
		document.forms["dkkmForm"].action.value = "Tao moi";
	    document.forms["dkkmForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["dkkmForm"].diengiai.value = "";
	    document.forms["dkkmForm"].tungay.value = "";
	    document.forms["dkkmForm"].denngay.value = "";
	    document.forms["dkkmForm"].masp.value = "";
	    document.forms["dkkmForm"].tensp.value = "";
	    document.forms["dkkmForm"].dieukienId.value = "";
	    document.forms["dkkmForm"].submit();
	    
	 }
	 function thongbao()
	 {
		 var tt = document.forms["dkkmForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["dkkmForm"].msg.value);
	 	}
	 
	 function xuatExcel()
	 {
	 	document.forms['dkkmForm'].action.value= 'toExcel';
	 	document.forms['dkkmForm'].submit();
	 	document.forms['dkkmForm'].action.value= '';
	 }

	 
	</SCRIPT>
</head>
<body>
<form name="dkkmForm" method="post" action="../../DieukienkhuyenmaiSvl">

<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value="<%= userId %>" >

<input type="hidden" name="action" value="1" >
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >


<input type="hidden" name="msg" value='<%=Utility.GLanguage(obj.getmsg(),session,jedis) %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&#160; <%= url %>
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
                        <TD class="plainlabel" valign="middle" width="10%"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle" width="25%">
                            <input type="text" name="diengiai" value="<%= obj.getDiengiai() %>">
                        </TD>
                        <TD class="plainlabel" valign="middle" width="10%"><%=Utility.GLanguage("Mã điều kiện",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                            <input type="text" name="dieukienId" value="<%= obj.getDieukienId() %>">
                        </TD>                         
                    </TR>
                    <TR>
                        <TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Mã sản phẩm",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                            <input type="text" name="masp" value="<%= obj.getMasp() %>">
                        </TD>     
                           <TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Tên sản phẩm",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                            <input type="text" name="tensp" value="<%= obj.getTensp() %>">
                        </TD>                    
                    </TR>          
                     
                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" size="10"  class="days" 
                                   id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" />
                        </TD>
                              <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" size="10"  class="days" 
                                   id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" />
                        </TD>
                    </TR>

                    <tr style="background-color:#C5E8CD">
                        <td colspan="4">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                
                                
                                <a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>        					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle"><%=Utility.GLanguage("Điều kiện khuyến mãi",session,jedis) %> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	<%if(quyen[Utility.THEM]!=0) {%>
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>
                           <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
				                    <TH align="center"><%=Utility.GLanguage("Mã ĐK",session,jedis) %></TH>
				                    <TH align="center"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TH>
				                    <TH align="center"><%=Utility.GLanguage("Tổng lượng",session,jedis) %></TH>
				                    <TH align="center"><%=Utility.GLanguage("Tổng tiền",session,jedis) %></TH>
				                    <TH align="center"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
				                    <TH align="left"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
				                    <TH align="center"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </TH>
				                    <TH align="left"><%=Utility.GLanguage("Người sửa",session,jedis) %> </TH>
				                    <TH align="center" ><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
				                </TR>
							<%      
		                        int m = 0;
		                        if(dkkmList != null){
		                        while (dkkmList.next()){
		                    %> 
		                    <%if((m%2)== 0) {%>
		                         <TR class='tbdarkrow'>
		                         <%}else{ %>
		                           <TR class='tblightrow'>
		                         <%} %>
				                    <TD align="center"><%= dkkmList.getString("dkkmId") %></TD>
				                    <TD align="left"><%= dkkmList.getString("diengiai") %></TD>
				                    <TD align="right"><%= dkkmList.getString("tongluong") %></TD>										                                    
				                    <TD align="right"><%=formatter.format(Utility.parseDouble(dkkmList.getString("tongtien").replace(",","")))  %></TD>
				                    <TD align="center"><%= dkkmList.getString("ngaytao") %></TD>
				                    <TD align="left"><%= dkkmList.getString("nguoitao") %></TD>
				                    <TD align="center"><%= dkkmList.getString("ngaysua") %></TD>	
				                    <TD align="left"><%= dkkmList.getString("nguoisua") %></TD>				
				                    <TD align="center"> 
				                    	<%if(quyen[Utility.SUA]!=0) {%>
		                                <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DkkhuyenmaiUpdateSvl?userId="+userId+"&update="+ dkkmList.getString("dkkmId") +"")%>"><IMG src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" border=0></A>&nbsp;
		                               	<%} %>
		                               	<%if(quyen[Utility.XOA]!=0){ %>
		                                <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DieukienkhuyenmaiSvl?userId="+userId+"&delete="+ dkkmList.getString("dkkmId")+"") %>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa điều kiện khuyến mại này?')) return false;"></A>									
				                    	<%} %>
				                    </TD>
				                </TR>
		                     <% m++; }} %>														
                                
								<tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('dkkmForm', 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View('dkkmForm', eval(dkkmForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
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
													<a href="javascript:View('dkkmForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View('dkkmForm', eval(dkkmForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('dkkmForm', -1, 'view')"> &nbsp;
										   		<%} %>
											</TD>
										 </tr>  

						</TABLE>
            </fieldset>
        </div>
    </div>  
</div><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</body>
<%} %>
</HTML>