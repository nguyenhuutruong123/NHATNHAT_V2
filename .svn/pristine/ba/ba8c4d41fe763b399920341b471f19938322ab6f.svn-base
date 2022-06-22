package geso.dms.center.servlets.reports;

import geso.dms.center.beans.bandott.IBandott;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

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

public class SrperformanceGroupSku extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public SrperformanceGroupSku()
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
		String nextJSP = request.getContextPath() + "/pages/Center/SalesrepPerfomanceGroupSku.jsp";
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

		obj.setMonth(util.antiSQLInspection(request.getParameter("month")) == null ? "" : util.antiSQLInspection(request.getParameter("month")));

		obj.setYear(util.antiSQLInspection(request.getParameter("year")) == null ? "" : util.antiSQLInspection(request.getParameter("year")));

		obj.setvungId(util.antiSQLInspection(request.getParameter("vungId")) == null ? "" : util.antiSQLInspection(request.getParameter("vungId")));

		obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId")) == null ? "" : util.antiSQLInspection(request.getParameter("khuvucId")));

		obj.setdvdlId(util.antiSQLInspection(request.getParameter("dvdlId")) == null ? "" : util.antiSQLInspection(request.getParameter("dvdlId")));

		obj.setDdkd(util.antiSQLInspection(request.getParameter("ddkdId")) == null ? "" : util.antiSQLInspection(request.getParameter("ddkdId")));

		obj.setgsbhId(util.antiSQLInspection(request.getParameter("gsbhId")) == null ? "" : util.antiSQLInspection(request.getParameter("gsbhId")));

		String[] fieldsHien = request.getParameterValues("fieldsHien");
		obj.setFieldShow(fieldsHien);

		String userId = request.getParameter("userId");
		obj.setuserId(userId);
		String view=request.getParameter("view");
		if(view == null)
			view = "";

	
		
	
		
		
		String tctctId =  request.getParameter("tctctId");
		obj.setTctctId(tctctId);
		
		
		String action = util.antiSQLInspection(request.getParameter("action"));
		if (action.equals("Taomoi"))
		{
			try
			{
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=ThucHienChiTieuSR_" + util.setTieuDe(obj) + ".xlsm");
				OutputStream out = response.getOutputStream();

				dbutils db = new dbutils();
				ExportToExcel(db,action,out,obj);
				db.shutDown();
				
			} catch (Exception ex)
			{
				ex.printStackTrace();
				obj.init();
				session.setAttribute("obj", obj);
				session.setAttribute("userId", obj.getuserId());
				obj.setMsg("Lỗi không lấy được báo cáo ! Kiểm tra lại chỉ tiêu nhân viên hoặc công thức thưởng");
			}
		} else if (action.equals("Taomoi2")){
			try
			{
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=ThucHienChiTieuSR_" + util.setTieuDe(obj) + ".xlsm");
				OutputStream out = response.getOutputStream();

				dbutils db = new dbutils();
				ExportToExcel(db,action,out,obj);
				db.shutDown();
				
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
		String nextJSP = request.getContextPath() + "/pages/Center/SalesrepPerfomanceGroupSku.jsp";
		response.sendRedirect(nextJSP);

	}



	


	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private void TaoBaoCao(dbutils db,com.aspose.cells.Workbook workbook,IStockintransit obj,String query,int countRow,int column,int sheetNum,String diengiai,String tieuchi,int kt)throws Exception
	{
		try{


			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(sheetNum);
			worksheet.setName(diengiai);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");	
		    ReportAPI.getCellStyle(cell,Color.RED, true, 16, "Thực Hiện Chỉ Tiêu SR");
		    cell = cells.getCell("A4");
		    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Ngày tạo : " + this.getDateTime()); 
		    cell = cells.getCell("A5");
		    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Người tạo : " + obj.getuserTen());
			
			Style style;
			Font font = new Font();
			font.setColor(Color.RED);//mau chu
			font.setSize(16);// size chu
			font.setBold(true);

			ResultSet	rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			System.out.println("Số cột trong SQL: "+socottrongSql);
			if (socottrongSql == 11) socottrongSql = 9;


			for( int i =3 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(countRow, column + i-1 );cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			countRow ++;
			int stt = 0;
			double diem = 0;
			long mang[] = new long[10000];
			while(rs.next())
			{
				
				Color c = Color.WHITE;
				boolean bold =false;
				if(tieuchi.equals("9") )	
				{
					if(rs.getDouble("Tỷ lệ") >= 1)
						c 	= new Color(243,253,207);
					/*if(rs.getString("Loại").equals("Tổng"))
					{
						c 	= Color.YELLOW;
						bold= true;
					}*/
				} 
				mang[stt] = rs.getLong("pk_seq");
			
				if(stt > 0 && mang[stt-1] != rs.getLong("pk_seq") && kt == 0)
				{
					/*ReportAPI.mergeCells(worksheet, countRow, countRow, 2, 9);
					cell = cells.getCell(countRow,2);
					cell.setValue("Tổng Cộng");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell(countRow,10);
					cell.setValue(diem);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 43);*/
					countRow++;
					diem= 0;
				}
				stt++;
				for(int i =3;i <=socottrongSql ; i ++)
				{
					if(i ==socottrongSql)
						diem+= rs.getDouble(i);
					
					
					cell = cells.getCell(countRow,column + i-1 );
					if(rsmd.getColumnName(i).equals("STT"))
					{					
						cell.setValue(stt);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						//System.out.println("STT: "+stt);

					}else
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						int format = 43;
							if(rsmd.getColumnName(i).contains("Tỷ lệ"))	
								format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
					}
				}
				
				++countRow;
			}
			if(rs!=null)rs.close();
			if(stt > 0 && kt == 0)
			{
				/*ReportAPI.mergeCells(worksheet, countRow, countRow, 2, 9);
				cell = cells.getCell(countRow,2);
				cell.setValue("Tổng Cộng");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell(countRow,3);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell(countRow,4);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell(countRow,5);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell(countRow,6);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell(countRow,7);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell(countRow,8);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell(countRow,9);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell(countRow,10);
				cell.setValue(diem);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 43);
				countRow++;
				diem= 0;*/
				countRow++;
			}


		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}
	}


	private void ExportToExcel(dbutils db,String action,OutputStream out,IStockintransit obj )throws Exception
	{
		try{ 			

			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			int sheetNum = 0;
			
			
				if(workbook.getWorksheets().getNumberOfSheets() == sheetNum)
					workbook.getWorksheets().addSheet();
				String query = setQuery(obj, action,db);
				if(action.equals("Taomoi2"))
				{
					query = setQueryTheoSp(obj, action,db);
					TaoBaoCao(db,workbook, obj, query,7,0,sheetNum++,"ThucDatNhanVienTheoSP", "ThucDatNhanVienTheoSP",1);
				}else
					TaoBaoCao(db,workbook, obj, query,7,0,sheetNum++,"ThucDatNhanVienTheo", "ThucDatNhanVienTheo",0);
			workbook.save(out);			

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	

	public String setQuery (IStockintransit obj,String action ,dbutils db) throws SQLException
	{			
		String quyen = "\n select PK_SEQ   " + 
		 "\n from DAIDIENKINHDOANH yddkd  " + 
		 "\n where exists   " + 
		 "\n (  " + 
		 "\n 	select 1 from nhanvien xnv where xnv.pk_seq =  " + obj.getuserId() + 
		 "\n 		and (  	   (  isnull(xnv.loai,0) = 0 and  exists (select 1 from DaiDienKinhDoanh_NPP  where  ddkd_fk = yddkd.pk_seq   )        )	 	   " + 
		 "\n 				or (  xnv.loai = 3 and  exists (select 1 from ddkd_gsbh where gsbh_fk = xnv.gsbh_fk and ddkd_fk = yddkd.pk_seq   )        )	  " + 
		 "\n 				or (  xnv.loai = 2 and  exists ( select 1 from diaban where pk_seq = yddkd.diaban_fk and khuvuc_fk in (select khuvuc_fk from asm_khuvuc where asm_fk =xnv.asm_fk )     )        )	  " + 
		 "\n 		)  " + 
		 "\n 		  " + 
		 "\n )  " ;
		
		
		String query="";
		String sql =  	" select a.loai isTinhLuong,a.tieuchi,a.pk_seq, a.diengiai from tieuchithuong_chitiet a" +
				"\n inner join tieuchitinhthuong b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ where b.THANG = "+obj.getMonth()+" and b.NAM = "+obj.getYear()+
								"\n and b.TRANGTHAI = 1 and b.LOAI = 1  " ;
				if(obj.getTctctId().trim().length() > 0)
					sql +="\n and a.pk_Seq = " + obj.getTctctId();
				
				if(obj.getDdkd().length()  > 0)
				{
					sql += "\n and exists (select 1 from  ChiTieuNhanVien_DDKD ct "
							+ " where ct.TCTCT_FK = a.pk_seq  and ct.NhanVien_FK = '"+obj.getDdkd()+"' and ct.chitieu > 0 ) ";
				}
				
					sql += "\n and exists (select 1 from  ChiTieuNhanVien_DDKD ct inner join chitieunhanvien ctnv on ct.ctnv_fk = ctnv.pk_seq "
							+ " where ct.TCTCT_FK = a.pk_seq and ct.chitieu > 0 and ctnv.trangthai = 1 "
							+ ") ";
				
				System.out.println("QRBC "+sql );
				ResultSet rs = db.get(sql);
				while(rs.next())
				{
					
					String tctctId = rs.getString("pk_seq");
					String tieuchi = rs.getString("tieuchi");
							query +=	"\n select ct.tieuchi as loai, ddkd.pk_seq, Row_number() OVER ( order by ( ddkd.mafast ) desc)[STT]  " +
									"\n		, ddkd.mafast as [MÃ NV], ddkd.Ten as [NVBH],tct.DienGiai [Tên Chỉ Tiêu]  " +
									"\n		, ct.ChiTieu as [Chỉ Tiêu], isnull(td.thucdat,0) [Thực đạt] , dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ) [Tỷ lệ đạt]" +
									"\n 	,[dbo].[TinhThuongKPI]( ct.tctct_fk,dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ), ct.tieuchi, 0, isnull(td.thucdat,0) )[Thuong]"+
									//"\n ,ct.trongso [Trọng Số],[dbo].[TinhThuongKPI]( dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ) ,ct.trongso,dbo.PhepChia(ct.thuchientoida,100) ) [Điểm] " +
									"\n from ChiTieuNhanVien_DDKD ct " +
									"\n inner join DaiDienKinhDoanh ddkd on ct.NhanVien_FK = ddkd.pk_seq " +
								/*	"\n inner join khuvuc kv on ddkd.khuvuc_fk = kv.pk_seq " +*/
									"\n inner join tieuchithuong_chitiet tct on ct.tctct_fk = tct.pk_seq  " +
									
									//"\n left join [dbo].[ufn_KPI_DDKD](0,'"+obj.getYear() +"-"+ obj.getMonth() +"-01"+"'," + tctctId+",null) td on ct.NhanVien_FK = td.NhanVien_FK " +
									"\n outer apply [dbo].[ufn_KPI_DDKD_TEST]('"+obj.getYear() +"-"+ obj.getMonth() +"-01"+"',ct.tctct_fk, ct.NhanVien_FK) td " +
									
									"\n where ct.chitieu > 0  "
									+ " and ct.tctct_fk = " + tctctId+" ";
							if(obj.getDdkd().length()  > 0)
							{
								query += " and ct.NhanVien_FK = '"+obj.getDdkd()+"' ";
							}
							
							query +=  "\n and ct.NhanVien_FK in ("+quyen+") ";
							
							
							query +=	"\n union all ";
					}
				if(query.length() > 0)
				{
					query = query.substring(0,query.length() - 12);
				}
				
				query+= " order by ddkd.pk_seq,loai ";	
		
		System.out.println("query bc= " + query);
		
		return query;
		
	}
	public String setQueryTheoSp (IStockintransit obj,String action ,dbutils db) throws SQLException
	{			
		
		String quyen = "\n select PK_SEQ   " + 
		 "\n from DAIDIENKINHDOANH yddkd  " + 
		 "\n where exists   " + 
		 "\n (  " + 
		 "\n 	select 1 from nhanvien xnv where xnv.pk_seq =  " + obj.getuserId() + 
		 "\n 		and (  	   (  xnv.loai = 0 and  exists (select 1 from DaiDienKinhDoanh_NPP  where  ddkd_fk = yddkd.pk_seq and NPP_FK in ( select npp_fk from phamvihoatdong where nhanvien_fk = xnv.pk_seq )   )        )	 	   " + 
		 "\n 				or (  xnv.loai = 3 and  exists (select 1 from ddkd_gsbh where gsbh_fk = xnv.gsbh_fk and ddkd_fk = yddkd.pk_seq   )        )	  " + 
		 "\n 				or (  xnv.loai = 2 and  exists ( select 1 from diaban where pk_seq = yddkd.diaban_fk and khuvuc_fk in (select khuvuc_fk from asm_khuvuc where asm_fk =xnv.asm_fk )     )        )	  " + 
		 "\n 		)  " + 
		 "\n 		  " + 
		 "\n )  " ;
		
		String query="";
		String sql =  	" select a.loai isTinhLuong,a.tieuchi,a.pk_seq, a.diengiai from tieuchithuong_chitiet a" +
				"\n inner join tieuchitinhthuong b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ where b.THANG = "+obj.getMonth()+" and b.NAM = "+obj.getYear()+
								"\n and b.TRANGTHAI = 1 and b.LOAI = 1  " ;
				if(obj.getTctctId().trim().length() > 0)
					sql +="\n and a.pk_Seq = " + obj.getTctctId();
				
				if(obj.getDdkd().length()  > 0)
				{
					sql += "\n and exists (select 1 from  ChiTieuNhanVien_DDKD ct "
							+ " where ct.TCTCT_FK = a.pk_seq  and ct.NhanVien_FK = '"+obj.getDdkd()+"' and ct.chitieu > 0 ) ";
				}
				
					sql += "\n and exists (select 1 from  ChiTieuNhanVien_DDKD ct inner join chitieunhanvien ctnv on ct.ctnv_fk = ctnv.pk_seq "
							+ " where ct.TCTCT_FK = a.pk_seq and ct.chitieu > 0 and ctnv.trangthai = 1 "
							+ ") ";
				
				System.out.println("QRBC "+sql );
				ResultSet rs = db.get(sql);
				while(rs.next())
				{
					String tctctId = rs.getString("pk_seq");
					String tieuchi = rs.getString("tieuchi");
							query +=	"\n select 0 as loai, ddkd.pk_seq, Row_number() OVER ( order by ( ddkd.mafast ) desc)[STT]  " +
									"\n		, ddkd.mafast as [MÃ NV], ddkd.Ten as [NVBH],tct.DienGiai [Tên Chỉ Tiêu],sp.Ten as [Sản Phẩm]  " +
									"\n		, dpmh.ChiTieu as [Chỉ Tiêu], isnull(td.thucdat,0) [Thực đạt] , dbo.PhepChia(isnull(td.thucdat,0),cast(dpmh.ChiTieu as float) ) [Tỷ lệ]" +
									"\n  " +
									"\n from ChiTieuNhanVien_DDKD ct " +
									"\n inner join DaiDienKinhDoanh ddkd on ct.NhanVien_FK = ddkd.pk_seq " +
								/*	"\n inner join khuvuc kv on ddkd.khuvuc_fk = kv.pk_seq " +*/
									"\n inner join tieuchithuong_chitiet tct on ct.tctct_fk = tct.pk_seq  " +
									"\n inner join ChiTieuNhanVien_DDKD_DoPhuMatHang dpmh on dpmh.tctct_fk = tct.pk_seq and dpmh.CTNV_FK = ct.CTNV_FK  and ct.NhanVien_FK = dpmh.NhanVien_FK " +
									"\n left join [dbo].[ufn_KPI_DDKD_SP](0,'"+obj.getYear() +"-"+ obj.getMonth() +"-01"+"'," + tctctId+",null) td on ct.NhanVien_FK = td.NhanVien_FK "
								  + "\n inner join SanPham sp on td.sanpham_fk = sp.pk_seq and dpmh.SanPham_FK = sp.PK_SEQ" +
									"\n where ct.chitieu > 0  "
									+ " and ct.tctct_fk = " + tctctId+" ";
							if(obj.getDdkd().length()  > 0)
							{
								query += " and ct.NhanVien_FK = '"+obj.getDdkd()+"' ";
							}
							
							query +=  "\n and ct.NhanVien_FK in ("+quyen+") ";
							
							query +=	"\n union all ";
					}
				if(query.length() > 0)
				{
					query = query.substring(0,query.length() - 12);
				}
				
				query+= " order by ddkd.pk_seq,loai ";	
		
		System.out.println("query bc= " + query);
		
		return query;
		
	}

}
