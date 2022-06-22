package geso.dms.center.servlets.reports;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;



import java.sql.ResultSetMetaData;
import java.sql.Types;

import geso.dms.center.beans.stockintransit.IStockintransit;

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


public class BCTuyenNVBH extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	String query="";
   
    public BCTuyenNVBH() {
        super();
        }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		 Utility util = new Utility();
		 String querystring = request.getQueryString();
		 String  userId = util.getUserId(querystring);
		 IStockintransit obj = new Stockintransit();
	    obj.setuserId(userId);
	    obj.init();	    
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/BCTuyenNVBH.jsp";
		response.sendRedirect(nextJSP);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    HttpSession session = request.getSession();
		String userTen = (String)session.getAttribute("userTen");
		String userId = (String)session.getAttribute("userId");

	    IStockintransit obj = new Stockintransit();
		try{
			obj.settungay(request.getParameter("Sdays"));
			obj.setdenngay(request.getParameter("Edays"));
			obj.setDdkd(request.getParameter("ddkds"));
			String action = request.getParameter("action");
			obj.setuserId(userId);
			obj.setuserTen(userTen);

			Utility util= new Utility();
			if (action.equals("create")) {
				response.setContentType("application/xlsm");
		        response.setHeader("Content-Disposition", "attachment; filename=BCTuyenNVBH"+util.setTieuDe(obj)+".xlsm");
		        OutputStream out = response.getOutputStream();
				setQuery(obj);
				ExportToExcel(out,obj);
			}	
			
		}catch(Exception ex){
			session.setAttribute("errors", ex.getMessage());
		}
		obj.init();
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/BCTuyenNVBH.jsp";
		response.sendRedirect(nextJSP);
	}
	private void ExportToExcel(OutputStream out,IStockintransit obj)throws Exception{
		try{ 
			
			
			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			CreateHeader(workbook,obj);
			FillData(workbook,obj);
			
			workbook.save(out);	
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}

	private void CreateHeader(Workbook workbook,IStockintransit obj) throws Exception {
		try {
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("Sheet1");

			Cells cells = worksheet.getCells();
			cells.setRowHeight(0, 50.0f);
			Cell cell = cells.getCell("A1");
			ReportAPI.getCellStyle(cell, Color.RED, true, 16,
					"Báo Cáo Kết Quả Đi Tuyến NVBH");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10,
					"Từ ngày : " + obj.gettungay() + " Tới ngày: " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10, "Ngày tạo : "
					+ obj.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10,
					"Người tạo : " + obj.getuserTen());
			
			
		} catch (Exception ex) {
			System.out.println(ex.toString());
			throw new Exception("Khong the tao duoc Header cho bao cao.!!!");
		}
	}	
	private void FillData(Workbook workbook,IStockintransit obj)throws Exception{
		try{
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setGridlinesVisible(false);
			Cells cells = worksheet.getCells();
			dbutils db = new dbutils();
			ResultSet rs = db.get(getQuery());
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			Cell cell;
			int countRow = 10;

			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(countRow,i-1 );cell.setValue(rsmd.getColumnName(i));
				cells.setColumnWidth(i, 20.0f);
				ReportAPI.setCellBackground(cell, new Color(70,130,180), BorderLineType.THIN, true, 0);	
			 
			}
			countRow ++;
			
			int stt = 0;
			while(rs.next())
			{
				
				stt++;
			
				for(int i =1;i <=socottrongSql ; i ++)
				{
					cell = cells.getCell(countRow,i-1 );
					if(rsmd.getColumnName(i).equals("STT"))
					{					
						cell.setValue(stt);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						//System.out.println("STT: "+stt);

					}else
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL)
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
			
			System.out.println("Errrorr : "+ex.toString());
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}
	private void setQuery(IStockintransit obj)
	{
		Utility Ult =new Utility();
		query = 
				" select a.Ngay,a.TENNVBH,a.DiaBan,a.KhuVuc,a.TongSoKH,KHDungTuyen, case  when isnull(a.TongSoKH,0) = 0 then 0 else (isnull(KHDungTuyen,0)/(a.TongSoKH*1.0))*100 end as TyLe from"
						 +"\n ("
						 +"\n 	select DISTINCT ddkd.pk_seq as MaNVBH, convert(varchar(10),ddkh.ngay,120) as Ngay,ddkd.TEN as TENNVBH,db.TEN as DiaBan,kv.TEN as KhuVuc, ddkh.SOKH TongSoKH "
						 +"\n  	from DAIDIENKINHDOANH ddkd left join "
						 +"\n 	ddkd_sokh ddkh on ddkd.PK_SEQ = ddkh.ddkd_fk "
						 +"\n 	left join DIABAN db on db.PK_SEQ = ddkd.diaban_fk"
						 +"\n 	left join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK"
						 +"\n ) a left join "
						 +"\n ("
						 +"\n select ddkd.pk_seq as MaNVBH,convert(varchar(10),ddkh.thoidiem,120) as Ngay,ddkd.TEN as TENNVBH,db.TEN as DiaBan,kv.TEN as KhuVuc,COUNT(distinct ddkh.khachhang_fk) as KHDungTuyen"
						 +"\n  from DAIDIENKINHDOANH ddkd left join "
						 +"\n ddkd_khachhang ddkh on ddkd.PK_SEQ = ddkh.ddkd_fk"
						 +"\n left join DIABAN db on db.PK_SEQ = ddkd.diaban_fk"
						 +"\n left join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK"
						 +"\n where isdungtuyen = 1 "
						 +"\n group by ddkd.PK_SEQ,convert(varchar(10),ddkh.thoidiem,120),ddkd.TEN,db.TEN,kv.TEN"
						 +"\n ) "
						 +"\n b on a.MaNVBH = b.MaNVBH"
						 +"\n and a.Ngay = b.Ngay "
						 +"\n where a.Ngay  >='" + obj.gettungay() +"' and a.Ngay  <='" + obj.getdenngay() +"' ";
			        if(obj.getDdkd().length()>0)
			        	query +=" and a.MaNVBH ='"+ obj.getDdkd() +"'";
				System.out.println("QR : "+query);
	}
	private String getQuery()
	{
		return query;
	}

}
