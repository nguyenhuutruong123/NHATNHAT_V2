<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.tratrungbay.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% ITratrungbayList obj = (ITratrungbayList)session.getAttribute("obj"); %>
<% List<ITratrungbay> dkkmList = (List<ITratrungbay>)obj.getTratbList(); %>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");
String sum = (String) session.getAttribute("sum");
Utility util = (Utility) session.getAttribute("util");
if(!util.check(userId, userTen, sum)){
	response.sendRedirect("/ORGALIFE/index.jsp");
}else{
	int[] quyen = new  int[6];
	quyen = util.Getquyen("TratrungbaySvl","",userId);

%>
<% Utility Util = new Utility(); %>
<%String url = util.getChuyenNguUrl("TratrungbaySvl", "",session); %>

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
					changeYear: true	,
					dateFormat: "yy-mm-dd"
			});            
        }); 		
		
</script>
    
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
   
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
	    if(confirm("Ban co muon dang xuat?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	 }
	 function submitform()
	 {   
	    document.forms["tratbForm"].submit();
	 }
	 function newform()
	 {   
		document.forms["tratbForm"].action.value = "Tao moi";
	    document.forms["tratbForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["tratbForm"].diengiai.value = "";
	    document.forms["tratbForm"].tungay.value = "";
	    document.forms["tratbForm"].denngay.value = "";
	    document.forms["tratbForm"].submit();
	 }
	 
	 function xuatExcel()
	 {
	 	document.forms['tratbForm'].action.value= 'toExcel';
	 	document.forms['tratbForm'].submit();
	 	document.forms['tratbForm'].action.value= '';
	 }
	 function thongbao()
	 {
	 	if (document.getElementById("msg").value != '')
	 		alert(document.getElementById("msg").value);
	 }
	 
	</SCRIPT>
</head>
<body>
<form name="tratbForm" method="post" action="../../TratrungbaySvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value="<%= userId %>" >

<input type="hidden" name="msg" id="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>
<input type="hidden" name="action" value="1" >
<script type="text/javascript">
	thongbao();
</script>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	<%=url %>
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset>
            <legend class="legendtitle"> <%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> </legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px">								                 
                    <TR>
                        <TD class="plainlabel" valign="middle" width="15%"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                            <input type="text" name="diengiai" value="<%= obj.getDiengiai() %>">
                        </TD>                        
                    </TR>            
                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input autocomplete ="off" type="text" size="10" class="days"
                                   id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" />
                        </TD>
                    </TR>
                     <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input autocomplete ="off"  type="text" size="10" class="days"
                                   id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" />
                        </TD>
                    </TR>
                    <tr style="background-color:#C5E8CD">
                        <td colspan="2">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
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
            	<legend><span class="legendtitle"><%=Utility.GLanguage("Trả trưng bày",session,jedis) %></span>&nbsp;&nbsp;&nbsp;
            	<%if(quyen[Utility.THEM]!=0){ %>
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %>  </a>
                           <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
				                    <TH align="center"><%=Utility.GLanguage("Mã trả TB",session,jedis) %></TH>
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
		                        ITratrungbay dkkm = null;
		                        int size = dkkmList.size();
		                        int m = 0;
		                        while (m < size){
		                            dkkm = dkkmList.get(m);
		                            if(m%2==0){
		                    %> 
		                         <TR class='tbdarkrow'>
		                         <%}else{ %>
		                         <TR class='tblightrow'>
		                         <%} %>
				                    <TD align="center"><%= dkkm.getId() %></TD>
				                    <TD align="left"><%= dkkm.getDiengiai() %></TD>
				                    <TD align="right"><%= dkkm.getTongluong() %></TD>										                                    
				                    <TD align="right"><%= dkkm.getTongtien() %></TD>
				                    <TD align="center"><%= dkkm.getNgaytao() %></TD>
				                    <TD align="left"><%= dkkm.getNguoitao() %></TD>
				                    <TD align="center"><%= dkkm.getNgaysua() %></TD>	
				                    <TD align="left"><%= dkkm.getNguoisua() %></TD>				
				                    <TD align="center">
				                        <TABLE cellpadding="1" cellspacing="0">
				                            <TR><TD>
				                            <%if(quyen[Utility.SUA]!=0){ %>
				                                <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TratrungbayUpdateSvl?userId="+userId+"&update="+ dkkm.getId()+"")%>"><IMG src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" border="0"></A>
				                            <%} %>
				                            </TD><TD>
				                            <%if(quyen[Utility.XOA]!=0){ %>
				                                <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TratrungbaySvl?userId="+userId+"&delete="+ dkkm.getId() +"")%>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn trả trưng bày này?')) return false;"></A>
				                           <%} %>
				                            </TD></TR>
				                        </TABLE>									
				                    </TD>
				                </TR>
		                     <% m++; }%>														
                                
							<TR class="tbfooter">
								<TD align="center" colspan="10"> |< < 1 to 20 of 100 > >|</TD>
							</TR>
						</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</body>
</HTML>
<%}%>