<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.daidienkinhdoanh.IDaidienkinhdoanh" %>
<%@ page  import = "geso.dms.center.beans.daidienkinhdoanh.IDaidienkinhdoanhList" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IDaidienkinhdoanhList obj =(IDaidienkinhdoanhList)session.getAttribute("obj"); %>
<% ResultSet ddkdList = (ResultSet)obj.getDdkdList(); %>
<% ResultSet gsbh =  (ResultSet) obj.getGsbanhang(); %>
<% ResultSet npp =  (ResultSet) obj.getNhaphanphoi(); %>
<% ResultSet kbh =  (ResultSet) obj.getKenhbanhang(); %>
<% obj.setNextSplittings(); 
	int[] quyen = new  int[6];
	quyen = util.Getquyen("DaidienkinhdoanhSvl","",userId);
	
	String url = util.getChuyenNguUrl("DaidienkinhdoanhSvl", "",session);
	if(obj.getIsPG().equals("1"))
		url =  util.getUrl("DaidienkinhdoanhSvl","&isPG=1");
	else
		url =  util.getUrl("DaidienkinhdoanhSvl","");
	

%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
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
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>  	
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" src="../scripts/phanTrang.js"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<SCRIPT language="javascript" type="text/javascript">

$(document).ready(function()
		{
			$("#nppId").select2();
			$("#gsbhId").select2();
		});		

function clearform()
{
	document.ddkdForm.ddkdTen.value = "";
	document.ddkdForm.maFAST.value = "";
    document.ddkdForm.DienThoai.value = "";      
    document.ddkdForm.KenhBanHang.selectedIndex = 0;
    document.ddkdForm.GSBanHang.selectedIndex = 0;
    document.ddkdForm.NhaPhanPhoi.selectedIndex = 0;
    document.ddkdForm.TrangThai.selectedIndex = 2;
    submitform();    
}

function submitform()
{
	document.forms['ddkdForm'].action.value= 'search';
	document.forms['ddkdForm'].submit();
}
function xuatexcel()
{
	document.forms['ddkdForm'].action.value= 'excel';
	document.forms['ddkdForm'].submit();
}
function newform()
{
	document.forms['ddkdForm'].action.value= 'new';
	document.forms['ddkdForm'].submit();
}
function thongbao(){
	var tt = document.forms['ddkdForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['ddkdForm'].msg.value);
}

</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ddkdForm" method="post" action="../../DaidienkinhdoanhSvl">

<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>


<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="userTen" value='<%= userTen %>'>
<input type="hidden" name="isPG" value='<%= obj.getIsPG() %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

 
<input type="hidden" name="msg" value='<%= Utility.GLanguage(obj.getMsg(),session,jedis)   %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<table  width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<tr>
		<td colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
		<!--begin common dossier info---> <!--End common dossier info--->
		<table  width="100%" cellpadding="0" cellspacing="1">
			
				<tr>
				  <td align="left"><table  width="100%" border="0" cellpadding="0" cellspacing="0">

						  <tr height="20">
						   <td align="left" colspan="2" class="tbnavigation"><%=url %></td>
   
						   <td colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </td>
						  </tr>
 					  </table>
					</td>
				</tr>

		</table>
		<table  width="100%" cellpadding="0" cellspacing="0">		
				<tr>
					<td>
					<table  border="0" width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" align="center" >
							<FIELDSET>
							  <LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>
							<table   width="100%" cellpadding="6" cellspacing="0">
								  <tr>  
								    <td width="15%" class="plainlabel"><%=Utility.GLanguage("Mã / Họ tên",session,jedis) %> </td>
								    <td class="plainlabel"><input type="text" name="ddkdTen" value="<%= obj.getTen() %>" onChange="submitform();"></td>
								  </tr>
								  
								   <TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Mã DMS",session,jedis) %></TD>
								    <TD class="plainlabel">
											<INPUT name="maFAST" type="text" value="<%= obj.getMaFAST() %>" onChange = "submitform();">
								  </TD>					
								</TR>
								  
								  <tr>
								    <td class="plainlabel"><%=Utility.GLanguage("Điện thoại",session,jedis) %> </td>
								    <td class="plainlabel"><input type="text" name="DienThoai" value="<%= obj.getSodienthoai() %>" onChange="submitform();"></td>
							      </tr>
							      							     
							      
							      
								  <tr style="display: none;" >
									    <td class="plainlabel"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></td>
									    <td class="plainlabel">
									    	<SELECT name="KenhBanHang" onChange = "submitform();">
										    <option value=""></option> 
										      <% try{ while(kbh.next()){ 
										    	if(kbh.getString("kbhId").equals(obj.getKbhId())){ %>
										      		<option value='<%=kbh.getString("kbhId") %>' selected='selected'><%=kbh.getString("kbhTen") %></option>
										      	<%}else{ %>
										     		<option value='<%=kbh.getString("kbhId") %>' ><%=kbh.getString("kbhTen") %></option>
										     	<%}}}catch(java.sql.SQLException e){} %>
										     	
										    </SELECT>
										</td>
					              </tr>
								  <tr>
								    <td class="plainlabel"><%=Utility.GLanguage("Giám sát",session,jedis) %> </td>
								    <td class="plainlabel">
								    	<SELECT name="GSBanHang"  id="gsbhId"  onChange = "submitform();" style="width:350px" >
									    	<option value=""></option> 
									        <% try{ while(gsbh.next()){ 
									    	if(gsbh.getString("gsbhId").equals(obj.getGsbhId())){ %>
									      		<option value='<%=gsbh.getString("gsbhId") %>' selected='selected'><%=gsbh.getString("gsbhTen") %></option>
									      	<%}else{ %>
									     		<option value='<%=gsbh.getString("gsbhId") %>' ><%=gsbh.getString("gsbhTen") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
									     	
									    </SELECT> 
									  </td>
								  </tr>
								  <tr>
						            <td class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></td>
									<td class="plainlabel">
									<SELECT name="NhaPhanPhoi" id="nppId" onChange = "submitform()" style="width:350px" >
									<option value=""> </option>
									<% if(npp != null){
										  try
										  { 
											  String optionGroup = "";
											  String optionName = "";
											  int i = 0;
											  
											  while(npp.next())
											  { 
												 if(i == 0)
												 {
													 optionGroup = npp.getString("kvTen");
													 optionName = npp.getString("kvTen");
													 
													 %>
													 
													 <optgroup label="<%= optionName %>" >
												 <% }
												 
												 optionGroup = npp.getString("kvTen");
												 if(optionGroup.trim().equals(optionName.trim()))
												 { %>
													 <% if(npp.getString("nppId").equals(obj.getNppId())) {%>
													 	<option value="<%= npp.getString("nppId") %>" selected="selected" ><%= npp.getString("nppTen") %></option>
													 <%} else { %>
													 	<option value="<%= npp.getString("nppId") %>"><%= npp.getString("nppTen") %></option>
													 <%} %>
												 <% }
												 else
												 {
													 %>
													</optgroup>
													<% optionName = optionGroup; %>
													<optgroup label="<%= optionName %>" >
													<% if(npp.getString("nppId").equals(obj.getNppId())) {%>
													 	<option value="<%= npp.getString("nppId") %>" selected="selected" ><%= npp.getString("nppTen") %></option>
													 <%} else { %>
													 	<option value="<%= npp.getString("nppId") %>"><%= npp.getString("nppTen") %></option>
													 <%} %>
												 <% }
												 i++;
								     	 	  } 
											  %>
											  	</optgroup>
											  <%npp.close(); 
								     	 }catch(Exception e){}}%>	  
                                	</SELECT>
								</td>
						            
							  </tr>
								  <tr>
								    <td class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </td>
								    <td colspan="3" class="plainlabel"><SELECT name="TrangThai" onChange = "submitform();">
                                      
                                      <% if (obj.getTrangthai().equals("1")){%>
											  <option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="2"></option>
											  
										<%}else if(obj.getTrangthai().equals("0")) {%>
											  <option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="2" ></option>
											  
										<%}else{%>																						  
											  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="2" selected></option>
										<%}%>
                                    </SELECT></td>
						      </tr>
							    <tr>
									<td colspan="4" class="plainlabel">
									<a class="button2" href="javascript:clearform()">
											<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="button2" href="javascript:xuatexcel()">
											<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
								</tr>

							</table>

							</FIELDSET>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>

					<td width="100%">
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Nhân viên bán hàng",session,jedis) %> &nbsp;&nbsp;&nbsp;
							<%if(quyen[Utility.THEM]!=0) {%>
							<a class="button3" href="javascript:newform()">
    								<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>    
							<%} %> 
							
						</LEGEND>
	
						    <table  class="" width="100%">
						<tr>
							<td width="98%">
							<table  id="table" class="tabledetail sortable"  width="100%" border="0" cellspacing="1" cellpadding="4">
							<thead>
								<tr class="tbheader">
									<TH width="3%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
									<th width="5%"><%=Utility.GLanguage("Mã",session,jedis) %></th>
									<TH width="7%"><%=Utility.GLanguage("Mã DMS",session,jedis) %></TH>
									<th width="12%"><%=Utility.GLanguage("Họ tên",session,jedis) %></th>
									<th width="10%"><%=Utility.GLanguage("Điện thoại",session,jedis) %></th>
									<th width="20%"><%=Utility.GLanguage("Trực thuộc",session,jedis) %></th>
									<th width="10%"><%=Utility.GLanguage("Cấp trên",session,jedis) %></th>
									<th width="8%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </th>
									<th width="8%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </th>
									<th width="12%"><%=Utility.GLanguage("Người sửa",session,jedis) %></th>
									<th class="nosort" width="10%" align="center">&nbsp;<%=Utility.GLanguage("Tác vụ",session,jedis) %></th>
								</tr></thead>
								<tbody>
                                <%      
                                    int m = 0;
                                    if(ddkdList!=null)
                                    while (ddkdList.next()){
                                       
                                        if (m % 2 != 0) {%>                     
                                            <tr class='tbdarkrow'>
                                        <%} else { %>
                                            <tr class='tblightrow' >
                                        <%}%>
                                        		<TD align="center"><%=ddkdList.getString("_no") %></TD>
                                        		<td align="left"><div align="left"><%=ddkdList.getString("manhanvien") %></div></td>
                                        		<TD align="left"><div align="left"><%=ddkdList.getString("MAFAST") %></div></TD>  
                                                <td align="left"><div align="left"><%= ddkdList.getString("ten") %></div></td>                                          
                                                <td><div align="center"><%= ddkdList.getString("dienthoai")  %></div></td>
                                                <td align="center"><div align="left"><%= ddkdList.getString("nppTen") %></div></td>
                                                <td align="center"><%= ddkdList.getString("gsbhTen") %></td>
                                                <% if (ddkdList.getString("trangthai").equals("1"))
                                                { %>
                                                    <td align="left"><%=Utility.GLanguage("Hoạt động",session,jedis) %></td>
                                                <%}else{%>
                                                    <td align="left"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></td>
                                                <%}%>
                                                <td align="center"><%=ddkdList.getString("ngaysua")%></td>
                                                <td align="center"><%= ddkdList.getString("nguoitao")%></td>
                                                <td align="center">
                                                <table  border = 0 cellpadding="0" cellspacing="0">
	                                                    <tr><td>
	                                                    <%if(quyen[Utility.SUA]!=0) {%>
	                                                    	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DaidienkinhdoanhUpdateSvl?userId="+userId+"&update="+ ddkdList.getString("id") +"&isPG="+obj.getIsPG()+"")%>"><img src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
	                                                    <%} %>
	                                                    </td>
	                                                    <td> <%if(quyen[Utility.XEM]!=0) {%>
	                                                    	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DaidienkinhdoanhUpdateSvl?userId="+userId+"&display="+ ddkdList.getString("id") +"&isPG="+obj.getIsPG()+"")%>"><img src="../images/Display20.png" alt="hien thi" title="Hiển thị" width="20" height="20" longdesc="hien thi" border = 0></A>
	                                                    <%} %></td>
	                                                    <td>
	                                                    <%if(quyen[Utility.XOA]!=0) {%>
	                                                        	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DaidienkinhdoanhSvl?userId="+userId+"&delete="+ ddkdList.getString("id")+"") %>&isPG=<%=obj.getIsPG()%>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa nhân viên bán hàng này?')) return false;"></A>
	                                                    <%} %>
	                                                    </td>
	                                                    </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                <% m++; }%>
                                
                                	 </tbody>
                					<tfoot>
                                		<tr class="sortbottom" class="tbfooter" > 
											 <td align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('ddkdForm', 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View('ddkdForm', eval(ddkdForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												System.out.println("Current page:" + obj.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View('ddkdForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View('ddkdForm', eval(ddkdForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('ddkdForm', -1, 'view')"> &nbsp;
										   		<%} %>
											</td>
										 </tr>  </tfoot> 
									
							</table>
							</td>
						</tr>
					</table>

					</FIELDSET>
					</td>
				</tr>
		</table>
		</td>
	</tr>
</table>
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




try{
if(ddkdList!=null){
ddkdList.close();
}
if(gsbh!=null){
	gsbh.close();
	}

if(npp!=null){
	npp.close();
	}
if(kbh!=null){
	kbh.close();
	}
obj.DbClose();
}catch(Exception er){
	
}

}%>

