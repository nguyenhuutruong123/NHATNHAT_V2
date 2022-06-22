<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="geso.dms.center.beans.PhanBoKMApGame.IPhanBoKMApGame"%>
<%@ page import = "geso.dms.center.beans.dieukienkhuyenmai.ISanpham" %>
<%@ page import = "geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.util.Hashtable" %>
<%@ page import = "java.util.List" %>
<% IPhanBoKMApGame ctkmBean = (IPhanBoKMApGame)session.getAttribute("ctkmBean"); %> 
<% ResultSet rsTraKM = ctkmBean.getRsTraKM(); %>
<%-- <% ResultSet rsNpp = ctkmBean.getRsNpp(); %> --%>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %> 
<% Hashtable<String,String> hashVung = ctkmBean.getHashVung(); %>
<% Hashtable<String,String> hashKhuVuc = ctkmBean.getHashKhuVuc(); %>
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
	<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
	<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">
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
			
			document.getElementById("btnSave1").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
			document.forms["ctkmForm"].action.value = "save";
			document.forms["ctkmForm"].submit();
		}
		function locnpp()
		{
			
			 
			document.forms["ctkmForm"].action.value = "locnpp";
			document.forms["ctkmForm"].submit();
		}
		 
		
		function upload()
		{
			document.getElementById("btnSave2").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
			document.forms['ctkmForm'].setAttribute('enctype', "multipart/form-data", 0);  
			document.forms["ctkmForm"].submit();
		}
		
		function xuatexcel(trakmId)
		{
			
			 
			document.forms["ctkmForm"].action.value = "xuatexcel";
			document.forms["ctkmForm"].traKMExcelId.value = trakmId; 
			document.forms["ctkmForm"].submit();
		}
	
		 
	</script>
	
	 
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ctkmForm" method="post" action="../../PhanBoKMApGameUpdateSvl">
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'> 
<input type="hidden" name="load" value='0'>
<input type="hidden" name="traKMExcelId" value='0'>
<input type="hidden" name="ctkmId" value='<%=ctkmBean.getCtkmId()%>'>
<div id="main" style="width:100%; padding-left: 1px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Quản lý khuyến mãi > Khai báo > Phân bổ khuyến mãi áp game > Hiển thị
        </div>
        <div align="right" style="padding:5px;" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp; &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "javascript:history.back()" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <!-- <span id="btnSave1"><A href="javascript:saveform()" >
        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A></span> -->
    </div>
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
    		<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly rows="1"><%= ctkmBean.getMsg() %></textarea>
    	</fieldset>
  	</div>
    <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
    <fieldset>
    	<legend class="legendtitle">Phân bổ khuyến mãi áp game</legend>
        <div style="width:100%; float:none" align="left">
                <TABLE width="100%" cellpadding="4" cellspacing="0">	
                	<tr><TD width="130px" class="plainlabel" colspan="6"></tr>							
                    <TR>
                     	<TD width="130px" class="plainlabel" >	
                        <TD width="130px" class="plainlabel">Scheme </TD>
                        <TD width="250px" class="plainlabel"><input type="text" name="scheme" id="scheme" readonly size="30" value="<%= ctkmBean.getScheme() %>"></TD>
                    
                        <TD width="130px" class="plainlabel" valign="top"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="top">
                        	<input type="text" name="diengiai" id="diengiai" style="width:400px" readonly value="<%= ctkmBean.getDiengiai() %>" >
                        </TD>
                        <TD width="130px" class="plainlabel" > 
                    </TR> 
                    <tr><TD width="130px" class="plainlabel" colspan="6"></tr>		
                </TABLE>       
         </div> 
         
         <ul class="tabs">
         <%if(rsTraKM != null){ %>
         <%String traKMIds="";String xacsuat=""; %>
         			<%while(rsTraKM.next()){ %>
						<li><a href="#tab<%=rsTraKM.getString("pk_seq")%>"><%=rsTraKM.getString("diengiai") %></a></li> 
						<%traKMIds +=rsTraKM.getString("pk_seq")+",";xacsuat +=rsTraKM.getString("xacsuat")+",";} %>
		</ul>
		<div class="panes">
		<%traKMIds = traKMIds.substring(0, traKMIds.length()-1); %>	
		<%String [] arrTraKM =traKMIds.split(","); %>
		<%xacsuat = xacsuat.substring(0, xacsuat.length()-1); %>	
		<%String [] arrxacsuat =xacsuat.split(","); %>
		<%for(int i=0;i<arrTraKM.length;i++){ %> 
					
						<div id="tab<%=arrTraKM[i] %>" class="tab_content">
							<input type="hidden" name="trakmId" id="trakmId" value='<%=arrTraKM[i]%>'>
							<TABLE class="tabledetail" width="100%" border="0"
								cellspacing="1px" cellpadding="5px">
								<tr>
									<TD class="plainlabel" colspan="3">File upload: <INPUT type="file" name="fileup<%=arrTraKM[i] %>" value="" style="margin-left: 50px"> 
									<!-- <span id="btnSave2"><a class="button" href="javascript:upload();"> 
										<img style="top: -4px;" src="../images/button.png" alt=""> Upload </a></span></TD> -->
								</tr>
								<tr> 
									<TD width="50px" class="plainlabel" align="right">Tỷ lệ </TD>
                       				<TD width="250px" class="plainlabel" colspan="1"><input type="text" name="tile.<%=arrTraKM[i]%>" id="scheme" size="30" value="<%=arrxacsuat[i] %>" ></TD>
                    				<TD width="50px" class="plainlabel"></TD>
								</tr> 
								<tr>
									<td class="plainlabel" colspan="3"> <div id="btnSave1"><a class="button" href="javascript:xuatexcel(<%=arrTraKM[i]%>);"> 
										<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Xuất Excel",session,jedis) %></a></div></td>
								</tr>
								<tr>
									  <TD class="plainlabel"> Vùng  <SELECT name="vungId" id="vungId" onChange = "locnpp();">								      
								  	 		<option value =""></option>
								  	 <%
								  		 if(hashVung!=null)
								  		 {
								  			for (String key : hashVung.keySet()) { %>
								  				<%if(ctkmBean.getVungId().equals(key)) {%>
								  	            	<option value ="<%=key%>" selected><%=hashVung.get(key) %></option>
								  	            <%} else{ %>
								  	           	 	<option value ="<%=key%>" ><%=hashVung.get(key) %></option>
								  	            <%} %> 
								  	<%        } 
								    	}  %>	
								     	
									</SELECT></TD>
									
									<TD class="plainlabel" colspan="2"> Khu vực  <SELECT name="khuVucId" id="khuVucId" onChange = "locnpp();">								      
								  	 		<option value =""></option>
								  	 <%
								  		 if(hashKhuVuc!=null)
								  		 {
								  			for (String key : hashKhuVuc.keySet()) { %>
								  				<%if(ctkmBean.getKhuvucId().equals(key)) {%>
								  	            	<option value ="<%=key%>" selected><%=hashKhuVuc.get(key) %></option>
								  	            <%} else{ %>
								  	           	 	<option value ="<%=key%>" ><%=hashKhuVuc.get(key) %></option>
								  	            <%} %> 
								  	<%        } 
								    	}  %>	
								     	
									</SELECT></TD>
								</tr>
								<TR class="tbheader">
									<TH align="center" width="20%">Mã nhà phân phối</TH>
									<TH align="left" width="30%">Tên nhà phân phối </TH>
									<TH align="left" width="10%">Số lượng</TH> 
								</TR>
								 <%
								 String nppInfo="";
				        		String query = "\n select distinct pk_seq, ma,ten, b.sosuat as soluong " + 
								 "\n from nhaphanphoi a inner join phanbo_ctkm_trakm_npp b on a.pk_seq=b.npp_fk" + 
									"\n where a.trangthai=1 and b.ctkm_fk= "+ ctkmBean.getCtkmId()+" and trakm_fk="+arrTraKM[i]+
									"\n union"+ 
									"\n select distinct pk_seq, ma,ten, 0 as soluong " + 
									"\n  from nhaphanphoi a inner join ctkm_npp b on a.pk_seq=b.npp_fk " + 
									"\n  where a.trangthai=1 and b.ctkm_fk= "+ ctkmBean.getCtkmId()+" "+ 
									"\n and a.pk_Seq not in (select npp_fk from phanbo_ctkm_trakm_npp where ctkm_fk="+ctkmBean.getCtkmId()+" and trakm_fk="+arrTraKM[i]+")";
									if(ctkmBean.getVungId().trim().length()>0)
									{
										query +=" and khuvuc_fk in (select pk_seq from khuvuc where vung_fk="+ctkmBean.getVungId()+")";
									}
									if(ctkmBean.getKhuvucId().trim().length()>0)
									{
										query +=" and khuvuc_fk ="+ctkmBean.getKhuvucId()+"";
									}
									ResultSet rsNppTemp = ctkmBean.getDb().get(query);
				         		if(rsNppTemp != null)
				         		{
				         			while (rsNppTemp.next())
				         			{
				         				nppInfo += rsNppTemp.getString("pk_seq")+";"+rsNppTemp.getString("ma")+";"+rsNppTemp.getString("ten")+";"+rsNppTemp.getString("soluong")+",";
				         			}
				         		}
				         		%>
								<%      int k=0;  
		                    	String[] arrNPP = nppInfo.split(",");
		                    	for(int j=0;j<arrNPP.length;j++)
		                    	{ 
		                    		String[] tempNpp = arrNPP[j].split(";"); 
		                    			if(k % 2 == 0){
		                        			%>
										<TR class='tbdarkrow' >
											<%}else{ %>
										
										<TR class='tblightrow'  >
											<% } %>
											<input type="hidden" name="nppIds.<%=arrTraKM[i] %>" id="nppIds.<%=arrTraKM[i] %>" value='<%=tempNpp[0]%>'>
											<TD align="left"><%=tempNpp[1]%></TD>
											<TD align="left"><%=tempNpp[2]%></TD>
											<TD align="left"><input type="text" name="soluong.<%=arrTraKM[i]%>" value="<%=tempNpp[3]%>"></TD> 
										</TR>
										<%k++;
		                    	} %>
		                    	</TABLE>
		                    	
		                    	</div>
		                    <%}%>
		                    
				<%}%> 	
						</div>
      </fieldset>
  </div>    
</div>
</form> 
</body>
</HTML>
