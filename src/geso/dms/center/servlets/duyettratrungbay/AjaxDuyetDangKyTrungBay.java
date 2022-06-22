package geso.dms.center.servlets.duyettratrungbay;

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

public class AjaxDuyetDangKyTrungBay extends HttpServlet
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
		Utility  util = new Utility();
		
		PrintWriter out = response.getWriter();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));	
		String dktrungbay_fk = util.antiSQLInspection(request.getParameter("dktrungbay_fk"));
		String khId = util.antiSQLInspection(request.getParameter("khId"));


		System.out.println("userId = " + userId + "&dktrungbay_fk = " + dktrungbay_fk + "&khId = " + khId);
		
		try{
			dbutils db = new dbutils();
			
			db.getConnection().setAutoCommit(false);
			
			String query = "";
			query = "UPDATE DKTRUNGBAY_KHACHHANG SET SUATDUYETDK = 1, NGUOIDUYET = "+userId+" where dktrungbay_fk = " + dktrungbay_fk + " and KHACHHANG_FK = " + khId;
			System.out.println(query);
			if(db.updateReturnInt(query) != 1){
				db.getConnection().rollback();
				db.shutDown();
				out.write("Lỗi duyệt ảnh đăng ký trưng bày " + query);
				return;
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
