<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.dangkytrungbay.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>

<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<% IDangkytrungbay dktbBean = (IDangkytrungbay)session.getAttribute("dktbBean"); %>
<% ResultSet nvbh = (ResultSet)dktbBean.getNvBanhang(); %>
<% ResultSet cttrungbay = (ResultSet)dktbBean.getCttrungbay(); %>
<% ResultSet khList = (ResultSet)dktbBean.getKhList(); %>

<% Hashtable<Integer, String> nvbhIds = (Hashtable<Integer, String>)dktbBean.getNvbhIds(); %>
<% Hashtable<String, Integer> kh_soxuat = dktbBean.getKh_Soxuat(); %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
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
   <script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
   
  	<script type="text/javascript" src="..scripts/jquery-1.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<style type="text/css">

	#tabledetail tbody tr input:HOVER{
		background: #C5E8CD;
	}
	#tabledetail tbody tr:hover{
		background: #C5E8CD;
	}
	</style>
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
    <script type="text/javascript">
	    function saveform()
		{  
			var cttb = document.getElementById("cttbTen");
			if(cttb.value == "")
			{
				alert('Vui l??ng ch???n ch????ng tr??nh tr??ng b??y...');
				return;
			}
			if(checkDangky() == false)
			{
				alert('Kh??ng c?? kh??ch h??ng n??o ???????c ch???n ????? ????ng k?? tham gia ch????ng tr??nh...');
				return;
			}
			document.getElementById("saveid").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>";	
		    
			document.forms['dktbForm'].action.value='save';
		    
			document.forms['dktbForm'].submit();
		}
	    
	    function checkDangky()
	    {
	    	
	    	var dangky = document.getElementsByName("dangky");
	    	for(i = 0; i < dangky.length; i++)
			{
				if(dangky.item(i).value != null)
					return true;
			}
	    	return false;
	    }
	    
	    function Check(str)
		{
	    	kiemtrahetphanbo();
	    	var dangkythem = document.forms['dktbForm'].dangkythem.value;
	    	
			var id = document.getElementById(str);
			var hidden = document.getElementById(str + '.hidden').value;
			
			<% if(dktbBean.getIstinhds().equals("1")){ %>
			
			if( parseInt(id.value) > parseInt(hidden))
			{
				alert('S??? xu???t ????ng k?? ph???i nh??? h??n s??? xu???t b??n ???? mua..');
				id.value = "";
				return;
			}
			<% } %>
			congdon();
		}
	    
		function submitform()
		{
			document.forms['dktbForm'].action.value = 'submit';
		    document.forms['dktbForm'].submit();
		}
		
		function submitform2()
		{
			var cttb = document.getElementById("cttbTen");
			if(cttb.value == "")
			{
				alert('vui l??ng ch???n ch????ng tr??nh tr??ng b??y...');
				return;
			}
			document.forms['dktbForm'].action.value='submit';
		    document.forms['dktbForm'].submit();
		}
			
		function kiemtrahetphanbo()
		{
			var sosuatphanbo = document.getElementById("sosuatphanbo").value;
			var sosuatdaphanbo = document.getElementById("sosuatdaphanbo").value;
			var tong =0;
			var conlai = sosuatphanbo;// - sosuatdaphanbo;
	    	var dangky = document.getElementsByName("dangky");
	    	for(i = 0; i < dangky.length; i++)
			{
			  	tong = tong +   parseInt(dangky.item(i).value) ;
			    if(tong > conlai)
		    	{
			    	alert('B???n ch??? c??n ' + conlai +' xu???t ph??n b???');
			    	dangky.item(i).value = 0;
			    	return;
		    	}
			}
			
		}
		
		function congdon()
	    {  	
			var sosuatphanbo = document.getElementById("sosuatdaphanbo");
	    	
			var hidden = document.getElementsByName("dangky");
			var tong = 0;
			
			for(i = 0; i< hidden.length; i++)
			{   
				if(hidden.item(i).value.length > 0)
				  tong = tong + parseInt(hidden.item(i).value);
			}
			
			sosuatphanbo.value = tong;
	    }
		
		function check_all(value)
		{
			var checkone=document.getElementsByName("nvbhIds");
			for(var i=0; i<checkone.length; i++ )
			{
				checkone.item(i).checked=value;
			}
		}
		
		function sellectAll()
		 {
			 var chkAll = document.getElementById("chkAll");
			 var nppIds = document.getElementsByName("nvbhIds");
			 
			 if(chkAll.checked)
			 {
				 for(i = 0; i < nppIds.length; i++)
				 {
					 nppIds.item(i).checked = true;
				 }
			 }
			 else
			 {
				 for(i = 0; i < nppIds.length; i++)
				 {
					 nppIds.item(i).checked = false;
				 }
			 }
		 }
		
	</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dktbForm" method="post" action="../../DangkytrungbayUpdateSvl">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="nppId" value='<%= dktbBean.getNppId() %>'>
<input type="hidden" name="dangkythem" value='<%= dktbBean.getdangkythem() %>'>
<input type="hidden" name="istinhds" id="istinhds" value='<%= dktbBean.getIstinhds()  %>'>
<input type="hidden" name="action" value='1'>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:50%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? tr??ng b??y > ????ng k?? tr??ng b??y > Hi???n th???
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Ch??o m???ng  <%= dktbBean.getNppTen() %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "javascript:history.back()" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        	
        	  <A href = "../../DangkytrungbaySvl?userId=<%=userId%>&excel=<%=dktbBean.getId()%>"><img src="../images/excel.gif" alt="Excel" title="Excel" width="20" height="20" longdesc="Excel" border ="0"></A>
    </div>
  	<div align="left" style="width:100%; float: none"> 
    	<fieldset>
        	<legend class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
            <textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" cols="71" rows="1"  style="width: 100% " readonly="readonly" ><%= dktbBean.getMessage() %></textarea>
            <% dktbBean.setMessage(""); %>
        </fieldset>
    </div>
    <div align="left" style="width:100%; float:none">
    <fieldset>
    	<legend class="legendtitle"> ????ng k?? tr??ng b??y </legend>
        <div style=" width:100%; float:non; clear:left; font-size:0.7em">
        <TABLE width="100%" cellpadding="5px" cellspacing="0px">
             <TR>
                <TD class="plainlabel"  width="23%">Ch????ng tr??nh </TD>
                <TD class="plainlabel" colspan="2">
                    <select name="cttbTen" id="cttbTen" onchange="submitform()">
                        <option value="">&nbsp;</option>
                            <% if(cttrungbay != null){
					  		try{ while(cttrungbay.next()){ 
		    					if(cttrungbay.getString("cttbId").equals(dktbBean.getCttbId())){ %>
		      					<option value='<%=cttrungbay.getString("cttbId")%>' selected><%= cttrungbay.getString("cttbTen") %></option>
		      				<%}else{ %>
		     					<option value='<%=cttrungbay.getString("cttbId")%>'><%=cttrungbay.getString("cttbTen") %></option>
		     			<%}} cttrungbay.close(); }catch(java.sql.SQLException e){} }%>
                     </select>
                </TD>
            </TR>	
            <TR>
                <TD class="plainlabel">M?? Ch????ng tr??nh </TD>
                <TD class="plainlabel" colspan="2">
                 <%=dktbBean.getscheme() %>
                     </TD>
            </TR> 
             <TR>
                <TD class="plainlabel">Th???i gian t??nh doanh s??? : T??? ng??y</TD>
                <TD class="plainlabel" colspan="2">
                    <input type="text" size="15" name="batdauds" value="<%=dktbBean.getTgbdTinhds() %>" disabled="disabled" />
                     &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; ?????n ng??y  &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" size="15" name="ketthucds" value="<%=dktbBean.getTgktTinhds() %>" disabled="disabled" />
                </TD>
            </TR> 
            <TR>
                
            </TR> 							
            <TR>
                <TD class="plainlabel">Th???i gian tr??ng b??y &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:T??? ng??y </TD>
                <TD class="plainlabel" colspan="2">
                    <input type="text" size="15" name="batdau" value="<%= dktbBean.getThoigianbd() %>" disabled="disabled" />
                   &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; ?????n ng??y &nbsp;&nbsp;&nbsp;&nbsp; 
                    <input type="text" size="15" name="ketthuc" value="<%= dktbBean.getThoigiankt() %>" disabled="disabled" />
                </TD>
            </TR> 
            <% if(dktbBean.getType()==1){%>
            	
            <TR>
                <TD class="plainlabel">Th???i gian ????ng k?? &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:T??? ng??y </TD>
                <TD class="plainlabel" colspan="2">
                    <input type="text" size="15" name="batdau" value="<%=dktbBean.getTgktTinhds() %>" disabled="disabled" />
                    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;?????n ng??y&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    <input type="text" size="15" name="ketthuc" value="<%= dktbBean.getThoigianbd() %>" disabled="disabled" />
                </TD>
            </TR> 
           <% } else {%>
            <TR>
                <TD class="plainlabel">Th???i gian ????ng k?? &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:T??? ng??y </TD>
                <TD class="plainlabel" colspan="2">
                    <input type="text" size="15" name="batdau" value="<%=dktbBean.getTgktTinhds() %>" disabled="disabled" />
                    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;?????n ng??y&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    <input type="text" size="15" name="ketthuc" value="<%=dktbBean.getThoigiankt()  %>" disabled="disabled" />
                </TD>
            </TR> 
           <%} %>
            <TR>
                
            </TR> 
           
            <TR>
                <TD class="plainlabel">S??? l???n thanh to??n</TD>
                <TD class="plainlabel" colspan="2" >
                    <input type="text" size="15" name="solan" value="<%= dktbBean.getSolantt() %>" disabled="disabled" />
                </TD>                   
            </TR>  
            <TR>           
                 <TD class="plainlabel">S??? su???t ph??n b???</TD>
                <TD class="plainlabel" colspan="2" >
                    <input type="text" size="15" name="sosuatphanbo" id ="sosuatphanbo" value="<%= dktbBean.getSosuatphanbo() %>" disabled="disabled" />
                </TD>     
            </TR>            
            <TR>
                <TD class="plainlabel">S??? su???t ???? ????ng k??</TD>
                <TD class="plainlabel" colspan="2">
                       <input type="text" size="15" name="sosuatdaphanbo" id ="sosuatdaphanbo" value="<%= dktbBean.getSosuatdaphanbo() %>" disabled="disabled" />
                  </TD>                 
            </TR>
            
            <TR>
                <TD class="plainlabel" ><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %> </TD>
				<TD class="plainlabel" colspan="2">
					<a href="" id="nvbh" rel="subcontentNvbh">
        	 							&nbsp; <img alt="Ch???n nh??n vi??n b??n h??ng" src="../images/vitriluu.png"></a>
        	 		
                        <DIV id="subcontentNvbh" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
		                             background-color: white; width: 560px; max-height:300px; overflow:auto; padding: 4px;">
		                    <table width="90%" align="center">
		                        <tr>
		                            <th width="80px" style="font-size: 12px">M?? NVBH</th>
		                            <th width="250px" style="font-size: 12px">T??n NVBH</th>
		                            <th width="80px" style="font-size: 12px">??i???n tho???i</th>
		                            <th width="80px" align="center" style="font-size: 12px">Ch???n h???t <input type="checkbox" onchange="sellectAll()" id="chkAll" ></th>
		                        </tr>
                            	<%
	                        		if(nvbh != null)
	                        		{
	                        			while(nvbh.next())
	                        			{  %>
	                        			
	                        			<tr>
	                        				<td><input style="width: 100%" value="<%= nvbh.getString("nvbhId") %>"></td>
	                        				<td><input style="width: 100%" value="<%= nvbh.getString("nvbhTen") %>"></td>
	                        				<td><input style="width: 100%" value="<%= nvbh.getString("dienthoai") %>"></td>
	                        				<td align="center">
	                        				<% if(nvbhIds.contains(nvbh.getString("nvbhId"))){ %>
							                    <input type="checkbox" name="nvbhIds" value="<%= nvbh.getString("nvbhId") %>" checked>
						                   <%}else{ %>
							                    <input type="checkbox" name="nvbhIds" value="<%= nvbh.getString("nvbhId") %>">
						                   <%} %>
	                        				</td>
	                        			</tr>
	                        			
	                        		 <% } nvbh.close(); } %>
		                    </table>
		                     <div align="right">
		                     	<label style="color: red" ></label>
		                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                     	<a href="javascript:dropdowncontent.hidediv('subcontentNvbh')" onclick="submitform()" >Ho??n t???t</a>
		                     </div>
		            </DIV>         
				</TD>
          </TR >
              				
        </TABLE>
        </div>

        <hr>
        <div style="width:100%; float:none; clear:left" align="left">
             <table id="tabledetail" style="width:100%;" cellpadding="1px" cellspacing="1px">
             	<thead  class="tbheader">
                	<th width="10%" align="center">M?? KH</th>
                    <th width="25%" align="left">H??? t??n</th>
                    <th width="30%" align="left">?????a ch???</th>
                    <th width="15%" align="center">??i???n tho???i</th>
                    <th width="10%" align="center">S??? su???t mua</th>
                    <th width="10%" align="center">S??? su???t ??K</th>
                  </thead>

                <% 
                if(khList != null)
                {
                	 String lightrow = "tblightrow";
						String darkrow = "tbdarkrow";
				  	try
				  	{ 
				  		int m = 0;
				  		while(khList.next())
				  		{if (m % 2 != 0) {%>						
						<TR  class= <%=lightrow%> >
						<%} else {%>
							<TR   class= <%= darkrow%> >
						<%}%>
			                	<td align="center">
			                		<%= khList.getString("ma") %>
			                	<input type="hidden" name="khIds" value="<%= khList.getString("khachhang_fk") %>" >
			                	
			                	</td>
			                    <td align="left"><%= khList.getString("khTen") %></td>
			                    <td align="left"><%= khList.getString("DiaChi") %></td>
			                    <td align="center"><%= khList.getString("DienThoai") %></td>
			                    <td align="center">
			                   		 <input type="text" name="soxuat" value="<%= khList.getInt("SoXuat") %>" style="text-align:right;background-color: transparent;border: none;" size="6" readonly></td>
			                    <td align="center">
				                    <input type="text" name="dangky" id="<%= "dangky" + Integer.toString(m) %>" value="<%= khList.getString("dangky") %>" style="text-align:right;background-color: transparent;border: none;" readonly="readonly" size="6" onkeyup="Check('<%= "dangky" + Integer.toString(m) %>')" AUTOCOMPLETE="off" >
				                    <input type="hidden" id="<%= "dangky" + Integer.toString(m) + ".hidden" %>" value="<%= khList.getString("SoXuat") %>" >
			                   	</td>
			                   	
			                </tr>
	     				<% m++;	
	     			   } khList.close(); }catch(Exception e){ System.out.println("Loi trang JSP:  " + e.getMessage() ); } }%>               
                <tr class="tbfooter"><td colspan="6">&nbsp;</td></tr>
             </table>
        </div> 
    </fieldset>
    </div>   
</div>
</form>
<script type="text/javascript">
	dropdowncontent.init('nvbh', "right-top", 300, "click");
</script>
</BODY>
</HTML>
<% 	
	try{
		if(nvbh!=null)
		nvbh.close();
		if(cttrungbay!=null)
		cttrungbay.close();
		if(khList!=null)
		khList.close();
		if(dktbBean!=null){
		dktbBean.DBclose();
		dktbBean = null;
		}
		if(nvbhIds!=null){
			nvbhIds.clear();
		}
		if(kh_soxuat!=null){
			kh_soxuat.clear();
		}
		session.setAttribute("dktbBean",null);
	}
	catch (SQLException e) {}

%>
<%}%>


    