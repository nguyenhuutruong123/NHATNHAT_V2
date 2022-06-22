<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page  import = "geso.dms.center.beans.kehoachpg.IKehoachPGList" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SEANEST/index.jsp");
	}else{ int[] quyen = new  int[6];		
%>

<% IKehoachPGList obj =(IKehoachPGList)session.getAttribute("obj"); %>
<% ResultSet ddkdList = (ResultSet)obj.getDdkdList(); %>
<% ResultSet npp =  (ResultSet) obj.getNhaphanphoi(); %>
<% obj.setNextSplittings(); %>

<% String nnId = (String)session.getAttribute("nnId"); %> 
<% if(nnId == null) {
	nnId = "vi"; 
 } 

String url = util.getChuyenNguUrl("KehoachPGSvl", "",session);
String view = obj.getView();
if (view != null && view.equals("TT")) {
	
	
	url = util.getUrl("KehoachPGSvl","&view=TT"); 
	quyen = util.Getquyen("KehoachPGSvl","&view=TT",userId);
}
else {
	quyen = util.Getquyen("KehoachPGSvl","",userId);
	url = util.getUrl("KehoachPGSvl",""); 
}

%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/s">



<script type="text/javascript" src="../scripts/jquery-1.js"></script>
<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>


<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" src="../scripts/phanTrang.js"></script>
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">


<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<SCRIPT language="javascript" type="text/javascript">

function clearform()
{
	document.ddkdForm.ddkdTen.value = "";
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

function Xoa(id){
	
	document.forms['ddkdForm'].IdXoa.value = id;
	document.forms['ddkdForm'].action.value= 'Xoa';
	document.forms['ddkdForm'].submit();
}

</SCRIPT>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#NhaPhanPhoi").select2();
	
});
</script>



</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">


<form name="ddkdForm" method="post" action="../../KehoachPGSvl">  
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="IdXoa" value=''>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >
<input type="hidden" name="ngonnguId" value='<%=nnId%>'>
<input type="hidden" name="msg" value='<%=obj.getMsg() %>'>
<input type="hidden" name="view" value='<%=view%>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
		<!--begin common dossier info---> <!--End common dossier info--->
		<TABLE width="100%" cellpadding="0" cellspacing="1">
			
				<TR>
				  <TD align="left"><table width="100%" border="0" cellpadding="0" cellspacing="0">

						  <tr height="20">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%= " " + url %> </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng",session,jedis) %> <%=userTen %>&nbsp;</TD>
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
							  <LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								    <tr>  
								    <TD width="24%" class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
								        <TD class="plainlabel"><input type="text" name="ddkdTen" value="<%= obj.getTen() %>" onChange="submitform();"></TD>
								  	</TR>
								  
								  <TR>
								    <TD width="24%" class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
								    <TD  class="plainlabel" >
								    	<SELECT name="TrangThai" onChange = "submitform();">
                                      
                                      <% if (obj.getTrangthai().equals("1")){%>
											  <option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value=""></option>
											  
										<%}else if(obj.getTrangthai().equals("0")) {%>
											  <option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="" ></option>
											  
										<%}else{%>																						  
											  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="" selected></option>
										<%}%>
                                   	 </SELECT>
                                    </TD>
								      
						      </TR>
								  <TR>
								   
									    
						            <TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TD>
									<TD  class="plainlabel">
									<SELECT name="NhaPhanPhoi" id="NhaPhanPhoi" onChange = "submitform()" style = "width:350px" >
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
													 <% if(obj.getNppId() != null && npp.getString("nppId").equals(obj.getNppId())) {%>
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
													<% if(obj.getNppId() != null && npp.getString("nppId").equals(obj.getNppId())) {%>
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
								     	 }catch(java.sql.SQLException e){}}%>	  
                                	</SELECT>
								</TD>
									     
				              </TR>
								  <TR>
								   

						            
							  </TR>
							  <TR>
								  
						      </TR>
								  <TR>
								  
						      </TR>
							    <TR>
									<TD colspan="2" class="plainlabel">
									<a class="button2" href="javascript:clearform()">
											<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
								       </TD>
								</TR>

							</TABLE>

							</FIELDSET>
							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>

				<TR>

					<TD width="100%">
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Kế hoạch PG",session,jedis) %> &nbsp;&nbsp;&nbsp;
							<%if(quyen[0]!=0){ %>
							<a class="button3" href="javascript:newform()">
    								<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>    
							<%} %>
							
						</LEGEND>
	
						    <TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">

								<TR class="tbheader">

									<TH width="13%"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TH>
									
									<TH width="18%"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </TH>
									<TH width="8%"><%=Utility.GLanguage("Tuần",session,jedis) %></TH>
									<TH width="8%"><%=Utility.GLanguage("Năm",session,jedis) %></TH>
									<TH width="7%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
									<TH width="8%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
									<TH width="8%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
									<TH width="8%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
									<TH width="8%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
									<TH width="17%" align="center">&nbsp;Tác vụ</TH>
								</TR>

                                <%      
									int m = 0;
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    
                                    if(ddkdList!=null)
                                    while (ddkdList.next()){
                                       
                                        if (m % 2 != 0) {%>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else { %>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                        		<TD align="center"><%= ddkdList.getString("diengiai") %></TD>
                                        		<TD align="center"><%= ddkdList.getString("npp") %></TD>
                                                <TD align="center"><%= ddkdList.getString("tuan") %></TD>
                                                <TD align="center"><%= ddkdList.getString("nam") %></TD>
                                                <% if (ddkdList.getString("trangthai").equals("1")){ %>
                                                    <TD align="left"><%=Utility.GLanguage("Đã chốt",session,jedis) %></TD>
                                                <%}else{%>
                                                    <TD align="left"><%=Utility.GLanguage("Chưa chốt",session,jedis) %></TD>
                                                <%}%>
                                                <TD align="center"><%= ddkdList.getString("ngaytao") %></TD>
												<TD align="center"><%= ddkdList.getString("ngaysua") %></TD>
												<TD align="center"><%= ddkdList.getString("nguoitao") %></TD>
												<TD align="center"><%= ddkdList.getString("nguoisua") %></TD>
                                                
                                               
                                                <TD align="center">
                                                <TABLE border = 0 cellpadding="0" cellspacing="0">
                                                    <TR>
                                                  
                                                    
                                                      <TD>
                                                     	<%if(ddkdList.getString("trangthai").equals("0")  && quyen[Utility.CHOT]!=0) {%>
                                                       
                                                        <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KehoachPGSvl?userId="+userId+"&chot="+ ddkdList.getString("id") +"&view="+view+"")%>">
                                                        	<img src="../images/Chot.png" alt="Xoa" title="chốt" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn chốt kế hoạch này?')) return false;">
                                                        </A>
                                                        
                                                     	<%} %>
                                                     </TD>
                                                     
                                                    <TD>&nbsp;</TD>
                                                    <TD>
                                                    <%if( ddkdList.getString("trangthai").equals("0")  && quyen[2]!=0){ %>
                                                    	
                                                   		<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KehoachPGUpdateSvl?userId="+userId+"&update="+ ddkdList.getString("id") +"&view="+view+"")%>">
                                                   				<img src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0>
                                                   		</A>
                                                   
                                                    <%} %>
                                                    </TD>
                                                    <TD>&nbsp;</TD>
                                                    <TD>
                                                    <%if(  ddkdList.getString("trangthai").equals("0")  && quyen[Utility.XOA]!=0){ %>
                                                        	<A href="javascript:Xoa('<%=ddkdList.getString("id") %>');">
                                                        		<img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 
                                                        		onclick="if(!confirm('Bạn có muốn xóa kế hoạch này?')) return false;"></A>
                                                    <%} %>
                                                    </TD>
                                                    <TD>&nbsp;</TD>
                                                    <TD>	
														<%if(quyen[3]!=0){ %>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KehoachPGUpdateSvl?userId="+userId+"&display="+ddkdList.getString("id") +"&view="+view+"")%>"><img src="../images/Display20.png" alt="Hien thi" title="Hiển thị" width="20" height="20" longdesc="Hien thi" border = 0></A>&nbsp;
														<%} %>
													</TD>
                                                    </TR></TABLE>
                                                </TD>
                                            </TR>
                                <%m++; }%>
                                
                                
                                <tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang đầu" src="../images/first.gif" style="cursor: pointer;" onclick="View('ddkdForm', 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang đầu" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang đầu" src="../images/prev.gif" style="cursor: pointer;" onclick="View('ddkdForm', eval(ddkdForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang trước" src="../images/prev_d.gif" > &nbsp;
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
													&nbsp; <img alt="Trang tiếp" src="../images/next.gif" style="cursor: pointer;" onclick="View('ddkdForm', eval(ddkdForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang tiếp" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang cuối" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang cuối" src="../images/last.gif" style="cursor: pointer;" onclick="View('ddkdForm', -1, 'view')"> &nbsp;
										   		<%} %>
											</TD>
										 </tr>  
									
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
</TABLE>
</form>
</body>  <script type='text/javascript' src='../scripts/loadingv2.js'></script>
</HTML>


<%

try{
	if(ddkdList!=null){ ddkdList.close(); ddkdList = null; }
	
	if(npp!=null){ npp.close(); npp = null;  }

	
	obj.DbClose(); obj = null;
	session.setAttribute("obj",null);
	
}catch(Exception er){
	
}

}%>

