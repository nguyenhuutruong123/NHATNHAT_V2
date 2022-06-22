package geso.dms.center.servlets.duyettratrungbay;

import geso.dms.center.beans.duyettratrungbay.IDuyetAnhTrungBay;
import geso.dms.center.beans.duyettratrungbay.imp.DuyetAnhTrungBay;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

public class DuyetAnhTrungBaySvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String setQuery(IDuyetAnhTrungBay obj) 
	{
		String condition ="";
		if(obj.getSchemeId().trim().length() > 0)
			condition +=" and ct.pk_seq = "+ obj.getSchemeId();
		if(obj.getNppId().trim().length() > 0)
			condition +=" and p.pk_seq = "+ obj.getNppId();
		if(obj.getDdkdId().trim().length() > 0)
			condition +=" and kd.pk_seq = "+ obj.getDdkdId();
		if(obj.getVungId().length() > 0)
			condition += " and p.KHUVUC_FK IN (SELECT PK_SEQ FROM KHUVUC WHERE VUNG_FK = "+obj.getVungId()+")";
		if(obj.getKvId().length() > 0)
			condition += " and p.KHUVUC_FK = " + obj.getKvId();
		
		String query=   
			"\n select distinct k.PK_SEQ, k.MAFAST, k.TEN, ct.SCHEME, p.TEN AS NPP, kd.TEN AS DDKD, " +
			"\n 	STUFF( " +
			"\n		( " +
			"\n			SELECT ' , ' + c.ANHCHUP + '-' + CAST(c.NGAYGIOTAO as varchar(50)) " +
			"\n			FROM KHACHHANG_ANHCHUP c where c.KHACHHANG_FK = k.PK_SEQ AND c.CTTB_FK = a.CTTB_FK " +
			"\n			ORDER BY c.NGAYGIOTAO " +
			"\n			FOR XML PATH('') " +
			"\n		), 1, 2, '' " +
			"\n ) as ANH, " +
			"\n STUFF(" +
			"\n (" +
			"\n 	select ', ' + CAST(ISNULL(a.XUATDUYET,0) AS VARCHAR(10)) + '-' + CAST(B.TRANGTHAI AS VARCHAR(1))" +
			"\n 	from DENGHITRATB_KHACHHANG A INNER JOIN DENGHITRATRUNGBAY B ON A.DENGHITRATB_FK = B.PK_SEQ" +
			"\n 	AND B.CTTRUNGBAY_FK = ct.PK_SEQ AND A.KHACHHANG_FK = k.PK_SEQ" +
			"\n 	right join (" +
			"\n 		select 1 as lan" +
			"\n 		union" +
			"\n 		select 2 as lan" +
			"\n 		union" +
			"\n 		select 3 as lan" +
			"\n 		union" +
			"\n 		select 4 as lan" +
			"\n 		union" +
			"\n 		select 5 as lan" +
			"\n 		union" +
			"\n 		select 6 as lan" +
			"\n 		union" +
			"\n 		select 7 as lan" +
			"\n 		union" +
			"\n 		select 8 as lan" +
			"\n 		union" +
			"\n 		select 9 as lan" +
			"\n 		union" +
			"\n 		select 10 as lan" +
			"\n 	) lan on lan.lan = b.LANTHANHTOAN" +
			"\n 	ORDER BY lan.LAN" +
			"\n 	FOR XML PATH('') " +
			"\n ), 1, 2, ''" +
			"\n ) AS DUYET "+
			"\n from KHACHHANG_ANHCHUP a inner join KHACHHANG k on a.KHACHHANG_FK = k.PK_SEQ " +
			"\n INNER JOIN DAIDIENKINHDOANH kd on kd.PK_SEQ = a.DDKD_FK " +
			"\n INNER JOIN NHAPHANPHOI p on p.PK_SEQ =a.NPP_FK " +
			"\n INNER JOIN CTTRUNGBAY ct on ct.PK_SEQ = a.CTTB_FK" + 
			"\n    " + 
			"\n where 1=1 " + condition;
			System.out.println("____BC anh trung bay: " + query);
		return query;
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IDuyetAnhTrungBay obj = new DuyetAnhTrungBay();
	
	    Utility util = new Utility();
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    String view = request.getParameter("view");
	    if(view == null)
	    	view = "";	    
	    
	    obj.setUserId(userId);
	    obj.init();	
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		
		String nextJSP = request.getContextPath() + "/pages/Center/DuyetAnhTrungBay.jsp";
		response.sendRedirect(nextJSP);
	}

	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IDuyetAnhTrungBay obj = new DuyetAnhTrungBay();
		Utility  util = new Utility();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		if (userId == null)
			userId = "";
		obj.setUserId(userId);
		
		String userTen = (String) session.getAttribute("userTen");
		obj.setUserTen(userTen);
		
		
		String nppId ="";
		
		nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if(nppId == null)
			nppId = "";
		obj.setNppId(nppId);
		
		
		
		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		if (ddkdId == null)
			ddkdId = "";
		obj.setDdkdId(ddkdId);

	
		/*String tungay = util.antiSQLInspection(request.getParameter("Sdays"));
		if (tungay == null)
			tungay = "";
		obj.settungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("Edays"));
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);*/
		
		/*geso.htp.center.util.Utility Ult = new geso.htp.center.util.Utility();
		nppId = Ult.getIdNhapp(userId);*/

		obj.setVungId(util.antiSQLInspection(request.getParameter("vungId"))!=null?
				util.antiSQLInspection(request.getParameter("vungId")):"");
			
		obj.setKvId(util.antiSQLInspection(request.getParameter("khuvucId"))!=null?
				util.antiSQLInspection(request.getParameter("khuvucId")):"");
		
		obj.setSchemeId(util.antiSQLInspection(request.getParameter("cttbId"))!=null?
				util.antiSQLInspection(request.getParameter("cttbId")):"");
	
	

		String action = request.getParameter("action");
		if (action.equals("searchbc")) 
		{
			obj.init();
			String query = setQuery(obj);
			obj.setAnhtrungbayRs(query);
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			
			String nextJSP = request.getContextPath() + "/pages/Center/DuyetAnhTrungBay.jsp";
			response.sendRedirect(nextJSP);
			return;
		}
		if (action.equals("excel")) 
		{
			try 
			{
				request.setCharacterEncoding("utf-8");
			
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCAnhTrungBay.xlsm");

				OutputStream out = response.getOutputStream();

				String query = setQuery(obj);
				ExportToExcel(out, obj, query, action);
			} 
			catch (Exception ex) 
			{
				request.getSession().setAttribute("errors", ex.getMessage());
			}
		}
		else{
				obj.init();
				//String query = setQuery(obj);
				session.setAttribute("obj", obj);
				session.setAttribute("userId", userId);
				
				String nextJSP = request.getContextPath() + "/pages/Center/DuyetAnhTrungBay.jsp";
				response.sendRedirect(nextJSP);
		}
		
	}

	



	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	
	


	private void TaoBaoCao(Workbook workbook, IDuyetAnhTrungBay obj, String query, String action )throws Exception
	{
		try{
			
			
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("Sheet1");

			Cells cells = worksheet.getCells();
			cells.setRowHeight(0, 50.0f);
			Cell cell = cells.getCell("A1");
			ReportAPI.getCellStyle(cell, Color.RED, true, 16,"Bao Cao Anh Trung Bay");

			cell = cells.getCell("A3");
			//ReportAPI.getCellStyle(cell, Color.BLUE, true, 10, "Ngày tạo : "+ obj.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10,"Người tạo : " + obj.getUserTen());
			
			dbutils db = new dbutils();

			ResultSet	rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int countRow = 8;
			int column = 0;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(countRow, column + i-1 );cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			countRow ++;
			
			
			
			while(rs.next())
			{


				for(int i =1;i <=socottrongSql ; i ++)
				{



					Color c = Color.WHITE;
					cell = cells.getCell(countRow,column + i-1 );
					
					if(rsmd.getColumnName(i).contains("Ảnh Chụp"))
					{
						if(action.equals("exceldownload"))
						{
							cells.setRowHeight(countRow, 50f);
							String img = rs.getString(i);
							String dir = getServletContext().getInitParameter("pathhinh")+"AnhTrungBay\\";					
							img=dir+img;
							System.out.println("img: " +img);
							if (img.trim().length() > 0)
							{
								try
								{
									ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
									int pictureIndex=worksheet.getPictures().add(countRow,cell.getColumnIndex(),countRow,cell.getColumnIndex(),img);
									Picture picture = worksheet.getPictures().get(pictureIndex);						
									picture.setWidth(100);
									picture.setHeight(50);
			
									
								} catch (Exception e)
								{
									System.out.println("Exception: " + e.getMessage());
								}
							}
						}
						else
						{
							String linkUrl =  getServletContext().getInitParameter("pathhinhJSP")  +"AnhTrungBay/";
							if(rs.getString(i) != null && !rs.getString(i).equals("NA"))
								cell.setFormula("=HYPERLINK(\""+linkUrl+ rs.getString(i) + "\")");
							else
								cell.setValue("NA");
							ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 0);
						}
						/**/
					}
					else
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 41);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 0);
					}
				}
				++countRow;
			}
			if(rs!=null)rs.close();
			if(db!=null){
				db.shutDown();
			}

	
		}catch(Exception ex){
			
			System.out.println("Errrorr : "+ex.toString());
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}
	
	private void ExportToExcel(OutputStream out, IDuyetAnhTrungBay obj,String query,String action )throws Exception
	 {
		try{ 		
			
			Workbook workbook = new Workbook();

			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(workbook,obj,query,action);		
			workbook.save(out);	
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		
	}
	
}
