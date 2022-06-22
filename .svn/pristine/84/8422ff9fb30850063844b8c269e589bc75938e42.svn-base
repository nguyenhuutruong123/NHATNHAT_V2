package geso.dms.center.servlets.reports;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.beans.khachhangtt.IKhachhangTTList;
import geso.dms.distributor.beans.khachhangtt.imp.KhachhangTTList;
import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.HorizontalAlignmentType;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class BCTonKhoCuaHieu extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private int items = 50;
	private int splittings = 20;


	public BCTonKhoCuaHieu() 
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else {

			IKhachhangTTList obj;
			PrintWriter out; 


			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			out  = response.getWriter();

			Utility util = new Utility();
			out = response.getWriter();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			out.println(userId);

			if (userId.length()==0)
				userId = util.antiSQLInspection(request.getParameter("userId"));

			String action = util.getAction(querystring);
			out.println(action);

			obj = new KhachhangTTList();


			obj.setUserId(userId);
			obj.init("select 1 ");
			//obj.createRS();

			session.setAttribute("obj", obj);

			String nextJSP = request.getContextPath() + "/pages/Center/BCTonKhoCuaHieu.jsp";
			response.sendRedirect(nextJSP);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
		else
		{
			OutputStream out = response.getOutputStream();	
			IKhachhangTTList obj = new KhachhangTTList();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			obj.setUserId(userId);
			Utility util = new Utility();
			userId = util.antiSQLInspection(request.getParameter("userId"));
			String action = request.getParameter("action");
			if (action == null){
				action = "";
			}
			if (action.equals("search"))
			{	    
				String search = getSearchQuery(request, obj);
				obj.setUserId(userId);
				obj.init("select 1");
				session.setAttribute("obj", obj);  	
				session.setAttribute("userId", userId);
				response.sendRedirect(request.getContextPath() + "/pages/Center/BCTonKhoCuaHieu.jsp");	    		    	
			}
			else if (action.equals("excel"))
			{
				try
				{
					obj.setTen(userTen);
					//System.out.println("userTen: "+userTen);
					ExportToExcel(response,out, obj, request,userTen);
					return;
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					obj.setMsg("Khong the tao pivot.");
				}
				obj.setUserId(userId);
				session.setAttribute("obj", obj);
				session.setAttribute("userId", userId);
				response.sendRedirect(request.getContextPath() + "/pages/Center/BCTonKhoCuaHieu.jsp");	    		
			}
			else if(action.equals("chitiet"))
			{
				dbutils db = new dbutils();
				try
				{
					response.setContentType("application/xlsm");
					response.setHeader("Content-Disposition", "attachment; filename=BCTonKhoCuaHieu_ChiTiet.xlsm");
					
					
					db.update("IF OBJECT_ID('tempdb.dbo.#LIST') IS NOT NULL DROP TABLE #LIST ");
					String query = getSearchQuery_chitiet(request,(IKhachhangTTList) obj);
					db.update(query);
					
					String header_str = "", header_str_pivot = "", main_header = "";
					query = " SELECT DISTINCT [NGAY], [NGAY]+'_'+COL COL_HEADER " +
							" FROM " +
							" ( " +
							"	SELECT NGAY, [ORDER], [STOCK] FROM #LIST " +
							" ) RT UNPIVOT (VALUE FOR COL IN ([ORDER],[STOCK]) ) UNPIV " +
							" ORDER BY NGAY ASC, COL_HEADER DESC ";
					ResultSet rs = db.get(query);
					while(rs.next())
					{
						header_str += "ISNULL(["+rs.getString("COL_HEADER")+"],0) AS ["+rs.getString("COL_HEADER")+"],";
						header_str_pivot += "["+rs.getString("COL_HEADER")+"],";
					}
					rs.close();
					
					query = " SELECT DISTINCT [NGAY] COL_HEADER " +
							" FROM " +
							" ( " +
							"	SELECT NGAY, [ORDER], [STOCK] FROM #LIST " +
							" ) RT UNPIVOT (VALUE FOR COL IN ([ORDER],[STOCK]) ) UNPIV " +
							" ORDER BY COL_HEADER ";
					rs = db.get(query);
					while(rs.next())
					{
						main_header += "["+rs.getString("COL_HEADER")+"],";
					}
					rs.close();
					
					if(header_str.trim().length() > 0)
					{ 
						header_str = header_str.substring(0, header_str.lastIndexOf(",")); 
						header_str_pivot = header_str_pivot.substring(0, header_str_pivot.lastIndexOf(","));
						main_header = main_header.substring(0, main_header.lastIndexOf(","));
						
						com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
						workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
						
				        if(!CreatePivotTable(workbook, out,obj,header_str, header_str_pivot, main_header, db))
				        {
				        	obj.setUserId(userId);
							session.setAttribute("obj", obj);
							session.setAttribute("userId", userId);
							response.sendRedirect(request.getContextPath() + "/pages/Center/BCTonKhoCuaHieu.jsp");
				        }
				        else
				        {
				        	db.update("IF OBJECT_ID('tempdb.dbo.#LIST') IS NOT NULL DROP TABLE #LIST ");
				        	obj.setUserId(userId);
							obj.init("select 1 ");
							//obj.createRS();
							session.setAttribute("obj", obj);
							String nextJSP = request.getContextPath() + "/pages/Center/BCTonKhoCuaHieu.jsp";
							response.sendRedirect(nextJSP);
				        }
					}
					else
					{
						obj.setUserId(userId);
						obj.init("select 1 ");
						//obj.createRS();
						obj.setMsg("Không có báo cáo theo dữ liệu đã lọc.");
						session.setAttribute("obj", obj);
						String nextJSP = request.getContextPath() + "/pages/Center/BCTonKhoCuaHieu.jsp";
						response.sendRedirect(nextJSP);
					}
				}catch(Exception ex){ ex.printStackTrace(); }
				finally{ try { if(db!=null){ db.shutDown(); } } catch (Exception e) { e.printStackTrace(); } }
			}
		}
	}
	
	private boolean CreatePivotTable(com.aspose.cells.Workbook workbook, OutputStream out, IKhachhangTTList obj, String header_str, String header_str_pivot, String main_header, dbutils db) throws Exception 
	{
		/*boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		String chuoi=getServletContext().getInitParameter("path") + "\\BCTonKhoCuaHieu_ChiTiet.xlsm";				
		fstream = new FileInputStream(chuoi);
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);*/
		
		FileInputStream fstream = null;
		//Workbook workbook = new Workbook();
		String chuoi=getServletContext().getInitParameter("path") + "\\BCTonKhoCuaHieu_ChiTiet_2.xlsm";				
		fstream = new FileInputStream(chuoi);
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		//worksheets.removeSheet("Sheet2");
		worksheet.setName("BCTonKhoCuaHieu_ChiTiet");

		/*com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		worksheet.setName("BCTonKhoCuaHieu_ChiTiet");*/
		
		Cells cells = worksheet.getCells();
	    Style style;
	    Font font = new Font();
	    font.setColor(Color.RED);//mau chu
	    font.setSize(16);// size chu
	   	font.setBold(true);
	   	
	    cells.setRowHeight(0, 20.0f);
	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 	        
	    
	    String tieude = "BÁO CÁO TỒN KHO KHÁCH HÀNG";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày lấy báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
	     
	    String query =
	    		"\n select [MaKhachHang] as [Mã KH], [TenKhachHang] as [Tên KH], [MaSanPham] as [Mã SP], [TenSanPham] as [Tên SP], " + header_str +
	    		"\n from " +
	    		"\n ( "+
	    		"\n 	select [MaKhachHang], [TenKhachHang], [MaSanPham], [TenSanPham], [Ngay]+'_'+col col, value from " +
	    		"\n 	( " +
	    	    "\n 		select [MaKhachHang], [TenKhachHang], [Ngay], [MaSanPham], [TenSanPham], sum(cast([Order] as float)) [Order], sum(cast([Stock] as float)) [Stock] " +
	    		"\n 		from #list " +
	    		"\n 		group by [MaKhachHang], [TenKhachHang], [Ngay], [MaSanPham], [TenSanPham] " +
	    		"\n 	) rt unpivot (value for col in ([Order],[Stock]) ) unpiv " +
	    		"\n ) t " +
	    		"\n pivot ( SUM(value) FOR col in ("+ header_str_pivot +")) piv "; 
		ResultSet rs = db.get(query);
	    ResultSetMetaData rsmd = rs.getMetaData();
		int socottrongSql = rsmd.getColumnCount();

		int countRow = 5;
		int j = 0;
		int column = 5;
		String array[] = main_header.split(",");
		//System.out.println("array : "+ array.length);
		for( int i = 0 ; i < array.length; i ++ )
		{
			cell = cells.getCell(countRow, column + j - 1); cell.setValue(array[i]);
			//System.out.println((countRow)+", "+ (countRow)+", "+ (column + j - 1)+", "+(column + j));
			ReportAPI.mergeCells(worksheet, countRow, countRow, column + j - 1, column + j);
			ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, false, 0);
			cell = cells.getCell(countRow, column + j);
			ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, false, 0);
			j = j + 2;
		}
		countRow ++;
		
		j = 0;
		for( int i =1 ; i <=socottrongSql ; i ++  )
		{
			if(rsmd.getColumnName(i).contains("_ORDER")) { cell = cells.getCell(countRow, j); cell.setValue("O"); }
			else if(rsmd.getColumnName(i).contains("_STOCK")) { cell = cells.getCell(countRow, j); cell.setValue("S"); }
			else { cell = cells.getCell(countRow, j); cell.setValue(rsmd.getColumnName(i)); }
			ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, false, 0);
			j++;
		}
		countRow ++;

		while(rs.next())
		{
			j = 0;
			for(int i =1;i <=socottrongSql ; i ++)
			{
				cell = cells.getCell(countRow, j);
				if(rsmd.getColumnType(i) == Types.DOUBLE)
				{
					cell.setValue(rs.getDouble(i));
				}
				else
				{
					cell.setValue(rs.getString(i));
				}
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				j++;
			}
			++countRow;
		}
		if(rs!=null)rs.close();
		
		CreateHeader(workbook,obj, header_str, header_str_pivot, main_header, db);
		workbook.save(out);
		//fstream.close();
		return true;
	}
	
	private void CreateHeader(Workbook workbook, IKhachhangTTList obj, String header_str, String header_str_pivot, String main_header, dbutils db)throws Exception
	{	
 		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		worksheets.removeSheet("Sheet2");
		worksheet.setName("Sheet1");
		Cells cells = worksheet.getCells();
		
	    Style style;
	    Font font = new Font();
	    font.setColor(Color.RED);//mau chu
	    font.setSize(16);// size chu
	   	font.setBold(true);
	   	
	    cells.setRowHeight(0, 20.0f);
	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 	        
	    
	    String tieude = "BÁO CÁO TỒN KHO KHÁCH HÀNG";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày lấy báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
	    
	    /*cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A3");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  " + obj.getTen());*/
	    
	    String query =
	    		"\n select [MaKhachHang] as [Mã KH], [TenKhachHang] as [Tên KH], [MaSanPham] as [Mã SP], [TenSanPham] as [Tên SP], " + header_str +
	    		"\n from " +
	    		"\n ( "+
	    		"\n 	select [MaKhachHang], [TenKhachHang], [MaSanPham], [TenSanPham], [Ngay]+'_'+col col, value from " +
	    		"\n 	( " +
	    	    "\n 		select [MaKhachHang], [TenKhachHang], [Ngay], [MaSanPham], [TenSanPham], sum(cast([Order] as float)) [Order], sum(cast([Stock] as float)) [Stock] " +
	    		"\n 		from #list " +
	    		"\n 		group by [MaKhachHang], [TenKhachHang], [Ngay], [MaSanPham], [TenSanPham] " +
	    		"\n 	) rt unpivot (value for col in ([Order],[Stock]) ) unpiv " +
	    		"\n ) t " +
	    		"\n pivot ( SUM(value) FOR col in ("+ header_str_pivot +")) piv "; 
	   // System.out.println("query : "+ query);
		ResultSet rs = db.get(query);
	    ResultSetMetaData rsmd = rs.getMetaData();
		int socottrongSql = rsmd.getColumnCount();

		int countRow = 5;
		//int j = 105;
		int j = 0;
		int column = 5;
		String array[] = main_header.split(",");
		//System.out.println("array : "+ array.length);
		for( int i = 0 ; i < array.length; i ++ )
		{
			//cell = cells.getCell(countRow, j); cell.setValue(array[i]);
			cell = cells.getCell(countRow, column + j - 1); cell.setValue(array[i]);
			//System.out.println((countRow)+", "+ (countRow)+", "+ (column + j - 1)+", "+(column + j));
			ReportAPI.mergeCells(worksheet, countRow, countRow, column + j - 1, column + j);
			ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, false, 0);
			cell = cells.getCell(countRow, column + j);
			ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, false, 0);
			
			//cell = cells.getCell(countRow - 1, column + j - 1 );cell.setValue( sortedMap.get(key) ); cells.setRowHeight(countRow - 1, 23.0f);
			//ReportAPI.mergeCells(worksheet, countRow - 1, countRow - 1, column + j - 1, column + j + 1);
			//ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			//cell = cells.getCell(countRow - 1, column + j ); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			//cell = cells.getCell(countRow - 1, column + j + 1 ); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			j = j + 2;
		}
		countRow ++;
		
		j = 0;
		for( int i =1 ; i <=socottrongSql ; i ++  )
		{
			if(rsmd.getColumnName(i).contains("_ORDER")) { cell = cells.getCell(countRow, j); cell.setValue("O"); }
			else if(rsmd.getColumnName(i).contains("_STOCK")) { cell = cells.getCell(countRow, j); cell.setValue("S"); }
			else { cell = cells.getCell(countRow, j); cell.setValue(rsmd.getColumnName(i)); }
			//System.out.println(" rsmd.getColumnName("+i+") : "+ rsmd.getColumnName(i));
			ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, false, 0);
			j++;
		}
		countRow ++;

		while(rs.next())
		{
			//j = 105;
			j = 0;
			for(int i =1;i <=socottrongSql ; i ++)
			{
				//cell = cells.getCell(countRow,i-1 );
				cell = cells.getCell(countRow, j);
				if(rsmd.getColumnType(i) == Types.DOUBLE)
				{
					cell.setValue(rs.getDouble(i));
				}
				else
				{
					cell.setValue(rs.getString(i));
				}
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				j++;
			}
			++countRow;
		}
		if(rs!=null)rs.close();

		/*ResultSetMetaData rsmd = rs.getMetaData();
		int socottrongSql = rsmd.getColumnCount();
		//int countRow = 1;
		int j = 105;
		int countRow = 1;
		for( int i = 1 ; i <=socottrongSql ; i ++ )
		{
			cell = cells.getCell(htb.get(j) + Integer.toString(countRow));
			cell.setValue(rsmd.getColumnName(i));
			j++;
			//ReportAPI.setCellBackground(cell, Color.YELLOW, BorderLineType.THIN, true, 0);	
		}
		countRow++;
		
		while(rs.next())
		{
			for(int i =1;i <=socottrongSql ; i ++)
			{
				cell = cells.getCell(countRow,i-1 );
				if(rsmd.getColumnType(i) == Types.DOUBLE )
				{
					cell.setValue(rs.getDouble(i));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
				}
				else
				{
					cell.setValue(rs.getString(i));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				}
			}
			++countRow;
		}

		if(rs!=null)rs.close();
		if(db!=null){
			db.shutDown();
		}*/
	}
	
	private String getSearchQuery_chitiet(HttpServletRequest request, IKhachhangTTList obj)
	{	
		Utility util = new Utility();
		String ten = util.antiSQLInspection(request.getParameter("khTen"));
		if ( ten == null)
			ten = "";
		obj.setTen(ten);

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if ( nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String hchId = util.antiSQLInspection(request.getParameter("hchTen"));
		if (hchId == null)
			hchId = "";    	
		obj.setHchId(hchId);

		String kbhId = util.antiSQLInspection(request.getParameter("kbhTen"));
		if (kbhId == null)
			kbhId = "";    	
		obj.setKbhId(kbhId);

		String vtchId = util.antiSQLInspection(request.getParameter("vtchTen"));
		if (vtchId == null)
			vtchId = "";    	
		obj.setVtId(vtchId);

		String lchId = util.antiSQLInspection(request.getParameter("lchTen"));
		if (lchId == null)
			lchId = "";    	
		obj.setLchId(lchId);

		String diadiemId = util.antiSQLInspection(request.getParameter("diadiemId"));
		if (diadiemId == null)
			diadiemId = "";    	
		obj.setDiadiemId(diadiemId);

		String xuatkhau = util.antiSQLInspection(request.getParameter("xuatkhau"));
		if (xuatkhau == null)
			xuatkhau = "0";    	
		obj.setXuatkhau(xuatkhau);

		String diachi = util.antiSQLInspection(request.getParameter("diachi"));
		if (diachi == null)
			diachi = "";    	
		obj.setDiachi(diachi.trim());

		String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));
		if (maFAST == null)
			maFAST = "";    	
		obj.setMaFAST(maFAST);

		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		if (ddkdId == null)
			ddkdId = "";    	
		obj.setDdkdId(ddkdId);

		String tbhId = util.antiSQLInspection(request.getParameter("tbhId"));
		if (tbhId == null)
			tbhId = "";    	
		obj.setTbhId(tbhId);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai")); 	
		if (trangthai == null)
			trangthai = "";    		
		obj.setTrangthai(trangthai);

		String tungay = util.antiSQLInspection(request.getParameter("tungay")); 	
		if (tungay == null)
			tungay = "";    		
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay")); 	
		if (denngay == null)
			denngay = "";    		
		obj.setDenngay(denngay);
		//System.out.println("[den ngay] : " + obj.getDenngay());

		String loaiKH = util.antiSQLInspection(request.getParameter("loaikh")); 	
		if (loaiKH == null)
			loaiKH = "";    		
		obj.setloaiKH(loaiKH);

		String hopdong = util.antiSQLInspection(request.getParameter("hopdong")); 	
		if (hopdong == null)
			hopdong = "";    		
		obj.setHopdong(hopdong);
		String vung = util.antiSQLInspection(request.getParameter("vungid"));
		if ( vung == null)
			vung = "";
		obj.setVungid(vung);
		String mien = util.antiSQLInspection(request.getParameter("mienid"));
		if ( mien == null)
			mien = "";
		obj.setMienid(mien);

		String dayOfWeekId = util.antiSQLInspection(request.getParameter("dayOfWeekId"));
		if ( dayOfWeekId == null)
			dayOfWeekId = "";
		obj.setDayOfWeekId(dayOfWeekId);

		String condition = "";
		String condition2 = "";
		//obj.setTungay("2019-09-01");
		//obj.setDenngay("2019-09-05");
		if(obj.getTungay().trim().length() > 0) {condition += " and dh.ngaynhap >= '"+ obj.getTungay().trim() +"' "; }
		if(obj.getDenngay().trim().length() > 0) {condition += " and dh.ngaynhap <= '"+ obj.getDenngay().trim() +"' "; }
		if(obj.getMaFAST().trim().length() > 0) 
		{
			condition += " and exists ( select 1 from khachhang kh where kh.timkiem like N'%" + util.replaceAEIOU(obj.getMaFAST().trim()) + "%' and kh.pk_seq = dh.khachhang_fk ) ";
			condition2 += " and exists ( select 1 from khachhang kh1 where kh1.timkiem like N'%" + util.replaceAEIOU(obj.getMaFAST().trim()) + "%' and kh.pk_seq = kh1.pk_seq ) ";
		}
		String query = 
		 "\n with data as  "+ 
		 "\n (   "+ 
		 "\n 	select a.NPP_FK,a.KHO_FK,a.KHACHHANG_FK,a.NGAYNHAP,a.Sanpham_fk, A.MA, a.TEN,a.soluong from  "+ 
		 "\n 	(    "+ 
		 "\n 		select dh.NPP_FK,dh.KHO_FK,dh.KHACHHANG_FK,dh.NGAYNHAP,dh_sp.Sanpham_fk,sp.MA, sp.TEN,sum(isnull(soluong, 0))soluong from donhang dh    "+ 
		 "\n 		inner join DONHANG_SANPHAM dh_sp on dh_sp.DONHANG_FK = dh.PK_SEQ and dh.TRANGTHAI !=2    "+ 
		 "\n 		inner join sanpham sp on sp.PK_SEQ=dh_sp.SANPHAM_FK  "+ 
		 "\n 		where 1=1 "+ condition +  
		 "\n 		group by dh.NPP_FK,dh.KHO_FK,dh.KHACHHANG_FK,dh.NGAYNHAP,dh_sp.Sanpham_fk,SP.MA, sp.TEN   "+ 
		 "\n 	) a   "+ 
		 "\n ), "+
		  
		 "\n infoKH as  "+ 
		 "\n (  "+ 
		 "\n 	select kh.smartid, kh.PK_SEQ,kh.TEN+'-'+kh.SMARTID+'-'+kh.DIACHI KHTEN,kh.KBH_FK from khachhang kh where 1=1 "+ condition2 + 
		 "\n ), "+
		 
		 "\n dataTonKho as  "+ 
		 "\n (  "+ 
		 "\n 	select kh_fk PK_SEQ, ngaynhap ngay, sanpham_fk, sp.MA, sp.TEN, SOLUONG as AVAILABLE "+ 
		 "\n 	from ( SELECT KH_FK, PK_SEQ, NGAY ngaynhap, SANPHAM_FK, SOLUONG FROM TONKHO_KHACHHANG ) dh "+ 
		 "\n 	inner join sanpham sp on sp.PK_SEQ = dh.sanpham_fk "+ 
		 "\n  	where 1=1 "+ condition + 
		 "\n ) "+  
		 
		"\n select SMARTID [MaKhachHang], KHTEN [TenKhachHang], NGAYNHAP [Ngay], SPMA [MaSanPham], SPTEN [TenSanPham], " +
		"\n sum(cast(SOLUONG as float)) [Order], sum(cast(AVAILABLE as float)) [Stock] INTO #LIST "+ 
		"\n from "+ 
		"\n (  "+ 
		"\n 	select infoKH.PK_SEQ, infoKH.smartid, infoKH.KHTEN, isnull(data.ma, '') SPMA, isnull(data.TEN,'') SPTEN,data.NGAYNHAP,isnull(data.SOLUONG,0) SOLUONG,0 AVAILABLE   "+ 
		"\n 	from data "+ 
		"\n 	inner join infoKH on infoKH.PK_SEQ=data.khachhang_fk "+ 
		"\n 	where 1=1 and  isnull(data.soluong,0) !=0  "+ 
		"\n 	union all  "+ 
		"\n 	select infoKH.PK_SEQ, infoKH.smartid, infoKH.KHTEN, isnull(d.ma, '') SPMA, isnull(d.TEN,'') SPTEN,d.Ngay NGAYNHAP,0 SOLUONG,isnull(d.AVAILABLE,0) AVAILABLE   "+ 
		"\n 	from dataTonKho d  "+ 
		"\n 	inner join infoKH on  d.PK_SEQ=infoKH.PK_SEQ  "+ 
		"\n 	where 1=1 and  isnull(d.AVAILABLE,0)!=0   "+ 
		"\n )a   "+ 
		"\n group by SMARTID,KHTEN,NGAYNHAP, SPMA, SPTEN "; 
		//"\n order by PK_SEQ,KHTEN,NGAYNHAP,SPTEN ";
				 
		 /*"\n select infoKH.PK_SEQ [MaKhachHang], infoKH.KHTEN [TenKhachHang], isnull(data.TEN,d.TEN) [TenSanPham], tg.Ngay [Ngay], isnull(data.SOLUONG,0) [Order], isnull(d.AVAILABLE,0) [Stock] "+ 
		 "\n from dbo.uf_CacNgayTrongKhoangThoiGian('"+ tungay +"','"+ denngay +"') tg  "+ 
		 "\n inner join infoKH on 1=1  "+ 
		 "\n left join data  on data.NGAYNHAP=tg.Ngay and infoKH.PK_SEQ=data.khachhang_fk  "+ 
		 "\n outer apply   "+ 
		 "\n ( 	select sanpham_fk,sp.TEN, SOLUONG as AVAILABLE    "+ 
		 "\n 	from TONKHO_KHACHHANG  tk   "+ 
		 "\n 	inner join sanpham sp on sp.PK_SEQ=tk.sanpham_fk "+
		 "\n 	where ngay = tg.Ngay and kh_fk=infoKH.pk_seq "+ 
		 "\n ) d "+ 
		 "\n where 1=1 and  (isnull(d.AVAILABLE,0)!=0 or isnull(data.soluong,0) !=0)   "+ 
		 "\n order by PK_SEQ,KHTEN,NGAYNHAP,isnull(data.TEN,d.TEN) ";*/
		//system.out.println("query : "+ query);
		return query;
	}

	private String getSearchQuery(HttpServletRequest request, IKhachhangTTList obj)
	{		
		Utility util = new Utility();
		String ten = util.antiSQLInspection(request.getParameter("khTen"));
		if ( ten == null)
			ten = "";
		obj.setTen(ten);

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if ( nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String hchId = util.antiSQLInspection(request.getParameter("hchTen"));
		if (hchId == null)
			hchId = "";    	
		obj.setHchId(hchId);

		String kbhId = util.antiSQLInspection(request.getParameter("kbhTen"));
		if (kbhId == null)
			kbhId = "";    	
		obj.setKbhId(kbhId);

		String vtchId = util.antiSQLInspection(request.getParameter("vtchTen"));
		if (vtchId == null)
			vtchId = "";    	
		obj.setVtId(vtchId);

		String lchId = util.antiSQLInspection(request.getParameter("lchTen"));
		if (lchId == null)
			lchId = "";    	
		obj.setLchId(lchId);

		String diadiemId = util.antiSQLInspection(request.getParameter("diadiemId"));
		if (diadiemId == null)
			diadiemId = "";    	
		obj.setDiadiemId(diadiemId);

		String xuatkhau = util.antiSQLInspection(request.getParameter("xuatkhau"));
		if (xuatkhau == null)
			xuatkhau = "0";    	
		obj.setXuatkhau(xuatkhau);

		String diachi = util.antiSQLInspection(request.getParameter("diachi"));
		if (diachi == null)
			diachi = "";    	
		obj.setDiachi(diachi.trim());

		String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));
		if (maFAST == null)
			maFAST = "";    	
		obj.setMaFAST(maFAST);

		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		if (ddkdId == null)
			ddkdId = "";    	
		obj.setDdkdId(ddkdId);

		String tbhId = util.antiSQLInspection(request.getParameter("tbhId"));
		if (tbhId == null)
			tbhId = "";    	
		obj.setTbhId(tbhId);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai")); 	
		if (trangthai == null)
			trangthai = "";    		
		obj.setTrangthai(trangthai);

		String tungay = util.antiSQLInspection(request.getParameter("tungay")); 	
		if (tungay == null)
			tungay = "";    		
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay")); 	
		if (denngay == null)
			denngay = "";    		
		obj.setDenngay(denngay);
		//System.out.println("[den ngay] : " + obj.getDenngay());

		String loaiKH = util.antiSQLInspection(request.getParameter("loaikh")); 	
		if (loaiKH == null)
			loaiKH = "";    		
		obj.setloaiKH(loaiKH);

		String hopdong = util.antiSQLInspection(request.getParameter("hopdong")); 	
		if (hopdong == null)
			hopdong = "";    		
		obj.setHopdong(hopdong);
		String vung = util.antiSQLInspection(request.getParameter("vungid"));
		if ( vung == null)
			vung = "";
		obj.setVungid(vung);
		String mien = util.antiSQLInspection(request.getParameter("mienid"));
		if ( mien == null)
			mien = "";
		obj.setMienid(mien);

		String dayOfWeekId = util.antiSQLInspection(request.getParameter("dayOfWeekId"));
		if ( dayOfWeekId == null)
			dayOfWeekId = "";
		obj.setDayOfWeekId(dayOfWeekId);

		String query = "\n select tk.Ngay as [Ngày],kbh.TEN as [Kênh Bán Hàng], tt.TEN as [Tỉnh Thành],npp.TEN as [Nhà Phân Phối],  " +
		"\n  ddkd.ten as [Trình Dược Viên], kh.SMARTID as [Mã KH], kh.TEN as [Khách Hàng], sp.ma as [Mã SP], " +
		"\n  sp.ten as [Sản Phẩm], cl.ten as [Chủng loại],nh.ten as [Nhãn Hàng], " +
		"\n  CAST(tk.SoLuong AS FLOAT) as [Tồn Kho] " +
		"\n  from TONKHO_KHACHHANG tk   " +
		"\n inner join sanpham sp on sp.pk_Seq = tk.sanpham_fk	" +
		"\n  inner join khachhang kh on tk.kh_fk = kh.pk_seq     " +
		"\n  left join" +
		"\n  ( " +
		"\n     select khachhang_fk,max(ddkd_fk) ddkd_fk" +
		"\n     from khachhang_tuyenbh kht " +
		"\n     inner join tuyenbanhang tbh on kht.tbh_fk = tbh.pk_seq" +
		"\n     group by khachHang_fk " +
		"\n  )ddkd_kh on ddkd_kh.khachhang_fk = kh.pk_seq	" +
		"\n left join daidienkinhdoanh ddkd on ddkd.pk_Seq = ddkd_kh.ddkd_fk" +
		"\n left join loaicuahang lch on kh.lch_fk = lch.pk_seq     " + 
		"\n left join nhaphanphoi npp on tk.npp_fk = npp.pk_seq " +
		"\n  left join KENHBANHANG kbh on kbh.PK_SEQ = kh.KBH_FK " +
		"\n  left join Chungloai cl on cl.PK_SEQ = sp.chungloai_FK " +
		"\n  left join nhanhang nh on nh.PK_SEQ = sp.nhanhang_FK " +
		"\n  left join TINHTHANH tt on tt.PK_SEQ = kh.TINHTHANH_FK " +
		"\n where 1 = 1 ";
		 
		if(nppId.trim().length() > 0)
			query += " and tk.npp_fk = '" + nppId + "' ";

		geso.dms.center.util.Utility ut = new geso.dms.center.util.Utility();	
		query +=  "\n and tk.npp_fk in "+ ut.quyen_npp(obj.getUserId())+ " ";
		if (ten.length()>0)
		{ 
			query = query + "\n and ( upper(dbo.ftBoDau(kh.ten)) like upper(N'%" + util.replaceAEIOU(ten) + "%') or kh.smartid like upper(N'%" + ten.trim()+ "%')) ";			
		}

		if (kbhId.length()>0){
			query = query + "\n and kh.kbh_fk ='" + kbhId + "'";	
		}
		
		if (loaiKH.length()>0){
			query = query + "\n and kh.lch_fk='" + lchId + "'";			
		}
		
		if(diachi.length()>0)
		{
			query+="\n and (upper(dbo.ftBoDau(kh.diachi)) like (N'%" + util.replaceAEIOU(diachi) + "%') )  ";
		}

		if(maFAST.length()>0)
		{
			query+= "\n and kh.maFAST like '%"+maFAST+"%' ";
		}

		if(ddkdId.length()>0 && dayOfWeekId.length()>0)
		{
			query+= "\n and kh.pk_Seq in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select PK_SEQ from tuyenbanhang where ddkd_Fk='"+ddkdId+"' and ngayId = "+dayOfWeekId+")) ";
		}
		else if(ddkdId.length()>0 )
		{
			query+= "\n and kh.pk_Seq in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select PK_SEQ from tuyenbanhang where ddkd_Fk='"+ddkdId+"')) ";
		}
		else if(dayOfWeekId.length()>0)
		{
			query+= "\n and kh.pk_Seq in (select KHACHHANG_FK from KHACHHANG_TUYENBH where tbh_fk in (select pk_seq from tuyenbanhang where ngayId = "+dayOfWeekId+") ) ";
		}

		if (tungay.length() > 0)
		{
			query = query + "\n and tk.Ngay>='" + tungay + "'";
		}

		if (denngay.length() > 0)
		{
			query = query + "\n and tk.Ngay<='" + denngay + "'";
		}
		if (vung.length() > 0)
		{
			query = query + "\n and v.pk_seq='" + vung + "'";
		}
		if (mien.length() > 0)
		{
			query = query + "\n and tt.pk_seq='" + mien + "'";
		}
		
		query +="\n order by tk.ngay  desc,tt.TEN  desc,npp.TEN  desc";
		System.out.println("Query tìm kiếm: "+query);
		return query;
	}	

	private String getSearchQuery_DoiThu(HttpServletRequest request, IKhachhangTTList obj)
	{		
		Utility util = new Utility();
		String ten = util.antiSQLInspection(request.getParameter("khTen"));
		if ( ten == null)
			ten = "";
		obj.setTen(ten);

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if ( nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String hchId = util.antiSQLInspection(request.getParameter("hchTen"));
		if (hchId == null)
			hchId = "";    	
		obj.setHchId(hchId);

		String kbhId = util.antiSQLInspection(request.getParameter("kbhTen"));
		if (kbhId == null)
			kbhId = "";    	
		obj.setKbhId(kbhId);

		String vtchId = util.antiSQLInspection(request.getParameter("vtchTen"));
		if (vtchId == null)
			vtchId = "";    	
		obj.setVtId(vtchId);

		String lchId = util.antiSQLInspection(request.getParameter("lchTen"));
		if (lchId == null)
			lchId = "";    	
		obj.setLchId(lchId);


		String diadiemId = util.antiSQLInspection(request.getParameter("diadiemId"));
		if (diadiemId == null)
			diadiemId = "";    	
		obj.setDiadiemId(diadiemId);


		String xuatkhau = util.antiSQLInspection(request.getParameter("xuatkhau"));
		if (xuatkhau == null)
			xuatkhau = "0";    	
		obj.setXuatkhau(xuatkhau);

		String diachi = util.antiSQLInspection(request.getParameter("diachi"));
		if (diachi == null)
			diachi = "";    	
		obj.setDiachi(diachi.trim());


		String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));
		if (maFAST == null)
			maFAST = "";    	
		obj.setMaFAST(maFAST);


		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		if (ddkdId == null)
			ddkdId = "";    	
		obj.setDdkdId(ddkdId);


		String tbhId = util.antiSQLInspection(request.getParameter("tbhId"));
		if (tbhId == null)
			tbhId = "";    	
		obj.setTbhId(tbhId);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai")); 	
		if (trangthai == null)
			trangthai = "";    		
		obj.setTrangthai(trangthai);

		String tungay = util.antiSQLInspection(request.getParameter("tungay")); 	
		if (tungay == null)
			tungay = "";    		
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay")); 	
		if (denngay == null)
			denngay = "";    		
		obj.setDenngay(denngay);
		//System.out.println("[den ngay] : " + obj.getDenngay());

		String loaiKH = util.antiSQLInspection(request.getParameter("loaikh")); 	
		if (loaiKH == null)
			loaiKH = "";    		
		obj.setloaiKH(loaiKH);

		String hopdong = util.antiSQLInspection(request.getParameter("hopdong")); 	
		if (hopdong == null)
			hopdong = "";    		
		obj.setHopdong(hopdong);
		String vung = util.antiSQLInspection(request.getParameter("vungid"));
		if ( vung == null)
			vung = "";
		obj.setVungid(vung);
		String mien = util.antiSQLInspection(request.getParameter("mienid"));
		if ( mien == null)
			mien = "";
		obj.setMienid(mien);

		String dayOfWeekId = util.antiSQLInspection(request.getParameter("dayOfWeekId"));
		if ( dayOfWeekId == null)
			dayOfWeekId = "";
		obj.setDayOfWeekId(dayOfWeekId);

		String query = 
			 "\n 			  select tk.Ngay as [Ngày],kbh.TEN as [Kênh Bán Hàng],v.ten as [Miền],kv.ten as [Khu vực],tt.TEN as [Tỉnh Thành]  " + 
			 "\n 				,'' as [Địa Bàn]	,ddkd.ten as [Trình Dược Viên]	,kh.smartId as [Mã KH], kh.TEN as [Khách Hàng],tk.SANPHAMTEN as [Sản Phẩm] ,CAST(tk.SoLuong AS FLOAT) as [Tồn Kho], " +
			 "\n				convert(float,isnull(tk.gia,0)) [Giá], isnull(tk.ctkm,'') [CTKM] " + 
			 "\n 			  from KHACHHANG_KHODOITHU tk   " +
			 "\n 			  inner join khachhang kh on tk.kh_fk = kh.pk_seq     " +
			 "\n	  		  left join" +
			 "\n	  		  (" +
			 "\n				 select khachhang_fk,max(ddkd_fk) ddkd_fk" +
			 "\n				 from khachhang_tuyenbh kht " +
			 "\n				 inner join tuyenbanhang tbh on kht.tbh_fk = tbh.pk_seq" +
			 "\n				 group by khachHang_fk " +
			 "\n	  		  )ddkd_kh on ddkd_kh.khachhang_fk = kh.pk_seq	" + 
			 "\n			  left join daidienkinhdoanh ddkd on ddkd.pk_Seq = ddkd_kh.ddkd_fk" +
			 "\n			  inner join nhaphanphoi npp on npp.pk_seq = kh.npp_fk	" +
			 "\n 			  inner join loaicuahang lch on kh.lch_fk = lch.pk_seq      " +
			 "\n 			  inner join KENHBANHANG kbh on kbh.PK_SEQ = kh.KBH_FK  " + 
			 "\n 			  left join TINHTHANH tt on tt.PK_SEQ = kh.TINHTHANH_FK " +
			 "\n		      left join khuvuc kv on kv.pk_seq = npp.khuvuc_fk  " + 
			 "\n 			  left join VUNG v on v.PK_SEQ = kv.VUNG_FK  " +
			 "\n			  where 1=1 	";		
		if(nppId.trim().length() > 0)
			query += " and tk.npp_fk = '" + nppId + "' ";

		geso.dms.center.util.Utility ut = new geso.dms.center.util.Utility();	
		if (ten.length()>0)
		{ 
			query = query + "\n and ( upper(dbo.ftBoDau(kh.ten)) like upper(N'%" + util.replaceAEIOU(ten) + "%') or kh.smartid like upper(N'%" + ten.trim()+ "%')) ";			
		}

		if (kbhId.length()>0){
			query = query + "\n and kh.kbh_fk ='" + kbhId + "'";	
		}
		if (loaiKH.length()>0){
			query = query + "\n and kh.lch_fk='" + lchId + "'";			
		}
		if(diachi.length()>0)
		{
			query+="\n and (upper(dbo.ftBoDau(kh.diachi)) like (N'%" + util.replaceAEIOU(diachi) + "%') )  ";
		}

		if(maFAST.length()>0)
		{
			query+= "\n and kh.maFAST like '%"+maFAST+"%' ";
		}

		if(ddkdId.length()>0 && dayOfWeekId.length()>0)
		{
			query+= "\n and kh.pk_Seq in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select PK_SEQ from tuyenbanhang where ddkd_Fk='"+ddkdId+"' and ngayId = "+dayOfWeekId+")) ";
		}
		else if(ddkdId.length()>0 )
		{
			query+= "\n and kh.pk_Seq in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select PK_SEQ from tuyenbanhang where ddkd_Fk='"+ddkdId+"')) ";
		}
		else if(dayOfWeekId.length()>0)
		{
			query+= "\n and kh.pk_Seq in (select KHACHHANG_FK from KHACHHANG_TUYENBH where tbh_fk in (select pk_seq from tuyenbanhang where ngayId = "+dayOfWeekId+") ) ";
		}

		if (tungay.length() > 0)
		{
			query = query + "\n and tk.Ngay>='" + tungay + "'";
		}

		if (denngay.length() > 0)
		{
			query = query + "\n and tk.Ngay<='" + denngay + "'";
		}
		if (vung.length() > 0)
		{
			query = query + "\n and v.pk_seq='" + vung + "'";
		}
		if (mien.length() > 0)
		{
			query = query + "\n and tt.pk_seq='" + mien + "'";
		}
		query +="\n order by tk.ngay  desc,v.TEN  desc,tt.TEN desc";




		//System.out.println("Query KHO_DOITHU: " + query + "\n");


		return query;
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void TaoBaoCao(Workbook workbook,IKhachhangTTList obj,String query,int sheetnum,String nguoitao )throws Exception
	{
		try{


			Worksheets worksheets = workbook.getWorksheets();
			if(sheetnum == 1)
				workbook.getWorksheets().addSheet();
			Worksheet worksheet = worksheets.getSheet(sheetnum);
			if(sheetnum == 0)
				worksheet.setName("KhachHang");
			else
				worksheet.setName("DoiThu");

			Cells cells = worksheet.getCells();
			cells.setRowHeight(0, 50.0f);
			Cell cell = cells.getCell("A1");
			if(sheetnum == 0)
				ReportAPI.getCellStyle(cell, Color.RED, true, 16,"TỒN KHO KHÁCH HÀNG");
			else
				ReportAPI.getCellStyle(cell, Color.RED, true, 16,"TỒN KHO ĐỐI THỦ");

			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10, "Ngày tạo : "+ getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10,"Người tạo : " +nguoitao );

			worksheet.setGridlinesVisible(false);
			dbutils db = new dbutils();
			ResultSet rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int countRow = 8;

			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(countRow,i-1 );cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, Color.YELLOW, BorderLineType.THIN, true, 0);	

			}
			countRow ++;


			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{
					cell = cells.getCell(countRow,i-1 );
					if(rsmd.getColumnType(i) == Types.DOUBLE )
					{
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				++countRow;
			}

			if(rs!=null)rs.close();
			if(db!=null){
				db.shutDown();
			}


		}catch(Exception ex){

			//System.out.println("Errrorr : "+ex.toString());
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}

	private void ExportToExcel(HttpServletResponse response,OutputStream out,IKhachhangTTList obj,HttpServletRequest request,String nguoitao )throws Exception
	{
		try{ 		
			response.setContentType("application/xlsm");
    		response.setHeader("Content-Disposition", "attachment; filename=BCTonKhoKhachHang.xlsm");
    		String query = getSearchQuery(request, (IKhachhangTTList) obj);

			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(workbook,obj,query,0,nguoitao);	
		//	query = getSearchQuery_DoiThu(request, (IKhachhangTTList) obj);
			//TaoBaoCao(workbook,obj,query,1,nguoitao);	
			workbook.save(out);	

		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}

	}


}
