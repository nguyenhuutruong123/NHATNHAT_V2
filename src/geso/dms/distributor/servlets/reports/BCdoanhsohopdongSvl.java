package geso.dms.distributor.servlets.reports;

import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.beans.BCdoanhsohopdong.IBCdoanhsohopdong;
import geso.dms.distributor.beans.BCdoanhsohopdong.imp.BCdoanhsohopdong;
import geso.dms.distributor.beans.lotrinh.*;
import geso.dms.distributor.beans.lotrinh.imp.*;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.CodingErrorAction;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

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

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class BCdoanhsohopdongSvl extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	Utility util = new Utility();
	NumberFormat formatter = new DecimalFormat("#,###,###.###");
	public BCdoanhsohopdongSvl()
	{
		super();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String userTen = (String) session.getAttribute("userTen");
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		session.setAttribute("userId", userId);
		session.setAttribute("tungay", "");
		session.setAttribute("denngay", "");
		session.setAttribute("loi", "");
		session.setAttribute("userTen", userTen);

		ILoTrinh obj = new LoTrinh();
		obj.setUserId(userId);
		obj.setStatus("1");
		obj.init();

		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Distributor/BCdoanhsohopdong.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		IBCdoanhsohopdong obj = new BCdoanhsohopdong();
		
		String nnId = (String)session.getAttribute("nnId");

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));

		

		if (nppId == null)
			nppId = "";
		obj.setnppId(nppId);

		

		
		
		 String userId = util.antiSQLInspection(request.getParameter("userId")==null?"":request.getParameter("userId"));    
		 obj.setUserId(userId);
		
		String tungay = request.getParameter("tungay");
    	if(tungay == null) tungay = ""; else tungay = tungay.trim();
    	obj.setTungay(tungay);
    	
    	String denngay = request.getParameter("denngay");
    	if(denngay == null || denngay.trim().length() <= 0) 
    		denngay = getDateTime();
    	else 
    		denngay = denngay.trim();
    	obj.setDenngay(denngay);


		
		session.setAttribute("loi", "");
		
		obj.init();
		String action = request.getParameter("action");

		if (action.equals("exportmcp"))
		{
			System.out.println("XuatMcp__");
			if(tungay.trim().length() <= 0 || denngay.trim().length() <= 0){
				session.setAttribute("loi", "Bạn phải chọn ngày xem báo cáo"); 
				session.setAttribute("obj", obj);
				String nextJSP = request.getContextPath() + "/pages/Distributor/BCdoanhsohopdong.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				XuatFileExcelSR(response, obj, nnId);
			}
		}  
		else
		{
			
			{
				String status = util.antiSQLInspection(request.getParameter("status"));
				obj.setStatus(status);
			//	obj.createNPP();
				session.setAttribute("obj", obj);
				String nextJSP = request.getContextPath() + "/pages/Distributor/BCdoanhsohopdong.jsp";
				response.sendRedirect(nextJSP);
			}
		}
	}

	

	private void XuatFileExcelSR(HttpServletResponse response, IBCdoanhsohopdong obj, String nnId) throws IOException
	{
		response.setContentType("application/xlsm");
		response.setHeader("Content-Disposition", "attachment; filename=BCdoanhsohopdong.xlsm");
		OutputStream out = response.getOutputStream();
		try
		{
		
			String strfstream = getServletContext().getInitParameter("path") + "\\BCdoanhsohopdong.xlsm";
			
			/*com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2003);
			*/
			
			FileInputStream fstream = new FileInputStream(strfstream);
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells  cells = worksheet.getCells();
			Cell cell = cells.getCell("B3");
			dbutils db = new dbutils();
			String sql="select ten,diachi from nhaphanphoi where pk_seq="+obj.getnppId();
			ResultSet rsch=db.get(sql);
			rsch.next();
			cell = cells.getCell(1, 0 );cell.setValue(rsch.getString("ten"));
			cell = cells.getCell(1, 0 );cell.setValue(rsch.getString("diachi"));
			rsch.close();
			cell = cells.getCell(4, 0 );cell.setValue("(Từ ngày: "+ obj.getTungay().substring(8,10) + " tháng  "+ obj.getTungay().substring(5,7) + " năm  "+ obj.getTungay().substring(0,4) + " Đến ngày "+ obj.getDenngay().substring(8,10) + " tháng  "+ obj.getDenngay().substring(5,7) + " năm  "+ obj.getDenngay().substring(0,4) + ")");
			//cell = cells.getCell(4, 0 );cell.setValue("(Từ ngày: "+ obj.getTungay().substring(1, 2) +"  Đến ngày "+obj.getDenngay());
		
			 sql =" select '' STT,kh.maFAST,kh.TEN,hd.MaHopDong,sum(b.SOTIENAVAT) as SOTIENAVAT,sum(b.SOTIENTT) as SOTIENTT,sum(b.SOTIENAVAT-b.SOTIENTT) as conlai"+
						"\n	 from ERP_THUTIENNPP a "+
						"\n	 inner join  ERP_THUTIENNPP_HOADON b on a.PK_SEQ=b.THUTIENNPP_FK "+
						"\n	 inner join ERP_HOADONNPP c on c.PK_SEQ=b.HOADONNPP_FK "+
						"\n	 inner join ERP_HOADONNPP_DDH d on d.HOADONNPP_FK=c.PK_SEQ "+
						"\n	 inner join ERP_DONDATHANGNPP e on e.PK_SEQ=d.DDH_FK "+
						"\n	 inner join KHACHHANG kh on kh.PK_SEQ=a.KHACHHANG_FK "+
						"\n	 inner join ERP_HOPDONGNPP hd on hd.PK_SEQ=e.HOPDONG_FK "+
						"\n	 where e.HOPDONG_FK is not null and c.NGAYXUATHD>= '"+obj.getTungay()+"' and c.NGAYXUATHD<= '"+obj.getDenngay()+"'   "+
						"\n	 group by  kh.maFAST,kh.TEN,hd.MaHopDong ";
			ResultSet rs = db.get(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
	
			int k = 0;
			int countRow = 6;
			int column = -1;
			int j=1;
			while (rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{
					Color c = Color.WHITE;
					cell = cells.getCell(countRow,column + i);
					
					if(rsmd.getColumnName(i).equals("STT"))
					{
						cell.setValue(j);
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 41);
					}
					else if(rsmd.getColumnName(i).equals("Tỷ lệ DT/DT Trần"))
					{
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 10);
					}
					else
					{
						if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
						{
							cell.setValue(rs.getDouble(i));
							ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 41);
						}
						else
						{
							cell.setValue(rs.getString(i));
							ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 0);
						}
					}
					
				}
				j++;
				++countRow;
			}
			
			workbook.save(out);		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public double getKhoangCachHaversine(double lat1, double long1, double lat2, double long2)
    {
        double R = 6371.00;
        double dLat, dLon, a, c;

        dLat = toRad(lat2 - lat1);
        dLon = toRad(long2 - long1);
        lat1 = toRad(lat1);
        lat2 = toRad(lat2);
        a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Math.abs(R * c * 1000  ); //m
    }
	
	private double toRad(double value)
    {
        return value * Math.PI / 180;
    }
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private String getMonth() 
	{
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private String getYear() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	

	public static void main(String[] args)
	{
		String tu_ngay="2016-05-01";
		System.out.println("COMPARE: " + tu_ngay.substring(0,4));
		System.out.println("COMPARE: " + tu_ngay.substring(5,7));
		System.out.println("COMPARE: " + tu_ngay.substring(8,10));
		
	}
	
	
}
