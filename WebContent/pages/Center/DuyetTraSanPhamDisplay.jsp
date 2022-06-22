<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.duyettrasanpham.*" %>
<%@ page  import = "geso.dms.distributor.beans.donhangtrave.ISanpham" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DateFormat" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.SimpleDateFormat" %>
<% IDuyettrasanpham dtspBean = (IDuyettrasanpham)session.getAttribute("dtspBean"); %>
<% List<ISanpham> splist = (List<ISanpham>)dtspBean.getSpList(); %>
<% ResultSet ddkd = (ResultSet)dtspBean.getDdkdList(); %>
<% ResultSet gsbh = (ResultSet)dtspBean.getGsbhList(); %>
<% ResultSet kho = (ResultSet)dtspBean.getKhoList(); %>

<% String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");	%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script language="javascript" type="text/javascript" >
function replaces()
{
		var masp = document.getElementsByName("masp");
		var tensp = document.getElementsByName("tensp");
		var donvitinh = document.getElementsByName("donvitinh");
		var dongia = document.getElementsByName("dongia");
		var soluong = document.getElementsByName("soluong");
		var thanhtien = document.getElementsByName("thanhtien");
		var i;
		for(i=0; i < masp.length; i++)
		{
			if(masp.item(i).value != "")
			{
				var sp = masp.item(i).value;
				var pos = parseInt(sp.indexOf(" - "));
				if(pos > 0)
				{
					masp.item(i).value = sp.substring(0, pos);
					sp = sp.substr(parseInt(sp.indexOf(" - ")) + 3);
					tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf(" [")));
					sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
					donvitinh.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
				}
				if(parseInt(soluong.item(i).value) > 0)
				{				
					var don_gia = dongia.item(i).value;
					var tt = parseFloat(soluong.item(i).value) * parseFloat(don_gia);
					thanhtien.item(i).value = tt;
					TinhTien();
				}
				else			
				{
					thanhtien.item(i).value = "";
				}
			}
			else
			{
				tensp.item(i).value = "";
				donvitinh.item(i).value = "";
				dongia.item(i).value = "";
				soluong.item(i).value = "";
				thanhtien.item(i).value = "";
	
				TinhTien();
			}		
		}	

	setTimeout(replaces, 20);
	}
	
	replaces();
	
	function TinhTien()
	{
		var thanhtien = document.getElementsByName("thanhtien");
		//var chietkhau = document.getElementsByName("ChietKhau");
		var tongtien = 0;
		for(var i=0; i < thanhtien.length; i++)
		{
			if(thanhtien.item(i).value != "")
			{
				//var thanh_tien = thanhtien.item(i).value.replace(".", "");
				var thanh_tien = thanhtien.item(i).value;
				tongtien = parseFloat(tongtien) +  parseFloat(thanh_tien);
			}
		}
		var tienchuaVAT = tongtien;
		document.getElementById("SoTienChuaVAT").value = tienchuaVAT;

		
	}
	
	function TongchietkhauKM()
	{
		var ckTrakm = document.getElementsByName("ckTrakm");
		var sum = 0;
		if(ckTrakm.length > 0)
		{
			for(h =0; h < ckTrakm.length; h++)
			{
				if(ckTrakm.item(h).value != "")
					sum = parseFloat(sum) + parseFloat(ckTrakm.item(h).value);
			}
		}
		return sum;
	}
	
</script>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dhtvForm" method="post" action="../../DuyettraspUpdateSvl">
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="id" value='<%= dtspBean.getId() %>'>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFEE"><!--begin body Dossier-->
				<TABLE border =0 width = "100%" cellpadding="2" cellspacing="0">
				<TBODY>
					<TR height="22">
						<TD align="left" >
							<TABLE width="100%" cellpadding="0" cellspacing="0">
								<TR>
									<TD align="left">
									   <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
										   <TR height="22">
			 								   <TD align="left"  class="tbnavigation">&nbsp;Quản lý bán hàng > Duyệt trả hàng > Duyệt trả sản phẩm > Hiển thị </TD>								   
			 								   <TD align="right" class="tbnavigation">Chào mừng  <%= userTen %>&nbsp; </TD>
					    				 </TR>
									  </TABLE>
								  </TD>
							  </TR>	
						  	</TABLE>
							<TABLE width="100%" border="0" cellpadding="1" cellspacing="0">
								<TR ><TD >
									<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
										<TR class = "tbdarkrow">
											<TD width="30" align="left"><A href = "../../DuyettrasanphamSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
										    <TD width="2" align="left" >&nbsp;</TD>			    		
										</TR>
									</TABLE>
								</TD></TR>
							</TABLE>												
							<TABLE border="0" width="100%" cellpadding = "0" cellspacing = "0">
								<tr>
								<TD align="left" colspan="4" class="legendtitle">
									<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>			
				    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width:100%" readonly="readonly" rows="1"><%= dtspBean.getMessage() %></textarea>
									</FIELDSET>
							   </TD>
								</tr>
								<TR>
									<TD  align="left">						
										<FIELDSET>
										<LEGEND class="legendtitle">&nbsp;Đơn hàng trả về </LEGEND>
										<TABLE cellpadding = "6" cellspacing = "0" width = "100%" border = 0>
											<TR >
											  <TD class="plainlabel">Ngày trả hàng </TD>
											  <TD colspan="3" class="plainlabel">
											  <table border=0 cellpadding="0" cellspacing="0">
                                                <TR><TD>
											    <input type="text" name="tungay" size="20" value="<%= dtspBean.getNgaygiaodich() %>" >
											</TD><TD>
												<a href="javascript: void(0);" onMouseOver="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onMouseOut="if (timeoutDelay) calendarTimeout();window.status='';" onClick="g_Calendar.show(event,'dhtvForm.tungay',false, 'yyyy-mm-dd'); return false;">
		  											&nbsp;<img src="../images/Calendar20.png" name="imgCalendar" border="0" alt=""></a>
											</TD></TR>
                                          </table>	</TR>
                                          <TR >
												<TD width="22%" class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %>  </TD>
												<TD colspan="3" class="plainlabel"><%= dtspBean.getNppTen() %> </TD>
										  </TR>	
                                          <TR class="tblightrow">
												<TD  class="plainlabel">ASM </TD>
												<TD colspan="3" class="plainlabel"> 
									 			<SELECT name="gsbhTen" id="gsbhTen" disabled="disabled">
										 			 <option value=""></option>
													  <% if(gsbh != null){
														  try{ while(gsbh.next()){ 													 
											    			if(gsbh.getString("pk_seq").equals(dtspBean.getGsbhId())){ %>
											      				<option value='<%=gsbh.getString("pk_seq")%>' selected><%= gsbh.getString("ten") %></option>
											      			<%}else{ %>
											     				<option value='<%=gsbh.getString("pk_seq")%>'><%= gsbh.getString("ten") %></option>
											     			<%}} gsbh.close(); }catch(java.sql.SQLException e){}} %>	 
                                    			</SELECT></TD>
											</TR>								
											<TR class="tblightrow">
												<TD  class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %> </TD>
												<TD colspan="3" class="plainlabel"> 
									 			<SELECT name="ddkdTen" id="ddkdTen" disabled="disabled">
										 			 <option value=""> </option>
													  <% if(ddkd != null){
														  try{ while(ddkd.next()){ 													 
											    			if(ddkd.getString("ddkdId").equals(dtspBean.getDdkdId())){ %>
											      				<option value='<%=ddkd.getString("ddkdId")%>' selected><%=ddkd.getString("ddkdTen") %></option>
											      			<%}else{ %>
											     				<option value='<%=ddkd.getString("ddkdId")%>'><%=ddkd.getString("ddkdTen") %></option>
											     			<%}} ddkd.close(); }catch(java.sql.SQLException e){}} %>	 
                                    			</SELECT></TD>
											</TR>
											<TR >
												<TD class="plainlabel">Mã / tên khách hàng</TD>
												<TD colspan="3" class="plainlabel"><TABLE cellpadding="0" cellspacing="0">
                                                  <TR>
                                                    <TD>
                                                    	<input type="text" id="smartId" name="smartId" value="<%= dtspBean.getSmartId() %>"  size="30" readonly="readonly"/>
                                                    	<input type="text" id="khTen" name="khTen" size="25" value="<%=dtspBean.getKhTen() %>" readonly/>                                                  	
                                                    </TD>
                                                    <TD>&nbsp;</TD>                                                   
                                                    <TD>&nbsp;</TD>
                                                                                                     
                                                  </TR>                                                 
                                                </TABLE></TD>
											</TR>
											
											<TR class="tblightrow">
												<TD  class="plainlabel">Kho nhận hàng </TD>
												<TD colspan="3" class="plainlabel"> 
									 			<SELECT name="khoTen" id="khoTen" disabled="disabled">
										 			 <option value="">&nbsp;</option>
													  <% if(kho != null){
														  try{ while(kho.next()){
											    			if(kho.getString("khoId").equals(dtspBean.getKhoId())){ %>
											      				<option value='<%= kho.getString("khoId") %>' selected onMouseover="ddrivetip('<%= kho.getString("diengiai") %>', 300)"; onMouseout="hideddrivetip()"><%= kho.getString("Ten") + " " %></option>
											      			<%}else{ %>
											     				<option value='<%= kho.getString("khoId") %>' onMouseover="ddrivetip('<%= kho.getString("diengiai") %>', 300)"; onMouseout="hideddrivetip()"><%= kho.getString("Ten") + " " %></option>
											     			<%}}}catch(java.sql.SQLException e){} }%>
                                    			</SELECT></TD>
											</TR>
											<TR class="tblightrow">
											    <TD  class="plainlabel">Tổng số tiền(VND) </TD>	     
										        <TD colspan="3"  class="plainlabel"><input name="SoTienChuaVAT" id="SoTienChuaVAT" type="text" value="<%=dtspBean.getTongtientruocVAT()%>" style="text-align: right;" readonly > VND </TD>
									       	</TR>

										  															  
										</TABLE>
										
									</FIELDSET>
								  </TD>
							   </TR>	
							   <TR>
							   		<TD>
										<TABLE width = "100%"  border="0" cellpadding="0" cellspacing="1">
										<tbody id="san_pham">
												<TR class="tbheader" >
													<TH width="15%" height="20">Mã sản phẩm </TH>
													<TH width="28%">Tên sản phẩm </TH>
													<TH width="5%">Số lượng </TH>
													<TH width="7%">DVT</TH>
													<TH width="10%">Đơn giá </TH>
													<TH width="10%">Thành tiền </TH>
												</TR>
								<% 
							if(splist != null){
							ISanpham sanpham = null;
							int size = splist.size();
							int m = 0;
							while (m < size){
								sanpham = splist.get(m); 
								 %>										
								    <TR class= 'tblightrow' >							
									<TD align="left" >
										<input name="masp" type="text"  value="<%=sanpham.getMasanpham()%>" style="width:100% ;text-align: left;" readonly="readonly">
									</TD>
									<TD align="left" >
										<input name="tensp" type="text" readonly value="<%=sanpham.getTensanpham()%>" style="width:100% ;text-align: left;"></TD>
																		
						        	<TD align = "center" >
							        <input name="soluong" type="text" value="<%= sanpham.getSoluong() %>" readonly="readonly" style="width:100%; text-align:left;">
							        </TD>
								
								    <TD align = "center" ><input name="donvitinh" type="text" value="<%= sanpham.getDonvitinh() %>" readonly style="width:100% ;text-align: left;"></TD>
								    <TD align = "center" >
								    	<input type="text" name="dongia" value="<%= sanpham.getDongia() %>" readonly style="width:100% ;text-align: left;">
								    </TD>
								    <TD align = "center" ><input name="thanhtien" type="text" value="" readonly style="width:100% ;text-align: left;"></TD>
								</TR>
								<% m++; }}%>
																																																																																																					
								</tbody>
								</TABLE>
								
								</TD>
							  </TR>	
							 		   
						  </TABLE>
						</TD>
					</TR>	
				</TBODY>
			</TABLE>
	</td>
  </tr>

</TABLE>
</form>
</BODY>
</HTML>
