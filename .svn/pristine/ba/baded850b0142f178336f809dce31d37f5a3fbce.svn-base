<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.phieuxuatkho.*" %>
<%@ page  import = "geso.dms.distributor.beans.donhang.ISanpham" %>
<%@ page  import = "geso.dms.distributor.beans.donhang.imp.Sanpham" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page import = "java.text.DateFormat" %>
<%@ page import = "java.text.DecimalFormat" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>


<% IPhieuxuatkho pxkBean = (IPhieuxuatkho) session.getAttribute("pxkBean"); %>
<% ResultSet nvgn = (ResultSet) pxkBean.getNhanvienGN(); %>
<% ResultSet donhangList = (ResultSet)pxkBean.getDonhangList(); %>

<% List<ISanpham> spList = (List<ISanpham>) pxkBean.getPxk_spList(); %>
<% List<ISanpham> spkmList = (List<ISanpham>) pxkBean.getPxk_spkmList(); %>

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
   
    <script language="javascript" type="text/javascript">
		function submitform()
		{ 
		    document.forms['pxkForm'].action.value='submit';
		    document.forms['pxkForm'].submit();
		}	
	</script>
    
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="pxkForm" method="post" action="../../PhieuxuatkhoPdfSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
<INPUT name="userId" type="hidden" value='<%=userId%>' size="30">
<input type="hidden" name="id" value='<%=pxkBean.getId()%>'>
<input type="hidden" name="nppId" value='<%=pxkBean.getNppId()%>'>
<input type="hidden" name="action" value='1'>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Quản lý bán hàng > Bán hàng OTC > Phiếu xuất kho > Hiển thị
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
			Chào mừng  <%=pxkBean.getNppTen()%> 
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	
        	<% String ur = Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"PhieuxuatkhoSvl?userId="+userId);  %>			
												<A href="../../RouterSvl?task=<%=ur %>" >
													<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset">
												</A>
        
    </div>
   	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle"> Phiếu xuất kho </legend>
        	
        	<div style="float:none; width:100%" align="left">
            	<table width="100%" cellspacing="0" cellpadding="6px">
                    <tr>
                    	<td class="plainlabel" width="30%" valign="middle">Ngày lập phiếu</td>
                    	<td class="plainlabel">
                            <input type="text" size="11" value="<%=pxkBean.getNgaylap()%>" maxlength="10" readonly />
                    	</td> 
                    	<td class="plainlabel">&nbsp;</td>                   
                    </tr>       
                    <tr>
           				<td class="plainlabel">Nhân viên giao nhận<FONT class="erroralert"> *</FONT></td> 
                        <td class="plainlabel">
                            <SELECT name="nvgnTen" id="nvgnList" disabled="disabled" >
					 			 <option value="">&nbsp;</option>
								  <%
								  	if (nvgn != null) {
								  		try {
								  			while (nvgn.next()) {
								  				if (nvgn.getString("nvgnId").equals(pxkBean.getNvgnId())) {
								  %>
						      				<option value='<%=nvgn.getString("nvgnId")%>' selected><%=nvgn.getString("nvgnTen")%></option>
						      			<%
						      				} else {
						      			%>
						     				<option value='<%=nvgn.getString("nvgnId")%>'><%=nvgn.getString("nvgnTen")%></option>
						     	<%
						     		}
						     				}
						     			} catch (java.sql.SQLException e) {
						     			}
						     		}
						     	%>	 
                              </SELECT>
                        </td>
                        <td class="plainlabel">&nbsp;</td>
                    </tr>
                 </table>
                 <hr>                 
                 <% if(donhangList != null){ DecimalFormat df2 = new DecimalFormat( "#,###,###,##0" ); %>
                 <table width="100%" cellpadding="4px" cellspacing="1px">
                 	<tr>
                    	<th class="tbheader" align="center">Số đơn hàng</th>
                        <th class="tbheader" align="center">Ngày đơn hàng</th>
                        <th class="tbheader" align="center">Mã khách hàng</th>
                        <th class="tbheader" align="left">Tên khách hàng</th>
                        <th class="tbheader" align="center">Tổng giá trị</th>
                    </tr>
                    
                    <% try{ while(donhangList.next()){ 
                    	String style = "";
                    	if( donhangList.getString("loaihoadon").equals("1") )
                    		style = "style='color:red;'";
                    	%>
		    			<tr class="tbdarkrow" <%= style %> >
	                    	<td align="center">
	                    		<%= donhangList.getString("DDH_FK") %>
	                    	</td>
	                        <td align="center"><%= donhangList.getDate("ngaynhap").toString() %></td>
	                        <td align="center"><%= donhangList.getString("maFAST") %></td>
	                        <td><%= donhangList.getString("khTen") %></td>
	                        <td align="center">
	                        	<%= df2.format(donhangList.getFloat("tonggiatri")).toString() %>
	                        </td>
                    	</tr>
	     			<% } } catch(Exception e){ e.printStackTrace(); } %>
	     			
                    <tr class="tbfooter"><td colspan="7">&nbsp;</td></tr>
                 </table>
                 <%} %>
			
                 <hr>
                 <table width="100%" cellpadding="4px" cellspacing="1px">
                 	<tr>
                    	<th class="tbheader" align="center">Mã sản phẩm</th>
                        <th class="tbheader" align="center">Tên sản phẩm</th>
                        <th class="tbheader" align="center">Số lượng</th>
                        <th class="tbheader" align="center">Kho</th>
                        <th class="tbheader" align="center"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></th>
                        <th class="tbheader" align="center">Loại sản phẩm</th>
                    </tr>
                    <%
                    	for(int i = 0; i < spList.size(); i ++)
                    	{
                    		Sanpham sp = (Sanpham)spList.get(i);
                    	%>
		    			<tr class="tbdarkrow">
	                    	<td align="center"><%= sp.getMasanpham() %></td>
	                        <td align="left"><%= sp.getTensanpham() %></td>
	                        <td align="center"><%= sp.getSoluong() %></td>
	                        <td align="center"><%= sp.getDonvitinh() %></td>
	                        <td align="center"><%= sp.getDongia() %></td>
	                        <td align="center">Hàng bán</td>
                    	</tr>
	     			<%}%>
	     			<%
	     			
	     				System.out.println("san pham list km la "+ spkmList.size());
                    	for(int i = 0; i < spkmList.size(); i ++)
                    	{
                    		Sanpham sp = (Sanpham)spkmList.get(i);
                    	%>
		    			<tr class="tbdarkrow">
	                    	<td align="center"><%= sp.getMasanpham() %></td>
	                        <td align="left"><%= sp.getTensanpham() %></td>
	                        <td align="center"><%= sp.getSoluong() %></td>
	                        <td align="center"><%= sp.getDonvitinh() %></td>
	                        <td align="center"><%= sp.getDongia() %></td>
	                        <td align="center">Hàng khuyến mại</td>
                    	</tr>
	     			<%}%>
                    <tr class="tbfooter"><td colspan="6">&nbsp;</td></tr>
                 </table>
            </div>
            <div align="left" style="width:100%; float:none; clear:none; display:none" id="sanphamList">                   
        </div>      
    </fieldset>	
    </div>
</div>
</form>
</BODY>
</HTML>

<% 	

	try{
	if(donhangList != null)
		donhangList.close();
	if(nvgn != null)
		nvgn.close();
	if(pxkBean != null){
		pxkBean.DBclose();
		pxkBean= null;
	}
	 
	 session.setAttribute("pxkBean",null);
	}
	catch (SQLException e) {}

%>
<%}%>