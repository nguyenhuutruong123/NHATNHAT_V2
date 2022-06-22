<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhomsanpham.INhomsanpham" %>
<%@ page  import = "geso.dms.center.beans.nhomsanpham.INhomsanphamList" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% INhomsanphamList obj = (INhomsanphamList)session.getAttribute("obj"); %>
<% List<INhomsanpham> nsplist = (List<INhomsanpham>)obj.getNspList(); 
	int[] quyen = new  int[6];
	quyen = util.Getquyen("NhomsanphamSvl","",userId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">


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


<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	 document.nspForm.action.value = "new";
   	 document.forms["nspForm"].submit();
}

function searchform()
{
	 document.nspForm.action.value = "search";
   	 document.forms["nspForm"].submit();
}

function clearform()
{
	document.nspForm.diengiai.value = "";
	document.nspForm.thanhvien.selectedIndex = 2;
    document.nspForm.trangthai.selectedIndex = 0;
    document.nspForm.lnhom.value = "";
	document.nspForm.tungay.value = "";
	document.nspForm.denngay.value = "";
 	document.forms["nspForm"].submit();
}


function xuatExcel()
{
 	document.forms['nspForm'].action.value= 'excel';
 	document.forms['nspForm'].submit();
 	document.forms['nspForm'].action.value= '';
}
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nspForm" method="post" action="../../NhomsanphamSvl">
<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value="1">

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
						  			&nbsp;Dữ liệu nền &gt; Sản phẩm  &gt; Nhóm sản phẩm</TD> 
						  		<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
						  	</tr>
						</table></TD>
			  	</TR>
			</TABLE>
			
			<TABLE width="100%" border="0" cellpadding="0">
				<TR>
					<TD >
						<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>
							<TABLE  width="100%" cellspacing="0" cellpadding="6">
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
								    <TD class="plainlabel"><TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<INPUT name="diengiai" type="text" size="40" value='<%=obj.getDiengiai()%>' onChange ="searchform();">
											</TD></TR>
										</TABLE>								
									<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
									<TD class="plainlabel" >
												<TABLE cellpadding="0" cellspacing="0">
													<TR><TD>
														<input class="days" type="text" name="tungay" value='<%=obj.getTungay() %>'  size="20" onchange = "searchform();">
													</TD>
													
													</TR>
												</TABLE>																					
		  									</TD>
								</TR>
								<TR>
								  <TD class="plainlabel">Loại thành viên </TD>
								  <TD  class="plainlabel"><select name="thanhvien" onChange = "searchform();">
								    <% if (obj.getLoaithanhvien().equals("1")){ %>								    
								    	<option value="1" selected>Nhóm sản phẩm</option>
								    	<option value="2">San pham</option>
								    	<option value=""> </option>
								    <%}else{ 
								    	 if (obj.getLoaithanhvien().equals("2")){ %>								    
								    		<option value="1">NHóm sản phẩm</option>
								    		<option value="2" selected><%=Utility.GLanguage("Sản phẩm",session,jedis) %></option>
								    		<option value=""> </option>
								    	<%}else{ %>							    							    
								    		<option value="1" >Nhóm sản phẩm</option>
								    		<option value="2"><%=Utility.GLanguage("Sản phẩm",session,jedis) %></option>
								    		<option value="" selected> </option>
								    <%} }%>
								    
								    </select> </TD>								  							 							  							 
							  	  <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
								  <TD class="plainlabel" ><TABLE cellpadding="0" cellspacing="0">
										  		<TR>
										  			<TD>
										 				<input class="days" type="text" name="denngay" value='<%=obj.getDenngay() %>' size="20" onchange = "searchform();">
										  			</TD>
											  </TR>
											 </TABLE></TD>	 
							  </TR>
							  
							  <TR>
							  	  <TD class="plainlabel">Loại nhóm </TD>
								  <TD class="plainlabel"><select name="lnhom" onChange = "searchform();">
								    <% if (obj.getLoainhom().equals("0")){ %>	
								    	<option value=""> </option>							    
								    	<option value="0" selected>Nhóm bình thường</option>
								    	<option value="3">Nhóm chỉ tiêu</option>								    	
								    <%}else{ 
								    	 if (obj.getLoainhom().equals("3")){ %>	
								    	 	<option value=""> </option>							    								    		
								    		<option value="0">Nhóm bình thường</option>
								    		<option value="3" selected>Nhóm chỉ tiêu</option>								    		
								    	<%}else{ %>	
								    		<option value="" selected> </option>						    							    
								    		<option value="0">Nhóm bình thường</option>
								    		<option value="3">Nhóm chỉ tiêu</option>								    		
								    <%} }%>
								    
								    </select>
								  </TD>				
							  	  <TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
								  <TD class="plainlabel"><SELECT name = "trangthai"  onChange = "searchform();">
								  	<% if (obj.getTrangthai().equals("0")){ %>
								    	<option value="2"> </option>
								    	<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}else{ 							
								  		if (obj.getTrangthai().equals("1")){ %>
								    	<option value="2"> </option>
								    	<option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}else{ %>
								    	<option value="2" selected> </option>
								    	<option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}} %>

										</SELECT></TD>
							  </TR>
							  		
								<TR>
									<TD class="plainlabel" colspan="4">
										<a class="button2" href="javascript:clearform()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>
                                    </TD>
                                    												
								</TR>
								
							</TABLE>
					  </FIELDSET>
					</TD>	
				</TR>
			</TABLE>
			
			<TABLE width="100%" border = "0" cellpading = "0" cellspacing = "0">
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Nhóm sản phẩm &nbsp;&nbsp;&nbsp;
					<%if(quyen[Utility.THEM]!=0) {%>
						<a class="button3" href="javascript:submitform()" >
    					<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>   
    				<%} %>                         		
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="4%">STT</TH>
									<TH width="9%">Tên nhóm</TH>
									<TH width="18%"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TH>
									<TH width="12%">Loại thành viên </TH>
									<TH width="9%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
									<TH width="8%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %> </TH>
									<TH width="12%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
									<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
									<TH width="12%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH> 
									<TH width="9%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
								</TR>
						<% 
							INhomsanpham nsp = null;
							int size = nsplist.size();
							int m = 0;
							Hashtable parent = new Hashtable();
							parent.put("0", "* ");
							String star = "";
							String lightrow = "tblightrow";
							String darkrow = "tbdarkrow";
							while (m < size){
								nsp = nsplist.get(m);
								if (m % 2 != 0) {%>						
									<TR class= <%=lightrow%> >
								<%} else {%>
									<TR class= <%= darkrow%> >
								<%} %>						
									<TD align="center"><%=m+1%></TD>				
															
							<%	if(!obj.getSearch()){
							
									if (parent.containsKey(nsp.getParent())){									
										star = (String)parent.get(nsp.getParent()); 
									}else{																		
										star = star + "* ";
										parent.put(nsp.getParent(), star);
									}		%>

									<TD align="left"><%= star %><%=nsp.getTen() %></TD>
							
							<%	}else{ %>		
							
									<TD align="left"><%=nsp.getTen() %></TD>
							
							<%	} %>
									<TD><%=nsp.getDiengiai() %></TD>
									<% if(nsp.getThanhvien().equals("2")) {%>
										<TD align="center"><div align="left"> Sản phẩm </div></TD>
									<%}else {%>
										<TD align="center"><div align="left">Nhóm sản phẩm</div></TD>
									<%} %>
									
									<% if(nsp.getTrangthai().equals("1")) {%>
										<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
									<%}else {%>
										<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
									<%} %>
									<TD align="center"><%=nsp.getNgaytao() %></TD>
									<TD align="center"><%=nsp.getNguoitao() %></TD>
									<TD align="center"><%=nsp.getNgaysua() %></TD>
									<TD align="center"><%=nsp.getNguoisua() %> </TD>
									<TD align="center">
										<TABLE>
											<TR><TD>
											<%if(quyen[Utility.SUA]!=0) {%>
												<A href = "../../NhomsanphamUpdateSvl?userId=<%=userId%>&update=<%=nsp.getId()%>"><img src="../images/Display.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
											<%} %>
											</TD>
											<TD>&nbsp;</TD>
											<TD>
											<%-- <%if(quyen[Utility.XOA]!=0 && Integer.parseInt(nsp.getId()) > 100004 ) {%>
												<A href = "../../NhomsanphamSvl?userId=<%=userId%>&delete=<%=nsp.getId()%>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn muốn xóa nhóm sản phẩm này?')) return false;"></A>
												<%} %> --%>
												</TD>
											</TR>												
										</TABLE>									
									</TD>
								</TR>
							<%m++; }%>
							
								<TR>
									<TD align="center" colspan="12" class="tbfooter">&nbsp;</TD>
								</TR>
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
</BODY>
</HTML>
<%}%>