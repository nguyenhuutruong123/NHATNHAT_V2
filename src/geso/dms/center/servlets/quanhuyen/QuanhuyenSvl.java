package geso.dms.center.servlets.quanhuyen;

import geso.dms.center.beans.quanhuyen.IQuanhuyen;
import geso.dms.center.beans.quanhuyen.IQuanhuyenList;
import geso.dms.center.beans.quanhuyen.imp.Quanhuyen;
import geso.dms.center.beans.quanhuyen.imp.QuanhuyenList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.servlets.reports.AjaxDistributionTT;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Servlet implementation class QuanhuyenSvl
 */
public class QuanhuyenSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanhuyenSvl() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
	    Utility util = new Utility();
	    
	    IQuanhuyenList quanList = new QuanhuyenList();
	    quanList.init();
	    
	    session.setAttribute("quanList", quanList);
		response.sendRedirect(request.getContextPath() + "/pages/Center/Quanhuyen.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    Utility util = new Utility();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    OutputStream out = response.getOutputStream();
	    IQuanhuyenList quanList = new QuanhuyenList();
	    
	    String ten = util.antiSQLInspection(request.getParameter("ten"));
	    if (ten == null) {
	    	ten = "";
	    }
	    quanList.setTen(ten);
	    
	    String tinhId = util.antiSQLInspection(request.getParameter("tinhId"));
	    if (tinhId == null) {
	    	tinhId = "";
	    }
	    quanList.setTinhId(tinhId);
	    
	    String action = util.antiSQLInspection(request.getParameter("action"));
	    System.out.println("action : "+ action);
	    if (action.equals("submit")) {
	    	quanList.init();
		    session.setAttribute("quanList", quanList);
			response.sendRedirect(request.getContextPath() + "/pages/Center/Quanhuyen.jsp");
	    }
	    else if (action.equals("new")) {
	    	IQuanhuyen quan = new Quanhuyen(userId);
	    	quan.createRs();
		    session.setAttribute("quan", quan);
			response.sendRedirect(request.getContextPath() + "/pages/Center/QuanhuyenUpdate.jsp");
	    }
	    else if (action.equals("excel"))
		{
	    	
	    	try {
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=Thongtinquanhuyen.xlsm");
				String query = AjaxDistributionTT.queryQuanhuyen(request, util,AjaxDistributionTT.QUERY_EXCEL_ACTION,0,0) ;
				ExportToExcel(out, quanList, query);
				quanList.getDb().shutDown();
				return;
			}
			catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Error Here : "+ex.toString());
				request.getSession().setAttribute("errors", ex.getMessage());
			}
		}
	    else
		{
		    quanList.init();
		    session.setAttribute("quanList", quanList);
			response.sendRedirect(request.getContextPath() + "/pages/Center/Quanhuyen.jsp"); 
		}
	}
	
	private void ExportToExcel(OutputStream out,IQuanhuyenList obj,String query )throws Exception
	{
		try
		{ 					
			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(workbook, obj, query);			
			workbook.save(out);					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}
	
	private String getSearchQuery(HttpServletRequest request,IQuanhuyenList obj){
		PrintWriter out;
		Utility util = new Utility();
		
		String ten = util.antiSQLInspection(request.getParameter("ten"));
	    	if ( ten == null)
	    		ten = "";
	    	obj.setTen(ten);
	    	
    	 String tinhId = util.antiSQLInspection(request.getParameter("tinhId"));
 	     if (tinhId == null) {
 	    	tinhId = "";
 	     }
 	     obj.setTinhId(tinhId);
    	String query;
    	query = "SELECT quan.PK_SEQ, isnull(QUAN.MA,'') MA, quan.TEN AS QUAN, TINH.PK_SEQ MAHTTINH, TINH.MA MATINH, tinh.TEN AS TINH, tao.TEN AS NGUOITAO, quan.NGAYTAO, "
    			+ "\n	sua.TEN AS NGUOISUA, quan.NGAYSUA "
    			+ "\nFROM dbo.QUANHUYEN quan "
    			+ "\n	INNER JOIN dbo.TINHTHANH tinh ON tinh.PK_SEQ = quan.TINHTHANH_FK "
    			+ "\n	INNER JOIN dbo.NHANVIEN tao ON tao.PK_SEQ = quan.NGUOITAO "
    			+ "\n	INNER JOIN dbo.NHANVIEN sua ON sua.PK_SEQ = quan.NGUOISUA "
    			+ "\nWHERE 1 = 1 ";
    		if (!ten.equals("")) {
    			query += "\n	AND quan.TEN LIKE N'%" + ten + "%' ";
    		}
    		if (!tinhId.equals("")) {
    			query += "\n	AND tinh.PK_SEQ = " + tinhId + " ";
    		}
    		query += "\nORDER BY tinh.TEN, quan.TEN ";
    	return query;
		}	
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook, IQuanhuyenList obj, String query)throws Exception
	{
		try
		{		
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "THÔNG TIN QUẬN HUYỆN");
			cell = cells.getCell("A2");
			/*ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "  Đến ngày : " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());		*/	
			
			ResultSet rs = obj.getDb().get(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			
			int location  = 0;
			int row = 10;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i).replace("(%)",""));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			
			row ++;
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{					
					cell = cells.getCell(row,location + i-1 );
					
					if(!rsmd.getColumnName(i).contains("Ma") && rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						int format = 37;
							if(rsmd.getColumnName(i).contains("%")|| rsmd.getColumnName(i).contains("(%)") )	
								format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				++row;
			}
			if(rs!=null)rs.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("Lỗi : Không có dữ liệu để xuất file !");
		}	
	}
	
	
	private void ToExcel(HttpServletResponse response, IQuanhuyenList obj, String query) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=Quanhuyen.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 5;
			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet = w.createSheet("QuanHuyen", k);
			sheet.addCell(new Label(0, 1, "QUẬN HUYỆN : ", new WritableCellFormat(cellTitle)));

			sheet.addCell(new Label(0, 2, "Ngày tạo: "));
			sheet.addCell(new Label(1, 2, "" + getDateTime()));
			WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
			cellFont.setColour(Colour.BLACK);

			WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

			cellFormat.setBackground(jxl.format.Colour.LIME);
			cellFormat.setWrap(true);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cellFormatSpecical = new WritableCellFormat(cellFont);

			cellFormatSpecical.setBackground(jxl.format.Colour.LIGHT_ORANGE);
			cellFormatSpecical.setWrap(true);
			cellFormatSpecical.setAlignment(Alignment.CENTRE);
			cellFormatSpecical.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormatSpecical.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			sheet.addCell(new Label(0, 4, "MÃ HỆ THỐNG", cellFormat));
			sheet.addCell(new Label(1, 4, "MÃ ĐỒNG BỘ", cellFormat));
			sheet.addCell(new Label(2, 4, "TÊN QUẬN HUYỆN", cellFormatSpecical));
			sheet.addCell(new Label(3, 4, "MÃ HỆ THỐNG TỈNH", cellFormat));
			sheet.addCell(new Label(4, 4, "MÃ ĐỒNG BỘ TỈNH", cellFormat));
			sheet.addCell(new Label(5, 4, "TÊN TỈNH", cellFormat));
			sheet.setRowView(100, 4);

			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 15);
			sheet.setColumnView(7, 35);
			sheet.setColumnView(8, 15);
			sheet.setColumnView(9, 15);
			sheet.setColumnView(10, 15);
			sheet.setColumnView(11, 15);
			sheet.setColumnView(12, 15);
			sheet.setColumnView(13, 15);
			sheet.setColumnView(14, 60);
			dbutils db = new dbutils();

			ResultSet rs = db.get(query);

			WritableCellFormat cellFormat2 = new WritableCellFormat(new jxl.write.NumberFormat("#,###,###"));

			cellFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cellFormat3 = new WritableCellFormat(new jxl.write.NumberFormat("#,###,###"));
			cellFormat3.setBackground(jxl.format.Colour.YELLOW);
			cellFormat3.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat3.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat3.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat3.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cformat = null;
			Label label;
			while (rs.next())
			{
				String type = "0";
				cformat = type.equals("1") ? cellFormat3 : cellFormat2;
				label = new Label(0, j, rs.getString("PK_SEQ"), cformat);sheet.addCell(label);
				label = new Label(1, j, rs.getString("MA"), cformat);sheet.addCell(label);
				label = new Label(2, j, rs.getString("QUAN"), cformat);sheet.addCell(label);
				label = new Label(3, j, rs.getString("MAHTTINH"), cformat);sheet.addCell(label);
				label = new Label(4, j, rs.getString("MATINH"), cformat);sheet.addCell(label);
				label = new Label(5, j, rs.getString("TINH"), cformat);sheet.addCell(label);
				j++;
			}
			w.write();
			w.close();
			rs.close();
			db.shutDown();
		} catch (Exception e)
		{
			System.out.println("Error Cac Ban : " + e.getMessage());
			e.printStackTrace();
		} finally
		{
			if (out != null)
				out.close();

		}
	}
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
