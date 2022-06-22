<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="geso.dms.center.beans.chitieu.imp.ChiTieuNPP"%>
<%@page import="java.util.Calendar"%>
<%@page import="geso.dms.center.beans.chitieuttchovung.imp.ChiTieuTTKhuVuc"%>
<%@page import="geso.dms.center.beans.chitieuttchovung.imp.ChiTieuTTChoVung"%>
<%@page import="java.util.Date"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page import="geso.dms.center.beans.chitieu.imp.ChiTieu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhomkhuyenmai.INhomkhuyenmai" %>
<%@ page  import = "geso.dms.center.beans.nhomkhuyenmai.imp.Nhomkhuyenmai" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "javax.swing.table.DefaultTableModel" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>


<%
	NumberFormat formatter = new DecimalFormat("#,###,###");
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	//Lay doi tuong objbean
 	IStockintransit obj=(IStockintransit)session.getAttribute("obj");
 	DefaultTableModel tableDbRs = obj.getTableDbRS();
 	DefaultTableModel dataDbRs = obj.getDataDbRs();
	Utility util = new Utility();
	String url = util.getUrl("DongBoDuLieuSvl","");
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">


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

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nppId").select2();
	
});
</script>


<SCRIPT language="JavaScript" type="text/javascript"> 
function upload()
{
	 document.forms["ChiTieuTTForm"].setAttribute('enctype', "multipart/form-data", 0);
    document.forms["ChiTieuTTForm"].submit();
  
}
function seach()
{
	document.forms['ChiTieuTTForm'].action.value= 'seach';
    document.forms["ChiTieuTTForm"].submit();
}

function sync()
{
	document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
	document.forms['ChiTieuTTForm'].action.value= 'sync';
    document.forms["ChiTieuTTForm"].submit();
}

</SCRIPT>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/ajax.js"></script>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="ChiTieuTTForm" method="post" action="../../DongBoDuLieuSvl" >
<input type="hidden"  name="userId" value='<%=userId%>'>
<input type="hidden" name="userTen" value='<%=userTen%>'>
<input type="hidden" name="action" value="0">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation"><%= url %></TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
	
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
				<TR class="tbdarkrow">
										
										<TD width="30" align="left">
											<div id="btnSave">
												<A href="javascript:sync()"><IMG
													src="../images/Save30.png" title="Luu lai" alt="Luu lai"
													border="1" style="border-style: outset">
												</A>
											</div></TD>
										
									</TR>
			
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
	    				<textarea name="dataerror"  style="width: 100%" readonly="readonly" rows="1"><%=obj.getMsg()%></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Thông tin chỉ tiêu</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
						
							
							
						  
				  		   <TR>
								<TD class="plainlabel">Bảng dữ liệu</TD>
									<TD class="plainlabel">
										<select name="tableDbName" id="tableDbName" onchange="seach();">
											<option value="" selected></option>
											<%if (tableDbRs != null)
													for(int i= 0 ; i < tableDbRs.getRowCount();i ++) 
													{
														
														String value  = tableDbRs.getValueAt(i,tableDbRs.findColumn("TABLE_NAME")).toString();
														
														if (value.equals(obj.getTableDbName())) {%>
											   				<option value="<%=value%>" selected><%=value%></option>
											   			<%} else {%>
											   				<option value="<%=value%>"><%=value%></option>
														<%}}%>
										</select>
									</TD>
						  	</TR>
						  	<TR>
						  		<TD class="plainlabel" valign="middle" width="120px"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
								<TD class="plainlabel" valign="middle"><input onchange="seach();"
									type="text" class="days" name="tungay" id="tungay"
									value="<%=obj.gettungay()%>"></TD>
									
						  	
						  	</TR>
						  	<TR>
						  		<TD class="plainlabel" valign="middle" width="120px"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
								<TD class="plainlabel" valign="middle"><input onchange="seach();"
									type="text" class="days" name="denngay" id="denngay"
									value="<%=obj.getdenngay()%>"></TD>
									
						  	
						  	</TR>
                      
						  	
						</TABLE>
										
										
										
										
										
													
						</FIELDSET>						
					</TD>
				</TR>
			</TABLE>
			<%if(dataDbRs != null && dataDbRs.getRowCount() > 0){ %>
			<div >
			<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					
										<TR class="tbheader">
										<% for(int x = 0 ; x < dataDbRs.getColumnCount(); x ++  ){ %>
										
											<TH width="10%" ><%= dataDbRs.getColumnName(x) %></TH>
										<!-- 	<TH width="20%">Tên nhà cung cấp</TH>
											<TH width="10%">Tên viết tắt </TH>
											<TH width="12%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
											<TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
											<TH width="13%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
											<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </TH>
											<TH width="13%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
											<TH width="13%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH> -->
										<%} %>
										</TR>
								<% 
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									for(int y = 0 ; y < dataDbRs.getRowCount(); y ++  )
									{
										if (m % 2 != 0) {
										%>
										<TR class= <%=lightrow%> >
										<%} else {%>
										<TR class= <%= darkrow%> >
										<%
										}
										
										
										for(int z = 0 ; z < dataDbRs.getColumnCount(); z ++  )
										{
											String value = "error";
											if( y < dataDbRs.getRowCount() && z < dataDbRs.getColumnCount())
											{
												
												try{
													


													if (dataDbRs.getValueAt(y,z) instanceof Double || dataDbRs.getValueAt(y,z) instanceof Float ) 
													{
														value = formatter.format((Double)dataDbRs.getValueAt(y,z));
													}
													else	
														value = dataDbRs.getValueAt(y,z).toString();				
													
												
												}catch(Exception e)
												{e.printStackTrace();}
											}
										%>
										<TD align="center"><%=value%></TD>
										
										<%}%>	
									
									<%m++; }%>

							</TABLE>
							</div>
			<%} %>
			
		</TD>
	</TR>
	</TABLE>
</form>
<script type="text/javascript">

</script>
</BODY>
<%
	try
	{
		tableDbRs = null;
		if(obj != null){
			obj.DBclose();
			obj = null;
		}
	}catch(Exception e){}
	
%>
</HTML>