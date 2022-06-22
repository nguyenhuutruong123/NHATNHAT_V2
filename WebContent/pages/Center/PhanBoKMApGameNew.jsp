<%@page import="geso.dms.center.beans.PhanBoKMApGame.IPhanBoKMApGame"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page  import = "geso.dms.center.beans.dieukienkhuyenmai.ISanpham" %>
<%@ page  import = "geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<% IPhanBoKMApGame ctkmBean = (IPhanBoKMApGame)session.getAttribute("ctkmBean"); %> 
<% ResultSet rsTraKM = ctkmBean.getRsTraKM(); %>
<% ResultSet rsNpp = ctkmBean.getRsNpp(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %> 

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
  	<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
	<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">
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
   <script>
	$(function() {
	 	$("ul.tabs").tabs("div.panes > div");
	});
	</script>


	<script>
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
	 
    <script language="javascript" type="text/javascript">
    
    	 
		
		function saveform()
		{
			
			 
			document.forms["ctkmForm"].action.value = "save";
			document.forms["ctkmForm"].submit();
		}
	
		 
	</script>
	
	 
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ctkmForm" method="post" action="../../PhanBoKMApGameUpdateSvl">
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'> 
<input type="hidden" name="load" value='0'>

<div id="main" style="width:100%; padding-left: 1px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Quản lý khuyến mãi > Khai báo > Phân bổ khuyến mãi áp game > Tạo mới
        </div>
        <div align="right" style="padding:5px;" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp; &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "javascript:history.back()" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <A href="javascript:saveform()" >
        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
    </div>
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
    		<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= ctkmBean.getMsg() %></textarea>
		         <%=ctkmBean.getMsg() %>
    	</fieldset>
  	</div>
    <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
    <fieldset>
    	<legend class="legendtitle">Phân bổ khuyến mãi áp game</legend>
        <div style="width:100%; float:none" align="left">
                <TABLE width="100%" cellpadding="4" cellspacing="0">								
                    <TR>
                        <TD width="130px" class="plainlabel">Scheme </TD>
                        <TD width="250px" class="plainlabel"><input type="text" name="scheme" id="scheme" size="30" value="<%= ctkmBean.getScheme() %>"></TD>
                    
                        <TD width="130px" class="plainlabel" valign="top"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="top">
                        	<input type="text" name="diengiai" id="diengiai" style="width:400px" value="<%= ctkmBean.getDiengiai() %>" >
                        </TD>
                    </TR> 
                </TABLE>       
         </div> 
         
         
         <ul class="tabs">
         <%if(rsTraKM != null){ %>
         			<%while(rsTraKM.next()){ %>
						<li><a href="#tab1"><%=rsTraKM.getString("diengiai") %></a></li> 
					</ul>

					<div class="panes">
						<div id="tab1" class="tab_content">

							<TABLE class="tabledetail" width="100%" border="0"
								cellspacing="1px" cellpadding="5px">
								<tr>
									<TD width="130px" class="plainlabel">Tỷ lệ </TD>
                       				<TD width="250px" class="plainlabel"><input type="text" name="tile.<%=rsTraKM.getString("pk_seq")%>" id="tile.<%=rsTraKM.getString("pk_seq")%>" size="30" ></TD>
                    
								</tr>
								<TR class="tbheader">
									<TH align="center" width="20%">Mã nhà phân phối</TH>
									<TH align="left" width="30%">Tên nhà phân phối </TH>
									<TH align="left" width="10%">Số lượng</TH> 
								</TR>
								<%      int k=0;
                    if(rsNpp != null)
                    {
                    	while(rsNpp.next())
                    	{
                    			if(k % 2 == 0){
                        			%>
								<TR class='tbdarkrow' >
									<%}else{ %>
								
								<TR class='tblightrow' >
									<% } %>
									<TD align="left"><%=rsNpp.getString("ma")%></TD>
									<TD align="left"><%=rsNpp.getString("ten")%></TD>
									<TD align="left"><input type="text" name="soluong.<%=rsNpp.getString("pk_Seq")%>.<%=rsTraKM.getString("pk_seq")%>" value="<%=rsNpp.getString("soluong")%>"></TD> 
								</TR>
								<%k++;
                    	} 
                    }}}%>
							</TABLE>
						</div>
					</div>
      </fieldset>
  </div>    
</div>
</form>
<script type="text/javascript">
	replaces();
</script>
</body>  <script type='text/javascript' src='../scripts/loadingv2.js'></script>
</HTML>
