<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="geso.dms.center.beans.phanbokhuyenmai.IPhanbokhuyenmai"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="geso.dms.center.util.*"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/AHF/index.jsp");
	}else{ %>
<% IPhanbokhuyenmai pbkmBean = (IPhanbokhuyenmai)session.getAttribute("pbkm"); %>
<% String schemeId = (String)session.getAttribute("schemeId"); %>
<% ResultSet scheme = (ResultSet)pbkmBean.getSchemeRS() ; %>
<% ResultSet schemeKogh = (ResultSet)pbkmBean.getSchemeKoGioiHanRs(); %>
<%
Hashtable<String, String> usedPro = (Hashtable<String, String>)pbkmBean.getusedPro(); 
ResultSet vungRs=pbkmBean.getVung();
ResultSet khuvucRs=pbkmBean.getKv();
ResultSet nppRs = pbkmBean.getNppRs();
ResultSet npp=pbkmBean.getPhanboRs();
NumberFormat formatter = new DecimalFormat("#,###,###");
int[] quyen = new  int[6];
quyen = util.Getquyen("PhanbokhuyenmaiSvl","", userId);
String view = pbkmBean.getView();
String url = util.getChuyenNguUrl("PhanbokhuyenmaiSvl", "&view="+view, session);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" language="JavaScript"
	src="../scripts/jquery.tools.min.js"></script>

<script type="text/javascript" src="../scripts/ajax.js"></script>
<LINK rel="stylesheet" type="text/css" media="screen"
	href="../css/tabs.css">
<LINK rel="stylesheet" type="text/css" media="screen"
	href="../css/tabs-panes.css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">

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
		document.forms[''].setAttribute('enctype', '', 0);
		var active =$(".tabs li.current").index();
		document.forms["pbkhForm"].activeTab.value =active;
	    document.forms['pbkhForm'].submit();
	}
	function dowload()
	{
		document.forms['pbkhForm'].action.value='xuatexcel';
		var active =$(".tabs li.current").index();
		document.forms["pbkhForm"].activeTab.value =active;
    document.forms['pbkhForm'].submit();
	}
	
	function search()
	{
		 document.forms['pbkhForm'].action.value='search';
			var active =$(".tabs li.current").index();
			document.forms["pbkhForm"].activeTab.value =active;
	    document.forms['pbkhForm'].submit();
	}
	function phanboCTKM_Ko_Gh()
	{
		 document.forms['pbkhForm'].action.value='phanbo';
			var active =$(".tabs li.current").index();
			document.forms["pbkhForm"].activeTab.value =active;
	    document.forms['pbkhForm'].submit();
	}
	
	function upload()
	{
		document.forms['pbkhForm'].setAttribute('enctype', "multipart/form-data", 0);
		var active =$(".tabs li.current").index();
		document.forms["pbkhForm"].activeTab.value =active;
	    document.forms['pbkhForm'].submit();
	}
	
	function sellectAll(cbId1,cbId2 )
	{
		 var chkAll_Lct = document.getElementById(cbId1);
		 var loaiCtIds = document.getElementsByName(cbId2);
		 

		 
		 if(chkAll_Lct.checked )
		 {
			 for(var i = 0; i < loaiCtIds.length; i++)
			 {
				/*  if(!loaiCtIds.item(i).disabled)
				{ */
					 loaiCtIds.item(i).checked = true;
				/*  }*/
			 }
		 }
		 else
		 {
			 for(var i = 0; i < loaiCtIds.length; i++)
			 {
				 loaiCtIds.item(i).checked = false;
			 }
		 }
	}
</SCRIPT>

<script>
	$(function() {
	 	$("ul.tabs").tabs("div.panes > div");
	});
	</script>


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
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="pbkhForm" method="post" action="../../PhanbokhuyenmaiSvl"> 
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="action" value="0"> 
		<input type="hidden" name="flag" value="<%= pbkmBean.getFlag() %>">
		<input type="hidden" id="activeTab" name="activeTab" value='<%=pbkmBean.getActiveTab()%>'>
		<input type="hidden" name="userId" value="<%=userId %>">
		<input type="hidden" name="view" value="<%=view %>">

		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<TR>
							<TD align="left" class="tbnavigation">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation"><%=url %></TD>
										<TD colspan="2" align="right" class="tbnavigation">Chào mừng <%= userTen %>&nbsp;
										</TD>
									</tr>
								</table>
							</TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<TR>
							<TD>
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<TR class="tbdarkrow">
										<TD>&nbsp;</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
								<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
								<textarea name="dataerror"
								style="width: 100%; color: #F00; font-weight: bold; width:100%" rows="1" readonly="readonly"><%= pbkmBean.getMessage() %></textarea>
								</FIELDSET>
							</TD>
						</tr>

						<TR>
							<TD height="100%" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle" style="color: black"><%=Utility.GLanguage("Phân bổ khuyến mãi",session,jedis) %> </LEGEND>
									<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
										<TR>
											<TD class="plainlabel" width="10%"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
											<TD colspan="3" class="plainlabel">
												<table border=0 cellpadding="0" cellspacing="0">
													<TR>
														<TD><input class="days" type="text" name="tungay"
															id="tungay" style="width: 100%"
															value="<%= pbkmBean.getTungay() %>"></TD>
													</TR>
												</table>
										</TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
											<TD colspan="3" class="plainlabel">
												<table border=0 cellpadding="0" cellspacing="0">
													<TR>
														<TD><input class="days" type="text" name="denngay"
															id="denngay" style="width: 100%"
															value="<%= pbkmBean.getDenngay() %>"></TD>
													</TR>
												</table>
										</TR>
										<TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Vùng",session,jedis) %></TD>
											<TD class="plainlabel"><select name="vungId"
												onchange="search();" id="vungId" style="width: 350px;">
													<option value="" selected>All</option>
													<%if (vungRs != null)
											while (vungRs.next()) {
												if (vungRs.getString("vungId").equals(pbkmBean.getVungId())) {%>
													<option value="<%=vungRs.getString("vungId")%>" selected><%=vungRs.getString("vungTen")%></option>
													<%} else {%>
													<option value="<%=vungRs.getString("vungId")%>"><%=vungRs.getString("vungTen")%></option>
													<%}}%>
											</select></TD>
										</TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %></TD>
											<TD class="plainlabel"><select name="khuvucId"
												onchange="search();" id="khuvucId" style="width: 350px;">
													<option value="" selected>All</option>
													<%if (khuvucRs != null)
											while (khuvucRs.next()) {
												if (khuvucRs.getString("khuvucId").equals(pbkmBean.getKvId()  )) {%>
													<option value="<%=khuvucRs.getString("khuvucId")%>"
														selected><%=khuvucRs.getString("khuvucTen")%></option>
													<%} else {%>
													<option value="<%=khuvucRs.getString("khuvucId")%>"><%=khuvucRs.getString("khuvucTen")%></option>
													<%}}%>
											</select></TD>
										</TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TD>
											<TD class="plainlabel">
												<select name="nppIdSearch" onchange="search();" id="nppIdSearch" style="width: 350px;">
													<option value="" selected>All</option>
													<%if (nppRs != null)
											while (nppRs.next()) {
												////System.out.println("npp "+ pbkmBean.getNppIdSearch());
												if (nppRs.getString("PK_SEQ").equals(pbkmBean.getNppIdSearch())) {%>
													<option value="<%=nppRs.getString("PK_SEQ")%>" selected><%=nppRs.getString("TEN")%></option>
													<%} else {%>
													<option value="<%=nppRs.getString("PK_SEQ")%>"><%=nppRs.getString("TEN")%></option>
													<%}}%>
											</select></TD>
										</TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Ngân sách",session,jedis) %></TD>
											<TD colspan="2" class="plainlabel"><SELECT
												name="loaingansach" id="loaingansach" class="legendtitle">
													<option value=""></option>
													<% 											 
										if(pbkmBean.getLoaingansach().equals("1")){ %>
													<option value='1' selected><%=Utility.GLanguage("Giới hạn",session,jedis) %></option>
													<%}else{ %>
													<option value='1'><%=Utility.GLanguage("Giới hạn",session,jedis) %></option>
													<%} %>
													<%		if(pbkmBean.getLoaingansach().equals("0")){ %>
													<option value='0' selected><%=Utility.GLanguage("Không giới hạn",session,jedis) %></option>
													<%}else{ %>
													<option value='0'><%=Utility.GLanguage("Không giới hạn",session,jedis) %></option>
													<%} %>
											</SELECT></TD>
										</TR>

										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Tình trạng",session,jedis) %></TD>
											<TD colspan="2" class="plainlabel"><SELECT name="phanbo"
												id="phanbo" class="legendtitle">
													<option value=""></option>
													<% 											 
										if(pbkmBean.getPhanbo().equals("1")){ %>
													<option value='1' selected><%=Utility.GLanguage("Đã phân bổ",session,jedis) %></option>
													<%}else{ %>
													<option value='1'><%=Utility.GLanguage("Đã phân bổ",session,jedis) %></option>
													<%} %>
													<%		if(pbkmBean.getPhanbo().equals("0")){ %>
													<option value='0' selected><%=Utility.GLanguage("Chưa phân bổ",session,jedis) %></option>
													<%}else{ %>
													<option value='0'><%=Utility.GLanguage("Chưa phân bổ",session,jedis) %></option>
													<%} %>
											</SELECT></TD>
										</TR>


										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Chọn tập tin",session,jedis) %></TD>
											<TD class="plainlabel"><INPUT type="file"
												name="filename" size="40" value=''>&nbsp;&nbsp;&nbsp;&nbsp;
												<a class="button2" href="javascript:upload();"><img
													style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Upload",session,jedis) %></a>
											</TD>
										</TR>
										
										<TR>
											<TD class="plainlabel">Phân bổ số suất cho TDV</TD>
                    						<TD class="plainlabel" colspan="1" ><input type="checkbox" name="phanbotdv" id="phanbotdv" value = "1"></TD>
										</TR>
										
										<TR>
											<td class="plainlabel" colspan="2"><a class="button2"
												href="javascript:search();"> <img style="top: -4px;"
													src="../images/button.png" alt="">1.<%=Utility.GLanguage("Lọc chương trình khuyến mãi",session,jedis) %>
											</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="button2"
												href="javascript:search();"><img style="top: -4px;"
													src="../images/button.png" alt="">2.<%=Utility.GLanguage("Thêm nhà phân phối",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
													 <a class="button2"
												href="javascript:phanboCTKM_Ko_Gh();"><img
													style="top: -4px;" src="../images/button.png" alt="">3.<%=Utility.GLanguage("Lưu phân bổ chương trình khuyến mãi",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp; 
												
											 <a class="button2"
												href="javascript:dowload();"><img
													style="top: -4px;" src="../images/button.png" alt="">4.<%=Utility.GLanguage("Download phân bổ chương trình khuyến mãi",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp; 	
													
											</TD>
										</TR>
										<TR>
											<td class="plainlabel" colspan="2">&nbsp;</td>
										</TR>
									</TABLE>
								</FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<ul class="tabs">
						<li <%=pbkmBean.getActiveTab().equals("0") ? "class='current'" : ""%>><a href="#tab1"><%=Utility.GLanguage("Chương trình khuyến mãi",session,jedis) %></a></li>
						<li <%=pbkmBean.getActiveTab().equals("1") ? "class='current'" : "" %>><a href="#tab2"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </a></li>
					</ul>

					<div class="panes">
						<div id="tab1" class="tab_content">

							<TABLE class="tabledetail" width="100%" border="0"
								cellspacing="1px" cellpadding="5px">
								<TR class="tbheader">
									<TH align="center" width="10%"><%=Utility.GLanguage("Scheme",session,jedis) %></TH>
									<TH align="left" width="30%"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TH>
									<TH align="left" width="10%"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TH>
									<TH align="left" width="10%"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TH>
									<TH align="center" width="15%"><%=Utility.GLanguage("Ngân sách",session,jedis) %></TH>
									<TH align="center" width="10%"><%=Utility.GLanguage("Chọn tất cả",session,jedis) %> <input
										type="checkbox" id="cbCtkmId" name="all"
										onclick="sellectAll('cbCtkmId','ctkmId')"></TH>
								</TR>
								<%      int k=0;
                    if(scheme != null)
                    {
                    	//System.out.println(":::: SCHEME KHAC NULL..........");
                    	while(scheme.next())
                    	{
                    		String color="style=\"line-height:30px\"";
                    		if(scheme.getString("DaPhanBo").equals("1"))
                    			color="style=\"line-height:30px;color:red\"";
                    		
                    			if(k % 2 == 0){
                        			%>
								<TR class='tbdarkrow' <%=color %>>
									<%}else{ %>
								
								<TR class='tblightrow' <%=color %>>
									<% } %>
									<TD align="left"><%=scheme.getString("SCHEME")%></TD>
									<TD align="left"><%=scheme.getString("DIENGIAI")%></TD>
									<TD align="left"><%=scheme.getString("TUNGAY")%></TD>
									<TD align="left"><%=scheme.getString("DENNGAY")%></TD>
									<TD align="left">
									<%if(scheme.getString("loaingansach").equals("0")){ %> <%=Utility.GLanguage("Không giới hạn",session,jedis) %> <%}else  {%> <%=Utility.GLanguage("Giới hạn",session,jedis) %> <%} %>
									</TD>
									<TD align="center">
										<%if(  pbkmBean.getSchemeId().indexOf(scheme.getString("pk_seq") )>=0  ){ %>
										<input type="checkbox" name="ctkmId"
										value="<%=scheme.getString("pk_seq")%>" checked="checked">
										<%}else { %> <input type="checkbox" name="ctkmId"
										value="<%=scheme.getString("pk_seq")%>"> <% 	} %>
									</TD>
								</TR>
								<%	k++;
                    	} }%>

							</TABLE>

						</div>


						<div id="tab2" class="tab_content">
							<TABLE border="0" width="100%" cellpadding="6" cellspacing="1">
								<TR class="tbheader">
									<TH width="20%"><%=Utility.GLanguage("Scheme",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("Mã nhà phân phối",session,jedis) %></TH>
									<TH width="20%"><%=Utility.GLanguage("Tên nhà phân phối",session,jedis) %></TH>
									<TH width="20%"><%=Utility.GLanguage("Khách hàng",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("Ngân sách",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("Đã sử dụng",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("Còn lại",session,jedis) %></TH>

									<% try{
							    String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								int m = 0;
								if(npp != null){
									while(npp.next()){ 
									if (m % 2 != 0) {%>
								
								<TR class=<%=lightrow%>>
									<%} else {%>
								
								<TR class=<%= darkrow%>>
									<%}%>
									<TD align="left"><input type="text" readonly="readonly"
										style="width: 100%"
										value="<%= npp.getString("SCHEME")%>__<%= npp.getString("DIENGIAI")%>">
										<input type="hidden" name="ctkmId.pb" readonly="readonly"
										style="width: 100%" value="<%= npp.getString("ctkm_fk")%>">
									</TD>
									<TD align="left"><input type="text" readonly="readonly"
										style="width: 100%" value="<%= npp.getString("NPPma")%>">
										<input type="hidden" name="nppId" readonly="readonly"
										style="width: 100%" value="<%= npp.getString("nppId")%>">
										<input type="hidden" name="loaingansach.pb"
										readonly="readonly" style="width: 100%"
										value="<%= npp.getString("loaingansach")%>"></TD>
									<TD align="left"><input type="text" readonly="readonly"
										style="width: 100%" value="<%=npp.getString("NPPten") %>">
									</TD>
									<TD align="left"><input type="text" readonly="readonly"
										style="width: 100%" value="<%=npp.getString("KHten") %>">
									</TD>
									<TD align="left"><input type="text" name="ngansach"
										<%=(npp.getInt("loaingansach")==0?"readonly=\"readonly\"":"") %>
										style="width: 100%"
										value="<%= npp.getDouble("ngansach")==99999999999.0 ? "Không giới hạn" : formatter.format(npp.getDouble("ngansach") ) %>">
									</TD>
									<TD align="left"><input type="text" readonly="readonly"
										style="width: 100%"
										value="<%= formatter.format(npp.getDouble("dasudung") )%>">
									</TD>
									<TD align="left"><input type="text" readonly="readonly"
										style="width: 100%"
										value="<%=formatter.format(npp.getDouble("ngansach")-npp.getDouble("dasudung")) %>">
									</TD>
								</TR>

								<% m++ ;} 
						  		
						  		}%>

								<%}catch(Exception e){e.printStackTrace();}%>
							</TABLE>
						</div>
					</div>
				</TD>
			</TR>
		</TABLE><%geso.dms.center.util.Utility.JedisClose(jedis); %>
	</form>
</body> 
</HTML>

<%
	if(scheme!= null){ scheme.close(); scheme = null; }
	if(schemeKogh!= null){ schemeKogh.close(); schemeKogh = null; }
	if(usedPro!= null){ usedPro.clear(); usedPro = null; }
	if(vungRs!= null){ vungRs.close(); vungRs = null; }
	if(khuvucRs!= null){ khuvucRs.close(); khuvucRs = null; }
	if(nppRs!= null){ nppRs.close(); nppRs = null; }
	if(npp!= null){ npp.close(); npp = null; }

	pbkmBean.DBClose(); pbkmBean=null;	
	session.setAttribute("pbkm",null); 
}%>
