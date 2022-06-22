package geso.dms.center.servlets.duyettratrungbay;

import geso.dms.center.beans.duyettratrungbay.IDuyetAnhTrungBay;
import geso.dms.center.beans.duyettratrungbay.imp.DuyetAnhTrungBay;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.aspose.cells.Picture;

public class AjaxDuyetAnhTrungBay extends HttpServlet
{
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IDuyetAnhTrungBay obj = new DuyetAnhTrungBay();
		Utility  util = new Utility();
		
		PrintWriter out = response.getWriter();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));	
		String cttbId = util.antiSQLInspection(request.getParameter("cttbId"));
		String khId = util.antiSQLInspection(request.getParameter("khId"));
		String duyet = util.antiSQLInspection(request.getParameter("duyet"));


		System.out.println("userId = " + userId + "&cttbId = " + cttbId + "&khId = " + khId + "&duyet = " + duyet);
		
		try{
			dbutils db = new dbutils();
			
			db.getConnection().setAutoCommit(false);
			
			duyet = duyet.substring(0, duyet.length() - 1);
			String[] duyets = duyet.split(",");
			for(int i=1; i <= duyets.length; i++){
				String denghi_fk = "";
				String query = "SELECT PK_SEQ from DENGHITRATRUNGBAY WHERE CTTRUNGBAY_FK = " + cttbId + " and LANTHANHTOAN = " + i;
				ResultSet rs = db.get(query);
				if(rs.next()){
					denghi_fk = rs.getString("PK_SEQ");
				}
				else{
					query = "insert DENGHITRATRUNGBAY(CTTRUNGBAY_FK, NGAYDENGHI, TRANGTHAI, NGAYSUA, NGUOITAO, NGUOISUA, LANTHANHTOAN) " +
							"values('"+cttbId+"', '"+getDateTime()+"', '0', '"+getDateTime()+"', '"+userId+"', '"+userId+"', '"+i+"')";
					System.out.println(query);
					if(db.updateReturnInt(query) != 1){
						db.getConnection().rollback();
						db.shutDown();
						out.write("Lỗi tạo mới duyệt ảnh (1)");
						return;
					}
					query = "select IDENT_CURRENT('DENGHITRATRUNGBAY') as denghi_fk";
					
					ResultSet rskm = db.get(query);						
					rskm.next();
					denghi_fk = rskm.getString("denghi_fk");
					rskm.close();
				}
				String XUATDUYET = "1";
				if(duyets[i-1].equals("false") || duyets[i-1].equals("FALSE") || duyets[i-1].equals("0"))
					XUATDUYET = "0";
				query = "delete from DENGHITRATB_KHACHHANG where DENGHITRATB_FK = " + denghi_fk + " and KHACHHANG_FK = " + khId;
				if(db.update(query) == false){
					db.getConnection().rollback();
					db.shutDown();
					out.write("Lỗi tạo mới duyệt ảnh (2)");
					return;
				}
				query = "INSERT DENGHITRATB_KHACHHANG(DENGHITRATB_FK, KHACHHANG_FK, XUATDUYET) VALUES ('"+denghi_fk+"', '"+khId+"', '"+XUATDUYET+"')";
				if(db.updateReturnInt(query) != 1){
					db.getConnection().rollback();
					db.shutDown();
					out.write("Lỗi tạo mới duyệt ảnh (3)");
					return;
				}
			}
			db.getConnection().commit();
			db.shutDown();
			out.write("");
		}
		catch (Exception e) {
			// TODO: handle exception
			out.write("Lỗi tạo mới duyệt ảnh - " + e.getMessage());
		}
		
	}

	



	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
