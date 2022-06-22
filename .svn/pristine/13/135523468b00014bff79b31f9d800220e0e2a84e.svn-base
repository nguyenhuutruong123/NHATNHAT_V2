package geso.dms.center.servlets.reports;

import geso.dms.center.beans.bandott.IBandott;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.report.Ireport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

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
import com.aspose.cells.Font;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class BCThucDatChiTieuPivot extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public BCThucDatChiTieuPivot()
	{
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		obj.setuserId(userId);
		//obj.getNppInfo();
		obj.init();
		
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/BCThucDatChiTieuPivot.jsp";
		response.sendRedirect(nextJSP);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		Utility util = new Utility();

		obj.setuserId((String) session.getAttribute("userId") == null ? "" : (String) session.getAttribute("userId"));

		obj.setuserTen((String) session.getAttribute("userTen") == null ? "" : (String) session.getAttribute("userTen"));

		obj.setnppId(util.antiSQLInspection(request.getParameter("nppId")) == null ? "" : util.antiSQLInspection(request.getParameter("nppId")));

		obj.setkenhId(util.antiSQLInspection(request.getParameter("kenhId")) == null ? "" : util.antiSQLInspection(request.getParameter("kenhId")));

		obj.setdvkdId(util.antiSQLInspection(request.getParameter("dvkdId")) == null ? "" : util.antiSQLInspection(request.getParameter("dvkdId")));

		obj.setMonth(util.antiSQLInspection(request.getParameter("month")) == null ? "0" : util.antiSQLInspection(request.getParameter("month")));

		obj.setYear(util.antiSQLInspection(request.getParameter("year")) == null ? "0" : util.antiSQLInspection(request.getParameter("year")));

		
		obj.setToMonth(util.antiSQLInspection(request.getParameter("to_month")) == null ? "0" : util.antiSQLInspection(request.getParameter("to_month")));
		
		
		obj.setvungId(util.antiSQLInspection(request.getParameter("vungId")) == null ? "" : util.antiSQLInspection(request.getParameter("vungId")));

		obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId")) == null ? "" : util.antiSQLInspection(request.getParameter("khuvucId")));

		obj.setdvdlId(util.antiSQLInspection(request.getParameter("dvdlId")) == null ? "" : util.antiSQLInspection(request.getParameter("dvdlId")));

		obj.setDdkd(util.antiSQLInspection(request.getParameter("ddkdId")) == null ? "" : util.antiSQLInspection(request.getParameter("ddkdId")));

		obj.setgsbhId(util.antiSQLInspection(request.getParameter("gsbhId")) == null ? "" : util.antiSQLInspection(request.getParameter("gsbhId")));

		
		String loainv = util.antiSQLInspection(request.getParameter("loainv"));
		if(loainv== null)
			loainv = "NVBH";
		obj.setLoaiNv(loainv);
		
		
		String[] fieldsHien = request.getParameterValues("fieldsHien");
		obj.setFieldShow(fieldsHien);

		String userId = request.getParameter("userId");
		obj.setuserId(userId);
		String view=request.getParameter("view");
		if(view == null)
			view = "";

	
		
	
		
		
		String tctctId =  request.getParameter("tctctId");
		if(tctctId == null)tctctId = "";
		obj.setTctctId(tctctId);
		
		
		String action = util.antiSQLInspection(request.getParameter("action"));
		if (action.equals("Taomoi"))
		{
			try
			{
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCThucDatChiTieuPivot_" + util.setTieuDe(obj) + ".xlsm");
				OutputStream out = response.getOutputStream();

				String query =setQuery(obj);
				ExportToExcel(out, obj, query);
			
				
			} catch (Exception ex)
			{
				ex.printStackTrace();
				obj.init();
				session.setAttribute("obj", obj);
				session.setAttribute("userId", obj.getuserId());
				obj.setMsg("Lỗi không lấy được báo cáo ! Kiểm tra lại chỉ tiêu nhân viên hoặc công thức thưởng");
			}
		
		}else
		{
			obj.init();
			session.setAttribute("obj", obj);
			session.setAttribute("userId", obj.getuserId());
		}
		String nextJSP = request.getContextPath() + "/pages/Center/BCThucDatChiTieuPivot.jsp";
		response.sendRedirect(nextJSP);

	}


	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IStockintransit obj,String query)throws Exception
	{
		dbutils db = new dbutils();
		try{

			

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Báo cáo thức đạt chỉ tiêu");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());

			
			
			ResultSet	rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			
			int location  = 200;
			int row = 0;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i));
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
						int format = 43;
							if(rsmd.getColumnName(i).contains("Tỷ lệ"))	
								format = 10;
						cell.setValue(rs.getDouble(i));
					//	ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						//ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				
				++row;
			}
			if(rs!=null)rs.close();
			
			
			
		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}
		db.shutDown();
	}


	private void ExportToExcel(OutputStream out,IStockintransit obj,String query )throws Exception
	{
		try{ 			

			FileInputStream fstream = null;
			Workbook workbook = new Workbook();

			fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCThucDatChiTieuPivot.xlsm");
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

			TaoBaoCao(workbook, obj, query);
			
			workbook.save(out);
			fstream.close();
						

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	

	public String setQuery (IStockintransit obj) throws SQLException
	{
		String query = "";
		if(obj.getLoaiNv().equals("NPP"))
		{
			query =   "\n select cast(ks.thang as varchar) thang, cast( ks.nam as varchar) nam, npp.TEN NHANVIEN,tc.DIENGIAI LOAI,tctct.DIENGIAI,isnull(nsp.DIENGIAI,'')NSP ,sum(ks.CHITIEU)chitieu,sum(ks.THUCDAT)THUCDAT--,dbo.PhepChia(sum(ks.THUCDAT),sum(ks.CHITIEU))TYLEDAT  " + 
			 "\n from KhoaSoChiTieu_DDKD ks  " + 
			 "\n inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = ks.nhanvien_fk  " + 
			 "\n inner join NHAPHANPHOI npp on npp.PK_SEQ in (select NPP_FK from DAIDIENKINHDOANH_NPP where DDKD_FK = ddkd.PK_SEQ )  " + 
			 "\n inner join tieuchi tc on tc.TIEUCHI = ks.tieuchi and tc.LOAI = 1  " + 
			 "\n inner join TIEUCHITHUONG_CHITIET tctct on tctct.PK_SEQ = ks.tctct_fk  " + 
			 "\n left join NHOMSANPHAMCHITIEU nsp on nsp.PK_SEQ = ks.nsp_fk  " +
			 "\n where ks.nam = "+obj.getYear()+" and ks.thang >="+obj.getMonth()+" and ks.thang <="+obj.getToMonth()+" " ;
			
			if(obj.getnppId().trim().length() >0)
			{
				query +=" and npp.pk_seq = "+ obj.getnppId();
			}
			
			
			query += "\n group by cast(ks.thang as varchar),cast( ks.nam as varchar), npp.TEN,tc.DIENGIAI,tctct.DIENGIAI,isnull(nsp.DIENGIAI,'')  " ;
			  
			
		}
		else
		{
			query =   	"\n select cast(ks.thang as varchar) thang, cast( ks.nam as varchar) nam, ddkd.TEN NHANVIEN,tc.DIENGIAI LOAI,tctct.DIENGIAI,isnull(nsp.DIENGIAI,'')NSP ,ks.CHITIEU,ks.THUCDAT --,ks.TYLEDAT  " + 
						 "\n from KhoaSoChiTieu_DDKD ks  " + 
						 "\n inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = ks.nhanvien_fk  " + 
						 "\n inner join tieuchi tc on tc.TIEUCHI = ks.tieuchi and tc.LOAI = 1  " + 
						 "\n inner join TIEUCHITHUONG_CHITIET tctct on tctct.PK_SEQ = ks.tctct_fk  " + 
						 "\n left join NHOMSANPHAMCHITIEU nsp on nsp.PK_SEQ = ks.nsp_fk  " +
						 "\n where ks.nam = "+obj.getYear()+" and ks.thang >="+obj.getMonth()+" and ks.thang <="+obj.getToMonth()+" " ;
			if(obj.getnppId().trim().length() >0)
			{
				query +=" and ddkd.pk_seq in ( select DDKD_FK from daidienkinhdoanh_npp where npp_fk  = "+ obj.getnppId() + " )";
			}
			if(obj.getDdkd().trim().length() > 0 )
			{
				query +=" and ddkd.pk_seq in ("+obj.getDdkd()+" )";	
			}
		}		 
		
			 
		query += "\n order by NHANVIEN,nam,thang,LOAI  " ;	 
			 
		System.out.println(" getQuery =  "+ query);
		return query ;
	}
	
}
