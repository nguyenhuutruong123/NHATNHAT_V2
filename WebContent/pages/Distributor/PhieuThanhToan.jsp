<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.phieuthanhtoan.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<% IPhieuthanhtoanList obj = (IPhieuthanhtoanList)session.getAttribute("obj"); %>
<% ResultSet nvgn = (ResultSet)obj.getNvgn();%>
<% ResultSet ptt = (ResultSet)obj.getPhieuthu();%>
<% String tongthu = (String)obj.getTongthu();%>
<% String tongttoan = (String)obj.getTongTToan() ;%>
<% ResultSet thtoan = (ResultSet)obj.getTtoan() ;%>

<% String userId = (String) session.getAttribute("userId");  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <LINK rel="stylesheet" type="text/css" href="../css/style.css" />
    
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	
   <link type="text/css" rel="stylesheet" href="../css/mybutton.css">
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
	</style>
	<script type="text/javascript" src="../scripts/ajax.js"></script>
	<script type="text/javascript" src="../scripts/ajax-dynamic-list-kh.js"></script>
  	<script type="text/javascript" src="..scripts/jquery-1.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
	
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
    </script>
    <script language="javascript" type="text/javascript">
     	
  
	function submitform()
	{	
		document.forms['pttForm'].action.value='submit';
	    document.forms['pttForm'].submit();
	}

	function keypress(e) //H??m d??ng d? ngan ngu?i d??ng nh?p c??c k?? t? kh??c k?? t? s? v??o TextBox
	{    
		var keypressed = null;
		if (window.event)
			keypressed = window.event.keyCode; //IE
		else
			keypressed = e.which; //NON-IE, Standard
		
		if (keypressed < 48 || keypressed > 57)
		{ 
			if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39)
			{//Ph??m Delete v?? Ph??m Back
				return;
			}
			return false;
		}

		
	}
	
	

	function SelectACustomer()
	{
		var khId = document.getElementsByName("khId"); 
		
		for(i=0; i < khId.length; i++)
		{
			var tem = khId.item(0).value;
			if(parseInt(tem.indexOf("--[")) > 0)
			{
				var tmp = tem.substring(0, parseInt(tem.indexOf("--[")));
				document.getElementById("khId").value = tmp.substring(parseInt(tem.indexOf("-")+1, tmp.length));
				
				if(khId != "")
				{
					document.forms["pttForm"].action.value='submit';
				    document.forms["pttForm"].submit();
				}
				
				break;
			}
		}
		
		
		setTimeout(SelectACustomer, 20);
	}
		
	SelectACustomer();
	
	 function clearform()
	 {   
		document.forms["pttForm"].nvgnId.value = "";
	    document.forms["pttForm"].tungay.value = "";
	    document.forms["pttForm"].denngay.value = "";
	    document.forms["pttForm"].pttId.value = "";
	    document.forms["pttForm"].khId.value = "";
		submitform();
	 }
	
	</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="pttForm" method="post" action="../../PhieuthanhtoanSvl">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="nppId" value='<%=obj.getNppId() %>'>
<input type="hidden" name="action" value='1'>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? c??ng n??? > Phi???u thanh to??n
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Ch??o m???ng   <%= obj.getNppTen() %> &nbsp;
        </div>
    </div>
  
    <div align="left" style="width:100%; float:none">
    <fieldset>
    	<legend class="legendtitle"><%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %></legend>
           <TABLE width="100%" cellpadding="5px" cellspacing="0px">
             	
            <TR>
                <TD class="plainlabel" width="15%">Nh??n vi??n giao nh???n</TD>
                <TD class="plainlabel" >
                    <select name="nvgnId" id="nvgnId" onChange= submitform();>
                        <option value="">&nbsp;</option>
                            <% if(nvgn != null){
					  				try{ while(nvgn.next()){
		    							if(nvgn.getString("nvgnId").equals(obj.getNvgnId())){ 		    							
		    							%>
		      								<option value='<%=nvgn.getString("nvgnId")%>' selected><%=nvgn.getString("nvgnTen") %></option>
		      							<%}else{ %>
		     								<option value='<%=nvgn.getString("nvgnId")%>'><%=nvgn.getString("nvgnTen") %></option>
		     							<%}
		    						} nvgn.close(); 
		    						}catch(java.sql.SQLException e){} 
		    					}%>
                     </select>
                </TD>
                <TD class="plainlabel">Phi???u thu ti???n</TD>
                <TD class="plainlabel" colspan="4">
                    <select name="pttId" id="pttId" onChange= submitform();>
                        <option value="">&nbsp;</option>
                            <% if(ptt != null){
					  				try{ while(ptt.next()){ 
		    							if(ptt.getString("pttId").equals(obj.getPttId())){ %>
		      								<option value='<%=ptt.getString("pttId")%>' selected><%=ptt.getString("diengiai")%></option>
		      							<%}else{ %>
		     								<option value='<%=ptt.getString("pttId")%>'><%=ptt.getString("diengiai")%></option>
		     							<%}
		    						} ptt.close(); 
		    						}catch(java.sql.SQLException e){} 
		    					}%>
                     </select>
                </TD>

            </TR> 
			<TR >
				<TD class="plainlabel">M?? / t??n kh??ch h??ng</TD>
				<TD  class="plainlabel" colspan="5">
					<TABLE cellpadding="0" cellspacing="0">
                       <TR>
                           <TD>                              	
                               	<input type="text" id="khId" name="khId" value = '<%= obj.getKhId() %>' size="25" >                                                  	
                           </TD>
                                                                                                     
                        </TR>                                                 
                     </TABLE></TD>
			</TR>
           <TR>
               <TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
               <TD class="plainlabel">
                   <input type="text" size="10" class="w8em format-d-m-y divider-dash highlight-days-12" 
                   id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" />
               </TD>
                <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
                <TD class="plainlabel" colspan="3">
                    <input type="text" size="10" class="w8em format-d-m-y divider-dash highlight-days-12" 
                     id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" />
                </TD>
               
           </TR>

			<TR>
                <TD class="plainlabel" >T???ng thu </TD>
                <TD class="plainlabel">
                    <input type="text" size="10" class="w8em format-d-m-y divider-dash highlight-days-12" 
                     id="denngay" name="denngay" value="<%=tongthu %>" maxlength="10" readonly/>
                </TD>
                <TD class="plainlabel" >T???ng thanh to??n </TD>
                <TD class="plainlabel">
                    <input type="text" size="10" class="w8em format-d-m-y divider-dash highlight-days-12" 
                     id="denngay" name="denngay" value="<%= tongttoan %>" maxlength="10" readonly/>
                </TD>
                
                <TD class="plainlabel" >C??n l???i </TD>
                <TD class="plainlabel">
                    <input type="text" size="10" class="w8em format-d-m-y divider-dash highlight-days-12" 
                     id="denngay" name="denngay" value="<%= Long.parseLong(tongthu) - Long.parseLong(tongttoan) %>" maxlength="10" readonly/>
                </TD>
           </TR>
			
            <TR style="background-color:#C5E8CD">
                <TD colspan="11">
                    <A class="button" href="javascript:submitform()">
                       <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %>  </a>&nbsp;&nbsp;&nbsp;&nbsp;
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <a class="button2" href="javascript:clearform()">
                       <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %>  </a>
                 </TD>
            </TR>        					
       

        </TABLE>
                <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle"> Phi???u thu </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1px" cellpadding="4px">
					<TR class="tbheader">
	                <th width="7%" align="center">M?? KH</th>
                    <th width="12%" align="center">T??n KH</th>
                    <th width="20%" align="center">?????a ch???</th>
                    <th width="7%" align="center">????n h??ng</th>
                    <th width="5%" align="center">Ng??y</th>
                    <th width="8%" align="center">T???ng ti???n</th>                  
                	<th width="8%" align="center">Thanh to??n</th>
                		
                	<th width="5%" align="center">Ng??y thu</th>
                	<th width="12%" align="center">NV Giao nh???n</th>
                	<th width="8%" align="center">S??? d??</th>
                </tr>
                <% int m = 0;
                	try{
                		if(thtoan != null){
                  			while(thtoan.next()) {
                    		if (m % 2 != 0) {%>						
								<TR class= "tblightrow">
					  		<%} else {%>
				   				<TR class= "tbdarkrow">
							<%}%>
							<TD align="center"><%=thtoan.getString("khId") %></TD>
							<TD align="left"><%=thtoan.getString("khTen") %></TD>
							<TD align="left"><%=thtoan.getString("diachi") %></TD>
							<TD align="right"><%=thtoan.getString("dhId") %></TD>
							<TD align="center"><%=thtoan.getString("ngay") %></TD>
					 		<TD align="right"><%=thtoan.getString("tongsotien") %></TD>					 		
					 		<TD align="right"><%=thtoan.getString("dathanhtoan") %></TD>
					 			
					 		<TD align="center"><%=thtoan.getString("ngaythu") %></TD>
					 		<TD align="left"><%=thtoan.getString("nvgnTen") %></TD>
					 		<TD align="center"><%=thtoan.getString("sodu") %></TD>
                    		</TR>
                  		<% m++;}
                  		
                  			thtoan.close();
                		}
                		
                  }catch(java.sql.SQLException e){}%>
                 <tr class="plainlabel">
                	<td colspan="11">&nbsp;</td>
                </tr>
            </table>
        </div>            
    </fieldset>
    </div>  

<script>
	jQuery(function()
	{		
			$("#khId").autocomplete("KhachHangList_PTT.jsp");		
	});	
	
</script>    
</div>
</form>
</BODY>
</HTML>

