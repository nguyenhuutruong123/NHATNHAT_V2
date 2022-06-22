package geso.dms.distributor.servlets.tuyenbanhang;

import geso.dms.distributor.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ajaxTuyenBh extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
  
    public ajaxTuyenBh() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		 
	    Utility util = new Utility();
		PrintWriter out = response.getWriter();
		
		String khTen =  util.antiSQLInspection(request.getParameter("khTen"));
		System.out.println("ten:: "+ khTen);
		String diachi = util.antiSQLInspection(request.getParameter("diachi"));
		String ddkdId = request.getParameter("ddkdId");
		String nppId = request.getParameter("nppId");
		String tbhId= util.antiSQLInspection(request.getParameter("tbhId"));
		String maFast = util.antiSQLInspection(request.getParameter("mafast"));
		
		dbutils db = new dbutils();		
		System.out.println("[tbhId]"+tbhId+"[ddkdId]"+ddkdId);
		
		String query = "select pk_seq as khId, smartid, maFAST, ten, diachi, maFAST  from khachhang where trangthai = '1' and npp_fk='" + nppId + "' \n";
		if(maFast !=null){
			query = query + " and maFAST like (dbo.ftBoDau('%" + maFast +"%'))";
		}
		
		query+=" and pk_seq not in ( select khachhang_fk from khachhang_tuyenbh where tbh_fk in(select pk_seq from tuyenbanhang where ddkd_fk!='"+ddkdId+"')) ";
		
		if(tbhId != null)
			query = query + " and pk_seq not in ( select khachhang_fk from khachhang_tuyenbh where tbh_fk='" + tbhId + "') \n";
		if(khTen != "")
		{  
			String st = util.replaceAEIOU(khTen.trim());
		  	query = query + " and ( smartid like (dbo.ftBoDau('%" + st + "%')) or upper(dbo.ftBoDau(ten)) like upper(dbo.ftBoDau(N'%" + st + "%')) )";
		}
		
		if(diachi != "")
		{
			String st = util.replaceAEIOU(diachi.trim());
		  	query = query + " and upper(dbo.ftBoDau(diachi)) like upper(dbo.ftBoDau(N'%" + st + "%'))";
		}
		query += " order by pk_seq desc";
		
		System.out.println("[query]"+query);
		
		String table = "<TABLE width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"6\" id=\"tb_kh_tbh_cdpt\"> " + 
		 		"<tbody id=\"kh_tbh_cdpt\"> " +
		 		"<TR class=\"tbheader\"> " +
		 		"<TH width=\"5%\">Mã KH</TH> " +
		 		"<TH width=\"10%\">Mã Fast</TH> " +
		 		"<TH width=\"45%\">Tên KH</TH> " +
				"<TH width=\"35%\">Địa chỉ </TH> " + 
				"<TH width=\"5%\">Chọn </TH> </TR>";
		
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				out.write("<option value=''></option>");
				int i = 0;
				while(rs.next())
				{
					if(i % 2 != 0)
						table += "<TR class= 'tblightrow' >";
					else
						table += "<TR class= 'tbdarkrow' >";
					table += "<TD align=\"left\" class=\"textfont\">" + rs.getString("smartid") + "</TD>";
					table += "<TD align=\"left\" class=\"textfont\">" + rs.getString("maFAST") + "</TD>";
					table += "<TD align=\"left\" class=\"textfont\">" + rs.getString("ten") + "</TD>";
					table += "<TD align=\"center\">" + rs.getString("diachi") + "</TD>";
					table += "<TD align=\"center\">" +
							"<input name=\"kh_tbh_cdptList\" type=\"checkbox\" value ='" + rs.getString("khId")+ ";"+ rs.getString("smartid")+ ";"+ rs.getString("maFAST")+ ";" + rs.getString("ten")+ ";"+ rs.getString("diachi") + "'></TD></tr>";
				}
			} 
			catch(Exception e) {}
		}
		table += "</table>";
		out.write(table);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		 
	    Utility util = new Utility();
		PrintWriter out = response.getWriter();
		
		String khTen =  request.getParameter("khTen");
		System.out.println("ten:: "+ khTen);
		String diachi = request.getParameter("diachi");
		String ddkdId = request.getParameter("ddkdId");
		String nppId = request.getParameter("nppId");
		String tbhId= request.getParameter("tbhId");
		String maFast = request.getParameter("mafast");
		
		dbutils db = new dbutils();		
		System.out.println("[tbhId]"+tbhId+"[ddkdId]"+ddkdId);
		
		String query = "select pk_seq as khId, smartid, maFAST, ten, diachi  from khachhang where trangthai = '1' and npp_fk='" + nppId + "' \n";
		if(maFast !=null && maFast.trim().length() > 0){
			String st = util.replaceAEIOU(maFast.trim());
			query = query + " and maFAST like (N'%" + st +"%')";
		}
		
		query+=" and pk_seq not in ( select khachhang_fk from khachhang_tuyenbh where tbh_fk in(select pk_seq from tuyenbanhang where ddkd_fk!='"+ddkdId+"')) ";
		
		if(tbhId != null)
			query = query + " and pk_seq not in ( select khachhang_fk from khachhang_tuyenbh where tbh_fk='" + tbhId + "') \n";
		if(khTen != "")
		{  
			String st = util.replaceAEIOU(khTen.trim());
		  	query = query + " and ( smartid like (N'%" + st + "%') or upper(dbo.ftBoDau(ten)) like upper(N'%" + st + "%') )";
		}
		
		if(diachi != "")
		{
			String st = util.replaceAEIOU(diachi.trim());
		  	query = query + " and upper(dbo.ftBoDau(diachi)) like upper(N'%" + st + "%')";
		}
		query += " order by pk_seq desc";
		
		System.out.println("[query]"+query);
		String table = "<TABLE width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"6\" id=\"tb_kh_tbh_cdpt\"> " + 
		 		"<tbody id=\"kh_tbh_cdpt\"> " +
		 		"<TR class=\"tbheader\"> " +
		 		"<TH width=\"5%\">Mã KH</TH> " +
		 		"<TH width=\"10%\">Mã Fast</TH> " +
		 		"<TH width=\"45%\">Tên KH</TH> " +
				"<TH width=\"35%\">Địa chỉ </TH> " + 
				"<TH width=\"5%\">Chọn <input type=\"checkbox\" name=\"chontatca\" id=\"chontatca_kh_tbh_cdptList\" value=\"1\" onclick=\"checkedAll('kh_tbh_cdptList',this);\" ></TH> </TR>";
		
		ResultSet rs = db.get(query);
		try {
			int i = 0;
			while(rs.next()) {
					if(i % 2 != 0)
						table += "<TR class= 'tblightrow' >";
					else
						table += "<TR class= 'tbdarkrow' >";
					table += "<TD align=\"left\" class=\"textfont\">" + rs.getString("smartid") + "</TD>";
					table += "<TD align=\"left\" class=\"textfont\">" + rs.getString("maFAST") + "</TD>";
					table += "<TD align=\"left\" class=\"textfont\">" + rs.getString("ten") + "</TD>";
					table += "<TD align=\"center\">" + rs.getString("diachi") + "</TD>";
				
					table += "<TD align=\"center\">" +
					"<input name=\"kh_tbh_cdptList\" type=\"checkbox\" value ='" + rs.getString("khId")+ ";"+ rs.getString("smartid")+ ";"+ rs.getString("maFAST")+ ";" + rs.getString("ten")+ ";"+ rs.getString("diachi") + "' onclick=\"check_each('kh_tbh_cdptList');\"></TD></tr>";
				
					i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.shutDown();
		}
		table += "</tbody></table>";
		System.out.println("table ne::: "+table);
		out.write(table);
	}
}


