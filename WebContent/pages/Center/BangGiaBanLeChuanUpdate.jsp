<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.banggiablc.IBanggiablc" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<%  IBanggiablc bgblcBean = (IBanggiablc)session.getAttribute("bgblcBean"); %>
<%  ResultSet dvkd = (ResultSet)bgblcBean.getDvkdIds();
	ResultSet kbhRs = (ResultSet)bgblcBean.getKbhRs();
	ResultSet khuvucRS = (ResultSet)bgblcBean.getKhuvucRs();
	ResultSet loaikhRS = (ResultSet)bgblcBean.getLoaiKhRs();
    ResultSet sanpham = (ResultSet)bgblcBean.getSanPhamList();
	NumberFormat formatter = new DecimalFormat("#,###,###");
	NumberFormat formatter2 = new DecimalFormat("#,###,###.####");
	
	String[] spIdArr = bgblcBean.getSpIdArr();
	String[] spMaArr =  bgblcBean.getSpMaArr() ;
	String[] spTenArr =  bgblcBean.getSpTenArr();
	String[] dongiaArr =  bgblcBean.getDongiaArr();
	String[]  donviArr= bgblcBean.getDonviArr();
	String url = util.getChuyenNguUrl("BanggiabanlechuanSvl", "",session);
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
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});            
	});	
</script>


<SCRIPT language="javascript" type="text/javascript">
<%if( !bgblcBean.getDisplay().equals("1") &&  !bgblcBean.getTrangthai().equals("1")){ %>


function RemoveDisable()
{
	 document.getElementById("dvkdId").removeAttribute('disabled');

}

			function submitform()
			{   
				RemoveDisable();	
			   document.forms["bgblcForm"].submit();
			}
			
			 function saveform()
			{
				RemoveDisable();		
				bgTen  = $('#bgTen').val();
				tungay = $('#tungay').val();
				if(bgTen === ""){
					alert("Vui l??ng nh???p T??n b???ng gi?? ");
					return;
				}
				if(tungay === ""){
					alert("Vui l??ng nh???p T??? ng??y ");
					return;
				}
				document.forms['bgblcForm'].action.value='save';
			    document.forms["bgblcForm"].submit();
			}
 
 <%}%>
 function DinhDangTien(num) 
 {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    num = Math.floor(num/100).toString();
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num);
}
 function Dinhdang(element)
	{
	 
	 var value=element.value;
	 var valueExtention='';
	 
	 //alert(value+'----'+element.value.split('.').length)
	 
	 	if((element.value+'').indexOf('.')>0){
	 		value=element.value.split('.')[0];
	 		if(element.value.split('.').length>=2)
	 		valueExtention='.'+element.value.split('.')[1]
	 		else
	 			valueExtention='.'
	 	}
	 		
		element.value=DinhDangTien(value)+valueExtention;
		if(element.value== '' ||element.value=='0' )
		{
			element.value= '';
		}
	}
 
	 function selectAllLKH()
	 {
	 	$('#loaikhId option').attr('selected', 'selected');	
	 }
	
	 function deSelectAllLKH()
	 {
	 	$('#loaikhId option').removeAttr("selected");
	 }
</SCRIPT>

<!-- <link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
  	$(document).ready(function() { 
  		$("select:not(.notuseselect2)").select2(); 
  	});
</script> -->

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgblcForm" method="post" action="../../BanggiabanlechuanUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=bgblcBean.getUserId() %>'>
<input type="hidden" name="id" value="<%=bgblcBean.getId() %>">
<input type="hidden" name="action" value='1'>
 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
		
			<TABLE width="100%" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						   <tr height="22">
 							   <TD align="left" colspan="2" class="tbnavigation">
 							   		&nbsp;<%=url%> > <%=Utility.GLanguage("C???p nh???t",session,jedis) %> </TD>
 							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;</td>
					     </tr>
						</table>
					 </TD>
				  </TR>	
		  	</TABLE>
			<TABLE width="100%" cellpadding="0" cellspacing="1">
			<TR ><TD >
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
					<TR class = "tbdarkrow">
						<TD width="30" align="left"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"BanggiabanlechuanSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
					    <TD width="2" align="left" ></TD>
					    <TD width="30" align="left"  >
					    <%if( !bgblcBean.getDisplay().equals("1") &&  !bgblcBean.getTrangthai().equals("1")){ %>
					    <A href="javascript: saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
					   <%} %>
					    </TD>
				    	<TD align="left" >&nbsp;</TD>
					</TR>
				</TABLE>
			</TD></TR>
			</TABLE>

		<TABLE width="100%"  border = "0" cellspacing="0" cellpadding="0">
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %> </LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" rows="1" style="width: 100%" readonly="readonly" ><%=bgblcBean.getMessage()%></textarea>
						<% bgblcBean.setMessage(""); %>
						</FIELDSET>
				   </TD>
				</tr>			
		 	
			<TR>
		 	
			<TR>
				<TD >
        			
				<FIELDSET>
				<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("B???ng gi?? b??n",session,jedis) %> &nbsp;</LEGEND>
				<TABLE width="100%" cellpadding="0" cellspacing="1">
					<TR><TD>					
						<TABLE  width="100%" cellpadding="4" cellspacing="0">
							<TR>
								<TD  width="15%" class="plainlabel"><%=Utility.GLanguage("T??n b???ng gi??",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
								<TD class="plainlabel" colspan="3">
								  <input name="bgTen" id="bgTen" type="text" value="<%= bgblcBean.getTen() %>" style="width:300px">
							  	</TD>
							</TR>
							
							<TR>
							<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Khu v???c",session,jedis) %></TD>
							<TD class="plainlabel" width="40%" colspan="1">
							<select name="kvId" id="kvId" multiple="multiple">
		                        <% if(khuvucRS != null) {
		                         while(khuvucRS.next()) 
		                         {
		                           if(bgblcBean.getKhuvucIds().indexOf(khuvucRS.getString("kvId")) >= 0 ){ %>
		                             <option value="<%= khuvucRS.getString("kvId") %>" selected style="padding: 2px" ><%= khuvucRS.getString("kvTen") %></option>
		                         <%}else { %>
		                         	<option value="<%= khuvucRS.getString("kvId") %>" style="padding: 2px"><%= khuvucRS.getString("kvTen") %></option>
		                         <%} }}%>        	
		                    </select>
							</TD>
							
							<!-- <TD class="plainlabel" rowspan="4" colspan="1">Lo???i kh??ch h??ng </TD>
	                        	<TD class="plainlabel" width="40%" rowspan="4" colspan="1"> -->
	                        	<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Lo???i kh??ch h??ng",session,jedis) %> </TD>
	                        	<TD class="plainlabel" width="40%" colspan="1">
	                        	<a href="javascript:;" onclick="selectAllLKH();"><%=Utility.GLanguage("Ch???n t???t c???",session,jedis) %></a> | <a href="javascript:;" onclick="deSelectAllLKH();"><%=Utility.GLanguage("B??? ch???n t???t c???",session,jedis) %></a>
									<br />
	                            <select name="loaikhId" id="loaikhId" multiple="multiple"  >
						        
			                        <% if(loaikhRS != null) {
			                         while(loaikhRS.next()) 
			                         {
			                           if(bgblcBean.getLoaiKhIds().indexOf(loaikhRS.getString("loaiId")) >= 0 ){ %>
			                             <option value="<%= loaikhRS.getString("loaiId") %>" selected style="padding: 2px" ><%= loaikhRS.getString("loaiTen")+"-"+loaikhRS.getString("diengiai") %></option>
			                         <%}else { %>
			                         	<option value="<%= loaikhRS.getString("loaiId") %>" style="padding: 2px"><%= loaikhRS.getString("loaiTen")+"-"+loaikhRS.getString("diengiai")  %></option>
			                         <%} }}%>        	
			                    </select>
			                 </TD>
							</TR>
								
							<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("????n v??? kinh doanh",session,jedis) %><FONT class="erroralert">*</FONT></TD>
								    <TD class="plainlabel" colspan="3">
								    <SELECT disabled  name="dvkdId" id="dvkdId"  onChange = "submitform();" style="width:300px">
								  		<option value =""></option>
								  		
								  	 <% try{ while(dvkd.next()){ 
								    	if(dvkd.getString("dvkdId").equals(bgblcBean.getDvkdId())){ %>
								      		<option value='<%=dvkd.getString("dvkdId") %>' selected><%=dvkd.getString("dvkd") %></option>
								      	<%}else{ %>
								     		<option value='<%=dvkd.getString("dvkdId") %>'><%=dvkd.getString("dvkd") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	
                                  </select></TD>
							</TR>
							
							<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %><FONT class="erroralert">*</FONT></TD>
								    <TD class="plainlabel" colspan="3">
								    <SELECT disabled  name="kbhId"  id="kbhId" onChange = "submitform();" style="width:300px">
								  		<option value =""></option>
								  		
								  	 <% try{ while(kbhRs.next()){ 
								    	if(kbhRs.getString("pk_seq").equals(bgblcBean.getKbhId())){ %>
								      		<option value='<%=kbhRs.getString("pk_seq") %>' selected><%=kbhRs.getString("ten") %></option>
								      	<%}else{ %>
								     		<option value='<%=kbhRs.getString("pk_seq") %>'><%=kbhRs.getString("ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	
                                  </select></TD>
							</TR>
							
							<TR>
							<TD class="plainlabel" width="15%"><%=Utility.GLanguage("T??? ng??y",session,jedis) %><FONT class="erroralert">*</FONT></TD>
							<TD class="plainlabel" colspan="3">
								<input name="tungay" id="tungay" type="text" class="days"  value="<%=bgblcBean.getTungay() %>" style="width:300px">
						  	</TD>
							</TR>
						</TABLE>
					</TD></TR>
				</TABLE>
				<TABLE class="tabledetail" cellpadding="0" cellspacing="0" width="100%">
						<TR id="spdvkd" bgcolor="#FFFFFF">
							<TD width="100%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="0">
								<TR class="tbheader">
									<TH width="25%"><%=Utility.GLanguage("M?? s???n ph???m",session,jedis) %></TH>
									<TH width="45%"><%=Utility.GLanguage("T??n s???n ph???m",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("????n v??? t??nh",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("Gi?? b??n l??? chu???n",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("Ti???n t???",session,jedis) %></TH>
								</TR>
								
								<%
								int j = 0;
								String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";

								for(int i =0 ; i < spIdArr.length; i ++)
								{ 
									if (j % 2 != 0) 
									{%>						
										<TR class= <%=lightrow%> >
								    <%} else 
								    {%>
										<TR class= <%= darkrow%> >
									<%}%>
											<TD align="center">
												<input type='text' name='spMaArr'  value="<%= spMaArr[i]%>" style="text-align: left"/>
											</TD>
											<TD align="center">
												<input type='text' name='spTenArr'  value="<%= spTenArr[i]%>" style="text-align: left"/>
											</TD>
											
											<TD align="center">
												<input type='text' name='donviArr'  value="<%= donviArr[i]%>" style="text-align: center"/>
											</TD>
											<TD align="center">
												<input type='text' name='dongiaArr' onkeyup="Dinhdang(this)"   value="<%= formatter2.format(Utility.parseDouble(dongiaArr[i].replace(",",""))) %>" style="text-align: right"/>
												<input type='hidden' name='spIdArr' value="<%= spIdArr[i] %>" />
												<input type='hidden' name='quycach' value="0" style="text-align: right"/>
											</TD>
											<TD  align="center">
												VN??
											</TD>
						     		<% j++;
								}
								%>
							</TABLE>

							</TD>
						</TR>
					</TABLE>
					</FIELDSET>
				</td>
			</TR>
		</TABLE>
	
	</TD>
	</TR>
</Table><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</BODY>
</HTML>

<% if(dvkd != null) dvkd.close(); %>
<% if (sanpham != null) sanpham.close(); 
	bgblcBean.DbClose();
%>
<%}%>