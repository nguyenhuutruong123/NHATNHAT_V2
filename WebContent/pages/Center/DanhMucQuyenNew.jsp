<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.danhmucquyen.IDanhmucquyen"%>
<%@ page import="geso.dms.center.beans.kho.IKho"%>
<%@ page import="geso.dms.center.util.*"%>
<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");

	if (!util.check(userId, userTen, sum))
	{
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	} else
	{
		IDanhmucquyen obj = (IDanhmucquyen) session.getAttribute("obj");
		ResultSet ungdungRs = (ResultSet) obj.getUngdungRs();
%>
<%String url = util.getChuyenNguUrl("DanhmucquyenSvl", "",session); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<style type="text/css">
#sku tr:HOVER {
	cursor: pointer;
	background: #E5E0E0;
}
</style>
<SCRIPT language="JavaScript" type="text/javascript">
<!--HPB_SCRIPT_CODE_40
//-->
function submitform()
{
    document.forms["khoForm"].submit();
}
function changeView()
{
	document.forms["khoForm"].action.value="loaiMenu";
	submitform();
}

function check_all(checkid, doituong)
{   
		 var spIds = document.getElementsByName(doituong);
		 for(var i = 0; i < spIds.length; i++)
		 {
			 spIds.item(i).checked = checkid;
		 }
}


function	CheckAllDanhMuc(danhmucId)
{
	var ungdungIds =document.getElementsByName("ungdungId");
	  for(var i = 0; i < ungdungIds.length; i++) 
	  {
		var ungdungId=danhmucId+'_'+ungdungIds.item(i).value;
		try
		{
			var hienthi =document.getElementById("ch_" + ungdungId);
			var them =document.getElementById("chthem_" + ungdungId);
			var chot = document.getElementById("chchot_" + ungdungId);
			var xem = document.getElementById("chxem_" + ungdungId);
			var xoa = document.getElementById("chxoa_" + ungdungId);
			var sua = document.getElementById("chsua_" + ungdungId);
			var huychot = document.getElementById("chhuychot_" + ungdungId);
			
			if(hienthi.checked ==false||them.checked==false) 
			{
				hienthi.checked=true;
				them.checked=true;
			    chot.checked=true;
			    xem.checked=true;
			    xoa.checked=true;
			    sua.checked=true;
			    huychot.checked=true;
			  
			} else 
			{
				hienthi.checked=false;
				them.checked=false;
			    chot.checked=false;
			    xem.checked=false;
			    xoa.checked=false;
			    sua.checked=false;
			    huychot.checked=false;
			}
		}catch(err)
		{
			
		}
	  }
}
function	CheckAllUngDung(ungdungId)
{
	var hienthi =document.getElementById("ch_" + ungdungId);
	var them =document.getElementById("chthem_" + ungdungId);
	var chot = document.getElementById("chchot_" + ungdungId);
	var xem = document.getElementById("chxem_" + ungdungId);
	var xoa = document.getElementById("chxoa_" + ungdungId);
	var sua = document.getElementById("chsua_" + ungdungId);
	var huychot = document.getElementById("chhuychot_" + ungdungId);
	
	if(hienthi.checked==false)
	{
		hienthi.checked=true;
		them.checked=true;
	    chot.checked=true;
	    xem.checked=true;
	    xoa.checked=true;
	    sua.checked=true;
	    huychot.checked=true;
	}else
	{
		hienthi.checked=false;
		them.checked=false;
	    chot.checked=false;
	    xem.checked=false;
	    xoa.checked=false;
	    sua.checked=false;
	    huychot.checked=false;
	}
}




</SCRIPT>
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript">
$( function() {
	//Created By: Brij Mohan
	//Website: http://techbrij.com
	function groupTable($rows, startIndex, total)
	{
		if (total === 0)
		{
			return;
		}
		var i , currentIndex = startIndex, count=1, lst=[];
		var tds = $rows.find('td:eq('+ currentIndex +')');
		var ctrl = $(tds[0]);
		lst.push($rows[0]);
		for (i=1;i<=tds.length;i++){
		if (ctrl.text() ==  $(tds[i]).text()){
		count++;
		$(tds[i]).addClass('deleted');
		lst.push($rows[i]);
		}
		else{
			if (count>1){
			ctrl.attr('rowspan',count);
			groupTable($(lst),startIndex+1,total-1)
			}
			count=1;
			lst = [];
			ctrl=$(tds[i]);
			lst.push($rows[i]);
		}
		}
	}
	var rowCount = $('#sku tr').length;

	groupTable($('#sku tr:has(td)'),0,rowCount);
	$('#sku .deleted').remove();

	}); 
    </script>
</HEAD>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="khoForm" method="post" action="../../DanhmucquyennewSvl" charset="utf-8">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<INPUT name="userId" type="hidden" value='<%=userId%>' size="30"> 
		<INPUT name="id" type="hidden" value='<%=obj.getId()%>' size="30">
		<input name="action" type="hidden" value='0'> 
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
					<TABLE width="100%" cellpadding="0" cellspacing="1">
						<TR>
							<TD align="left" class="tbnavigation">

								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation"><%=url %> > <%=Utility.GLanguage("T???o m???i",session,jedis) %></TD>
										<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen%> &nbsp;
										</td>
									</tr>

								</TABLE>
							</TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<TR>
							<TD>
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<TR class="tbdarkrow">
										<TD width="30" align="left"><A href="../../DanhmucquyenSvl"><img src="../images/Back30.png" alt="Quay ve" title="Quay ve" border="1" longdesc="Quay ve" style="border-style: outset"></A></TD>
										<TD width="2" align="left"></TD>
										<TD width="30" align="left"><A href="javascript: submitform()"><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border="1" style="border-style: outset"></A></TD>
										<TD>&nbsp;</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
					</TABLE>

					<TABLE width=100% cellpadding="4" cellspacing="0" border="0">
						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>

									<textarea name="dataerror" style="width: 100%; color: #F00; font-weight: bold" style="width:100%" readonly="readonly" rows="1"> <%=obj.getMsg()%> </textarea>

								</FIELDSET>
							</TD>
						</tr>

						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Th??ng tin quy???n",session,jedis) %> </LEGEND>
									<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
										<TR>
											<TD width="15%" class="plainlabel"><%=Utility.GLanguage("T??n quy???n",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
											<TD class="plainlabel"><INPUT name="ten" size="20" value="<%=obj.getTen()%> " type="text"></TD>
										</TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TD>
											<TD class="plainlabel"><INPUT name="diengiai" size="80" value="<%=obj.getDiengiai()%>" type="text"></TD>
										</TR>
									<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("Xem theo",session,jedis) %></TD>
										<TD class="plainlabel">
											<% if(obj.getLoaiMenu().equals("0")){%>
												<input type="radio" name="loaiMenu"	value="0"  checked="checked" onchange="changeView()"/><%=Utility.GLanguage("Trung t??m",session,jedis) %>  &nbsp;&nbsp;&nbsp; 
												<input type="radio" name="loaiMenu" value="1" onchange="changeView()"/><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %>
											<%}else{ %>
											<input type="radio" name="loaiMenu"	value="0"   onchange="changeView()"/><%=Utility.GLanguage("Trung t??m",session,jedis) %>  &nbsp;&nbsp;&nbsp; 
											<input type="radio" name="loaiMenu" value="1" checked="checked" onchange="changeView()"/><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %>
											<%} %>
									
																					
										</TD>	
									</TR>
										<TR>
											<TD class="plainlabel"><label> <input name="trangthai" type="checkbox" value="1" checked> <%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %>
											</label></TD>
											<TD class="plainlabel">&nbsp;</TD>
										</TR>
										<TR>


										</TR>
									</TABLE>
								</FIELDSET>
							</TD>
						</TR>
						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Th??ng tin quy???n",session,jedis) %> </LEGEND>
									<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
										<TR>
											<TD width="98%">
												<TABLE ID="sku" width="100%" border="1" cellspacing="1" cellpadding="4">
													<TR class="tbheader">
														<TH width="22%"><%=Utility.GLanguage("Menu",session,jedis) %></TH>
														<TH width="22%"><%=Utility.GLanguage("???ng d???ng",session,jedis) %></TH>
														<TH width="7%"> <%=Utility.GLanguage("Hi???n th???",session,jedis) %> <input type="checkbox" name="cbAll_HienThi_" value="1" onclick="check_all(this.checked,'cbHienThi')"></TH>
														<TH width="7%"><%=Utility.GLanguage("T???o m???i",session,jedis) %> <input type="checkbox" name="cbAll_Them" value="1" onclick="check_all(this.checked,'cbThem')"></TH>
														<TH width="7%"> <%=Utility.GLanguage("X??a",session,jedis) %><input type="checkbox" name="cbAll_Xoa" value="1" onclick="check_all(this.checked,'cbXoa')"></TH>
														<TH width="7%"><%=Utility.GLanguage("S???a",session,jedis) %> <input type="checkbox" name="cbAll_Sua" value="1" onclick="check_all(this.checked,'cbSua')"></TH>
														<TH width="7%"><%=Utility.GLanguage("Xem",session,jedis) %> <input type="checkbox" name="cbAll_Xem" value="1" onclick="check_all(this.checked,'cbXem')"></TH>
														<TH width="7%"><%=Utility.GLanguage("Ch???t",session,jedis) %> <input type="checkbox" name="cbAll_Chot" value="1" onclick="check_all(this.checked,'cbChot')"></TH>
														<TH width="7%"> <%=Utility.GLanguage("H???y Ch???t",session,jedis) %><input type="checkbox" name="cbAll_HuyChot" value="1" onclick="check_all(this.checked,'cbHuyChot')"></TH>

													</TR>
													<%
															while (ungdungRs.next())
															{
																String str=ungdungRs.getString("DanhMuc_FK")+"_"+ungdungRs.getString("pk_seq");
													%>
															<TR id="dong_<%=ungdungRs.getString("DanhMuc_FK")%>">
																<TD onclick="CheckAllDanhMuc(<%=ungdungRs.getString("DanhMuc_FK")%>)"><%=ungdungRs.getString("TENDANHMUC")%></TD>
																<TD align="left" onclick="CheckAllUngDung('<%=str%>');">
																	<input name="ungdungId" type="hidden"  value ="<%=ungdungRs.getString("pk_seq")%>"><%=ungdungRs.getString("Ten")%>
																</TD>
																
														<TD align="center">
														<%if(obj.getCbHienThi().indexOf(ungdungRs.getString("pk_seq") )>=0  ){ %>
																<input type="checkbox"   id="ch_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"   name="cbHienThi" value="<%=ungdungRs.getString("pk_seq")%>" checked="checked"  >
														<%}else { %>
																<input type="checkbox"   id="ch_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"   name="cbHienThi" value="<%=ungdungRs.getString("pk_seq")%>"   >
														<%} %>
														</TD>
														
											 			<TD align="center">
											 				<%if(obj.getCbThem().indexOf(ungdungRs.getString("pk_seq") )>=0  ){ %>
											 					<input type="checkbox"   id="chthem_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>" checked="checked"   name="cbThem" value="<%=ungdungRs.getString("pk_seq")%>">
											 				<%}else { %>
											 					<input type="checkbox"   id="chthem_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"   name="cbThem" value="<%=ungdungRs.getString("pk_seq")%>">
											 				<%} %>
											 			</TD>
											 			
														<TD align="center">
															<%if(obj.getCbXoa().indexOf(ungdungRs.getString("pk_seq") )>=0  ){ %>
																<input type="checkbox"   id="chxoa_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>" checked="checked"  name="cbXoa" value="<%=ungdungRs.getString("pk_seq")%>">
															<%}else { %>
																<input type="checkbox"   id="chxoa_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"  name="cbXoa" value="<%=ungdungRs.getString("pk_seq")%>">
															<%} %>
														</TD>
														<TD align="center">
															<%if(obj.getCbSua().indexOf(ungdungRs.getString("pk_seq") )>=0  ){ %>
																<input type="checkbox"   id="chsua_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"  name="cbSua" checked="checked" value="<%=ungdungRs.getString("pk_seq")%>">
																<%}else { %>
																<input type="checkbox"   id="chsua_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"  name="cbSua" value="<%=ungdungRs.getString("pk_seq")%>">
																<%} %>
														</TD>
														<TD align="center">
															<%if(obj.getCbXem().indexOf(ungdungRs.getString("pk_seq") )>=0  ){ %>
															<input type="checkbox"   id="chxem_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>" name="cbXem" checked="checked" value="<%=ungdungRs.getString("pk_seq")%>" >
															<%}else { %>
																<input type="checkbox"   id="chxem_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>" name="cbXem" value="<%=ungdungRs.getString("pk_seq")%>">
															<%} %>
														</TD>
														
														<TD align="center">
															<%if(obj.getCbChot().indexOf(ungdungRs.getString("pk_seq") )>=0  ){ %>	
																<input type="checkbox"   id="chchot_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"  name="cbChot" checked="checked" value="<%=ungdungRs.getString("pk_seq")%>">
															<%}else { %>
																	<input type="checkbox"   id="chchot_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"  name="cbChot" value="<%=ungdungRs.getString("pk_seq")%>">
															<%} %>
															</TD>
														<TD align="center">
															<%
															if(obj.getCbHuyChot().indexOf(ungdungRs.getString("pk_seq") )  >=0  ){ %>
																<input type="checkbox"   id="chhuychot_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"  name="cbHuyChot" checked="checked" value="<%=ungdungRs.getString("pk_seq")%>"  >
															<%}else { %>
																<input type="checkbox"   id="chhuychot_<%=ungdungRs.getString("DanhMuc_FK")%>_<%=ungdungRs.getString("pk_seq")%>"  name="cbHuyChot" value="<%=ungdungRs.getString("pk_seq")%>" >
															<%} %>
															</TD>
														<%
															}
														%>
													</TR>

													

												</TABLE>
											</TD>
										</TR>
									</TABLE>
								</FIELDSET>
					</TABLE>
				</TD>
			</TR>

		</TABLE><%geso.dms.center.util.Utility.JedisClose(jedis); %>
	</form>
</BODY>
</HTML>
<%
	}
%>

