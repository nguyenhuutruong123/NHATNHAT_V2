<% String nnId = (String)session.getAttribute("nnId"); %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.kehoachnhanvien.*" %>
<%@ page  import = "geso.dms.center.beans.kehoachnhanvien.imp.*" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.Date" %>
<%@ page  import = "java.text.DateFormat" %>
<%@ page  import = "java.text.SimpleDateFormat" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% 
IKeHoachNhanVien khnvBean = (IKeHoachNhanVien)session.getAttribute("khnvBean"); 
ResultSet nppRs = khnvBean.getNppRs();
ResultSet tinhRs = khnvBean.getTinhRs();
ResultSet quanRs = khnvBean.getQuanRs();
ResultSet nppTbhs = khnvBean.getNppRs();
ResultSet ddkdRs = khnvBean.getDDKDRs();
ResultSet tbhRs = khnvBean.getTBHRs();
String url = util.getChuyenNguUrl("KeHoachNhanVienSvl", "",session);
%>
<% Utility Util1 = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>

<SCRIPT type="text/javascript" src="../scripts/kehoachnhanvien.js"></SCRIPT>
<SCRIPT language="javascript" type="text/javascript">
	//Ch???a danh s??ch chi ti???t l???y t??? database ????? v??? l??n view
	var nppCts = []; 
	var ttCts = [];
	var tbhCts = [];

	//Th??m danh s??ch nh?? ph??n ph???i
	var nppArr = [];
	var ddkdArr = [];
	var tinhArr = [];
	var quanArr = [];
	var tbhArr = [];

	//Th??m danh s??ch nh?? ph??n ph???i
	<%	if(nppRs != null) {
			try {
				while(nppRs.next()) { %>
					nppArr.push(new NhaPhanPhoi('<%= nppRs.getString("PK_SEQ") %>', '<%= nppRs.getString("TEN") %>'));
			<%  }
				nppRs.close();
			} catch(Exception e) { }
		}
	%>
	
	//Th??m danh s??ch t???nh th??nh
	<%	if(tinhRs != null) {
			try {
				while(tinhRs.next()) { %>
					tinhArr.push(new TinhThanh('<%= tinhRs.getString("PK_SEQ") %>', '<%= tinhRs.getString("TEN") %>'));
			<%  }
				tinhRs.close();
			} catch(Exception e) { }
		}
	%>
	
	//Th??m danh s??ch qu???n huy???n
	<% 
		if(quanRs != null) {
			try {
				while(quanRs.next()) { %>
					quanArr.push(new QuanHuyen('<%= quanRs.getString("TINHTHANH_FK") %>', '<%= quanRs.getString("PK_SEQ") %>', '<%= quanRs.getString("TEN") %>'));
			<%	}
				quanRs.close();
			} catch(Exception e) { }
		}
	%>
	<% 
		if(ddkdRs != null) {
			try {
				while(ddkdRs.next()) { %>
					ddkdArr.push(new DaidienKinhdoanh('<%= ddkdRs.getString("NPP_FK") %>', '<%= ddkdRs.getString("PK_SEQ") %>', '<%= ddkdRs.getString("TEN") %>'));
			<%	}
				ddkdRs.close();
			} catch(Exception e) { }
		}
	%>
	<% 
		if(tbhRs != null) {
			try {
				while(tbhRs.next()) { %>
					tbhArr.push(new Tuyenbanhang('<%= tbhRs.getString("DDKD_FK") %>','<%= tbhRs.getString("NPP_FK") %>', '<%= tbhRs.getString("PK_SEQ") %>', '<%= tbhRs.getString("DIENGIAI") %>'));
			<%	}
				tbhRs.close();
			} catch(Exception e) { }
		}
	%>
	//????a c??c qu???n huy???n v??o t???nh th??nh
	var _tinh, _quan;
	for(var i = 0; i < tinhArr.length; i++) {
		_tinh = tinhArr[i];
		for(var j = 0; j < quanArr.length; j++) {
			_quan = quanArr[j];
			if(_quan.tinhId == _tinh.id) {
				_tinh.quans.push(_quan);
			}
		}
	}
	
	//Dua ddkd vao nha phan phoi
	var _npp, _ddkd;
	for(var i = 0; i < nppArr.length; i++) {
		_npp = nppArr[i];
		for(var j = 0; j < ddkdArr.length; j++) {
			_ddkd = ddkdArr[j];
			if(_ddkd.nppId == _npp.id) {
				_npp.ddkds.push(_ddkd);
			}
		}
	}
	//Dua tuyen ban hang vao ddkd
	var _tbh, __ddkd;
	for(var i = 0; i < ddkdArr.length; i++) {
		__ddkd = ddkdArr[i];
		for(var j = 0; j < tbhArr.length; j++) {
			_tbh = tbhArr[j];
			if(_tbh.ddkdId == __ddkd.id) {
				__ddkd.tbhs.push(_tbh);
			}
		}
	}
	
	function getNppSelect(selectedId, ngay) {		
		var html = '<option value=""></option>', qh;
		for(var i = 0; i < nppArr.length; i++) {
			npp = nppArr[i];
			if(npp.id == selectedId) {
				html+= '<option value="'+npp.id+'" selected>'+npp.ten+'</option>';
			} else {
				html+= '<option value="'+npp.id+'">'+npp.ten+'</option>';
			}
		}
		return "<select style='width: 300px;' ngay='"+ngay+"' loai='npp' name='ngay"+ngay+"_npp'>" + html + "</select>";
	}
	
	function getNppTBHSelect(selectedId, ngay) {		
		var html = '<option value=""></option>', npp;
		for(var i = 0; i < nppArr.length; i++) {
			npp = nppArr[i];
			if(npp.id == selectedId) {
				html+= '<option value="'+npp.id+'" selected>'+npp.ten+'</option>';
			} else {
				html+= '<option value="'+npp.id+'">'+npp.ten+'</option>';
			}
		}
		return "<select style='width: 300px;' ngay='"+ngay+"' loai='npp' name='ngay"+ngay+"__npp'  onchange='loadDaidienkinhdoanh(this, \"\")'>" + html + "</select>";
	}
	function getTBHSelect(ddkdId,nppId, tbhId, ngay){
		
		return "<select style='width: 300px;' ngay='"+ngay+"' loai='tbh' name='ngay"+ngay+"_tbh' >" + getTBHOptions(ddkdId,nppId, tbhId, ngay) + "</select>";
	
	}
	
	function getTinhThanhOptions(selectedId, ngay) {
		var html = '<option value=""></option>', qh;
		for(var i = 0; i < tinhArr.length; i++) {
			tinhthanh = tinhArr[i];
			if(tinhthanh.id == selectedId) {
				html+= '<option value="'+tinhthanh.id+'" selected>'+tinhthanh.ten+'</option>';
			} else {
				html+= '<option value="'+tinhthanh.id+'">'+tinhthanh.ten+'</option>';
			}
		}
		return html;
	}
	
	function getTinhThanhSelect(selectedId, ngay) {		
		return "<select style='width: 300px;' ngay='"+ngay+"' loai='tinhthanh' name='ngay"+ngay+"_tinhthanh' onchange='loadQuanHuyen(this, \"\")'>" + getTinhThanhOptions(selectedId, ngay) + "</select>";
	}
	
	function getQuanHuyenOptions(tinhthanhId, selectedId, ngay) {
		//T???o danh s??ch qu???n huy???n theo t???nh th??nh
		var html = '<option value=""></option>', qh, tinh;
		for(var i = 0; i < tinhArr.length; i++) {
			tinh = tinhArr[i];
			if(tinh.id == tinhthanhId) {
				for(var j = 0; j < tinh.quans.length; j++) {
					qh = tinh.quans[j];
					if(qh.id == selectedId) {
						html += '<option value="'+qh.id+'" selected>'+qh.ten+'</option>';
					} else {
						html += '<option value="'+qh.id+'">'+qh.ten+'</option>';
					}
				}
				break;
			}
		}
		return html;
	}
	 
	function getQuanHuyenSelect(tinhthanhId, selectedId, ngay) {
		return "<select style='width: 300px' ngay='"+ngay+"' loai='quanhuyen' name='ngay"+ngay+"_quanhuyen'>" + getQuanHuyenOptions(tinhthanhId, selectedId, ngay) + "</select>";
	}
	
	function getDDKDSelect(nppId, ddkdId, ngay){
		return "<select style='width: 300px' ngay='"+ngay+"' loai='ddkd' npp"+ngay+"='"+nppId+"' name='ngay"+ngay+"_ddkd'  onchange='loadTuyenbanhang(this, \"\")'>" + getDDKDOptions(nppId, ddkdId, ngay) + "</select>";
	}
	
	function getDDKDOptions(nppId, ddkdId, ngay) {
		//alert(' getDDKDOptions , nppI= '+ nppId);
		var html = '<option value=""></option>', ddkd, npp;
		for(var i = 0; i < nppArr.length; i++) {
			npp = nppArr[i];
			if(npp.id == nppId) {
				for(var j = 0; j < npp.ddkds.length; j++) {
					ddkd = npp.ddkds[j];
					if(ddkd.id == ddkdId) {
						html += '<option value="'+ddkd.id+'" selected>'+ddkd.ten+'</option>';
					} else {
						html += '<option value="'+ddkd.id+'">'+ddkd.ten+'</option>';
					}
				}
				break;
			}
		}
		return html;
	}

	function getTBHOptions(ddkdId,nppId, tbhId, ngay) {
		//alert('getTBHOptions,nppId = '+ nppId);
		var html = '<option value=""></option>', ddkd, tbh;
		for(var i = 0; i < ddkdArr.length; i++) {
			ddkd = ddkdArr[i];
			if(ddkd.id == ddkdId && ddkd.nppId == nppId ) {
				for(var j = 0; j < ddkd.tbhs.length; j++) {
					tbh = ddkd.tbhs[j];
					if(tbh.id == tbhId) {
						html += '<option value="'+tbh.id+'" selected>'+tbh.ten+'</option>';
					} else {
						html += '<option value="'+tbh.id+'">'+tbh.ten+'</option>';
					}
				}
				break;
			}
		}
		return html;
	}
	
	function loadQuanHuyen(tinhElement, selectedId) {
		//$("select").select2("destroy");
		var $tinh = $(tinhElement), tinhId = $tinh.val(), ngay = $tinh.attr("ngay");
		var $p = $tinh.parent();
		var $quan = $p.find("select[loai=quanhuyen]");
		var html = getQuanHuyenOptions(tinhId, selectedId, ngay);
		$quan.html(html);
		$("select").select2();
	}
	function loadDaidienkinhdoanh(nppElement, selectedId) {
		//$("select").select2("destroy");
		var $npp = $(nppElement), nppId = $npp.val(), ngay = $npp.attr("ngay");
		var $p = $npp.parent();
		var $ddkd = $p.find("select[loai=ddkd]");
		var html = getDDKDOptions(nppId, selectedId, ngay);
		$ddkd.attr("npp"+ngay,nppId);
		
		$ddkd.html(html);
		$("select").select2();
	}
	function loadTuyenbanhang(ddkdElement, selectedId) {
		//$("select").select2("destroy");
		var $ddkd = $(ddkdElement), ddkdId = $ddkd.val(), ngay = $ddkd.attr("ngay"),nppId =  $ddkd.attr("npp"+ngay)	;
	//	alert('loadTuyenbanhang nppId =  '+ nppId);
		
		var $p = $ddkd.parent();
		var $tbh = $p.find("select[loai=tbh]");
		var html = getTBHOptions(ddkdId,nppId, selectedId, ngay);
		
		$tbh.html(html);
		$("select").select2();
	}
	
	function xoaChiTiet(e) {
		e.parentNode.parentNode.removeChild(e.parentNode);
	}
	
	function addChiTiet(loai, ngay) {
		if(loai === "npp") {
			addNpp(ngay, "", "");
		} else if(loai === "thitruong") {
			addThiTruong(ngay, "", "", "");
		}
		else if(loai === "tbh"){
			addTuyenBanhang(ngay, "", "", "","","");
		}
		//$("select").select2("destroy");
		$("select").select2();
	}
	
	function addNpp(ngay, selectedId, ghichu, refresh) {
		$("#chitiet"+ngay).append("<p style='line-height:30px;'><span>NPP</span> " + getNppSelect(selectedId, ngay) + " <input type='text' name='ngay"+ngay+"_nppghichu' style='width: 30%; height: 29px; padding-left: 5px;' value='"+ghichu+"' placeholder='Nh???p ghi ch??'> <img src='../images/remove.png' title='X??a' width='30px' height='30px' align='top' onclick='xoaChiTiet(this);'/> </p>");
		if(refresh) {
			//$("select").select2("destroy");
			$("select").select2();
		}
	}
	
	function addThiTruong(ngay, tinhSelectedId, quanSelectedId, ghichu, refresh) {
		$("#chitiet"+ngay).append("<p style='line-height:30px;'>T???nh th??nh</span> " + getTinhThanhSelect(tinhSelectedId, ngay) + " <input type='text' name='ngay"+ngay+"_ttghichu' style='width: 30%; height: 29px; padding-left: 5px;'  value='"+ghichu+"' placeholder='Nh???p ghi ch??'> <img src='../images/remove.png' title='X??a' width='30px' height='30px' align='top' onclick='xoaChiTiet(this);'/>  <br/> Qu???n/Huy???n" + getQuanHuyenSelect(tinhSelectedId, quanSelectedId, ngay) + " <img src='../images/none.png' width='30px' height='30px' align='top'/> <img src='../images/none.png' width='30%' height='30px' align='top'/> </p>");
		if(refresh) {
			//$("select").select2("destroy");
			$("select").select2();
		}
	}
	function addTuyenBanhang(ngay, nppId, ddkdId, tbhId, ghichu, refresh){
		$("#chitiet"+ngay).append("<p style='line-height:30px;'><span>NPP</span> " + getNppTBHSelect(nppId, ngay) + "  ??DKD " + getDDKDSelect(nppId, ddkdId, ngay) + " <img src='../images/none.png' height='30px' align='top'/> <br/>"+
		"<span>TBH</span> " + getTBHSelect(ddkdId,nppId, tbhId, ngay) + "<input type='text' name='ngay"+ngay+"_tbhghichu' style='width: 30%; height: 29px; padding-left: 5px;'  value='"+ghichu+"' placeholder='Nh???p ghi ch??'> <img src='../images/remove.png' title='X??a' width='30px' height='30px' align='top' onclick='xoaChiTiet(this);'/></p>");
		if(refresh) {
			//$("select").select2("destroy");
			$("select").select2();
		}
	}
	window.onload = function(e) {
		//Add nh?? ph??n ph???i, th??? tr?????ng
		var z;
		for(z = 0; z < nppCts.length; z++ ) {
			var o = nppCts[z];
			addNpp(o.ngay, o.nppId, o.ghichu);
		}
		for(z = 0; z < ttCts.length; z++ ) {
			var o = ttCts[z];
			addThiTruong(o.ngay, o.tinhId, o.quanId, o.ghichu);
		}
		for(z = 0; z < tbhCts.length; z++ ) {
			var o = tbhCts[z];
			addTuyenBanhang(o.ngay, o.nppId, o.ddkdId, o.tbhId, o.ghichu);
		}
		//$("select").select2("destroy");
		$("select").select2();
	};
	
</SCRIPT>


<SCRIPT language="javascript" type="text/javascript">
	var num = 0;

	function submitform()
	{
	    document.forms['khnvForm'].submit();
	}
	
	function saveform()
	{
		document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	document.forms['khnvForm'].action.value= 'save';
	    document.forms['khnvForm'].submit();
	}
	
	function thaydoiloai(e) {
		var $e = $(e), loai = $e.val(), $p = $e.parent().parent();
		$p.find("td[loai]").css("display", "none");
		$p.find("td[loai="+loai+"]").css("display", "");
	}
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name='khnvForm' method="post" action="../../KeHoachNhanVienUpdateSvl">

<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>


<input type="hidden" name="action" value='1'>
<INPUT name="userId" type="hidden" value='<%= userId %>' size="30">
<INPUT name="id" type="hidden" value='<%= khnvBean.getId() %>' size="30">

<INPUT name="songay" type="hidden" value='<%= khnvBean != null && khnvBean.getNgayList() != null ? khnvBean.getNgayList().length : "" %>' size="30">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<TABLE width="100%">
				<TR>
					<TD align="left" class="tbnavigation">

					   	<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">
							   			<%= url%> > <%=Utility.GLanguage("C???p nh???t",session,jedis) %></TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;  </TD> 
						    </tr>
						   	<tr>
						   		<TD align="left" height="5" colspan="4" class=""></td>
  							</tr>
						</TABLE>
					</TD>
				</TR>
			</TABLE>	
			<TABLE width="100%" border="0" cellpadding="3" cellspacing="0">
				<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../RouterSvl?task=<%= Util1.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KeHoachNhanVienSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Tr??? l???i"  title="Tr??? l???i" border="1" longdesc="Tr??? l???i" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
						    <TD width="30" align="left" >
						    	<A id="btnSave" href="javascript: saveform()" ><IMG src="../images/Save30.png" title="L??u l???i" alt="L??u l???i" border = "1"  style="border-style:outset"></A>
						    </TD>
							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
				</TD></TR>
			</TABLE>
				
			<TABLE width = 100% cellpadding = "3" cellspacing = "0" border = "0">
			  	<TR>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle">Th??ng b??o </LEGEND>
		    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width: 100%" readonly="readonly" rows="1" ><%= khnvBean.getMessage() %></textarea>
						</FIELDSET>
				   </TD>
				</TR>
					<tr>
					   <TD align="left" colspan="4" class="legendtitle">
							<FIELDSET>
							<LEGEND class="legendtitle"><%=Utility.GLanguage("K??? ho???ch nh??n vi??n",session,jedis) %>
							</LEGEND>
							<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
								<TR>
								  <TD width="24%" class="plainlabel" ><%=Utility.GLanguage("Nh??n vi??n",session,jedis) %></TD>
								  <TD width="70%" class="plainlabel" ><INPUT name="tennhanvien"
									type="text" value='<%= khnvBean.getTenNhanVien() %>' size="20" readonly></TD>
								</TR>
								<TR>
								  <TD width="24%" class="plainlabel" ><%=Utility.GLanguage("Th??ng",session,jedis) %><FONT class="erroralert">*</FONT></TD>
								  <TD width="70%" class="plainlabel" >
								  	<select name="thang" style="width: 200px;" onchange="submit()">
								  	<%  String[] thang = {"1","2","3","4","5","6","7","8","9","10","11","12"};
								  		for(int i = 0; i < thang.length; i++) {
								  			if (Integer.parseInt(khnvBean.getThang())== Integer.parseInt(thang[i])){%>
									  			<option value ="<%=thang[i] %>" selected><%=thang[i] %></option>
									<%} 	else {%>
												<option value ="<%=thang[i] %>"><%=thang[i] %></option>
									<%		} 
										}%>
									</select>
								  </TD>
							  </TR>
								<TR>
								  <TD class="plainlabel" ><%=Utility.GLanguage("N??m",session,jedis) %><FONT class="erroralert">*</FONT></TD>
								  <TD width="70%" class="plainlabel" >
								  	<select name="nam" style="width: 200px;" onchange="submit()">
								  	<%  
									  	DateFormat dateFormat = new SimpleDateFormat("yyyy");
								        Date date = new Date();
								        int year = Integer.parseInt(dateFormat.format(date));
								  		for(int i = year; i < year + 2; i++) {
								  			if (khnvBean.getNam().equals(String.valueOf(i))) { %>
									  			<option value ="<%=i %>" selected><%=i %></option>
									<%} 	else { %>
												<option value ="<%=i %>"><%=i %></option>
									<%		}
										}%>
									</select>
								  </TD>
							  	</TR>
							</TABLE>
							</FIELDSET>
						</TD>
					</TR>
					<TR>
						<TD align="left" colspan="4" class="legendtitle">
							<FIELDSET>
							<LEGEND class="legendtitle"><%=Utility.GLanguage("Chi ti???t",session,jedis) %>
							</LEGEND>
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH width="5%"><%=Utility.GLanguage("Ng??y",session,jedis) %></TH>
												<TH width="15%"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
											    <TH width="80%"><%=Utility.GLanguage("N???i dung",session,jedis) %></TH>
											</TR>
								<% 
									if(khnvBean.getNgayList() != null) 
									{
										%>
										<script>num = <%=khnvBean.getNgayList().length %>;</script>
										<%
										String lightrow = "tblightrow";
										String darkrow = "tbdarkrow";
										
										for(int m = 0; m < khnvBean.getNgayList().length; m++) {
											IKeHoachNhanVienNgay khNgay = khnvBean.getNgayList()[m];
											if (m % 2 != 0) {%>						
												<TR class= <%=lightrow%> ngay="<%=khNgay.getNgay() %>" >
											<%} else {%>
												<TR class= <%=darkrow%> ngay="<%=khNgay.getNgay() %>" >
											<%}%>
													<TD align="center">
														<input type="hidden" name="ngay" value='<%=khNgay.getNgay() %>'>
														<b><%=khNgay.getNgay() %></b>
													</TD>
													<TD align="left" valign="top" style="line-height: 16px;">
														<A href="javascript: addChiTiet('npp', <%=khNgay.getNgay() %>)"><IMG src="../images/plus32.png" width="16" height="16" title="NPP" alt="NPP"> <b><%=Utility.GLanguage("??i Nh?? ph??n ph???i",session,jedis) %></b></A><br/> 
														<A href="javascript: addChiTiet('thitruong', <%=khNgay.getNgay() %>)"><IMG src="../images/plus32.png" width="16" height="16" title="Th??? Tr?????ng" alt="Th??? Tr?????ng"> <b><%=Utility.GLanguage("??i th??? tr?????ng",session,jedis) %></b></A><br/>
														<A href="javascript: addChiTiet('tbh', <%=khNgay.getNgay() %>)"><IMG src="../images/plus32.png" width="16" height="16" title="Tuy???n b??n h??ng" alt="Tuy???n b??n h??ng"> <b><%=Utility.GLanguage("??i tuy???n b??n h??ng",session,jedis) %></b></A>
													</TD>
													<TD align="right" id="chitiet<%=khNgay.getNgay() %>">
													<%
													IKeHoachNhanVienChiTiet chitiet;
													List<IKeHoachNhanVienChiTiet> nppList = khNgay.getNppList();
														
													for(int j = 0; j < nppList.size(); j++) {
														chitiet = nppList.get(j);
														%><script>nppCts.push({nppId: '<%=chitiet.getNppId()%>', ngay: '<%=khNgay.getNgay() %>', ghichu: '<%=chitiet.getGhiChu() %>'});</script>
													<%
													}
													nppList = khNgay.getThiTruongList();
														
													for(int j = 0; j < nppList.size(); j++) {
														chitiet = nppList.get(j);
														%><script>ttCts.push({tinhId: '<%=chitiet.getTinhId()%>', quanId: '<%=chitiet.getQuanHuyenId()%>', ngay: '<%=khNgay.getNgay() %>', ghichu: '<%=chitiet.getGhiChu() %>'});</script>
													<%
													}
													nppList = khNgay.getTBHList();
													for(int j = 0; j< nppList.size(); j++){
														chitiet = nppList.get(j);
														%><script>tbhCts.push({ngay:'<%=chitiet.getNgay()%>', nppId:'<%=chitiet.getNppTBHId()%>', ddkdId:'<%=chitiet.getDDKDId()%>', tbhId:'<%=chitiet.getTBHId()%>', ghichu:'<%=chitiet.getGhiChuTBH()%>'});</script>
													<%
													}
													%>
													</TD>
												</TR>
										<%
											}
										}
									%>
											<TR>
												<TD align="center" colspan="11" class="tbfooter">&nbsp;</TD>
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
<script>
	$("select").select2();
</script>
</HTML>

<% 
if(nppRs != null)
	nppRs.close();
if(tinhRs != null)
	tinhRs.close();
if(quanRs != null)
	quanRs.close();
if(ddkdRs != null)
	ddkdRs.close();
if(tbhRs != null)
	tbhRs.close();
khnvBean.closeDB(); %>
<%}
%>
