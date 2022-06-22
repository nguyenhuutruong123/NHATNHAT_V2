<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.SQLException"%>

<%@ page import = "java.net.URLDecoder" %>
<%@ page import = "java.nio.charset.StandardCharsets" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>

<%@ page import = "com.google.gson.JsonArray" %>
<%@ page import = "com.google.gson.JsonObject" %>
<%@ page import = "com.google.gson.JsonParser" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<% 
	String data = request.getParameter("json_data"); 
	//System.out.println("data : "+ data);
	String dieuchinh = request.getParameter("json_dieuchinh"); 
	NumberFormat formater = new DecimalFormat("##,###,###");
%>

<% 
JsonObject jsonO = (JsonObject) new JsonParser().parse(data);
if( jsonO.get("data").getAsString().length() > 0 )
{%>
	<div id="myModal" class="modal" style="display : block; text-align:left;">
	<!-- Modal content -->
	<div class="modal-content">
	<h5>Di chuyển mã chương trình khuyến mãi (CTKM) bạn muốn ưu tiên
	<a href="javascript:myFunction()" class="w3-btn w3-white w3-border" title="Ẩn">Ẩn</a>
	</h5>

	<div id = "myDIV">
	<ul id="sortable" style="cursor:pointer; width:60%;">
    <%
  	JsonArray  jsonA = (JsonArray) new JsonParser().parse(jsonO.get("data").getAsString());
  	for( int i_listctkm = 0; i_listctkm < jsonA.size(); i_listctkm ++)
	{
  		 JsonObject jsonO_listCTKM = (JsonObject) jsonA.get(i_listctkm);
  		 String idCTKM = jsonO_listCTKM.get("id").getAsString();
  %>
	<li onMouseOver="this.style.color='#F00'" onMouseOut="this.style.color='#000'">
		<input type="hidden" name="sapxep" value="" style="width: 40px; margin : 3px;">
	<%=jsonO_listCTKM.get("scheme").getAsString() + " - " + jsonO_listCTKM.get("diengiai").getAsString()%> 
	<input type="hidden" name="Scheme" value="<%=jsonO_listCTKM.get("scheme").getAsString()%>">
	<input type="hidden" name="SchemeId" value="<%=jsonO_listCTKM.get("id").getAsString()%>">
	<input type="hidden" name="schemeOR" value="<%=jsonO_listCTKM.get("traOr").getAsString()%>">
	<input type="hidden" name="diengiaiid" value="<%=jsonO_listCTKM.get("diengiai").getAsString()%>">
	</br>
<%  String trakhuyenmai = jsonO_listCTKM.get("trakhuyenmai").getAsString(); 
	JsonArray jsonA_Trakm_listCTKM = (JsonArray) new JsonParser().parse(trakhuyenmai);   
    for( int j = 0; j < jsonA_Trakm_listCTKM.size(); j ++ )
    {
    	JsonObject jsonO_Trakm_listCTKM = (JsonObject) jsonA_Trakm_listCTKM.get(j);
    	String traOr = jsonO_listCTKM.get("traOr").getAsString();
    	if(traOr.equals("0")) { %>
    	<p style="font-style: italic; font-weight: normal; "><input style="display : none; type="checkbox" checked="checked"  name="<%=idCTKM%>.chontrakm" value="<%=jsonO_Trakm_listCTKM.get("id").getAsString()%>"><%= jsonO_Trakm_listCTKM.get("diengiai").getAsString()%></p>
    	<%} else { %>
    	<p style="font-style: italic; font-weight: normal;"><input type="radio" name="<%=idCTKM%>.chontrakm" value="<%=jsonO_Trakm_listCTKM.get("id").getAsString()%>"><%= jsonO_Trakm_listCTKM.get("diengiai").getAsString()%></p>
    	<%} %>
		<input type="hidden" name="<%=idCTKM%>.trakmid" value="<%=jsonO_Trakm_listCTKM.get("id").getAsString()%>">
<%  } %>		
		</li>
  <% } %>     	
  </ul>
  <%  if (dieuchinh.equals("0") && jsonO.get("dung_khuyen_mai").getAsString().equals("1")) 
  	{ %>
   		<h5><input type="checkbox" id="Show_All" name="ShowAll" checked="checked" value='1' onchange="dieuchinhKM()"> Hiện tất cả các suất khuyến mãi ( tối đa ) có thể đạt được của đơn hàng </h5>
  <%  } 
      else 
      { %> 
      	<h5><input type="checkbox" id="Show_All" name="ShowAll"  value='0' onchange="dieuchinhKM()"> Hiện tất cả các suất khuyến mãi ( tối đa ) có thể đạt được của đơn hàng </h5>
   <% } %>
   </div>
   <% if(dieuchinh.equals("1") || jsonO.get("dung_khuyen_mai").getAsString().equals("0")) { %>
		<div style="margin-bottom : 3px" id=btnSave2>
			<a href="javascript:savekhuyenmaiform()" onclick="disableClicks(this);" class="w3-btn w3-white w3-border" title="Ẩn">Lưu lại</a>
        </div>
<%} %>
	<!-- <TABLE class = "chitieutable"> -->
	<table class="table table-bordered" style="font-size: 12px">
	<TR>
    	<th width="5%" align="center">Mã CTKM</th>
        <th width="30%" align="center">Điều kiện mua</th>
        <th width="30%" align="center">Trả khuyến mãi</th>
        <th width="10%"  align="center">Tổng giá trị</th>
    </TR>
<%  
	
	
	for( int i = 0 ; i < jsonA.size() ; i++)
	{
		String maCTKM = "", spmua = "", idTRA = "", loaiTRA = "", ctkmTRA = "", 
		chietkhauTRA = "", hinhthucTRA = "", idspTRA = "", spTRA = "", dungCTKM = "", trakmstr = "";
		int sttspTRA = 0, dkkm_sp_size = 0;
		double sosuatCTKM = 0, sudung = 0, tongtienTRA = 0, tongluongTRA = 0, soluongTRA = 0;
		
		
  		JsonObject jsonO_data = (JsonObject) jsonA.get(i);
   		String idCTKM = jsonO_data.get("id").getAsString();
   		maCTKM = jsonO_data.get("scheme").getAsString();
   		sosuatCTKM =  jsonO_data.get("sosuat").getAsDouble();
 		String dieukienkhuyenmai = jsonO_data.get("dieukienkhuyenmai").getAsString();
  		JsonArray jsonA_dkkm = (JsonArray) new JsonParser().parse(dieukienkhuyenmai);
  		String dkkmstr = "<TABLE  style=\"font-size: 12px\" width='100%' class = \"table table-bordered\"><TH>Mã</TH><TH>Sử dụng</TH><TH>Số suất</TH>";
  		
  		String pheptoan ="AND";
  		for(int j_dkkm = 0; j_dkkm < jsonA_dkkm.size(); j_dkkm ++ )
	    {
  			
   			JsonObject dkkm_O = (JsonObject) jsonA_dkkm.get(j_dkkm);
   			double sosuatDKKM = dkkm_O.get("sosuat").getAsDouble();
   			JsonArray jsonA_spList = dkkm_O.get("spList").getAsJsonArray();
   			if(dkkm_O.get("pheptoan").getAsString().equals("2"))
   				pheptoan= "OR";
   		 	dkkmstr += "<TR> <TD colspan = '2'>"+dkkm_O.get("diengiai").getAsString()+"</TD><TD>"+sosuatDKKM+"</TD></TR>";
  			for(int j_spList = 0; j_spList < jsonA_spList.size(); j_spList ++ )
		    { 
	   			dkkmstr += "<TR>";
				JsonObject dkkm_spList_O = (JsonObject) jsonA_spList.get(j_spList);
				spmua = dkkm_spList_O.get("ma").getAsString() + " -- " + dkkm_spList_O.get("ten").getAsString();
				sudung = dkkm_spList_O.get("soluong").getAsDouble();
				dkkmstr += "<TD>"+spmua+"</TD>";
				dkkmstr += "<TD>"+sudung+"</TD>";
				dkkmstr += "<TD></TD>"; /* "+sosuatDKKM+" */
				dkkmstr += "</TR>";
		    } // for sp dkkm
	   } // for dkkm 
	   if(jsonA_dkkm.size() > 1)
	   {
		   dkkmstr += "<TR> <TD colspan = '2'>Tổng số suất ("+pheptoan+") :   </TD><TD>"+sosuatCTKM+"</TD> </TR>";
	   }
	   
	   dkkmstr += "</TABLE>";
	   
	   String trakhuyenmai = jsonO_data.get("trakhuyenmai").getAsString();
	   JsonArray jsonA_Trakm = (JsonArray) new JsonParser().parse(trakhuyenmai);
	   trakmstr = "<TABLE  style=\"font-size: 12px\" width='100%' class = \"table table-bordered\">";
	   for(int j = 0; j < jsonA_Trakm.size(); j ++ )
	   {
		   String  dgTRA = "";
		   JsonObject recTra = (JsonObject) jsonA_Trakm.get(j);
		   idTRA = recTra.get("id").getAsString();
		   dgTRA = recTra.get("diengiai").getAsString();
		   loaiTRA = recTra.get("loai").getAsString();
		   ctkmTRA = recTra.get("ctkmId").getAsString();
		   chietkhauTRA = recTra.get("chietkhau").getAsString();
		   tongtienTRA += recTra.get("tongtien").getAsDouble();
		   tongluongTRA = recTra.get("tongluong").getAsDouble();
		   hinhthucTRA = recTra.get("hinhthuc").getAsString();
		   if(loaiTRA.equals("1")) { dgTRA = "Trả tiền : "+ formater.format( recTra.get("tongtien").getAsDouble()) + " VNĐ"; }
		   else
		   if(loaiTRA.equals("2")) { dgTRA = "Tổng chiết khấu KM " + chietkhauTRA +": "+ formater.format( recTra.get("tongtien").getAsDouble()) + " VNĐ"; }
		   else if(loaiTRA.equals("3")) 
		   {
			   if(hinhthucTRA.equals("2")) { dgTRA = "Tổng lượng KM " + Math.round((tongluongTRA * sosuatCTKM) - 0.5); }
			   else if(hinhthucTRA.equals("1")) { dgTRA = "Trả sản phẩm cố định"; }
		   }
		   trakmstr += "<TR  align='center'><TD colspan = '2'>"+ dgTRA +"  </TD></TR>";
		   String sptra = recTra.get("spTra").getAsString();
		   if(sptra.length() > 0)
		   {
			   JsonArray jsonA_sptra = (JsonArray) new JsonParser().parse(sptra);   
			   for(int j_sptra = 0; j_sptra < jsonA_sptra.size(); j_sptra ++ )
			   {
				    trakmstr += "<TR>";
					JsonObject recspTra = (JsonObject) jsonA_sptra.get(j_sptra);
					spTRA = recspTra.get("MA").getAsString()+" -- "+recspTra.get("TEN").getAsString();
					idspTRA = recspTra.get("spId").getAsString();
					sttspTRA = j_sptra;
					soluongTRA = recspTra.get("SOLUONG").getAsDouble();
					if(loaiTRA.equals("3")) 
					{ 
						if(hinhthucTRA.equals("2")) 
						{ 
						  	if(sttspTRA == 0)
						  	{ trakmstr += "<td><input name=\""+ idCTKM+"__" + idTRA +"__"+idspTRA +"\" type = \"text\" size=\"7\" style=\"text-align:right; width: 30px; margin-right: 5px; border : solid 1px gray;\" value=\'"+ Math.round((tongluongTRA * sosuatCTKM) - 0.5) +"'>&nbsp;&nbsp;&nbsp;"+ spTRA +"</td>"; }
						  	else 
						  	{ trakmstr += "<td><input name=\""+ idCTKM+"__" + idTRA +"__"+idspTRA +"\" type = \"text\" size=\"7\" style=\"text-align:right; width: 30px; margin-right: 5px; border : solid 1px gray;\" value=\"\">&nbsp;&nbsp;&nbsp;"+ spTRA +"</td> "; }
					  	} 
						else if(hinhthucTRA.equals("1")) 
						{  
							trakmstr += "<td><input type='text' size='7' value='"+ (soluongTRA ) +"' style='text-align:right; width: 30px' readonly='readonly'>&nbsp;&nbsp;&nbsp;"+ spTRA +"</td> ";
						} 
					}
					trakmstr += "</TR>";
			   }
		   } // if(sptra.length() > 0)
		 
	    } // for trakm
	    trakmstr += "</TABLE>";
	 %>
		<TR>
		<TD style="vertical-align: middle;" align="center"><%= maCTKM%></TD>
		<TD style="vertical-align: middle;" align="center"><%= dkkmstr%></TD>
		<TD style="vertical-align: middle;" align="center"><%= trakmstr %></TD>
		<TD style="vertical-align: middle;" align="center"><%=  formater.format(tongtienTRA)%></TD>
		</TR>	
 <% } %>
</table>
</div>
</div>
<%}%>