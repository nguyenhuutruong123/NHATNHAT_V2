package geso.dms.center.servlets.chuyenngu;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.FileFormatType;
import com.oreilly.servlet.MultipartRequest;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.*;
import geso.dms.distributor.db.sql.dbutils;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import javax.servlet.http.HttpServlet;

import redis.clients.jedis.Jedis;

public class UploadChuyenNguSvl extends HttpServlet {
	static final long serialVersionUID = 1L;

	public UploadChuyenNguSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/UploadChuyenNgu.jsp";
		response.sendRedirect(nextJSP);
	}

	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * request.setCharacterEncoding("UTF-8");
	 * response.setCharacterEncoding("UTF-8");
	 * response.setContentType("text/html; charset=UTF-8"); HttpSession session =
	 * request.getSession(); String contentType = request.getContentType();
	 * 
	 * String userId =
	 * geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("userId")
	 * ); Utility util = new Utility();
	 * 
	 * if ((contentType != null) && (contentType.indexOf("multipart/form-data") >=
	 * 0)) {
	 * 
	 * response.setContentType("application/vnd.ms-excel");
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=KETQUACHUYENNGU.xls"); WritableWorkbook w =
	 * Workbook.createWorkbook(response.getOutputStream()); WorkbookSettings ws =
	 * new WorkbookSettings(); ws.setEncoding("ISO8859_1");
	 * 
	 * MultipartRequest multi = new MultipartRequest(request,
	 * "C:\\java-tomcat\\data\\", 20000000, "UTF-8"); userId =
	 * util.antiSQLInspection(multi.getParameter("userId"));
	 * 
	 * Enumeration files = multi.getFileNames(); String filenameu = ""; while
	 * (files.hasMoreElements()) { String name = (String) files.nextElement();
	 * filenameu = multi.getFilesystemName(name); System.out.println("name :   " +
	 * name);
	 * 
	 * } System.out.println("1____READ EXCEL TOI DAY, FILE NAME......" + filenameu);
	 * 
	 * // String filename= "C:\\java-tomcat\\data\\"+ filenameu;
	 * 
	 * String filename = "C:\\java-tomcat\\data\\" + filenameu; if (filenameu !=
	 * null && filenameu.length() > 0) { // doc file excel File file = new
	 * File(filename); System.out.println("filename  " + file); Workbook workbook;
	 * Jedis jedis = null; dbutils db = new dbutils(); String query = ""; try {
	 * db.getConnection().setAutoCommit(false); jedis =
	 * ChuyenNgu.getJedis(ChuyenNgu.timeout * 120); workbook = Workbook.getWorkbook(
	 * file, ws ); //workbook = Workbook.getWorkbook(file); Sheet sheet
	 * =workbook.getSheet(0);
	 * 
	 * // TAO RA 1 SHEET LUU THONG TIN UPLOAD KHONG THANH CONG WritableSheet
	 * sheetwrite = w.createSheet(sheet.getName(), 0); sheetwrite.addCell(new
	 * Label(0, 0, "key")); sheetwrite.addCell(new Label(1, 0, "vi"));
	 * sheetwrite.addCell(new Label(2, 0, "en")); sheetwrite.addCell(new Label(3, 0,
	 * "zh"));
	 * 
	 * Cell[] cells; int indexRow = 1; for (int i = indexRow; i < sheet.getRows();
	 * i++) { cells = sheet.getRow(i); if (cells.length > 0) { if (cells[0] != null
	 * && cells[0].getContents().toString().length() > 0) { String key =
	 * getValue(sheet, 0, i).trim(); if (key.trim().length() > 0) {
	 * 
	 * sheetwrite.addCell(new Label(0, indexRow, key)); String vi = getValue(sheet,
	 * 1, i).trim().replace("'", "''"); if(vi.length() > 0) { long row_affect =
	 * ChuyenNgu.set(key, "vi", vi,jedis); sheetwrite.addCell(new Label(1, indexRow,
	 * row_affect + "" )); } String en = getValue(sheet, 2, i).trim().replace("'",
	 * "''"); if(en.length() > 0) { long row_affect = ChuyenNgu.set(key, "en",
	 * en,jedis); sheetwrite.addCell(new Label(2, indexRow, row_affect + "" )); }
	 * 
	 * db.AddParam("@VI", vi); db.AddParam("@EN", en); db.AddParam("@_key", key);
	 * query =
	 * " update ChuyenNgu set VI =@VI ,  EN = @EN ,  thoidiem = getdate()  where _key = @_key "
	 * ; int sd = db.update_with_param(query);
	 * 
	 * if(sd < 0) { System.out.println(" error " + query); }else if(sd == 0) {
	 * db.AddParam("@VI", vi); db.AddParam("@EN", en); db.AddParam("@_key", key);
	 * query =
	 * " insert ChuyenNgu(VI,EN,_key,thoidiem) select @VI,@EN,@_key,getdate() ";
	 * db.update_with_param(query); } } } } indexRow++;
	 * 
	 * }
	 * 
	 * Utility.commit_and_shutdown(db); ChuyenNgu.Save(jedis); jedis.close(); jedis
	 * = null; return; } catch (Exception ex) { Utility.rollback_and_shutdown(db);
	 * ex.printStackTrace(); if(jedis != null) jedis.close(); } } w.write(); try {
	 * w.close(); } catch (WriteException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return; } else { session.setAttribute("userId",
	 * userId); String nextJSP = request.getContextPath() + "/pages/Center/UploadChuyenNgu.jsp";
	 * response.sendRedirect(nextJSP); }
	 * 
	 * }
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String contentType = request.getContentType();

		String userId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("userId")); 
		Utility util = new Utility();
 
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {

			
			WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("ISO8859_1");
			
			MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8");
			userId = util.antiSQLInspection(multi.getParameter("userId")); 

			Enumeration files = multi.getFileNames();
			String filenameu = "";
			while (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				filenameu = multi.getFilesystemName(name);
				System.out.println("name :   " + name);

			}
			System.out.println("1____READ EXCEL TOI DAY, FILE NAME......" + filenameu);

			// String filename= "C:\\java-tomcat\\data\\"+ filenameu;

			String filename = "C:\\java-tomcat\\data\\" + filenameu;
			if (filenameu != null && filenameu.length() > 0) {
				// doc file excel
				File file = new File(filename);
				System.out.println("filename  " + file);
				Workbook workbook;
				Jedis jedis = null;
				dbutils db = new  dbutils();
				String query = "";
				try 
				{
					db.getConnection().setAutoCommit(false);
					jedis = ChuyenNgu.getJedis(ChuyenNgu.timeout * 120);
					workbook = Workbook.getWorkbook( file, ws );
					//workbook = Workbook.getWorkbook(file);
					Sheet sheet =workbook.getSheet(0);
					
					
					
					Cell[] cells;
					int indexRow = 1; 
					for (int i = indexRow; i < sheet.getRows(); i++) {
						cells = sheet.getRow(i); 
						if (cells.length > 0) 
						{ 
							if (cells[0] != null && cells[0].getContents().toString().length() > 0) {
								String key = getValue(sheet, 0, i).trim();
								if (key.trim().length() > 0) {
									
									String vi = getValue(sheet, 1, i).trim();
									if(vi.length() > 0)
									{
										long row_affect = ChuyenNgu.set(key, "vi", vi,jedis);
									}
									String en = getValue(sheet, 2, i).trim();
									if(en.length() > 0)
									{
										long row_affect = ChuyenNgu.set(key, "en", en,jedis);

									}
									
									db.AddParam("@VI", vi);
									db.AddParam("@EN", en);
									db.AddParam("@_key", key);
									query = " update ChuyenNgu set VI =@VI ,  EN = @EN ,  thoidiem = getdate()  where _key = @_key ";
									int sd = db.update_with_param(query);
									
									if(sd < 0)
									{
										System.out.println(" error " + query);
									}else if(sd == 0)
									{
										db.AddParam("@VI", vi);
										db.AddParam("@EN", en);
										db.AddParam("@_key", key);
										query = " insert ChuyenNgu(VI,EN,_key,thoidiem) select @VI,@EN,@_key,getdate() ";
										db.update_with_param(query);
									}
								}
							} 
						}
						indexRow++;

					}
					
					Utility.commit_and_shutdown(db);
					ChuyenNgu.Save(jedis);
					jedis.close();
					jedis = null;
					
					
					try {
			    		OutputStream out = response.getOutputStream();
						request.setCharacterEncoding("utf-8");
						response.setContentType("application/xls");
						response.setHeader("Content-Disposition", "attachment; filename=translate.xls");				
						ExportToExcel(out );
						return;
					}
					catch (Exception ex) {
						ex.printStackTrace();
						System.out.println("Error Here : "+ex.toString());
						request.getSession().setAttribute("errors", ex.getMessage());
					}
					
				} catch (Exception ex) {
					Utility.rollback_and_shutdown(db);
					ex.printStackTrace();
					if(jedis != null)
						jedis.close();
				}
			}
			w.write(); 
			try {
				w.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else
		{
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/UploadChuyenNgu.jsp";
			response.sendRedirect(nextJSP);
		}
		
	}

	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	String getValue(Sheet sheet, int col, int row) {
		return sheet.getCell(col, row).getContents().trim().replaceAll(",", "");
	}
	
	private void ExportToExcel(OutputStream out)throws Exception
	{
		try
		{ 					
			String query = " select _key, VI, EN, thoidiem from chuyenngu ";
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(workbook, query);			
			workbook.save(out);					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}
	
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook, String query)throws Exception
	{
		try
		{		
			dbutils db = new dbutils();
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			com.aspose.cells.Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			com.aspose.cells.Cell cell = cells.getCell("A1");
		   
			Jedis jedis = ChuyenNgu.getJedis(ChuyenNgu.timeout * 240);
		
			ResultSet rs =db.get(query);
			
			 
			cell = cells.getCell(0, 0 ); cell.setValue("key");
			cell = cells.getCell(0, 1 ); cell.setValue("vi");
			cell = cells.getCell(0, 2 ); cell.setValue("en");
			cell = cells.getCell(0, 3 ); cell.setValue("vi_log");
			cell = cells.getCell(0, 4 ); cell.setValue("en_log");
			
			int row  = 1;
			while(rs.next())
			{
				
				String _key =rs.getString("_key");
				
				cell = cells.getCell(row, 0 ); cell.setValue(rs.getString("_key"));
				cell = cells.getCell(row, 1 ); cell.setValue( Utility.GLanguage(_key, "vi", jedis) );
				cell = cells.getCell(row, 2); cell.setValue(  Utility.GLanguage(_key,  "en", jedis) );
				cell = cells.getCell(row, 3); cell.setValue( rs.getString("VI") );
				cell = cells.getCell(row, 4); cell.setValue(  rs.getString("EN") );
				System.out.println(" row = "+ _key) ;
				++row;
			}
			if(rs!=null)rs.close();
			
			Utility.JedisClose(jedis);
			db.shutDown();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("Lỗi : Không có dữ liệu để xuất file !");
		}	
	}
	
	
}