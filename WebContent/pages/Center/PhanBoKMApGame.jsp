<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geso.dms.center.beans.PhanBoKMApGame.IPhanBoKMApGameList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import = "java.util.Iterator" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.ArrayList" %> 
<%@page import = "java.sql.ResultSet" %>
<%@page import = "geso.dms.center.util.*" %>

<% IPhanBoKMApGameList obj = (IPhanBoKMApGameList)session.getAttribute("obj"); %>
<% ResultSet ctkmList = (ResultSet)obj.getCtkmList(); %>
<% ResultSet vung = (ResultSet)obj.getvungRs(); %>
<% ResultSet khuvuc = (ResultSet)obj.getkhuvucRs(); %>
<% ResultSet npp = (ResultSet)obj.getnppRs(); %>

<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[5];
		quyen = util.Getquyen("PhanBoKMApGameSvl","",userId);
%>

<% obj.setNextSplittings(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <link type="text/css" rel="stylesheet" href="../css/mybutton.css">
    <script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
    <script type="text/javascript" src="../scripts/phanTrang.js"></script>
     
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
  

  
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
 
  	<script type="text/javascript" src="..scripts/jquery-1.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	
	<script type="text/javascript" src="../scripts/jquery-latest.js"></script> 
	<script type="text/javascript" src="../scripts/jquery.tablesorter.js"></script>	
    <script type="text/javascript">
    	$(document).ready(function() 
    	    { 
    			$("#ctKMList").tablesorter();
    	    } 
    	);  
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
		 function submitform()
		 {  
			document.forms["ctkmForm"].action.value = "";
		    document.forms["ctkmForm"].submit();
		    
		 }
		 function newform()
		 {   
			document.forms["ctkmForm"].action.value = "Tao moi";
		    document.forms["ctkmForm"].submit();
		 }
		 function clearform()
		 {   
		    document.forms["ctkmForm"].diengiai.value = "";
		    document.forms["ctkmForm"].tungay.value = "";
		    document.forms["ctkmForm"].denngay.value = "";
		    document.forms["ctkmForm"].trangthai.value = "";
		    document.forms["ctkmForm"].khuvuc.value = "";
		    document.forms["ctkmForm"].vung.value = "";
		    document.forms["ctkmForm"].npp.value = "";
		    submitform();
		 } 
	</SCRIPT>
	
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>

<link href="../scripts/version/select2.min.css" rel="stylesheet" />
<script src="../scripts/version/select2.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});            
        }); 		
		
</script>
	
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ctkmForm" method="post" action="../../PhanBoKMApGameSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="userTen" value="<%= userTen %>" >
<input type="hidden" name="msg" value='<%=obj.getMessage() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<input type="hidden" name="action" value="1" >
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:70%; padding:5px; float:left" class="tbnavigation">
        	Quản lý khuyến mãi > Khai báo > Phân bổ khuyến mãi áp game
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
   	
    <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
    <fieldset>
    	<legend class="legendtitle">Tiếu chí <%=Utility.GLanguage("Tìm kiếm",session,jedis) %></legend>
        <div style="width:100%; float:none" align="left">
                <TABLE width="100%" cellpadding="6" cellspacing="0">								
                    <TR>
                        <TD width="10%" class="plainlabel">Mã/Tên CTKM </TD>
                        <TD class="plainlabel">
                        	<input type="text" value="<%=obj.getDiengiai()%>" onchange="javascript:submitform()" name="diengiai" size="40"></TD>
                    	
                    	<TD class="plainlabel"><%=Utility.GLanguage("Vùng",session,jedis) %> </TD>
						<TD class="plainlabel">
							<select name="vung" onChange = "submitform();">								      
								<option value =""></option>
						<% try{
							if(vung!=null)
								while(vung.next()){
									if(vung.getString("pk_seq").equals(obj.getvung())){ %>
								      	<option value='<%=vung.getString("pk_seq") %>' selected><%=vung.getString("ten") %></option>
									<%}else{%>
								     	<option value='<%=vung.getString("pk_seq") %>'><%=vung.getString("ten") %></option>
								     <%}}}catch(java.sql.SQLException e){} %>
								     	
							</select></TD>
                    </TR>              
                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
                        <TD class="plainlabel">
                            <input type="text" size="10" class="days" id="tungay" name="tungay" value="<%=obj.getTungay()%>" maxlength="10"/></TD>
                        
                        <TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %> </TD>
						<TD class="plainlabel">
							<select name="khuvuc" onChange = "submitform();">								      
								<option value =""></option>
						<% try{
							if(khuvuc!=null)
								while(khuvuc.next()){ 
									if(khuvuc.getString("pk_seq").equals(obj.getkhuvuc())){ %>
										<option value='<%=khuvuc.getString("pk_seq") %>' selected><%=khuvuc.getString("ten") %></option>
									<%}else{%>
										<option value='<%=khuvuc.getString("pk_seq") %>'><%=khuvuc.getString("ten") %></option>
									<%}}}catch(java.sql.SQLException e){} %>	
								     	
							</select></TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
                        <TD class="plainlabel">
                            <input type="text" size="10" class="days" id="denngay" name="denngay" value="<%=obj.getDenngay() %>" maxlength="10"/></TD>
                        
                        <TD class="plainlabel">Nhà phân phối</TD>
						<TD class="plainlabel">
							<select name="npp" onChange="submitform();">								      
								<option value =""></option>
						<% try{
							if(npp!=null)
								while(npp.next()){ 
									if(npp.getString("pk_seq").equals(obj.getnpp())){ %>
										<option value='<%=npp.getString("pk_seq") %>' selected><%=npp.getString("ten") %></option>
									<%}else{ %>
										<option value='<%=npp.getString("pk_seq") %>'><%=npp.getString("ten") %></option>
									<%}}}catch(java.sql.SQLException e){} %>
									</select></TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
                        <TD class="plainlabel" >
                        	<select name="trangthai" id="trangthai" class="legendtitle">
                        		<option value=""></option>
                        		<option value="1" <%=obj.getTrangthai().equals("1")?"selected":""%>>Còn hiệu lực</option>
                        		<option value="2" <%=obj.getTrangthai().equals("2")?"selected":""%>>Hết hiệu lực</option>
                        	</select>
                        </TD>
						<%-- <TD colspan="5" class="plainlabel">
				 			<select name="trangthai" id="trangthai" class="legendtitle">
					 			<option value=""></option>
							<% if(obj.getTrangthai().equals("1")){ %>
									<option value='1' selected>Còn hiệu lực</option>
							<%}else{%>
									<option value='1'>Còn hiệu lực</option>
							<%}%>
								
							<% if(obj.getTrangthai().equals("2")){ %>
									<option value='2' selected>Hết hiệu lực</option>
							<%}else{%>
									<option value='2'>Hết hiệu lực</option>
							<%}%>
                            </select>
                        </TD> --%>
                        <TD class="plainlabel" colspan="2"></TD>
                    </TR>
                    
                    <TR>
                        <TD class="plainlabel" colspan="5">
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>
                        </TD>
                    </TR>
                </TABLE>
         	</div>
      	</fieldset>
    </div>
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend><span class="legendtitle"> Phân bổ khuyến mãi áp game </span>&nbsp;&nbsp;&nbsp;
    	<%-- <%if(quyen[0]!=0){ %>
        	<a class="button3" href="javascript:newform()">
                    <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %></a><%} %> --%>
        </legend>
        <div style="width:100%; float:none" align="left">
            <TABLE  width="100%" id="table" class="tabledetail sortable" border="0" cellspacing="1" cellpadding="1">
				<thead>
				<TR class="tbheader">				
                    <TH class="nosort" width="12%" align="center">Mã CTKM</TH>		
                    <TH width="25%" align="center"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TH>
                    <TH width="7%" align="center"><%=Utility.GLanguage("Ngày bắt đầu",session,jedis) %></TH>
                    <TH width="7%" align="center"><%=Utility.GLanguage("Ngày kết thúc",session,jedis) %></TH>
                    <!-- <TH width="7%" align="center">Ngân sách</TH>
                    <TH width="9%" align="center">Loại CT</TH> -->
                    <TH width="9%" align="center"><%=Utility.GLanguage("Vùng",session,jedis) %></TH>
                    <!-- <TH width="7%" align="center"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH> -->
                    <!-- <TH width="7%" align="center"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH> -->
                    <TH width="7%" align="center"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
                    <TH width="7%" align="center"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
                    <TH width="9%" align="center"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>                    
                </TR></thead>
                <tbody>
                <%      
                NumberFormat formatter = new DecimalFormat("#,###,###"); 
                    int m = 0;
                    try{
                    	if(ctkmList != null)
                    		
                    	while (ctkmList.next()){
                        	if(m%2==0){
                %> 
                  		<TR class='tbdarkrow'>
                  		<% }else{%>
                	  
                	  	<TR class='tblightrow'>
                	  	<%
                  		   }
                         
	                  	String diengiai = "";
	                  	double ngansach=0;
	                  	ngansach= Double.parseDouble(ctkmList.getString("ngansach"));
	                  	
	                  	if(ctkmList.getString("diengiai") != null ) 
	                	  	diengiai = ctkmList.getString("scheme")+ " -- " + ctkmList.getString("diengiai");
	                  	else
	                  		diengiai = ctkmList.getString("scheme"); %>
                  <TD align="center" onMouseover="ddrivetip('<%= diengiai %>', 150)"; onMouseout="hideddrivetip()"><%= ctkmList.getString("scheme") %></TD>
                    <TD align="left"><%=ctkmList.getString("diengiai") %></TD>
                  <TD align="center"><%= ctkmList.getString("tungay") %></TD>
                  <TD align="center"><%=  ctkmList.getString("denngay") %></TD> 
                  <TD align="center"><%=  ctkmList.getString("vTen")==null?"":  ctkmList.getString("vTen")%></TD> 
                  <TD align="center"><%= ctkmList.getString("ngaysua") %></TD>	
                  <TD align="center"><%= ctkmList.getString("nguoisua") %></TD>				
                  <TD align="center"> 
                    
                  
                    
                     
                 <%--    <%if(quyen[4]!=0 && ctkmList.getInt("isDuyet")<= 0  )   { %>                  
                    <A href = "../../PhanBoKMApGameSvl?userId=<%=userId%>&duyet=<%= ctkmList.getString("ctkmId")%>"><IMG src="../images/Chot.png" alt="Duyệt" title="Duyệt"  width="20" height="20" border="0"></A>
                  	<%} %>   --%>
                  	<%if(quyen[2]!=0){ %>                  
                      <A href = "../../PhanBoKMApGameUpdateSvl?userId=<%=userId%>&update=<%= ctkmList.getString("ctkmId")%>"><IMG src="../images/Edit20.png" alt="Cập nhật" title="Update" border="0"></A>
                      <%} %>
                    <%if(quyen[3]!=0){ %>                  
                      <A href = "../../PhanBoKMApGameUpdateSvl?userId=<%=userId%>&display=<%= ctkmList.getString("ctkmId")%>"><IMG src="../images/Display20.png" alt="Xem" title="Xem" border="0"></A>
                      <%} %>
                 
                                                    								
                  </TD>                 
              	</TR>
                 	<%
                 	m++ ;
                 	}
                 }catch(java.sql.SQLException e){
                 }
                 %>
                </tbody>
                <tfoot>
				 <tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('ctkmForm', 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View('ctkmForm', eval(ctkmForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
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
													<a href="javascript:View('ctkmForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View('ctkmForm', eval(ctkmForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('ctkmForm', -1, 'view')"> &nbsp;
										   		<%} %>
											</TD>
										 </tr> </tfoot> 

            </TABLE>						
         </div>
      </fieldset>
  </div> 
</div>

</form>
</body>
</HTML>


<%
	if(ctkmList!= null){ctkmList.close(); ctkmList = null; }
	obj.DBclose(); obj = null;
	session.setAttribute("obj",null);

}%>