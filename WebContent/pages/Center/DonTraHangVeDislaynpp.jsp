<%@page import="geso.dms.center.beans.duyetdontrahangnpp.IDuyetdontrahangnpp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DateFormat" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.SimpleDateFormat" %>
<%
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		
		IDuyetdontrahangnpp dhtvBean = (IDuyetdontrahangnpp)session.getAttribute("dhtvBean"); 
		ResultSet sanpham = (ResultSet)dhtvBean.getsanpham();
		%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script language="javascript" type="text/javascript">
function replaces()
{
	//Sua TextBox Khach Hang
	var khTen = document.getElementsByName("khTen"); 
	var khId = document.getElementsByName("khId");
	var ckId = document.getElementsByName("ChietKhau");
	for(i=0; i < khTen.length; i++)
	{
		var tem = khTen.item(0).value;
		if(parseInt(tem.indexOf("-->[")) > 0)
		{
			khTen.item(0).value = tem.substring(0, parseInt(tem.indexOf("-->[")));
			tem = tem.substr(parseInt(tem.indexOf("-->[")) + 4);
			khId.item(0).value = tem.substring(0, parseInt(tem.indexOf("][")));
			tem = tem.substr(parseInt(tem.indexOf("][")) + 2);
			ckId.item(0).value = tem.substring(0, parseInt(tem.indexOf("]")));
			break;
		}
		if(tem == "")
		{
			khId.item(0).value = "";
			ckId.item(0).value = "0";
		}		
	}
	
	var masp = document.getElementsByName("masp");
	var tensp = document.getElementsByName("tensp");
	var donvitinh = document.getElementsByName("donvitinh");
	var dongia = document.getElementsByName("dongia");
	var chietkhau = document.getElementsByName("spchietkhau");
	var soluong = document.getElementsByName("soluong");
	var thanhtien = document.getElementsByName("thanhtien");
	var i;
	for(i=0; i < masp.length; i++)
	{
		if(masp.item(i).value != "")
		{
			var sp = masp.item(i).value;
			var pos = parseInt(sp.indexOf("-->"));
			//var pos_dvt = parseInt(sp.indexOf("["));
			//alert(pos);
			if(pos > 0)
			{
				masp.item(i).value = sp.substring(0, pos);
				sp = sp.substr(parseInt(sp.indexOf("-->")) + 3);
				tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf("[")));
				sp = sp.substr(parseInt(sp.indexOf("[")) + 1);
				donvitinh.item(i).value = sp.substring(0, parseInt(sp.indexOf("][")));
				sp = sp.substr(parseInt(sp.indexOf("][")) + 2);
				dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
			}
			if(soluong.item(i).value != "" && soluong.item(i).value != null)
			{
				var don_gia = dongia.item(i).value;
				don_gia = don_gia.replace(".", "");
				//tinh chiet khau theo san pham
				var tck = (parseFloat(ckId.item(0).value) * parseFloat(soluong.item(i).value) * parseFloat(don_gia)) / 100;
				chietkhau.item(i).value = tck; 
				var tt = parseFloat(soluong.item(i).value) * parseFloat(don_gia) - parseFloat(chietkhau.item(i).value);
				thanhtien.item(i).value = tt;
				TinhTien();
			}
			else
				thanhtien.item(i).value = "";
			
			if(checkMasp(masp.item(i).value) == true)
			{
				masp.item(i).parentNode.parentNode.bgColor = "#9FC";
			}
		}
		else
		{
			tensp.item(i).value = "";
			donvitinh.item(i).value = "";
			dongia.item(i).value = "";
			chietkhau.item(i).value = "";
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
			var thanh_tien = thanhtien.item(i).value.replace(".", "");
			tongtien = parseFloat(tongtien) +  parseFloat(thanh_tien);
		}
	}
	document.getElementById("SoTienChuaCK").value = tongtien;
	var ck = document.getElementById("ChietKhau").value;
	if(ck == "")
		ck = "0";
	var tienchietkhau = (tongtien * parseFloat(ck)) / 100;
	document.getElementById("TienChietKhau").value = tienchietkhau;
	var tienchuaVAT = tongtien - tienchietkhau;
	document.getElementById("SoTienChuaVAT").value = tienchuaVAT;
	var vat = document.getElementById("VAT").value;
	if(vat == "")
		vat = "10";
	document.getElementById("SoTienCoVAT").value = (parseFloat(vat) * tienchuaVAT) / 100 + tienchuaVAT;
}

function checkMasp(masanpham)
{
	var masp = document.getElementsByName("masp");
	for(i = 0; i < masp.length ; i++)
	{
		if(masp.item(i).value == masanpham)
			return true;
	}
	return false;
}

function confirmLogout()
 {
    if(confirm("B???n c?? mu???n ????ng xu???t?"))
    {
		top.location.href = "home.jsp";
    }
    return
 }

 function saveform()
 {	 
 	 document.forms['dhForm'].action.value='save';
     document.forms['dhForm'].submit();
 }

</script>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dhForm" method="post" action="../../DuyetdontrahangnppDislaySvl">
<input type="hidden" name="userId" value='<%=userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="chot_dh" value='false'>
<INPUT name="id" type="hidden" value=''>
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
			 								   <TD align="left"  class="tbnavigation">&nbsp;X??? l?? ????n h??ng> ????n h??ng tr??? v??? > Display </TD>								   
			 								   <TD align="right" class="tbnavigation">Ch??o m???ng   &nbsp;<%= userTen %> </TD>
					    				 </TR>
									  </TABLE>
								  </TD>
							  </TR>	
						  	</TABLE>
							<TABLE width="100%" border="0" cellpadding="1" cellspacing="0">
								<TR ><TD >
									<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
										<TR class = "tbdarkrow">
											<TD width="30" align="left"><A href = "../../DuyetdontrahangnppSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
										    <TD width="2" align="left" >&nbsp;</TD>
										   <TD width="30" align="left"><A href="Print.jsp" ><img src="../images/Printer30.png" alt="In" title="In chung tu" longdesc="In chung tu" border=1 style="border-style:outset"></A></TD>
								    		<TD align="left" >&nbsp;</TD>
										</TR>
									</TABLE>
								</TD></TR>
							</TABLE>												
							<TABLE border="0" width="100%" cellpadding = "0" cellspacing = "0">
								<TR>
									<TD  align="left">						
										<FIELDSET>
										<LEGEND class="legendtitle">&nbsp;????n h??ng b??n </LEGEND>
										<TABLE cellpadding = "6" cellspacing = "0" width = "100%" border = 0>
											<TR >
											  <TD class="plainlabel">Ng??y giao d???ch </TD>
											  <TD colspan="3" class="plainlabel">
											  <table border=0 cellpadding="0" cellspacing="0">
                                                <TR><TD>
											    <input type="text" name="tungay" size="20" value="<%= dhtvBean.getNgay() %>" >
											</TD></TR>
                                          </table>	</TR>
											<TR >
												<TD width="22%" class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %> </TD>
												<TD colspan="3" class="plainlabel"><%= dhtvBean.getnppId() %> </TD>
											</TR>							
											<TR class="tblightrow">
												<TD  class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %> </TD>
												<TD colspan="3" class="plainlabel"><%= dhtvBean.getddkdTen() %> </TD>
											<TR >
												<TD class="plainlabel">Kh??ch h??ng</TD>
												<TD colspan="3" class="plainlabel"><TABLE cellpadding="0" cellspacing="0">
                                                  <TR>
                                                    <TD>
                                                    	<input type="text" id="khachhang" name="khTen" size="30" value="<%=dhtvBean.getKahchhang() %>" disabled="disabled"/>
                                                    
                                                    </TD>
                                                    <TD>&nbsp;</TD>                                                   
                                                    <TD>&nbsp;</TD>
                                                                                                     
                                                  </TR>                                                 
                                                </TABLE></TD>
											</TR>
											  <TR class="tblightrow">
											  <TD  class="plainlabel">Tong so tien(Chua chiet khau)</TD>
											  <TD colspan="3" class="plainlabel"><input name="SoTienChuaCK" id="SoTienChuaCK" type="text" disabled="disabled" 
											  	  value="<%=dhtvBean.getTongtien() %>" >
											  	VND </TD>
										  </TR>
											  <TR class="tblightrow">
											    <TD  class="plainlabel">% Chiet khau </TD>
											    <TD width="9%"  class="plainlabel"><input name="ChietKhau" id="ChietKhau" type="text" value="<%=dhtvBean.getchietkhau()  %>" readonly="readonly"></TD>
									            <TD width="11%"  class="plainlabel"><div align="right">Chiet khau </div></TD>
									            <TD width="58%"  class="plainlabel"><input name="TienChietKhau" id="TienChietKhau" type="text" disabled="disabled" value=""> 
								                VND </TD>
										   </TR>
											  <TR class="tblightrow">
											    <TD  class="plainlabel">Tong so tien (truoc VAT) </TD>	     
										        <TD colspan="3"  class="plainlabel"><input name="SoTienChuaVAT" id="SoTienChuaVAT" type="text" disabled="disabled" 
										        	value="<%=dhtvBean.gettongbVat()%>"> 
										          VND </TD>
									       </TR>
										    <TR class="tblightrow">
											  <TD  class="plainlabel">VAT (%) </TD>
											  <TD colspan="3"  class="plainlabel"><input name="VAT" id="VAT" type="text" value="<%= dhtvBean.getvat()%>" disabled="disabled">%</TD>
										  </TR>
											<TR class="tblightrow">
											  <TD  class="plainlabel">Tong so tien (sau VAT)</TD>
											  <TD colspan="3"  class="plainlabel"><input name="SoTienCoVAT" id="SoTienCoVAT" type="text" disabled="disabled" 
											  	value="">
									          VND </TD>
										  </TR>

										</TABLE>
									</FIELDSET>
								  </TD>
							   </TR>	
							   <TR>
							   		<TD>
										<TABLE   width = 100%  border="0" cellpadding="0" cellspacing="1">
												<TR class="tbheader" >
													<TH width="15%" height="20">Ma san pham </TH>
													<TH width="28%">Ten san pham </TH>
													<TH width="5%">So luong </TH>
													<TH width="7%">DVT</TH>
													<TH width="10%">Don gia </TH>
													<TH width="5%">Chiet khau </TH>
													<TH width="10%">Thanh tien </TH>
												</TR>
									 <% while(sanpham.next()) { %>
									<TR class= 'tblightrow' >							
									<TD align="left" >
										<input name="masp" type="text" value="<%=sanpham.getString("spMa") %>" size="18" disabled="disabled">
									</TD>
									<TD align="left" >
										<input name="tensp" type="text" value ="<%=sanpham.getString("spTen") %>"></TD>
																							        	
						        	<TD align = "center" >
							        <input name="soluong" type="text" value="<%=sanpham.getString("soluong") %>" size="10" disabled="disabled">
							        </TD>
														    
								    <TD align = "center" ><input name="donvitinh" type="text" value="<%=sanpham.getString("donvi") %>" size="10" disabled="disabled"></TD>
								    <TD align = "center" >
								    		<input type="text" name="dongia" value="<%=sanpham.getString("dongia") %>" disabled="disabled" size="15">
								    </TD>
								    <TD align = "center" ><input name="spchietkhau" type="text" value="<%=sanpham.getString("chietkhau") %>" disabled="disabled" size="10"></TD>
								    <TD align = "center" ><input name="thanhtien" type="text" value="<%=sanpham.getString("thanhtien") %>" disabled="disabled" size="15"></TD>
								</TR>
								<% } %>																																																																																																											
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

