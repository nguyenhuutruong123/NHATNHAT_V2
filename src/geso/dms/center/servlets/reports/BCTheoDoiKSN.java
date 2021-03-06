package geso.dms.center.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;


public class BCTheoDoiKSN extends HttpServlet {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BCTheoDoiKSN() {
        super();
       
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		String querystring = request.getQueryString();
		Utility util = new Utility();
		
		obj.setuserId(util.getUserId(querystring));
		obj.setuserTen((String) session.getAttribute("userTen"));
			
		obj.init();
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/TheoDoiKhoaSo.jsp";
		
		String view = request.getParameter("view");
	    if(view == null)
	    	view = "";
		if(!view.equals("TT"))
		{
			nextJSP = request.getContextPath() + "/pages/Distributor/TheoDoiKhoaSo.jsp";
			response.sendRedirect(nextJSP);
		}
		else
		{
			 nextJSP = request.getContextPath() + "/pages/Center/TheoDoiKhoaSo.jsp";
			response.sendRedirect(nextJSP);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		OutputStream out = response.getOutputStream();
		Utility util = new Utility();
		
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		
		obj.settungay(util.antiSQLInspection(util.antiSQLInspection(request.getParameter("Sdays"))));
		obj.setdenngay(util.antiSQLInspection(request.getParameter("Edays")));
		
		obj.setuserId(userId!=null? userId:"");
		obj.setuserTen(userTen!=null? userTen:"");	
		
		obj.setvungId(util.antiSQLInspection(request.getParameter("vungId"))!=null?
				util.antiSQLInspection(request.getParameter("vungId")):"");
			
		obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId"))!=null?
				util.antiSQLInspection(request.getParameter("khuvucId")):"");
		
		
		obj.setnppId(util.antiSQLInspection(request.getParameter("nppId"))!=null?
				util.antiSQLInspection(request.getParameter("nppId")):"");
		
		String action = (String) request.getParameter("action");
	
		
		
		String view=request.getParameter("view");
		if(view == null)
			view = "";
		
	    String nppId ="";
		if(view.equals("TT"))
		{
			 nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			obj.setnppId(nppId);
		}else
		{
			nppId=util.getIdNhapp(userId);
			obj.setnppId(nppId);
		}
		
		
		String sql = "";
		if(obj.getvungId().length()>0){
			sql += " and v.pk_seq = '" + obj.getvungId() + "'";
		}
		if(obj.getkhuvucId().length()>0){
			sql += " and kv.pk_seq='"+ obj.getkhuvucId() + "'";
		}
		if(obj.getnppId().length()>0){
			sql +=" and n.pk_seq='" + obj.getnppId() +"'";
		}
		try
		{
			if (action.equals("create")) 
			{			
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCTheoDoiKhoaSoNPP_"+util.setTieuDe(obj)+".xlsm");
				
				String query = setQuery(sql, obj);
		        if(!ExportToExcel(out,obj,query))
		        {
		        	response.setContentType("text/html");
		            PrintWriter writer = new PrintWriter(out);
		            writer.print("Xin loi khong co bao cao trong thoi gian nay");
		            writer.close();
		        }
			}
		}catch(Exception ex)
		{
			obj.setMsg(ex.getMessage());
		}
		obj.init();
		session.setAttribute("obj", obj);
		
		if(!view.equals("TT"))
		{
			String	nextJSP = request.getContextPath() + "/pages/Distributor/TheoDoiKhoaSo.jsp";
			response.sendRedirect(nextJSP);
		}
		else
		{
			String	 nextJSP = request.getContextPath() + "/pages/Center/TheoDoiKhoaSo.jsp";
			response.sendRedirect(nextJSP);
		}
		
	}
	private String setQuery(String sql,IStockintransit obj){
		String query = "SELECT  v.TEN AS Region,kv.TEN AS Area,n.PK_SEQ AS DistributorCode," +
				"		n.SITECODE AS SiteCode,	n.TEN AS Distributor,convert(varchar, k.NGAYGIO, 120) AS ngaygio," +
				"		1 as  Data ,K.NGAYks as Date " +
				"   	 FROM KHOASONGAY k" +
				"		INNER JOIN NHAPHANPHOI n ON n.PK_SEQ = k.NPP_FK" +
				"		INNER JOIN KHUVUC kv ON kv.PK_SEQ = n.KHUVUC_FK" +
				"		INNER JOIN VUNG v ON v.PK_SEQ = kv.VUNG_FK" +
				"	WHERE k.NGAYKS>= '" + obj.gettungay() +"' AND k.NGAYKS <='" + obj.getdenngay() +"' " + sql + " order by k.ngayks";
		return query;
	}
	private boolean ExportToExcel(OutputStream out, IStockintransit obj,String query)throws Exception {
	
		
	FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\THEO-DOI-KHOA-SO-NPP.xlsm");
	Workbook workbook = new Workbook();
	workbook.open(fstream);
	workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

		
		
		boolean isFillData = false;
		CreateHeader(workbook, obj);
		isFillData = FillData(workbook, obj, query);
		if (!isFillData)
			return false;
		workbook.save(out);
		fstream.close();
		return true;	
	}


	private boolean FillData(Workbook workbook, IStockintransit obj,
			String query) throws Exception {
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();		
		ResultSet rs = db.get(query);
		int i = 2;
		if(rs!=null){
			Cell cell = null;
			try{
				while(rs.next()){					
					String region = rs.getString("Region");
					String area = rs.getString("Area");
					String distributorCode = rs.getString("DistributorCode");
					String siteCode = rs.getString("SiteCode");
					String distributor = rs.getString("Distributor");
					String date = rs.getString("Date");
					String data = rs.getString("Data");
					Double ngaygio=Double.parseDouble(rs.getString("ngaygio").replace("-","").replace(":", "").replace(" ", ""));
					
					cell = cells.getCell("EA" + Integer.toString(i));	cell.setValue(region);
					cell = cells.getCell("EB" + Integer.toString(i));	cell.setValue(area);
					cell = cells.getCell("EC" + Integer.toString(i));	cell.setValue(distributorCode);
					cell = cells.getCell("ED" + Integer.toString(i));	cell.setValue(siteCode);
					cell = cells.getCell("EE" + Integer.toString(i));	cell.setValue(distributor);
					cell = cells.getCell("EF" + Integer.toString(i));	cell.setValue(date);										
					cell = cells.getCell("EG" + Integer.toString(i));	cell.setValue(data);
					cell = cells.getCell("EH" + Integer.toString(i));	cell.setValue(ngaygio);
					++i;					
					
				}
				if (rs != null)
					rs.close();
				
				if(db != null) db.shutDown();
				
				if(i==2){					
					throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!");
				}
		
				
								
				  
			}catch(Exception ex){
				throw new Exception(ex.getMessage());
			}
		}else{
			return false;
		}
		return true;
	}


	private void CreateHeader(Workbook workbook, IStockintransit obj)throws Exception {
		try{
			
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("Sheet1");

			Cells cells = worksheet.getCells();
			cells.setRowHeight(0, 20.0f);
			Cell cell = cells.getCell("A1");
			ReportAPI.getCellStyle(cell, Color.RED, true, 16,
					"THEO D??I KH??A S??? NG??Y ");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 9,
					"T??? ng??y : " + obj.gettungay() + "?????n ng??y: " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ng??y t???o : "
					+ obj.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10,
					"???????c t???o b???i : " + obj.getuserTen());			
			
			cell = cells.getCell("EA1");		cell.setValue("Region");
			cell = cells.getCell("EB1");		cell.setValue("Area");			
			cell = cells.getCell("EC1");		cell.setValue("Distributor Code");
			cell = cells.getCell("ED1");		cell.setValue("SiteCode");	
			cell = cells.getCell("EE1");		cell.setValue("Distributor");
			cell = cells.getCell("EF1");		cell.setValue("Date");		
			cell = cells.getCell("EG1");		cell.setValue("Data");	
			cell = cells.getCell("EH1");		cell.setValue("NgayGio");	
		}catch(Exception ex){
			throw new Exception("Khong tao duoc Header cho bao cao");
		}
		
	}
/*	private void setHidden(Workbook workbook,int columCount){
		
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);	   	   
	    Cells cells = worksheet.getCells();
	    for(int i=120;i<=columCount;++i){
	    	cells.hideColumn(i);
	    }*/
}
	

