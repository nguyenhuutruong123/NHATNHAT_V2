package geso.dms.center.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
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
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Worksheet;


public class ThucDatChiTieuNPP extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	
	public ThucDatChiTieuNPP() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		obj.setuserId(userId);
		obj.init();

		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/ThucDatChiTieuNPP.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();	
		Utility util = new Utility();

		obj.setuserId((String)session.getAttribute("userId")==null?"":
			(String) session.getAttribute("userId"));
		obj.setuserId((String) session.getAttribute("userId"));

		obj.setuserTen((String)session.getAttribute("userTen")==null? "":
			(String) session.getAttribute("userTen"));

		obj.setnppId(util.antiSQLInspection(request.getParameter("nppId"))==null?"":
			util.antiSQLInspection(request.getParameter("nppId")));

		obj.setkenhId(util.antiSQLInspection(request.getParameter("kenhId"))==null? "":
			util.antiSQLInspection(request.getParameter("kenhId")));

		obj.setdvkdId(util.antiSQLInspection(request.getParameter("dvkdId"))==null? "":
			util.antiSQLInspection(request.getParameter("dvkdId")));

		obj.setMonth(util.antiSQLInspection(request.getParameter("month"))==null? "":
			util.antiSQLInspection(request.getParameter("month")));

		obj.setYear(util.antiSQLInspection(request.getParameter("year"))==null? "":
			util.antiSQLInspection(request.getParameter("year")));	   	 

		obj.setvungId(util.antiSQLInspection(request.getParameter("vungId"))==null? "":
			util.antiSQLInspection(request.getParameter("vungId")));	   	 

		obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId"))==null? "":
			util.antiSQLInspection(request.getParameter("khuvucId")));	 


		obj.setdvdlId(util.antiSQLInspection(request.getParameter("dvdlId"))==null? "":
			util.antiSQLInspection(request.getParameter("dvdlId")));
		obj.setLoaiNv(util.antiSQLInspection(request.getParameter("loainv"))==null? "":
			util.antiSQLInspection(request.getParameter("loainv")));

		obj.setDdkd(util.antiSQLInspection(request.getParameter("ddkdId"))==null? "":
			util.antiSQLInspection(request.getParameter("ddkdId")));


		String []fieldsHien = request.getParameterValues("fieldsHien");
		obj.setFieldShow(fieldsHien);		 


		String nextJSP = request.getContextPath() + "/pages/Center/ThucDatChiTieuSR.jsp";		 
		try
		{
			String action=util.antiSQLInspection(request.getParameter("action"));
			if(action.equals("Taomoi")){
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=ThucHienChiTieu.xlsm");
				OutputStream out = response.getOutputStream();
				ExportToExcel(out,obj);			}			
		}catch(Exception ex){
			obj.setMsg(ex.getMessage());
		}
		obj.init();	    
		session.setAttribute("obj", obj);
		session.setAttribute("userId", obj.getuserId());		
		response.sendRedirect(nextJSP);
	}

	private String GetExcelColumnName(int columnNumber)
	{
		int dividend = columnNumber;
		String columnName = "";
		int modulo;

		while (dividend > 0)
		{
			modulo = (dividend - 1) % 26;
			columnName = (char)(65 + modulo) + columnName;
			dividend = (int)((dividend - modulo) / 26);
		} 

		return columnName;
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook, IStockintransit obj, int sheetNum )throws Exception
	{
		try
		{
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(sheetNum);
			com.aspose.cells.Cells cells = worksheet.getCells();

			String nhanvien = "SR";
			
			dbutils db = new dbutils();
			
			cells.merge(0, 0, 0, 3 );		
			com.aspose.cells.Cell cell = cells.getCell("A1");
			cell.setValue("TH???C ?????T CH??? TI??U " + nhanvien);
			ReportAPI.setBorder_Style_MergerCell(cells, 0, 0, 0, 3, BorderLineType.NONE, Color.BLACK, cell.getStyle());
			
			cells.merge(1, 0, 1, 3 );
			cell = cells.getCell("A2");
			cell.setValue("Ng??y t???o " + getDateTime());
			ReportAPI.setBorder_Style_MergerCell(cells, 1, 1, 0, 3, BorderLineType.NONE, Color.BLACK, cell.getStyle());
			
			String dauthang = obj.getYear() + "-" + (obj.getMonth().trim().length() > 1 ? obj.getMonth() : "0" + obj.getMonth()) + "-01";
			
			String query = "";
			String queryCHITIEU = "";
			String queryTHUCDAT = "";
			
			//B1 L???y ti??u ch??
			query =  " select b.PK_SEQ, b.DIENGIAI, b.NHOMSP_FK, b.TIEUCHI,dbo.[Getthuong_chitieu](b.pk_seq,3,(" +
							" select distinct v.PK_SEQ from PHAMVIHOATDONG a inner join NHAPHANPHOI b "+
							 "  on a.Npp_fk=b.PK_SEQ inner join TINHTHANH tt on tt.PK_SEQ=b.TINHTHANH_FK "+
							 "	inner join VUNG v on v.PK_SEQ=tt.VUNG_FK inner join NHANVIEN nv "+
							 "	on nv.PK_SEQ=a.Nhanvien_fk where nv.PK_SEQ="+obj.getuserId()+
							 ")) as noidung, b.loaiDS   "+
					 " from TIEUCHITINHTHUONG a inner join TIEUCHITHUONG_CHITIET b on a.PK_SEQ = b.TIEUCHITINHTHUONG_FK"+
					 " where a.THANG = " + obj.getMonth() + " and a.NAM = '" + obj.getYear() + "' and a.TRANGTHAI = '1'"+
					 " order by PK_SEQ asc";
			System.out.println("::: LAY TIEU CHI THUONG: " + query);
			ResultSet rs = db.get(query);
			
			String sqlBaocao = "";
			String sqlTieuchi = "";
			String sqlThucdat01 = "";
			String sqlThucdat02 = "";
			String tieuchian="";
			while( rs.next() )
			{
				sqlTieuchi += "[" + rs.getString("DIENGIAI") + "], ";
				sqlThucdat01 += " ISNULL( [" + rs.getString("PK_SEQ") + "].thucdat, 0 ) as [TD_" + rs.getString("DIENGIAI") + "], ";
				sqlThucdat02 += " left join dbo.ufn_KPI_NPP('" + dauthang + "', " + rs.getString("PK_SEQ") + ", " + rs.getString("TIEUCHI") + ", " + rs.getString("nhomsp_fk") + ") [" + rs.getString("PK_SEQ") + "] on a.PK_SEQ = [" + rs.getString("PK_SEQ") + "].NhanVien_FK ";
			
				sqlBaocao += ", [" + rs.getString("DIENGIAI") + "], [TD_" + rs.getString("DIENGIAI") + "], " + 
							 " dbo.TyLeKPI( [TD_" + rs.getString("DIENGIAI") + "], [" + rs.getString("DIENGIAI") + "] ) as tile, " + 
							 " dbo.TinhThuongKPI( dbo.TyLeKPI( [TD_" + rs.getString("DIENGIAI") + "], [" + rs.getString("DIENGIAI") + "] ), " + rs.getString("TIEUCHI") + ", " + rs.getString("loaiDS") + ", [TD_" + rs.getString("DIENGIAI") + "], '" + rs.getString("noidung") + "' ) as thuong ";
				tieuchian +=rs.getString("TIEUCHI")+",";
			}
			rs.close();
			
			System.out.println("::: SQL BAO CAO: " + sqlBaocao);
			if( sqlThucdat01.trim().length() > 0 )
			{
				sqlThucdat01 = sqlThucdat01.substring(0, sqlThucdat01.length() - 2);
				queryTHUCDAT = "select a.PK_SEQ as manhanvien, " + sqlThucdat01 + " from nhaphanphoi a " + sqlThucdat02;
				
				sqlTieuchi = sqlTieuchi.substring(0, sqlTieuchi.length() - 2);
				queryCHITIEU =   "\nselect manhanvien, tennhanvien, " + sqlTieuchi + 
								 "\nfrom " + 
								 "\n( " + 
								 "\n	select c.PK_SEQ as manhanvien, c.TEN as tennhanvien, d.DIENGIAI as tieuchi, b.chitieu " + 
								 "\n	from CHITIEUNHANVIEN a inner join ChiTieuNhanVien_npp b on a.pk_seq = b.CTNV_FK " + 
								 "\n		inner join nhaphanphoi c on b.NhanVien_FK = c.PK_SEQ " + 
								 "\n		inner join TIEUCHITHUONG_CHITIET d on b.TCTCT_FK = d.PK_SEQ  " + 
								 "\n	where b.TRANGTHAI = 1 and a.THANG = '" + obj.getMonth() + "' and a.NAM = '" + obj.getYear() + "'  and b.mien_fk in (" +
							" select distinct v.PK_SEQ from PHAMVIHOATDONG a inner join NHAPHANPHOI b "+
							 "  on a.Npp_fk=b.PK_SEQ inner join TINHTHANH tt on tt.PK_SEQ=b.TINHTHANH_FK "+
							 "	inner join VUNG v on v.PK_SEQ=tt.VUNG_FK inner join NHANVIEN nv "+
							 "	on nv.PK_SEQ=a.Nhanvien_fk where nv.PK_SEQ="+obj.getuserId()+
							 ") " + 
								 "\n) " + 
								 "\nDT PIVOT ( max(chitieu) FOR tieuchi IN ( " + sqlTieuchi + " ) ) AS pvt ";
				System.out.println("SQL chi tieusdad"+queryCHITIEU );
			}
			
			System.out.println("::: L???Y CH??? TI??U: " + queryCHITIEU);
			System.out.println("::: L???Y TH???C ?????T: " + queryTHUCDAT);
			
			query = "select CT.manhanvien, CT.tennhanvien " + sqlBaocao + 
					"from ( " + queryCHITIEU + " ) CT left join ( " + queryTHUCDAT + " ) TD on CT.manhanvien = TD.manhanvien ";
			
			//V??? ti??u ?????
			cell = cells.getCell("A5");
			cell.setValue("M?? nh??n vi??n");
			ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell("B5");
			cell.setValue("T??n nh??n vi??n");
			cells.setColumnWidth(1, 30.0f);
			ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			String[] tieude = sqlTieuchi.split(",");
			String[] array_tieuchian=tieuchian.split(",");
			for( int i = 0; i < tieude.length; i++ )
			{
				int cobatdau = (i * 4) + 2;
				cells.merge(3, cobatdau, 3, cobatdau + 3 );		
				cell = cells.getCell(3, cobatdau );
				cell.setValue( tieude[i].replace("[", "").replace("]", "") );
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				ReportAPI.setBorder_Style_MergerCell(cells, 3, 3, cobatdau, cobatdau + 3, BorderLineType.THIN, Color.BLACK, cell.getStyle());
				
				cell = cells.getCell(4, cobatdau++);
				cell.setValue("Ch??? ti??u");
				cells.setColumnWidth(cobatdau - 1, 13.0f);
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(4, cobatdau++);
				cell.setValue("Th???c ?????t");
				cells.setColumnWidth(cobatdau - 1, 13.0f);
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(4, cobatdau++);
				cell.setValue("T??? l??? ?????t (%)");
				cells.setColumnWidth(cobatdau - 1, 13.0f);
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				if(array_tieuchian[i].equals("3")||array_tieuchian[i].equals("4"))
				{
					cells.hideColumn(cobatdau-1);
				}
				
				cell = cells.getCell(4, cobatdau++);
				cell.setValue("Th?????ng");
				cells.setColumnWidth(cobatdau - 1, 13.0f);
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}

			System.out.println("::: L???Y B??O C??O: " + query);
			ResultSet ctRs = db.get(query);
			ResultSetMetaData rsmd = ctRs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			int countRow = 5;
			while(ctRs.next())
			{
				for(int i = 1; i <= socottrongSql; i++)
				{
					Color c = Color.WHITE;
					cell = cells.getCell(countRow, i - 1 );
					
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						cell.setValue(ctRs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 41);
					}
					else
					{
						cell.setValue(ctRs.getString(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 0);
					}
				}
				
				countRow ++;
			}
			
			if(rs != null) rs.close();
			if(db != null){
				db.shutDown();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception("L???i ! kh??ng th??? t???o b??o c??o !");
		}
	}
	
	private void ExportToExcel(OutputStream out,IStockintransit obj )throws Exception
	{
		try
		{ 			
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			TaoBaoCao(workbook, obj, 0);
			workbook.save(out);			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}

}
