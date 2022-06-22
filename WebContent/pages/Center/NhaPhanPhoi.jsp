<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.List" %>
<%@ page  import = "geso.dms.center.beans.nhaphanphoi.INhaphanphoi" %>
<%@ page  import = "geso.dms.center.beans.nhaphanphoi.INhaphanphoiList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
	
 
<% INhaphanphoiList obj = (INhaphanphoiList)session.getAttribute("obj"); %>
<% ResultSet npplist = (ResultSet) obj.getNppList(); %>
<% ResultSet kv = (ResultSet)obj.getKhuvuc();  %>
<% ResultSet kenh = (ResultSet)obj.getRsKenh() ;  %>
<% ResultSet nppRs = (ResultSet)obj.getNppRs(); %>
<% ResultSet vung = (ResultSet)obj.getRsvung(); %>
<% ResultSet tinhthanh = (ResultSet)obj.getRsdiaban(); %>
<% ResultSet diabanRs = (ResultSet)obj.getDiabanRs(); %>
<% obj.setNextSplittings(); 

	int[] quyen = new  int[6];
	quyen = util.Getquyen("NhaphanphoiSvl", "&isKH=" + obj.getIsKhachhang(), userId);
		
	String url = util.getChuyenNguUrl("NhaphanphoiSvl",  "&isKH=" + obj.getIsKhachhang(),session);

%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>  	
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
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
  	
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { 
      $("select:not(.notuseselect2)").select2({ width: 'resolve' });     
     });
</script>  	
  	
<SCRIPT language="javascript" type="text/javascript">
function clearform()
{
	document.nppForm.maFAST.value = "";	
	document.nppForm.nppTen.value = "";	
	//document.nppForm.DienThoai.value = ""; 
	document.nppForm.kvId.selectedIndex = 0;      
    document.nppForm.TrangThai.selectedIndex = 2;
    document.nppForm.vung.selectedIndex = 0;
    document.nppForm.diaban.selectedIndex = 0;
    submitform();    
}

function submitform()
{
	document.forms['nppForm'].action.value= 'search';
	document.forms['nppForm'].submit();
}
function xuatexcel()
{
	document.forms['nppForm'].action.value= 'excel';
	document.forms['nppForm'].submit();
}
function newform()
{
	document.forms['nppForm'].action.value= 'new';
	document.forms['nppForm'].submit();
}
function thongbao()
{var tt = document.forms['nppForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['nppForm'].msg.value);
	}
</SCRIPT>
</HEAD>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nppForm" method="post" action="../../NhaphanphoiSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="userTen" value='<%= userTen %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >
<input type="hidden" name="msg" value='<%=obj.getMsg() %>'>
<input type="hidden" name="isKH" value='<%=obj.getIsKhachhang() %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
		<!--begin common dossier info---> <!--End common dossier info--->
		<TABLE width="100%" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
				
						   <TD align="left" colspan="2" class="tbnavigation"><%= url %>  </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
						  </tr>
 					  </table>
					</TD>
				</TR>
		</TABLE>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>				
							  <LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>
						    
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								    <tr>  
								    	<TD class="plainlabel"><%=Utility.GLanguage("Tên/Mã",session,jedis) %> </TD>
								    	<TD class="plainlabel"><input type="text" name="nppTen" value="<%=obj.getTen() %>" onChange="submitform();"> 			</TD>
								 		<TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %></TD>
								    	<TD class="plainlabel"><SELECT name="kvId" onChange = "submitform();">
										    <option value=""></option>
										      <% try{while(kv.next()){ 
										    	if(kv.getString("kvId").equals(obj.getKvId())){ %>
										      		<option value='<%=kv.getString("kvId")%>' selected><%=kv.getString("kvTen") %></option>
										      	<%}else{ %>
										     		<option value='<%=kv.getString("kvId")%>'><%=kv.getString("kvTen") %></option>
										     	<%}}}catch(java.sql.SQLException e){} %>	  
		                        				</SELECT></TD>
								  </TR>
								     <tr>  
								    	<TD class="plainlabel"><%=Utility.GLanguage("Vùng",session,jedis) %></TD>
								    	<TD class="plainlabel"><SELECT name="vung" onChange = "submitform();">
								    	
								    	 <option value=""></option>
										      <% try{while(vung.next()){ 
										    	  System.out.println("vung id "+obj.getVung());
										    	if(vung.getString("vid").equals(obj.getVung())){ %>
										      		<option value='<%=vung.getString("vId")%>' selected><%=vung.getString("vTen") %></option>
										      	<%}else{ %>
										     		<option value='<%=vung.getString("vId")%>'><%=vung.getString("vTen") %></option>
										     	<%}}}catch(java.sql.SQLException e){} %>	  
		                        				</SELECT>
								    	
								    	</TD>
								 		<TD class="plainlabel"><%=Utility.GLanguage("Tỉnh thành",session,jedis) %></TD>
								    	<TD class="plainlabel"><SELECT name="diaban" onChange = "submitform();">
										    <option value=""></option>
										      <% try{while(tinhthanh.next()){ 
										    	if(tinhthanh.getString("tId").equals(obj.getDiaban()  )){ %>
										      		<option value='<%=tinhthanh.getString("tId")%>' selected><%=tinhthanh.getString("tTen") %></option>
										      	<%}else{ %>
										     		<option value='<%=tinhthanh.getString("tId")%>'><%=tinhthanh.getString("tTen") %></option>
										     	<%}}}catch(java.sql.SQLException e){e.printStackTrace();} %>	  
		                        				</SELECT></TD>
								  </TR>
								  
								  	<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("Mã DMS",session,jedis) %></TD>
								   		<TD class="plainlabel"><INPUT name="maFAST" type="text" value="<%= obj.getMaFAST() %>" size="40" onChange = "submitform();">
								 		<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
								    	<TD class="plainlabel"><select name="TrangThai" onChange = "submitform();">
											
											<% if (obj.getTrangthai().equals("1")){%>
											  <option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="2"> </option>
											  
											<%}else if(obj.getTrangthai().equals("0")) {%>
											  <option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="2" > </option>
											  
											<%}else{%>																						  
											  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="2" selected> </option>
											<%}%>
										    </select></TD>
								  </TD>		</TR>				
								<%-- <TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Địa bàn",session,jedis) %></TD>
									<TD class="plainlabel">
									<SELECT name="diabanId" onChange = "submitform();"  style = "width:200px">
										    	<option value=""> </option>
											    <% try{ while(diabanRs.next()){ 
										    			if(diabanRs.getString("pk_seq").equals(obj.getDiabanId())){ %>
										      				<option value='<%=diabanRs.getString("pk_seq")%>' selected><%=diabanRs.getString("ten") %></option>
										      			<%}else{ %>
										     				<option value='<%=diabanRs.getString("pk_seq")%>'><%=diabanRs.getString("ten") %></option>
										     			<%}}}catch(java.sql.SQLException e){} %>
											 </SELECT>
											 </TD>
								    <TD class="plainlabel"><%=Utility.GLanguage("Số điện thoại",session,jedis) %> </TD>
								    <TD colspan="1" class="plainlabel"><INPUT name="DienThoai" type="text" size="40" value='<%=obj.getSodienthoai()%>' onChange="submitform();"> </TD>
						      </TR> --%>
						  
						       <TR style="display: none;" >
								    <TD class="plainlabel"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>
								    <TD colspan="3" class="plainlabel"><SELECT name="kenhId" onChange = "submitform();">
								    <option value=""></option>
								      <% try{while(kenh.next()){ 
								    	if(kenh.getString("pk_seq").equals(obj.getKenhId() )  ){ %>
								      		<option value='<%=kenh.getString("pk_seq")%>' selected><%=kenh.getString("ten") %></option>
								      	<%}else{ %>
								     		<option value='<%=kenh.getString("pk_seq")%>'><%=kenh.getString("ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        				</SELECT>			</TD>
                        				
						      </TR>
						      
						      
								  <TR>
								    
						      </TR>
							    <TR>
									<TD colspan="4" class="plainlabel">
									<a class="button2" href="javascript:clearform();">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
									<a class="button2" href="javascript:xuatexcel();">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;	
                                    </TD>
								</TR>
							</TABLE>

							</FIELDSET>
							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>
		</TABLE>
		<TABLE width="100%" cellpadding="0" cellspacing="0">		
				<TR>
					<TD width="100%">
						<FIELDSET><%-- <%= obj.getIsKhachhang().equals("1") %>  --%>
						<LEGEND class="legendtitle">
							<%if(quyen[Utility.THEM]!=0) {%>
							<a class="button3" href="javascript:newform()">
    							<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>   
    						<%} %>        
    						
    						                
						</LEGEND>
	
						    <TABLE class="" width="100%">
						<TR>
							<TD width="100%">
							<TABLE width="100%" id="table" class="tabledetail sortable" border="0" cellspacing="1" cellpadding="4">
							<thead>
								<TR class="tbheader">
									<TH width="4%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
									<TH width="8%"><%=Utility.GLanguage("Mã",session,jedis) %></TH>
									<TH width="8%"><%=Utility.GLanguage("Mã DMS",session,jedis) %></TH>
									<TH width="19%"><%=Utility.GLanguage("Tên",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("Loại",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("Tỉnh thành",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("Khu vực",session,jedis) %> </TH>
									<TH width="8%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
									<!-- <TH width="8%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH> -->
									<TH width="12%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
									<!-- <TH width="8%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH> -->
									<TH width="12%"><%=Utility.GLanguage("Người sửa",session,jedis) %> </TH>
									<TH class="nosort" width="8%" align="center"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
								</TR>
								</thead>
								<tbody>
								
								<% 
									INhaphanphoi npp = null;
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									if(npplist!=null)
									while (npplist.next()){
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
												<TD align="center"><%=npplist.getString("_no") %></TD>
												<TD align="left"><div align="left"><%=npplist.getString("nppMa")%></div></TD>
												<TD align="left"><div align="left"><%=npplist.getString("MAFAST") %></div></TD>
												<TD align="left"><div align="left"><%=npplist.getString("nppTen")%></div></TD>
												<TD><div align="left"><%=npplist.getString("loaiNPP")%></div></TD>                                 
												<TD><div align="left"><%=npplist.getString("tinhthanh")%></div></TD>                                   
												<TD><div align="left"><%= npplist.getString("khuvuc")%></div></TD>
												<% if (npplist.getString("trangthai").equals("1")){ %>
													<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
												<%}else{%>
													<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
												<%}%>
												<%-- <TD align="center"><%=npplist.getString("ngaytao")%></TD> --%>
												<TD align="left"><%=npplist.getString("nguoitao")%></TD>
												<%-- <TD align="center"><%=npplist.getString("ngaysua")%></TD> --%>
												<TD align="left"><%=npplist.getString("nguoisua")%></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="2">
													<TR><TD>
													<%if(quyen[Utility.SUA]!=0) {%>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhaphanphoiUpdateSvl?userId="+userId+"&update="+ npplist.getString("id")+"") %>"><img src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													<TD><%if(quyen[Utility.XEM]!=0) {%>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhaphanphoiUpdateSvl?userId="+userId+"&display="+ npplist.getString("id")+"") %>"><img src="../images/Display20.png" alt="hien thi" title="Hiển thị" width="20" height="20" longdesc="hien thi" border = 0></A>
													<%} %></TD>
													<TD>
													<%if(quyen[Utility.XOA]!=0) {
														String iskh=obj.getIsKhachhang();
													%>
														<A href ="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhaphanphoiSvl?userId="+userId+"&delete="+npplist.getString("id")+"")%>&isKH=<%=iskh%>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn muốn xóa Chi nhánh / NPP này?')) return true;"></A></TD>
													<%} %>
													</TR></TABLE>
												</TD>
											</TR>
								<%m++; } %>
								 </tbody>
                					<tfoot>											
								<tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting()>1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('nppForm', 1, 'view')"> &nbsp;
												<%} else { %>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting()< 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View('nppForm', eval(nppForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												
												
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View('nppForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View('nppForm', eval(nppForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('nppForm', -1, 'view')"> &nbsp;
										   		<%} %>
											</TD>
										 </tr> </tfoot> 

							</TABLE>
							</TD>
						</TR>
					</TABLE>

					</FIELDSET>
					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
</TABLE><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
<script type="text/javascript" src="../scripts/script-table-sorter.js"></script>
	<script type="text/javascript">
		var sorter = new TINY.table.sorter("sorter");
		sorter.head = "HEAD";
		//sorter.asc = "asc";
		sorter.desc = "desc";
		sorter.even = "tblightrow";
		sorter.odd = "tbdarkrow";
		sorter.evensel = "evenselected";
		sorter.oddsel = "oddselected";
		sorter.paginate = true;
		sorter.currentid = "currentpage";
		sorter.limitid = "pagelimit";
		sorter.init("table",0);
	</script> 
</BODY>
</HTML>
<%
	try {
		obj.DBclose();
	}
	catch (Exception e) {
		//e.printStackTrace();
	}
}%>