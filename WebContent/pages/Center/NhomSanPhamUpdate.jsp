<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhomsanpham.INhomsanpham" %>
<%@ page  import = "geso.dms.center.beans.nhomsanpham.imp.Nhomsanpham" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% INhomsanpham nspBean = (INhomsanpham)session.getAttribute("nspBean"); %>
<% 	ResultSet nspList = (ResultSet)nspBean.getNspList(); 
	ResultSet spList = (ResultSet)nspBean.getSpList(); 
	ResultSet spSelected = (ResultSet)nspBean.getSpSelected();
	ResultSet dvkdList = (ResultSet)nspBean.getDvkdList();
	ResultSet nhList = (ResultSet)nspBean.getNhList();
	ResultSet clList = (ResultSet)nspBean.getCLList();
	String dvkdId = (String) nspBean.getDvkdId();
	String nhId = (String)nspBean.getNhId();
	String clId = (String)nspBean.getClId();
	ResultSet rsB= nspBean.getSanpham_mienbac();
	ResultSet rsT= nspBean.getSanpham_mientrung();
	ResultSet rsN= nspBean.getSanpham_miennam();
	  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<script type="text/javascript" src="../scripts/ajax.js"></script>
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">
<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
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
<SCRIPT language="JavaScript" type="text/javascript">

function submitform()
{
	document.nspForm.action.value='save';
    document.forms["nspForm"].submit();
    
}

function filterDvkd()
{
    document.nspForm.action.value='filter';
    document.nspForm.nhId.value='0';
    document.nspForm.clId.value='0';
    document.forms["nspForm"].submit();       
}

function filterNh()
{
    document.nspForm.action.value='filter';
    document.nspForm.clId.value='0';
    document.forms["nspForm"].submit();   
    
}

function filterCl()
{
    document.nspForm.action.value='filter';
    document.forms["nspForm"].submit();       
}



function checkedAll(chk) {
	for(i=0; i<chk.length; i++){
	 	if(document.nspForm.chon.checked==true){
			chk[i].checked = true;
		}else{
			chk[i].checked = false;
		}
	 }
}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="nspForm" method="post" action="../../NhomsanphamUpdateSvl" >
<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="nspId" value='<%= nspBean.getId()  %>'>
<input type="hidden" name="action" value="0">

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">
							 	&nbsp;Dữ liệu &gt; Sản phẩm  &gt; Nhóm sản phẩm > Hiển thị</TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../NhomsanphamSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <!-- <TD width="2" align="left" ></TD>
						    <TD width="30" align="left" ><A href="javascript: submitform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD> -->
							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%=nspBean.getMessage()%></textarea>
						<% nspBean.setMessage(""); %>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Thông tin nhóm sản phẩm</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
							<TR>
								<TD class="plainlabel" >Tên nhóm sản phẩm<FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel"><INPUT type="text" name="ten" style="width:200px" value='<%= nspBean.getTen()%>'></TD>
								<TD class="plainlabel">Loại thành viên  <FONT class="erroralert"> *</FONT></TD>
							  	<TD class="plainlabel"><SELECT name="thanhvien" onchange=filter()>
	                              <% if (nspBean.getThanhvien().equals("1")){ %>                                
	                                	<OPTION value="1" selected >Nhóm sản phẩm</OPTION>
	                                	<OPTION value="2" ><%=Utility.GLanguage("Sản phẩm",session,jedis) %> </OPTION>
	                              <%}else{ %>
	                                	<OPTION value="1" >Nhóm sản phẩm</OPTION>
	                                	<OPTION value="2" selected><%=Utility.GLanguage("Sản phẩm",session,jedis) %></OPTION>
								  <%} %>                              
                              </SELECT></TD>
							</TR>
							<TR>
							  	<TD class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TD>
						  	  	<TD class="plainlabel"><INPUT type="text" name="diengiai" style="width:200px" value='<%= nspBean.getDiengiai() %>'></TD>
						  		<TD colspan="2" class="plainlabel" ><label>
							  	<% if(nspBean.getTrangthai().equals("1")){ %>
							    	<input name="trangthai" type="checkbox" value="1" checked >
							    <%}else{ %>
							    	<input name="trangthai" type="checkbox" value="0" >
							    <%} %>
							   Hoạt động</label></TD>
						  </TR>
							
						  
						  <% if (nspBean.getThanhvien().equals("2")){%>					  	  		
						  		<TR>
						  	  	<TD class="plainlabel">Đơn vị kinh doanh</TD>
						  	  	<TD class="plainlabel"><SELECT name="dvkdId" onchange="filterDvkd();">
						  	  		<OPTION value="0" ></OPTION>	
						  	  		<% if(dvkdList!= null){						  	  			
						   					while (dvkdList.next()){
						  	  					if (dvkdList.getString("pk_seq").equals(dvkdId)){%>
						  	  						<OPTION value=<%= dvkdList.getString("pk_seq")%> selected><%= dvkdList.getString("diengiai")%></OPTION>
						  	  					<%}else{ %>
						  	  						<OPTION value=<%= dvkdList.getString("pk_seq")%> ><%= dvkdList.getString("diengiai") %></OPTION>
						  	  	<%				  }
						  	  				
						  	  				}
						  	  			
						  	  		}%>						  	  			
						  	  	</SELECT>
						  	  	</TD>
						  		<TR>
						  		
						  		<TR>
						  	  	<TD class="plainlabel">Nhãn hàng</TD>
						  	  	<TD class="plainlabel"><SELECT name="nhId" onchange="filterNh();">
						  	  		<OPTION value="0" ></OPTION>	
						  	  		<% if(nhList!= null){						  	  		
							   				while (nhList.next()){
						  	  					if (nhList.getString("pk_seq").equals(nhId)){%>
						  	  						<OPTION value='<%= nhList.getString("pk_seq")%>' selected ><%= nhList.getString("ten") %></OPTION>
						  	  					<%}else{ %>
						  	  						<OPTION value='<%= nhList.getString("pk_seq")%>'  ><%= nhList.getString("ten") %></OPTION>
						  	  		<%			 }
						  	  				
						  	  				}
						  	  			
						  	  		}%>
						  	  		
						  	  	</SELECT>
						  	  	</TD>
						  		<TR>
						  		<TR>
						  	  	<TD class="plainlabel">Chủng loại</TD>
						  	  	<TD class="plainlabel"><SELECT name="clId" onchange="filterCl();">	
						  	  		<OPTION value="0" ></OPTION>
						  	  		<% if(clList!= null){					  	  			
							   				while (clList.next()){
							   					
						  	  					if (clList.getString("pk_seq").equals(clId)){%>
						  	  						<OPTION value="<%= clList.getString("pk_seq")%>" selected><%= clList.getString("ten") %></OPTION>
						  	  					<%}else{ %>
						  	  						<OPTION value="<%= clList.getString("pk_seq")%>" ><%= clList.getString("ten") %></OPTION>
						  	  	<%				  }
						  	  			
						  	  				}
						  	  			
						  	  		}%>
						  	  	</SELECT>
						  	  	</TD>
						  	  	</TR>
						  	  	<TR>
						  	  		<TR>
						  	  		
						  	  	<TD class="plainlabel">Loại nhóm</TD>
							  <TD width="71%" class="plainlabel"><SELECT name="lnhom">
                              <% if (nspBean.getLoainhom().equals("0")){ %>                                
                                	<OPTION value="0" selected>Nhóm bình thường</OPTION>
                                	<OPTION value="3" >Nhóm chỉ tiêu</OPTION>
                              <%}else if(nspBean.getLoainhom().equals("3")){ %>
                                	<OPTION value="0" >Nhóm bình thường</OPTION>
                                	<OPTION value="3" selected>Nhóm chỉ tiêu</OPTION>
							  <%} else{ %>
							        <OPTION value="" selected></OPTION>
							        <OPTION value="0" >Nhóm bình thường</OPTION>
                                	<OPTION value="3" >Nhóm chỉ tiêu</OPTION> 
                                <%}} %>                       
                              	
                              </SELECT></TD>
						  	
						  	  	
						  		<TR>
						  		
						  
							  
						  </TR>
						  
						</TABLE>
						<!-- dfs--------------------------------------------------------------- -->		
						 	<ul  class="tabs"  id="tabnew">			
								<li class="current" > <a href="#tab1">Miền Bắc</a></li>
								<li class="current" > <a href="#tab2">Miền Trung</a></li>
								<li class="current" > <a href="#tab3">Miền Nam</a></li>
								
								
						 </ul>  
						   <div class="panes"> 
						   	<!-- bac --> 
						   <div id="tab1" class ="tab_content" style=" height: 500px">
								<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
				                  		                  
				                  <TR class="tbheader">
				                  		<td align="left" > Mã sản phẩm</td>
				                  		<td align="left" > Tên sản phẩm</td>
				                  		<td align="center" > Chọn</td>
								</TR >	
								<% 
								if(rsB!=null){
								while(rsB.next()){%>	
								<TR class="tblightrow">	
										<td align="left" ><%=rsB.getString("MA") %></td>
				                  		<td align="left"> <%=rsB.getString("TEN") %></td>
				                  		<TD align="center" >
				                  			<% if( rsB.getString("trangthai").equals("1") ) { %> 
													<input type="checkbox" name="Bcb" value="<%= rsB.getString("PK_SEQ") %>"  checked="checked" >
											<%  } else { %> 
						
													<input type="checkbox" name="Bcb" value="<%= rsB.getString("PK_SEQ")%>" >	
											<% } %>
										</TD>
										</TR>
				                  		<%} }%>
															
														
														
														
								</TABLE>
							</div>
							<!-- trung -->
						<div id="tab2" class ="tab_content" style=" height: 500px">
								<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
				                  		                  
				                  <TR class="tbheader">
				                  		<td align="left" > Mã sản phẩm</td>
				                  		<td align="left" > Tên sản phẩm</td>
				                  		<td align="center" > Chọn</td>
								</TR >	
								<%
								if(rsT!=null){
								while(rsT.next()){%>	
								<TR class="tblightrow">	
										<td align="left" ><%=rsT.getString("MA") %></td>
				                  		<td align="left"> <%=rsT.getString("TEN") %></td>
				                  		<TD align="center">
				                  			<% if( rsT.getString("trangthai").equals("1") ) { %> 
												
													<input type="checkbox" name="Tcb" value="<%= rsT.getString("PK_SEQ") %>"  checked="checked" >
											
											<%  } else { %> 
												
													<input type="checkbox" name="Tcb" value="<%= rsT.getString("PK_SEQ") %>" >
												
											<% } %>
									</TD>	
								</TR>	
				                  		<%}} %>
														
														
														
														
								</TABLE>
							</div>
							<!--Nam -->
							<div id="tab3" class ="tab_content" style=" height: 500px">
								<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
				                  		                  
				                  <TR class="tbheader">
				                  		<td align="left" > Mã sản phẩm</td>
				                  		<td align="left" > Tên sản phẩm</td>
				                  		<td align="center" > Chọn</td>
								</TR >	
								<% if(rsN!=null){ 
								while(rsN.next()){%>	
								<TR class="tblightrow">	
										<td align="left" ><%=rsN.getString("MA") %></td>
				                  		<td align="left"> <%=rsN.getString("TEN") %></td>
				                  			<TD align="center">
				                  		
				                  			<% if( rsN.getString("trangthai").equals("1") ) { %> 
											
													<input type="checkbox" name="Ncb" value="<%= rsN.getString("PK_SEQ") %>"  checked="checked" >
												
											<%  } else { %> 
											
													<input type="checkbox" name="Ncb" value="<%= rsN.getString("PK_SEQ") %>" >
												
											<% } %>
											</TD>	
									</TR>
				                  		<%}} %>
															
														
														
														
								</TABLE>
							</div>
						
						
						
						
						
						</div>		
												
										
						
						
											
						</FIELDSET>						
					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
	</TABLE>
</form>
<% }%>
</BODY>
</HTML>
	

